package practicum2.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class EmFactoryUtil {
    private static final String DBNAME = "employees";
    private static final String PERSISTENCE_UNIT_NAME = "a_persistent_fellow";
    private static final EntityManagerFactory emf = createEntityManagerFactory();

    private EmFactoryUtil() {}

    public static EntityManagerFactory createEntityManagerFactory() {
        Map<String, String> persistenceMap = new HashMap<>();
        persistenceMap.put("jakarta.persistence.jdbc.url",
                "jdbc:mariadb://localhost:3306/" + DBNAME);

        return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, persistenceMap);
    }

    public static void closeResources(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
