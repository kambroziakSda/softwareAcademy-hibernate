package http.rest.academy.entity;

import http.rest.teacher.entity.Teacher;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.math.BigDecimal;

@Entity
public class TeacherInAcademy {

    @EmbeddedId
    private TeacherInAcademyPk teacherInAcademyPk;

    @MapsId("teacherId")
    @ManyToOne
    private Teacher teacher;

    @MapsId("academyName")
    @ManyToOne
    private Academy academy;

    private BigDecimal salary;

    TeacherInAcademy() {
    }

    public TeacherInAcademy(Teacher teacher, Academy academy, BigDecimal salary) {
        this.teacherInAcademyPk = new TeacherInAcademyPk(academy.getName(), teacher.getId());
        this.teacher = teacher;
        this.academy = academy;
        this.salary = salary;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Academy getAcademy() {
        return academy;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "TeacherInAcademy{" +
                "teacher=" + teacher.getFirstName() +
                ", academy=" + academy.getName() +
                ", salary=" + salary +
                '}';
    }
}
