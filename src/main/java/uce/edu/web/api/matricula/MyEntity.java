package uce.edu.web.api.matricula;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;


/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 *
 * This uses the active record pattern, you can also use the repository pattern instead:
 * .
 *
 * Usage (more example on the documentation)
 *
 * {@code
 *     public void doSomething() {
 *         MyEntity entity1 = new MyEntity();
 *         entity1.field = "field-1";
 *         entity1.persist();
 *
 *         List<MyEntity> entities = MyEntity.listAll();
 *     }
 * }
 */
@Entity
public class MyEntity extends PanacheEntity {
    public String field;
}

/*
Para iniciar el proyecto usar: mvnw quarkus:dev

Diseño guiado por dominios(Domain Driver Design): 
El software debe dividirse por dominios funcionales
Cada dominio es candidato de microservicio

Capas:
Infraestructura: Capa que accede a los datos
Application: Reside la logica del negocio
Interface: Reside el controlador o acceso a los recursos
Domain: Residen las entidades

Panache: Libreria que facilita el uso de JPA en Quarkus, simplificando el acceso a datos y operaciones comunes (CRUD).
*/

