package http.rest.academy.entity;

import http.rest.teacher.entity.Teacher;

import java.math.BigDecimal;

public class TeacherInAcademy {


    TeacherInAcademy() {
    }

    public TeacherInAcademy(Teacher teacher, Academy academy, BigDecimal salary) {

    }

    @Override
    public String toString() {
        return "TeacherInAcademy{" + '}';
    }
}
