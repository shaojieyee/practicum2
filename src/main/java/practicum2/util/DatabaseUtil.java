package practicum2.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
    private static final String DBNAME = "employees";
    private static final String PERSISTENCE_UNIT_NAME = "EmployeeService";

    private DatabaseUtil() {}

    public static EntityManager createEntityManager() {
        Map<String, String> persistenceMap = new HashMap<>();
        persistenceMap.put("jakarta.persistence.jdbc.url",
                "jdbc:mariadb://localhost:3306/" + DBNAME);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("a_persistent_fellow", persistenceMap);
        return emf.createEntityManager();
    }

    public static void closeResources(EntityManager em) {
        if (em != null && em.isOpen()) {
            EntityManagerFactory emf = em.getEntityManagerFactory();
            em.close();
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }
}
