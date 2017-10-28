package http.rest.student.boundary;

import http.rest.student.control.StudentManager;
import http.rest.student.entity.Student;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by krzysztof on 22.10.17.
 */
@Path("/students")
public class StudentsResource {

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastname) {
        List<Student> students;
        if (!Objects.isNull(firstName) && !Objects.isNull(lastname)) {
            students = StudentManager.getStudentByFirstAndLastName(firstName, lastname);

        } else {
            students = StudentManager.getAllStudents();
        }

        return Response
                .status(Response.Status.OK)
                .entity(students)
                .build();

    }


    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getStudentById(@PathParam("id") Integer id) {
        Optional<Student> optionalStudent = StudentManager.getStudentById(id);
        return optionalStudent
                .map(student -> buildCorrectResponse(student))
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Student not found by id: " + id)
                        .build());
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addStudent(Student student) throws URISyntaxException {
        Objects.requireNonNull(student.getFirstName(), "Student's first name is required!");
        Objects.requireNonNull(student.getLastName(), "Student's last name is required!");

        Integer studentId = StudentManager.add(student);

        return Response.status(Response.Status.CREATED)
                .header("objectId", studentId)
                .location(new URI("/students/" + studentId))
                .build();
    }

    private Response buildCorrectResponse(Student student) {
        return Response.status(Response.Status.OK)
                .entity(student)
                .build();
    }


}
