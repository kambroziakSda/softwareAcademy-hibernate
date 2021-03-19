package hibernate.core;

import http.rest.academy.entity.Academy;
import http.rest.grade.entity.Grade;
import http.rest.grade.entity.NumberGrade;
import http.rest.grade.entity.TextGrade;
import http.rest.student.entity.Student;
import http.rest.teacher.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public enum HibernateHelper {

    INSTANCE;
    private SessionFactory sessionFactory;

    HibernateHelper() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Teacher.class)
                .addAnnotatedClass(Academy.class)
                .addAnnotatedClass(Grade.class)
                .addAnnotatedClass(NumberGrade.class)
                .addAnnotatedClass(TextGrade.class)
                .buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
