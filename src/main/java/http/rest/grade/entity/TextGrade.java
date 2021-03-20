package http.rest.grade.entity;

import http.rest.academy.entity.Academy;
import http.rest.student.entity.Student;
import http.rest.teacher.entity.Teacher;

import javax.persistence.Entity;

@Entity
public class TextGrade extends Grade {

    private TextGrade() {
    }

    public TextGrade(Teacher teacher, Student student, Academy academy, String textGrade) {
        super(teacher, student, academy);
        this.textGrade = textGrade;
    }

    private String textGrade;

    public String getTextGrade() {
        return textGrade;
    }

    @Override
    public String gradeAsString() {
        return textGrade;
    }
}
