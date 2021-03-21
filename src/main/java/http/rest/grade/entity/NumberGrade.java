package http.rest.grade.entity;

import http.rest.academy.entity.Academy;
import http.rest.student.entity.Student;
import http.rest.teacher.entity.Teacher;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

public class NumberGrade extends Grade {


    @Override
    public String gradeAsString() {
        return null;
    }

}
