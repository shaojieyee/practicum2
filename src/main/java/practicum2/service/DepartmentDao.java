//package practicum2.service;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.NoResultException;
//import practicum2.entity.Department;
//import practicum2.util.DatabaseUtil;
//
//public class DepartmentDao {
//
//    public Department findAllUniqueDepartment(String deptNo) {
//        EntityManager em = null;
//
//        try {
//            em = DatabaseUtil.createEntityManager();
//            Department department = em.createNamedQuery("Department.findDistinctDepartments", Department.class)
//                    .setParameter("deptNo", deptNo)
//                    .getSingleResult();
//
//            return department;
//
//        } catch (NoResultException e) {
//            return null;
//        } finally {
//            DatabaseUtil.closeResources(em);
//        }
//    }
//}
