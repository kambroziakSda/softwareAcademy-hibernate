package http.rest.academy.entity;

import http.rest.student.entity.StudentDTO;

import java.util.List;

public class AcademyResponseDTO {

    private String name;

    private List<StudentDTO> students;

    public AcademyResponseDTO(String name, List<StudentDTO> students) {
        this.name = name;
        this.students = students;
    }

    public AcademyResponseDTO() {
    }

    public String getName() {
        return name;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }
}
