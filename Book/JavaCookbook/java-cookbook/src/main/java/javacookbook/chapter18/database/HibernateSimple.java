package javacookbook.chapter18.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HibernateSimple {
    public static void main(String[] args) {
        System.out.println("HibernateSimple.main()");

        Configuration cf = new Configuration();
        cf.configure();
        SessionFactory sf = null;
        Session session = null;

        try {
            sf = cf.buildSessionFactory();
            session = sf.openSession();

            Transaction tx = session.beginTransaction();

            //Address address = new Address("BA-37, Street No-126", "Kolkata", "India");
            String dob = "28/10/1979";
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Person person = new Person("Suman", "Khara", LocalDate.parse(dob, inputFormatter));
            System.out.println(person);

            session.save(person);
            tx.commit();

            int id = person.getId();
            System.out.println("Created person with id: " + id);

            tx = session.beginTransaction();
            person = new Person("Monalisha", "Khara", LocalDate.parse("05/10/1985", inputFormatter));
            System.out.println(person);

            session.save(person);
            tx.commit();

            tx = session.beginTransaction();

            Query query = session.createQuery("Select p from Person p order by p.dob");

            List<Person> list = query.list();
            System.out.println("There are " + list.size() + " persons");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
            list.forEach(p -> System.out.println(p.getFirstName() + " " + p.getLastName() + ", DOB: " + outputFormatter.format(p.getDob())));
        } finally {
            if (session != null) {
                session.close();
            }
        }


    }
}
