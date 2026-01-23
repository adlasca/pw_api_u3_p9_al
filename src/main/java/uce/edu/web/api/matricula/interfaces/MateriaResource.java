package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {
    
    @Inject
    private MateriaService  materiaService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Materia> listarMaterias(){
        return this.materiaService.listarMaterias();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Materia buscarPorId(@PathParam("id") Long id){
        return this.materiaService.consultarMateriaPorId(id);
    }

    @POST
    @Path("")
    public Response agregarMateria(Materia materia){
        this.materiaService.crearMateria(materia);
        return Response.status(Response.Status.CREATED).entity(materia).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarMateria(@PathParam("id") Long id,Materia materia){
        this.materiaService.actualizarMateria(id, materia);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Long id, Materia materia){
        this.materiaService.actualizarParcialMateria(id, materia);
    }

    @DELETE
    @Path("/{id}")
    public void eliminarMateria(@PathParam("id") Long id){
        this.materiaService.eliminarMateria(id);
    }

    @GET
    @Path("/disponibles")
    @Produces(MediaType.APPLICATION_JSON)
    public List <Materia> listarDisponibles(){
       return this.materiaService.listarDisponibles();
    }

    @GET
    @Path("/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Long contarMaterias(){
        return this.materiaService.contarMaterias();
    }

}
