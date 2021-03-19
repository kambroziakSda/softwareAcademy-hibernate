package http.rest.grade.control;

import hibernate.core.HibernateHelper;
import http.rest.academy.control.AcademyCrudException;
import http.rest.academy.entity.Academy;
import http.rest.grade.entity.Grade;
import http.rest.grade.entity.NumberGrade;
import http.rest.student.entity.Student;
import http.rest.teacher.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class GradeManager {

    public static void saveGrade(int studentId, int teacherId, String academyName, int value) throws AcademyCrudException {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Optional<Student> studentOpt = Optional.ofNullable(session.find(Student.class, studentId));
            if (!studentOpt.isPresent()) {
                throw new AcademyCrudException("Cant find student by id: " + studentId);
            }
            Optional<Academy> academyOpt = Optional.ofNullable(session.find(Academy.class, academyName));
            if (!academyOpt.isPresent()) {
                throw new AcademyCrudException("Cant find academy by name: " + academyName);
            }
            Optional<Teacher> teacherOpt = Optional.ofNullable(session.find(Teacher.class, teacherId));
            if (!teacherOpt.isPresent()) {
                throw new AcademyCrudException("Cant find teacher by id: " + teacherId);
            }
            Transaction transaction = session.beginTransaction();
            Grade grade = new NumberGrade(teacherOpt.get(), studentOpt.get(), academyOpt.get(), value);
            session.persist(grade);
            transaction.commit();

        }
    }

    public static List<Grade> findAll() {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            List<Grade> grades = session.createQuery("SELECT e from Grade e", Grade.class)
                    .getResultList();

            return grades;
        }
    }
}
