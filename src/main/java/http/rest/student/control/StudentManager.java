package http.rest.student.control;

import hibernate.core.HibernateHelper;
import http.rest.student.entity.Student;
import http.rest.student.entity.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by krzysztof on 22.10.17.
 */
public class StudentManager {


    public static List<Student> getStudentByFirstAndLastName(String firstName, String lastName) {
        return null;
    }

    public static Optional<Student> getStudentById(Integer id) {
        try (Session session = HibernateHelper.INSTANCE.getSession();) {
            return Optional.ofNullable(session.find(Student.class, id));
        }

    }

    public static Integer saveStudent(StudentDTO studentDTO) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = new Student(studentDTO.getFirstName());
            session.persist(student);
            transaction.commit();
            return student.getId();
        }
    }

    public static List<Student> getAllStudents() {
        try(Session session = HibernateHelper.INSTANCE.getSession()) {
            return session.createQuery("SELECT s from Student s", Student.class)
                    .getResultList();

        }
    }

    public static Optional<Student> updateStudent(Integer id, StudentDTO studentDTO) {
        return null;
    }

    public static boolean remove(Integer id) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Student student = session.find(Student.class, id);
            if (student == null) {
                return false;
            }
            Transaction transaction = session.beginTransaction();
            session.remove(student);
            transaction.commit();
            return true;
        }
    }
}
