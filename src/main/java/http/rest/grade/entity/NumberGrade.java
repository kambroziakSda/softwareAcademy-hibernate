package http.rest.grade.entity;

import http.rest.academy.entity.Academy;
import http.rest.student.entity.Student;
import http.rest.teacher.entity.Teacher;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries(@NamedQuery(name = NumberGrade.AVERAGE_BY_STUDENT_AND_ACADEMY, query = "SELECT avg(e.gradeValue) from NumberGrade e WHERE " +
        "e.academy = :academy AND e.student = :student "))
public class NumberGrade extends Grade {


    public static final String AVERAGE_BY_STUDENT_AND_ACADEMY = "Grade.averageByStudentIdAndAcademyName";

    private NumberGrade() {
    }

    public NumberGrade(Teacher teacher, Student student, Academy academy, int gradeValue) {
        super(teacher, student, academy);
        this.gradeValue = gradeValue;
    }

    private int gradeValue;

    public int getGradeValue() {
        return gradeValue;
    }

    @Override
    public String gradeAsString() {
        return String.valueOf(gradeValue);
    }

}
