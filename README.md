# TechCup Fútbol

Plataforma web para la gestión del torneo semestral de f
útbol de los programas de Ingeniería de Sistemas, Ingeniería de Inteligencia Artificial, Ingeniería de Ciberseguridad e Ingeniería Estadística de la Escuela Colombiana de Ingeniería.

El sistema centraliza el registro de jugadores y equipos, la gestión de pagos, la programación de partidos, el registro de resultados, la tabla de posiciones, las llaves eliminatorias y las estadísticas del torneo.

## Objetivo del proyecto

Diseñar e implementar una plataforma web que permita gestionar de forma organizada, centralizada y transparente el torneo semestral de fútbol, eliminando procesos manuales y mejorando la experiencia de participantes y organizadores.

## Funcionalidades principales

- Registro y administración de torneos.
- Registro de jugadores con perfil deportivo.
- Creación y gestión de equipos por capitanes.
- Búsqueda de jugadores por distintos criterios.
- Inscripción de equipos y verificación de pagos mediante comprobantes.
- Configuración del torneo (reglamento, fechas, canchas, sanciones).
- Gestión de alineaciones por partido.
- Registro de partidos y resultados.
- Cálculo automático de la tabla de posiciones.
- Generación automática de llaves eliminatorias.
- Consulta de estadísticas del torneo.

## Actores del sistema

- Estudiante
- Graduado
- Profesor
- Personal administrativo
- Familiar
- Capitán
- Organizador
- Árbitro
- Administrador

## Arquitectura y tecnologías

- Backend:
    - Java
    - Spring Boot
    - API REST
    - Arquitectura por capas (controladores, servicios, repositorios)
- Frontend:
    - React
    - TypeScript
- Base de datos:
    - PostgreSQL
- Control de versiones:
    - Git y GitHub
- Gestión del proyecto:
    - Scrum + Kanban
    - Jira
- Diseño de interfaces:
    - Figma

## Ejecución de pruebas

Las pruebas del backend se ejecutan usando Maven:

```bash
mvn test
```
Esto ejecuta todas las pruebas unitarias y de integración definidas en el proyecto.

## Diseño y documentación

- Los diagramas UML (casos de uso, clases, diagramas de contexto) se encuentran en la carpeta docs/uml.
- Los mockups, flujos de navegación y manual de identidad se encuentran en docs/ui.
- Las decisiones de diseño y arquitectura se documentan de forma incremental durante el desarrollo del proyecto.

## Documentación

mvn spring-boot:run

----
![DocumentacionP1.png](src/main/resources/docs/images/DocumentacionP1.png)
![DocumentacionP2.png](src/main/resources/docs/images/DocumentacionP2.png)
![DocumentacionP3.png](src/main/resources/docs/images/DocumentacionP3.png)
![DocumentacionP4.png](src/main/resources/docs/images/DocumentacionP4.png)
![DocumentacionP5.png](src/main/resources/docs/images/DocumentacionP5.png)
---
## Pruebas funcionamiento

http://localhost:8080/swagger-ui.html
---

## Preguntas 

1. ¿Para qué sirve el paquete Controller en la estructura Spring Boot?
   
    R/ El paquete Controller se encarga de manejar las solicitudes HTTP del cliente. Actúa como intermediario entre el usuario y la lógica del sistema, recibiendo                  peticiones, procesándolas y devolviendo respuestas (generalmente en formato JSON o vistas). Utiliza anotaciones como @RestController o @Controller.

2. ¿Para qué sirve el paquete Service en la estructura Spring Boot?
   
    R/ El paquete Service contiene la lógica de negocio de la aplicación. Aquí se procesan los datos antes de enviarlos al repositorio o devolverlos al controlador. Permite        separar responsabilidades y mantener el código organizado, utilizando anotaciones como @Service.

3. ¿Para qué sirve el paquete Repository en la estructura Spring Boot?
   
    R/ El paquete Repository se encarga del acceso a la base de datos. Permite realizar operaciones CRUD (crear, leer, actualizar y eliminar) sobre las entidades.                  Generalmente extiende interfaces como JpaRepository y usa la anotación @Repository.

4. ¿Para qué sirve el paquete Controller en la estructura Spring Boot?
   
    R/ El paquete Controller cumple la función de recibir y responder solicitudes HTTP, actuando como punto de entrada de la aplicación. Se encarga de dirigir las                  peticiones hacia los servicios correspondientes y devolver la información adecuada al cliente.

5. ¿Para qué sirve el paquete Entity en la estructura Spring Boot?
    
    R/ El paquete Entity define las clases que representan las tablas de la base de datos. Cada entidad está mapeada mediante anotaciones como @Entity, permitiendo la              persistencia de datos a través de JPA o Hibernate.

6. ¿Para qué sirve el paquete DTO en la estructura Spring Boot?
    
    R/El paquete DTO (Data Transfer Object) se utiliza para transferir datos entre capas de la aplicación. Ayuda a evitar exponer directamente las entidades y permite             controlar la información que se envía o recibe en las APIs.

7. ¿Para qué sirve el paquete Exception en la estructura Spring Boot?
   
    R/ El paquete Exception contiene las clases para el manejo de errores personalizados. Permite capturar y gestionar excepciones de forma centralizada, mejorando la              claridad del código y la respuesta al cliente mediante anotaciones como @ControllerAdvice.

## Bibliografía

Pivotal Software. (2023). Spring Boot Reference Documentation. Recuperado de https://spring.io

Baeldung. (2024). Guía de Spring Boot. Recuperado de https://www.baeldung.com

Oracle. (2023). Documentación oficial de Java. Recuperado de https://docs.oracle.com



## Miembros
- Luiza Mariana Gonzales Veloza

- Juan David Roa Hernández

- Juan David Moreno D'Aleman

- Karol Ximena Rodriguez Reyes

- Eduardo Rico Duarte

