package javacookbook.chapter18.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPASimple {
    public static void main(String[] args) {
        System.out.println("JPASimple.main()");

        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("jpademo");
            entityManager = entityManagerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Address address = new Address("BA-37, Street No-126", "Kolkata", "India");
            System.out.println(address);
            entityManager.persist(address);
            transaction.commit();

            int id = address.getId();
            System.out.println("Created address with id: " + id);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
