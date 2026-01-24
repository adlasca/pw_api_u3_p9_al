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

MediaType: Tipo de contenido que viajen en las peticiones HTTP (application/json, text/xml, etc.)
Request: Petición HTTP enviada por el cliente al servidor esta conformada por:
    URL
    Metodo HTTP
    Headers
    Body

Panache: Libreria que facilita el uso de JPA en Quarkus, simplificando el acceso a datos y operaciones comunes (CRUD).

Modelo de madurez de Richardson: Modelo que permite clasificar la madurez de una API web y existe 4 niveles:
Nivel 0: Uso de HTTP oHTTPS pero sin aprovechar sus características (como métodos y URIs).
Nivel 1: Uso de URIs para representar recursos las URLS,Uris deben ser autodescriptivas.
Nivel 2: 
-Uso adecuado de los métodos HTTP (GET, POST, PUT, DELETE, etc.) para operar sobre los recursos. 
-Uso correcto de los códigos de estado HTTP en las respuestas.
-Uso correcto y explícito del MediaType (JSON, XML, etc.)

Existen 5 grupos de codigos de estado: 
1.- Respuestas informativas del 100 al 1XX :   
100 Destinado para retornar una respuesta indicando que todo esta bien y que continue el proceso
102 El servidor ha recibido la solicitud y el cliente debe continuar con el proceso
2.- Respuestas satisfactorias del 200-2xx: 
200 La solicitud se ha procesado correctamente
201 Recurso creado correctamente
204 Petición procesada correctamente pero sin contenido que retornar
3.- Mensajes de redirección del 300-3xx: cambio en el endpoint
4.- Error en el cliente del 400-4xx: 

400 Error en la solicitud del cliente
404 Recurso no encontrado
403 Acceso denegado
404 Petición mal formada
405 Metodo no permitido
408 Request Timeout: El servidor agotó el tiempo de espera al esperar la solicitud
415 Unsupported Media Type: El servidor no soporta el tipo de medio de la solicitud
5.- Errores de servidor del 500-5xx: 
500 Error interno del servidor
503 Servicio no disponible


Nivel 3: Tambien conocido como (HATEOAS) es ineficientes traer  de esa manera, no se debe traer 
    todos los datos de una sola peticion, se utiliza hipervinculos;
        Cuando se trata de traer del endpoin, solo traera el principal, para el "hijo"se utiliza otro vinculo, 
        otro endpoint, 
        2.- No se expone loas entidades 

quarkus.resteasy.path=/matricula/api/v1.0 : Configura la ruta base para los endpoints RESTful en una aplicación Quarkus, estableciéndola en /matricula/api/v1.0.



*/

