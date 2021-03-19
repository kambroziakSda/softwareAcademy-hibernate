package http.rest.grade.entity;

import hibernate.core.VersionedEntity;
import http.rest.academy.entity.Academy;
import http.rest.student.entity.Student;
import http.rest.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Grade extends VersionedEntity {

    Grade() {
    }

    public Grade(Teacher teacher, Student student, Academy academy) {
        this.teacher = teacher;
        this.student = student;
        this.academy = academy;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idteacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "idstudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idacademy")
    private Academy academy;

    public Teacher getTeacher() {
        return teacher;
    }

    public Student getStudent() {
        return student;
    }

    public Academy getAcademy() {
        return academy;
    }

    public abstract String gradeAsString();

}
