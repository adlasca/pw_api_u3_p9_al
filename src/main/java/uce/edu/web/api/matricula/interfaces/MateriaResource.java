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
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.application.MateriaService;
import uce.edu.web.api.matricula.domain.Materia;

@Path("/materias")
public class MateriaResource {
    
    @Inject
    private MateriaService  materiaService;

    @GET
    @Path("")
    public List<Materia> listarMaterias(){
        return this.materiaService.listarMaterias();
    }

    @GET
    @Path("/{id}")
    public Materia buscarPorId(@PathParam("id") Long id){
        return this.materiaService.consultarMateriaPorId(id);
    }

    @POST
    @Path("")
    public void agregarMateria(Materia materia){
        this.materiaService.crearMateria(materia);
    }

    @PUT
    @Path("/{id}")
    public void actualizarMateria(@PathParam("id") Long id,Materia materia){
        this.materiaService.actualizarMateria(id, materia);
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
    public List <Materia> listarDisponibles(){
       return this.materiaService.listarDisponibles();
    }

    @GET
    @Path("/total")
    public Long contarMaterias(){
        return this.materiaService.contarMaterias();
    }

}
