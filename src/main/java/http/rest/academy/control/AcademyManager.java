package http.rest.academy.control;

import hibernate.core.HibernateHelper;
import http.rest.academy.entity.Academy;
import http.rest.student.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class AcademyManager {

    public static Optional<Academy> findAcademyByName(String academyName) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            return Optional.ofNullable(session.find(Academy.class, academyName));
        }

    }

    public static List<Academy> findAll() {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            return session.createQuery("SELECT a from Academy a", Academy.class).getResultList();
        }

    }

    public static List<Academy> findAllWithStudents() {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            return session.createQuery("SELECT a from Academy a " +
                    " join fetch a.students ", Academy.class).getResultList();
        }

    }

    public static void saveAcademy(String name) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Transaction transaction = session.beginTransaction();
            Academy academy = new Academy(name);
            session.persist(academy);
            transaction.commit();
        }
    }

    public static void addStudentToAcademy(String academyName, Integer studentId) throws AcademyCrudException {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Optional<Student> studentOpt = Optional.ofNullable(session.find(Student.class, studentId));
            if (!studentOpt.isPresent()) {
                throw new AcademyCrudException("Student not found by id: " + studentId);
            }
            Optional<Academy> academyByNameOpt = Optional.ofNullable(session.find(Academy.class, academyName));

            if (!academyByNameOpt.isPresent()) {
                throw new AcademyCrudException("Academy not found by name: " + academyName);
            }
            Transaction transaction = session.beginTransaction();
            Academy academy = academyByNameOpt.get();
            Student student = studentOpt.get();
            academy.getStudents().add(student);
            transaction.commit();
        }
    }

    public static void removeFromAcademy(String academyName, Integer studentId) throws AcademyCrudException {
        System.out.println("removeFromAcademy start");
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Optional<Academy> academyByNameOpt = Optional.ofNullable(session.find(Academy.class, academyName));
            if (!academyByNameOpt.isPresent()) {
                throw new AcademyCrudException("Academy not found by name: " + academyName);
            }

            Transaction transaction = session.beginTransaction();
            academyByNameOpt.get().getStudents().removeIf(s->s.getId().equals(studentId));
            transaction.commit();
            System.out.println("removeFromAcademy end");
        }
    }
}
