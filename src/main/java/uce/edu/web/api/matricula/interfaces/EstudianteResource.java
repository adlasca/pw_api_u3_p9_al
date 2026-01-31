package uce.edu.web.api.matricula.interfaces;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.application.representation.HijoRepresentation;
import uce.edu.web.api.matricula.application.representation.LinkDto;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> lista = new ArrayList<>();
        for (EstudianteRepresentation er : this.estudianteService.listarTodos()) {
            lista.add(construirLinks(er));
        }
        return lista;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public EstudianteRepresentation consultarPorId(@PathParam("id") Long id) {
        return this.construirLinks(this.estudianteService.consultarPorId(id));
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response guardar(Estudiante est) {
        this.estudianteService.crear(est);
        return Response.status(Response.Status.CREATED).entity(est).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response actualizar(@PathParam("id") Long id, EstudianteRepresentation estu) {
        this.estudianteService.actualizar(id, estu);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    @RolesAllowed("admin")
    public void actualizarParcial(@PathParam("id") Long id, EstudianteRepresentation est) {
        this.estudianteService.actualizarParcial(id, est);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public void borrar(@PathParam("id") Long id) {
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/provincia")
    @Produces(MediaType.APPLICATION_XML)
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia") String provincia) {
        System.out.println("Buscar por provincia: " + provincia);
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/buscarPorProvinciaGenero")
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorProvinciaGenero(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorProvinciaGenero(provincia, genero);
    }

    @GET
    @Path("/buscarPorGenero")
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorGenero(@QueryParam("genero") String genero) {
        return this.estudianteService.buscarPorGenero(genero);
    }

    @GET
    @RolesAllowed("admin")
    public List<Estudiante> buscarPorProvinciaGeneroEdad(@QueryParam("provincia") String provincia,
            @QueryParam("genero") String genero, @QueryParam("edad") Integer edad) {
        return this.estudianteService.buscarPorProvinciaGeneroEdad(provincia, genero, edad);
    }

    /*
     * No es necesario diferenciar las rutas ya que los métodos HTTP son diferentes
     * (GET, POST, PUT, DELETE)
     * Solo si se usara el mismo método HTTP (por ejemplo, dos GET) sería necesario
     * diferenciar las rutas.
     */

    @GET
    @Path("/{id}/hijos")
    @RolesAllowed("admin")
    public List<HijoRepresentation> buscarPorIdEstudiante(@PathParam("id") Long id) {
        return this.hijoService.buscarPorIdEstudiante(id);
    }

    private EstudianteRepresentation construirLinks(EstudianteRepresentation er) {
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
                .build().toString();

        String hijos = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id))
                .path("hijos").build().toString();
        er.links = List.of(new LinkDto(self, "self"), new LinkDto(hijos, "hijos"));
        return er;
    }

}
