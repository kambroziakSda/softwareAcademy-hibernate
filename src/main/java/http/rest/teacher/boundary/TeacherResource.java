package http.rest.teacher.boundary;

import http.rest.teacher.control.TeacherManager;
import http.rest.teacher.entity.TeacherDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/teachers")
public class TeacherResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response addTeacher(TeacherDTO teacherDTO){
        Integer teacherId = TeacherManager.saveTeacher(teacherDTO);
        return Response.ok(teacherId).build();
    }

}
