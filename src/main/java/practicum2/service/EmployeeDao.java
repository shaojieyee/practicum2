package practicum2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import practicum2.entity.Employee;
import practicum2.util.DatabaseUtil;

public class EmployeeDao {

    //find Employee by emp_No return null if no result
    public Employee findEmployeeByEmpNo(int empNo) {
        EntityManager em = null;

        try {
            em = DatabaseUtil.createEntityManager();
            Employee employee = em.createNamedQuery("Employee.findByEmpNo", Employee.class)
                    .setParameter("empNo", empNo)
                    .getSingleResult();

            // This forces initialization else have the failed to lazily initialize a collection of role error
            employee.getSalaries().size();
            employee.getTitles().size();
            employee.getDeptEmps().size();
            employee.getDeptManagers().size();

            return employee;

        } catch (NoResultException e) {
            return null;
        } finally {
            DatabaseUtil.closeResources(em);
        }
    }
}
