package http.rest.grade.boundary;

import common.ErrorResponse;
import common.Mappers;
import http.rest.academy.control.AcademyCrudException;
import http.rest.grade.control.GradeManager;
import http.rest.grade.entity.Grade;
import http.rest.grade.entity.GradeDTO;
import http.rest.grade.entity.SaveNumberGradeRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/grades")
public class GradeResource {

    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll(){
        List<Grade> gradeList = GradeManager.findAll();

        List<GradeDTO> gradeDTOS = gradeList.stream().map(Mappers.toGradeDto()).collect(Collectors.toList());

        return Response.ok(gradeDTOS).build();

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/students/{studentId}/academies/{academyName}/teachers/{teacherId}")
    public Response saveNumberGrade(@PathParam("studentId") Integer studentId,
                                    @PathParam("academyName") String academyName,
                                    @PathParam("teacherId") Integer teacherId,
                                    SaveNumberGradeRequest saveNumberGradeRequest) {

        try {
            GradeManager.saveGrade(studentId, teacherId, academyName, saveNumberGradeRequest.getValue());
        } catch (AcademyCrudException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
        return Response.ok().build();
    }
}
