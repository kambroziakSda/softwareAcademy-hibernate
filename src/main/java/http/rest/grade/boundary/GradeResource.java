package http.rest.grade.boundary;

import common.ErrorResponse;
import common.Mappers;
import http.rest.academy.control.AcademyCrudException;
import http.rest.grade.control.GradeManager;
import http.rest.grade.entity.Grade;
import http.rest.grade.entity.GradeDTO;
import http.rest.grade.entity.SaveNumberGradeRequest;
import http.rest.grade.entity.SaveTextGradeRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/grades")
public class GradeResource {

    /*
    Pobranie wszystkich ocen (tekstowych oraz numeryzcznych) z mechanizmem stronicowania i dziedziczeniem
     */
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET

    public Response getAllWithPagination(@QueryParam("page") @DefaultValue("1")  Integer page, @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        List<Grade> gradeList = GradeManager.findAllWithPagination(page, pageSize);
        List<GradeDTO> gradeDTOS = gradeList.stream().map(Mappers.toGradeDto()).collect(Collectors.toList());
        return Response.ok(gradeDTOS).build();

    }

    /*
    Dodanie oceny numerycznej z wykorzystaniem dziedziczenia
     */
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

    /*
    Dodanie oceny tekstowej z wykorzystaniem dziedziczenia
  */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/students/{studentId}/academies/{academyName}/teachers/{teacherId}/text")
    public Response avarageGrade(@PathParam("studentId") Integer studentId,
                                 @PathParam("academyName") String academyName,
                                 @PathParam("teacherId") Integer teacherId,
                                 SaveTextGradeRequest saveTextGradeRequest) {

        try {
            GradeManager.saveGrade(studentId, teacherId, academyName, saveTextGradeRequest.getTextGrade());
        } catch (AcademyCrudException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
        return Response.ok().build();
    }

    /*
    Pobranie średniej ocen studenta w akademii z wykorzystaniem hql
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/students/{studentId}/average/academies/{academyName}")
    public Response avarageGrade(@PathParam("studentId") Integer studentId,
                                 @PathParam("academyName") String academyName) {

        try {
            Double average = GradeManager.getAverage(studentId, academyName);
            System.out.println(average);
            return Response.ok(average).build();
        } catch (AcademyCrudException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }
}
