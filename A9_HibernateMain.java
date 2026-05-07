/*
 * Hibernate Project — pom.xml dependencies required:
 *
 * <dependencies>
 *     <dependency>
 *         <groupId>org.hibernate.orm</groupId>
 *         <artifactId>hibernate-core</artifactId>
 *         <version>6.4.4.Final</version>
 *     </dependency>
 *     <dependency>
 *         <groupId>com.mysql</groupId>
 *         <artifactId>mysql-connector-j</artifactId>
 *         <version>8.3.0</version>
 *     </dependency>
 * </dependencies>
 *
 * ===== hibernate.cfg.xml (place in src/main/resources/) =====
 *
 * <?xml version="1.0" encoding="UTF-8"?>
 * <!DOCTYPE hibernate-configuration PUBLIC
 *     "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
 *     "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
 *
 * <hibernate-configuration>
 *     <session-factory>
 *         <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
 *         <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/example_db</property>
 *         <property name="hibernate.connection.username">root</property>
 *         <property name="hibernate.connection.password">root</property>
 *         <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
 *         <property name="hibernate.hbm2ddl.auto">update</property>
 *         <property name="hibernate.show_sql">true</property>
 *         <property name="hibernate.format_sql">true</property>
 *         <mapping class="Student"/>
 *     </session-factory>
 * </hibernate-configuration>
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

// ===== Entity =====
@Entity
@Table(name = "students")
class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "marks")
    private double marks;

    public Student() {}  // Required by Hibernate

    public Student(String name, double marks) {
        this.name  = name;
        this.marks = marks;
    }

    public int    getId()    { return id; }
    public String getName()  { return name; }
    public double getMarks() { return marks; }

    public void setId(int id)          { this.id = id; }
    public void setName(String name)   { this.name = name; }
    public void setMarks(double marks) { this.marks = marks; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', marks=" + marks + "}";
    }
}

// ===== Main Execution =====
public class A9_HibernateMain {

    public static void main(String[] args) {

        // Build SessionFactory from hibernate.cfg.xml
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // --- CREATE ---
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            Student s1 = new Student("Aarav Patil",  88.5);
            Student s2 = new Student("Sneha Sharma", 92.0);

            session.persist(s1);
            session.persist(s2);

            tx.commit();
            System.out.println("Saved: " + s1);
            System.out.println("Saved: " + s2);
        }

        // --- READ ---
        try (Session session = factory.openSession()) {
            Student retrieved = session.get(Student.class, 1);

            if (retrieved != null) {
                System.out.println("\n=== Retrieved Student ===");
                System.out.println("ID    : " + retrieved.getId());
                System.out.println("Name  : " + retrieved.getName());
                System.out.println("Marks : " + retrieved.getMarks());
            } else {
                System.out.println("Student with ID 1 not found.");
            }

            // List all students
            System.out.println("\n=== All Students ===");
            session.createQuery("FROM Student", Student.class)
                   .getResultList()
                   .forEach(System.out::println);
        }

        factory.close();
        System.out.println("\nHibernate session closed.");
    }
}
