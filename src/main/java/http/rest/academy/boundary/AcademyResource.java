package http.rest.academy.boundary;

import common.ErrorResponse;
import common.Mappers;
import http.rest.academy.control.AcademyCrudException;
import http.rest.academy.control.AcademyManager;
import http.rest.academy.entity.Academy;
import http.rest.academy.entity.AcademyResponseDTO;
import http.rest.academy.entity.SaveAcademyRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Path("/academies")
public class AcademyResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response addAcademy(SaveAcademyRequest saveAcademyRequest) {
        AcademyManager.saveAcademy(saveAcademyRequest.getName());
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getAllAcademies() {
        List<AcademyResponseDTO> academyResponseDTOS = AcademyManager.findAll()
                .stream()
                .map(toAcademyDTO())
                .collect(Collectors.toList());
        return Response.ok(academyResponseDTOS).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/v2")
    public Response getAllAcademiesJoinFetchStudents() {
        List<AcademyResponseDTO> academyResponseDTOS = AcademyManager.findAllWithStudents()
                .stream()
                .map(toAcademyDTO())
                .collect(Collectors.toList());
        return Response.ok(academyResponseDTOS).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response getAcademy(@PathParam("name") String name) {
        Optional<AcademyResponseDTO> academyByName = AcademyManager.findAcademyByName(name)
                .map(toAcademyDTO());

        if (academyByName.isPresent()) {
            return Response.ok(academyByName.get()).build();

        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{name}/students/{studentId}")
    public Response addStudentToAcademy(@PathParam("name") String name, @PathParam("studentId") Integer studentId) {
        try {
            AcademyManager.addStudentToAcademy(name, studentId);
        } catch (AcademyCrudException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{name}/students/{studentId}")
    public Response deleteStudentFromAcademy(@PathParam("name") String name, @PathParam("studentId") Integer studentId) {
        try {
            AcademyManager.removeFromAcademy(name, studentId);
        } catch (AcademyCrudException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
        return Response.ok().build();
    }


    private Function<Academy, AcademyResponseDTO> toAcademyDTO() {
        return a -> new AcademyResponseDTO(a.getName(), a.getStudents().stream()
                .map(Mappers.toStudentDTO()).collect(Collectors.toList()));
    }


}
