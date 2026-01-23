package uce.edu.web.api.matricula.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.infraestructure.EstudianteRepository;
import uce.edu.web.api.matricula.domain.Estudiante;
import java.util.List;

@ApplicationScoped
public class EstudianteService {
    @Inject
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return this.estudianteRepository.listAll();
    }

    public Estudiante consultarPorId(Long id) {
        return this.estudianteRepository.findById(id);
    }

    @Transactional
    public void crear(Estudiante est) {
        this.estudianteRepository.persist(est);
    }

    @Transactional
    public void actualizar(Long id, Estudiante est) {
        Estudiante estu = this.consultarPorId(id);
        estu.apellido = est.apellido;
        estu.nombre = est.nombre;
        estu.fechaNacimiento = est.fechaNacimiento;
        // Se actualiza automaticamente por dirty checking
    }

    @Transactional
    public void actualizarParcial(Long id, Estudiante est) {
        Estudiante estu = this.consultarPorId(id);
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
}
