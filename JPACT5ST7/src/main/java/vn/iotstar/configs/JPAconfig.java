package vn.iotstar.configs;

import jakarta.persistence.*;

@PersistenceContext
public class JPAconfig {

	public static EntityManager getEntityManager() {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dataSource");

		return factory.createEntityManager();

	}
}
