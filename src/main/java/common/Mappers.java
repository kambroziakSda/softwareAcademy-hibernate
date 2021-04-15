package common;

import http.rest.academy.entity.Academy;
import http.rest.academy.entity.AcademyResponseDTO;
import http.rest.grade.entity.Grade;
import http.rest.grade.entity.GradeDTO;
import http.rest.student.entity.Student;
import http.rest.student.entity.StudentDTO;

import java.util.function.Function;

public class Mappers {

    public static Function<Student, StudentDTO> toStudentDTO() {
        return student -> new StudentDTO(student.getId(), student.getFirstName(), null, null, null);
    }

    public static Function<Grade, GradeDTO> toGradeDto() {
        return null;
    }

    private Function<Academy, AcademyResponseDTO> toAcademyDTO() {
        return null;
    }

}
