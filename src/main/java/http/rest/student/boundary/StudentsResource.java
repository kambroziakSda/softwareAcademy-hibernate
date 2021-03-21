package http.rest.student.boundary;

import common.Mappers;
import http.rest.student.control.StudentManager;
import http.rest.student.entity.StudentDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by krzysztof on 22.10.17.
 */
@Path("/students")
public class StudentsResource {

    /*
        Pobranie studenta po identyfikatorze
 */
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getStudentById(@PathParam("id") Integer id) {
        Optional<StudentDTO> optionalStudent = StudentManager.getStudentById(id)
                .map(Mappers.toStudentDTO());
        return optionalStudent
                .map(this::buildCorrectResponse)
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Student not found by id: " + id)
                        .build());
    }


    /*
        Pobranie studentow z opcjonalnym filtrowaniem po imieniu i nazwisku
     */
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents(@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastname) {
        List<StudentDTO> studentDTOS;
        if (!Objects.isNull(firstName) && !Objects.isNull(lastname)) {
            studentDTOS = StudentManager.getStudentByFirstAndLastName(firstName, lastname)
                    .stream().map(Mappers.toStudentDTO())
                    .collect(Collectors.toList());

        } else {
            studentDTOS = StudentManager.getAllStudents()
                    .stream()
                    .map(Mappers.toStudentDTO())
                    .collect(Collectors.toList());
        }

        return Response
                .status(Response.Status.OK)
                .entity(studentDTOS)
                .build();

    }


    /*
    Dodanie studenta
     */
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Response addStudent(StudentDTO studentDTO) throws URISyntaxException {
        Objects.requireNonNull(studentDTO.getFirstName(), "Student's first name is required!");
        Objects.requireNonNull(studentDTO.getLastName(), "Student's last name is required!");

        Integer studentId = StudentManager.saveStudent(studentDTO);

        return Response.status(Response.Status.CREATED)
                .header("objectId", studentId)
                .location(new URI("/students/" + studentId))
                .build();
    }

    /*
    Edycja studenta
     */
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") Integer studentId, StudentDTO studentDTO) throws URISyntaxException {
        Objects.requireNonNull(studentDTO.getFirstName(), "Student's first name is required!");
        Objects.requireNonNull(studentDTO.getLastName(), "Student's last name is required!");

        Optional<StudentDTO> student = StudentManager.updateStudent(studentId, studentDTO)
                .map(Mappers.toStudentDTO());

        if (!student.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(student.get()).build();
    }

    private Response buildCorrectResponse(StudentDTO studentDTO) {
        return Response.status(Response.Status.OK)
                .entity(studentDTO)
                .build();
    }

/*
Usuniecie studenta z bazy
 */
    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Integer id) {
        boolean remove = StudentManager.remove(id);

        if (remove) {
            return Response.status(204).build();
        }
        return
                Response.status(Response.Status.NOT_FOUND).build();

    }
}
