package uce.edu.web.api.matricula.application;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.application.representation.EstudianteRepresentation;
import uce.edu.web.api.matricula.domain.Estudiante;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;

@ApplicationScoped
public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<EstudianteRepresentation> listarTodos() {
        List<EstudianteRepresentation> list = new ArrayList<>();
        for (Estudiante estu : this.estudianteRepository.listAll()) {
            list.add(this.mapperToER(estu));
        }
        return  list;
    }

    public EstudianteRepresentation consultarPorId(Long id) {
        return this.mapperToER(this.estudianteRepository.findById(id));
    }

    @Transactional
    public void crear(Estudiante est) {
        this.estudianteRepository.persist(est);
    }

    @Transactional
    public void actualizar(Long id, EstudianteRepresentation est) {
        Estudiante estu = this.mapperToE( this.consultarPorId(id));
        estu.apellido = est.apellido;
        estu.nombre = est.nombre;
        estu.fechaNacimiento = est.fechaNacimiento;
        // Se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Long id, EstudianteRepresentation est) {
        Estudiante estu =this.mapperToE( this.consultarPorId(id));
        if (est.nombre != null) {
            estu.nombre = est.nombre;
        }
        if (est.apellido != null) {
            estu.apellido = est.apellido;
        }
        if (est.fechaNacimiento != null) {
            estu.fechaNacimiento = est.fechaNacimiento;
        }
    }

    @Transactional
    public void eliminar(Long id) {
        this.estudianteRepository.deleteById(id);
    }

    public List<Estudiante> buscarPorProvincia(String provincia) {
        return this.estudianteRepository.find("provinciaa", provincia).list();
    }

    public List<Estudiante> buscarPorProvinciaGenero(String provincia, String genero) {
        // return this.estudianteRepository.find("provincia", provincia).list();
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2", provincia, genero).list();
    }

    public List<Estudiante> buscarPorGenero(String genero) {
        return this.estudianteRepository.find("genero", genero).list();
    }

    public List<Estudiante> buscarPorProvinciaGeneroEdad(String provincia, String genero, Integer edad) {
        return this.estudianteRepository.find("provincia = ?1 and genero = ?2 and fechaNacimiento <= ?3", provincia, genero, edad).list();
    }

    private EstudianteRepresentation mapperToER(Estudiante est){
        EstudianteRepresentation estuR=new EstudianteRepresentation();
        estuR.id=est.id;
        estuR.apellido=est.apellido;
        estuR.fechaNacimiento=est.fechaNacimiento;
        estuR.genero=est.genero;
        estuR.provincia=est.provincia;
        return estuR;
    }

    private Estudiante mapperToE(EstudianteRepresentation estR){
        Estudiante estu=new Estudiante();
        estu.id=estR.id;
        estu.apellido=estR.apellido;
        estu.fechaNacimiento=estR.fechaNacimiento;
        estu.genero=estR.genero;
        estu.provincia=estR.provincia;
        return estu;
    }
}
