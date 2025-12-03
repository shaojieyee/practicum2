package practicum2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import practicum2.dto.EmployeeDto;
import practicum2.entity.*;
import practicum2.util.EmFactoryUtil;

import java.time.LocalDate;
import java.util.List;

public class EmployeeDao {

    LocalDate indefiniteDate = LocalDate.of(9999, 1, 1);

    //find Employee by emp_No return null if no result
    public Employee findEmployeeByEmpNo(int empNo) {
        EntityManager em = null;
        try {
            em = EmFactoryUtil.getEntityManager();
            return em.createNamedQuery("Employee.findByEmpNo", Employee.class)
                    .setParameter("empNo", empNo)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            EmFactoryUtil.closeResources(em);
        }
    }

    public List<EmployeeDto> findEmployeesByDept(String deptNo, int page) {
        EntityManager em = null;
        int pageSize = 20;
        int offset = (page - 1) * pageSize;

        if (page < 1) {
            throw new IllegalArgumentException("Page number needs to be greater than 0");
        }

        try {
            em = EmFactoryUtil.getEntityManager();
            return em.createQuery(
                         "SELECT NEW practicum2.dto.EmployeeDto(" +
                            "e.empNo, e.firstName, e.lastName, e.hireDate) " +
                            "FROM Employee e " +
                            "JOIN e.DeptEmps de " +
                            "WHERE de.department.deptNo = :deptNo " +
                            "ORDER BY e.hireDate DESC",
                            EmployeeDto.class)
                    .setParameter("deptNo", deptNo)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            EmFactoryUtil.closeResources(em);
        }
    }

    public void promote(int empNo, String newTitle, String newDeptNo, boolean newManager, int newSalary, LocalDate newStartEndDate){
        EntityManager em = null;

        try {
            em = EmFactoryUtil.getEntityManager();
            em.getTransaction().begin();

            Employee employee = em.find(Employee.class, empNo);
            if (employee == null) {
                throw new RuntimeException("Employee not found: " + empNo);
            }

            //process title change
            if (newTitle != null && !newTitle.trim().isEmpty()){
                processTitleChange(employee, em, empNo, newTitle, newStartEndDate);
            }
            //process department change
            if (newDeptNo != null && !newDeptNo.trim().isEmpty()){
                Department department = em.find(Department.class, newDeptNo);
                if (department == null) {
                    throw new RuntimeException("Department " + newDeptNo + " not found");
                }
                processDepartmentChange(employee, department, em,  empNo, newDeptNo, newStartEndDate);
            }
            //process salary change
            if (newSalary >=0) {
                processSalaryChange(employee, em, empNo, newSalary, newStartEndDate);
            }
            //process become manager
            if (newManager){
                Department department = em.find(Department.class, newDeptNo);
                if (department == null) {
                    throw new RuntimeException("Department " + newDeptNo + " not found");
                }
                processBecomeManager(employee, department, em, empNo, newDeptNo, newStartEndDate);
            }

            em.getTransaction().commit();

        }catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            EmFactoryUtil.closeResources(em);
        }
    }
    //Services
    private void processTitleChange(Employee employee, EntityManager em, int empNo, String newTitle, LocalDate effectiveDate){
        try {
            try {
                Title currentTitle = em.createQuery(
                                "SELECT t FROM Title t " +
                                        "WHERE t.employee.empNo = :empNo " +
                                        "AND t.toDate = :indefiniteDate", Title.class)
                        .setParameter("empNo", empNo)
                        .setParameter("indefiniteDate", indefiniteDate)
                        .getSingleResult();

                //set the toDate for the old title to the previous day of the new date
                currentTitle.setToDate(effectiveDate.minusDays(1));
                em.merge(currentTitle);
            } catch (NoResultException e) {}

            //new title record to push to db
            try {
                Title newTitleRecord = new Title(empNo, newTitle, effectiveDate, indefiniteDate);
                newTitleRecord.setEmployee(employee);
                em.merge(newTitleRecord);
            }catch (Exception e){
                throw new RuntimeException("{\"error\": \"employee is current having that title\"}");
            }

        } catch (NoResultException ignored) {
            throw new IllegalArgumentException("{\"error\": \"employee with this title not found\"}");
        }
    }

    private void processDepartmentChange(Employee employee, Department department, EntityManager em, int empNo, String newDeptNo, LocalDate effectiveDate){
        String currentDeptNo = "";
        try {
            DeptEmp currentDept = em.createQuery(
                            "SELECT de FROM DeptEmp de WHERE de.employee.empNo = :empNo " +
                                "AND de.toDate = :indefiniteDate", DeptEmp.class)
                            .setParameter("empNo", empNo)
                            .setParameter("indefiniteDate", indefiniteDate)
                            .getSingleResult();

            //set the toDate for the oldDepartment
            try {
                currentDept.setToDate(effectiveDate.minusDays(1));
                em.merge(currentDept);
                currentDeptNo = currentDept.getDeptNo();
            }catch (Exception e){
                throw new RuntimeException("{\"error\": \"employee is already in that department\"}");
            }

            //new department record to push to db
            if (!currentDeptNo.trim().isEmpty()) {
                DeptEmp newDeptEmp = new DeptEmp(empNo, newDeptNo, effectiveDate, indefiniteDate);
                newDeptEmp.setEmployee(employee);
                newDeptEmp.setDepartment(department);
                em.merge(newDeptEmp);
            } else {
                throw new IllegalArgumentException("{\"error\": \"Department not found\"}");
            }

        } catch (NoResultException ignored) {
            throw new IllegalArgumentException("{\"error\": \"employee in this department not found\"}");
        }
    }

    private void processSalaryChange(Employee employee, EntityManager em, int empNo, int newSalary, LocalDate effectiveDate){
        try {
            Salary currentSalary = em.createQuery(
                         "SELECT s FROM Salary s WHERE s.employee.empNo = :empNo " +
                            "AND s.toDate = :indefiniteDate", Salary.class)
                            .setParameter("empNo",empNo)
                            .setParameter("indefiniteDate", indefiniteDate)
                            .getSingleResult();

            //throw error if salary is the same as the new salary
            if (currentSalary.getSalary() == newSalary){
                throw new RuntimeException("Employee is already having this salary");
            }

            //set the toDate for the oldDepartment
            currentSalary.setToDate(effectiveDate.minusDays(1));
            em.merge(currentSalary);

            //new salary record to push to db
            try{
                Salary newSalaryRecord = new Salary(empNo, newSalary, effectiveDate, indefiniteDate);
                newSalaryRecord.setEmployee(employee);
                em.merge(newSalaryRecord);
            }catch (Exception e){
                throw new RuntimeException("{\"error\": \"employee is already having this salary\"}");
            }

        } catch (NoResultException ignored) {
            throw new IllegalArgumentException("Salary not found");
        }
    }
    private void processBecomeManager(Employee employee, Department department, EntityManager em, int empNo, String newDeptNo, LocalDate effectiveDate) {
        //check if he is already currently a manager of the new department
        boolean isThisDeptManager;

        try {
            DeptManager currentManager = em.createQuery(
                            "SELECT dm FROM DeptManager dm WHERE dm.employee.empNo = :empNo " +
                                "AND dm.toDate = :indefiniteDate", DeptManager.class)
                            .setParameter("empNo", empNo)
                            .setParameter("indefiniteDate", indefiniteDate)
                            .getSingleResult();

            isThisDeptManager = true;
            throw new IllegalArgumentException("This employee is a current manager of department: " + newDeptNo );

        } catch (NoResultException e) {
            isThisDeptManager = false;
        }
        //if !manager, no exception is thrown then continue to add new dept manager
        if (!isThisDeptManager) {
            DeptManager newDeptManager = new DeptManager(newDeptNo, empNo, effectiveDate, indefiniteDate);
            newDeptManager.setEmployee(employee);
            newDeptManager.setDepartment(department);
            em.merge(newDeptManager);
        }
    }
}
