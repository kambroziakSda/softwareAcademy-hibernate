package http.rest.student.control;

import hibernate.core.HibernateHelper;
import http.rest.student.entity.Student;
import http.rest.student.entity.StudentDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by krzysztof on 22.10.17.
 */
public class StudentManager {


    public static List<Student> getStudentByFirstAndLastName(String firstName, String lastName) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            List<Student> students = session.createNamedQuery(Student.FIND_BY_FIRST_AND_LAST_NAME, Student.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getResultList();

            return students
                    .stream()
                    // .map(toStudentDTO())
                    .collect(Collectors.toList());

        }
    }

    public static Optional<Student> getStudentById(Integer id) {
        try (Session session = HibernateHelper.INSTANCE.getSession();) {
            return Optional.ofNullable(session.find(Student.class, id));
            //.map(toStudentDTO());
        }

    }

    public static Integer saveStudent(StudentDTO studentDTO) {
        Student student = new Student(studentDTO.getFirstName(), studentDTO.getLastName(), studentDTO.getAddress());
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        }
        return student.getId();
    }

    public static List<Student> getAllStudents() {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            return session.createQuery("SELECT e from Student e", Student.class)
                    .getResultList()
                    .stream()
                    .collect(Collectors.toList());
        }
    }

    public static Optional<Student> updateStudent(Integer id, StudentDTO studentDTO) {
        Optional<Student> studentOpt = getStudentById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());

            try (Session session = HibernateHelper.INSTANCE.getSession()) {
                Transaction transaction = session.beginTransaction();
                session.update(student);
                transaction.commit();

            }
        }
        return Optional.empty();

    }

    public static boolean remove(Integer id) {
       try (Session session = HibernateHelper.INSTANCE.getSession()) {
           Student student = session.find(Student.class, id);
           if(student == null){
               return false;
           }
           Transaction transaction = session.beginTransaction();
           session.remove(student);
           transaction.commit();
           return true;
       }
    }
}
