package uce.edu.web.api.matricula.application;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.web.api.matricula.domain.Materia;
import uce.edu.web.api.matricula.infraestructure.MateriaRepository;

@ApplicationScoped
public class MateriaService {

    @Inject 
    private MateriaRepository materiaRepository;

    @Transactional
    public void crearMateria(Materia materia) {
        this.materiaRepository.persist(materia);
    }

    public List<Materia> listarMaterias() {
        return this.materiaRepository.listAll();
    }

    public Materia consultarMateriaPorId(Long id) {
        return this.materiaRepository.findById(id);
    }
    @Transactional
    public void actualizarMateria(Long id, Materia materia){
        Materia mat = this.consultarMateriaPorId(id);
        mat.creditos =materia.creditos;
        mat.cupos = materia.cupos;
        mat.disponibilidad = materia.disponibilidad;
        mat.nombre = materia.nombre;
    }

    @Transactional
    public void actualizarParcialMateria(Long id, Materia mat){
        Materia materia = this.consultarMateriaPorId(id);
        if(mat.disponibilidad!=null){
            materia.disponibilidad =mat.disponibilidad;
        }if(mat.creditos!=null){
            materia.creditos=mat.creditos;
        }if(mat.cupos!=null){
            materia.cupos=mat.cupos;
        }
    }

    @Transactional
    public void eliminarMateria(Long id){
        this.materiaRepository.deleteById(id);
    }

    public List <Materia> listarDisponibles() {
        return this.materiaRepository.list("disponibilidad", true);
    }

    public Long contarMaterias() {
        return this.materiaRepository.count();
    }

}
