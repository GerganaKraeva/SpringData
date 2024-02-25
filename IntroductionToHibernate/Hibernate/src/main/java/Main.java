import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        configuration.configure();

        SessionFactory sessionFactory=configuration.buildSessionFactory();
       Session session= sessionFactory.openSession();

       session.beginTransaction();

//        Student student=new Student();
//        student.setName("Pesho");
//        session.save(student);

//        Student student = session.get(Student.class,1);
//        System.out.printf("%d - %s", student.getId(), student.getName());
//
//        student.setName("Updated");
//
        List <Student> students = session.createQuery("FROM Student", Student.class).list();
        for (Student currentStudent : students) {
            System.out.println(currentStudent);
        }

       session.getTransaction().commit();
       session.close();
    }
}
