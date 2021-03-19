package http.rest.grade.entity;

import http.rest.academy.entity.Academy;
import http.rest.student.entity.Student;
import http.rest.teacher.entity.Teacher;

import javax.persistence.Entity;

@Entity
public class NumberGrade extends Grade {


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
