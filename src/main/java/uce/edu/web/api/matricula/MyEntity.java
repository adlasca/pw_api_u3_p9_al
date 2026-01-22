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

Modelo de madurez de Richardson: Modelo que permite clasificar la madurez de una API web y existe 4 niveles:
Nivel 0: Uso de HTTP oHTTPS pero sin aprovechar sus características (como métodos y URIs).
Nivel 1: Uso de URIs para representar recursos las URLS,Uris deben ser autodescriptivas.
Nivel 2: Uso adecuado de los métodos HTTP (GET, POST, PUT, DELETE, etc.) para operar sobre los recursos.
Nivel 3: Uso de hipermedios (HATEOAS) para guiar a los clientes a través de la aplicación mediante enlaces en las respuestas.

quarkus.resteasy.path=/matricula/api/v1.0 : Configura la ruta base para los endpoints RESTful en una aplicación Quarkus, estableciéndola en /matricula/api/v1.0.

*/

