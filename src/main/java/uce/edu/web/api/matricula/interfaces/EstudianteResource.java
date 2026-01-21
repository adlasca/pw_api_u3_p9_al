package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.domain.Estudiante;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @GET
    @Path("/todos")
    
    public List<Estudiante> listarTodos() {
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/consultarPorId/{id}" )
    public Estudiante consultarPorId(@PathParam("id") Long id){
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("/crear")
    public void crear(Estudiante est){
        this.estudianteService.crear(est);
    }

    @PUT
    @Path("actualizar/{id}")
    public void actualizar(@PathParam("id")Long id, Estudiante estu){
        this.estudianteService.actualizar(id, estu);
    }

    @PATCH
    @Path("/actualizarParcial/{id}")
    public void actualizarParcial(@PathParam("id") Long id, Estudiante est){
        this.estudianteService.actualizarParcial(id, est);
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void borrar(@PathParam("id") Long id){
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/buscarPorProvincia")
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia")  String provincia){
        return this.estudianteService.buscarPorProvincia(provincia);
    }

    @GET
    @Path("/buscarPorProvinciaGenero")
    public List<Estudiante> buscarPorProvinciaGenero(@QueryParam("provincia")  String provincia, @QueryParam("genero") String genero){
        return this.estudianteService.buscarPorProvinciaGenero(provincia, genero);
    }

    @GET
    @Path("/buscarPorGenero")
    public List<Estudiante> buscarPorGenero(@QueryParam("genero") String genero){
        return this.estudianteService.buscarPorGenero(genero);
    }

   public List<Estudiante> buscarPorProvinciaGeneroEdad(@QueryParam("provincia")String provincia, @QueryParam("genero") String genero, @QueryParam("edad") Integer edad) {
         return this.estudianteService.buscarPorProvinciaGeneroEdad(provincia, genero, edad);
    }


}
