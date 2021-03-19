package common;

import http.rest.grade.entity.Grade;
import http.rest.grade.entity.GradeDTO;
import http.rest.student.entity.Student;
import http.rest.student.entity.StudentDTO;

import java.util.function.Function;
import java.util.stream.Collectors;

public class Mappers {

    public static Function<Student, StudentDTO> toStudentDTO() {
        return s -> new StudentDTO(s.getId(), s.getFirstName(), s.getLastName(),
                s.getGrades().stream().map(toGradeDto()).collect(Collectors.toList()));
    }

    public static Function<Grade, GradeDTO> toGradeDto() {
        return g -> new GradeDTO(g.gradeAsString(), g.getTeacher().getId(), g.getAcademy().getName());
    }


}
