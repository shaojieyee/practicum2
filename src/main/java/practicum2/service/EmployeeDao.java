package practicum2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import practicum2.dto.EmployeeDto;
import practicum2.entity.Employee;
import practicum2.util.DatabaseUtil;

import java.util.List;

public class EmployeeDao {

    //find Employee by emp_No return null if no result
    public Employee findEmployeeByEmpNo(int empNo) {
        EntityManager em = null;
        try {
            em = DatabaseUtil.createEntityManager();
            return em.createNamedQuery("Employee.findByEmpNo", Employee.class)
                    .setParameter("empNo", empNo)
                    .getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            DatabaseUtil.closeResources(em);
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
            em = DatabaseUtil.createEntityManager();
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
            DatabaseUtil.closeResources(em);
        }
    }
}
