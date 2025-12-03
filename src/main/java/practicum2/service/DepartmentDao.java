package practicum2.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import practicum2.entity.Department;
import practicum2.util.EmFactoryUtil;

import java.util.List;

public class DepartmentDao {

    public List<Department> findAllUniqueDepartment() {
        EntityManager em = null;

        try {
            em = EmFactoryUtil.getEntityManager();
            return em.createNamedQuery("Department.findDistinctDepartments", Department.class)
                    .getResultList();

        } catch (NoResultException e) {
            return null;
        } finally {
            EmFactoryUtil.closeResources(em);
        }
    }
}
