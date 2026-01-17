package uce.edu.web.api.matricula.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Estudiante")
@SequenceGenerator(name="estudiante_seq",sequenceName = "cliente_secuencia",allocationSize = 1)
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq")
    public Long id;
    public String nombre;
    public String apellido;
    public LocalDateTime fechaNacimiento;

}
