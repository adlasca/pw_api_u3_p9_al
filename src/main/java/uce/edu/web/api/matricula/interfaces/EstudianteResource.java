package uce.edu.web.api.matricula.interfaces;

import java.util.List;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

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
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.matricula.application.EstudianteService;
import uce.edu.web.api.matricula.application.HijoService;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.domain.Hijo;

@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    private EstudianteService estudianteService;

    @Inject
    private HijoService hijoService;

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstudianteRepresentation> listarTodos() {
        System.out.println("Listar todos los estudiantes");
        return this.estudianteService.listarTodos();
    }

    @GET
    @Path("/{id}" )
    @Produces(MediaType.APPLICATION_XML)
    public EstudianteRepresentation consultarPorId(@PathParam("id") Long id){
        return this.estudianteService.consultarPorId(id);
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardar(Estudiante est){
        this.estudianteService.crear(est);
        return Response.status(Response.Status.CREATED).entity(est).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id")Long id, Estudiante estu){
        this.estudianteService.actualizar(id, estu);
        return Response.status(209).entity(null).build();
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@PathParam("id") Long id, Estudiante est){
        this.estudianteService.actualizarParcial(id, est);
    }

    @DELETE
    @Path("/{id}")
    public void borrar(@PathParam("id") Long id){
        this.estudianteService.eliminar(id);
    }

    @GET
    @Path("/provincia")
    @Produces(MediaType.APPLICATION_XML)
    public List<Estudiante> buscarPorProvincia(@QueryParam("provincia")  String provincia){
        System.out.println("Buscar por provincia: " + provincia);
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

    /*No es necesario diferenciar las rutas ya que los métodos HTTP son diferentes (GET, POST, PUT, DELETE)
    Solo si se usara el mismo método HTTP (por ejemplo, dos GET) sería necesario diferenciar las rutas.
    */

    @GET
    @Path("/{id}/hijos")
    
    public List<Hijo> buscarPorIdEstudiante(@PathParam("id") Long id){
        return this.hijoService.buscarPorIdEstudiante(id);
    }

}
