package http.rest.teacher.control;

import hibernate.core.HibernateHelper;
import http.rest.teacher.entity.Teacher;
import http.rest.teacher.entity.TeacherDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TeacherManager {

    public static Integer saveTeacher(TeacherDTO teacherDTO) {
        try (Session session = HibernateHelper.INSTANCE.getSession()) {
            Transaction transaction = session.beginTransaction();
            Teacher teacher = new Teacher(teacherDTO.getFirstName(), teacherDTO.getLastName());
            session.persist(teacher);
            transaction.commit();
            return teacher.getId();
        }

    }

}
