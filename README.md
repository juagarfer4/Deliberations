# Deliberaciones Agora@US 15-16

Deliberaciones es un subsistema de código abierto del proyecto Agora@US que introduce la funcionalidad de un foro para administrar hilos y mensajes de usuarios votantes. 
Dichos mensajes contendrán información sobre el votante que los haya realizado. Sólo los votantes válidos podrán realizar comentarios, por lo que se deberá consultar al sistema de autenticación.

### Version
0.1

### Tecnología usada
Deliberaciones está basado en Java y se apoya en Spring para ajustarse al MVC. La base de datos que recoge la información es MySQL.

### Integración

Deliberaciones usa varios subsistemas de código abierto para su correcto funcionamiento:

* [Auth] - Autenticación
* [Census] - Creación y administración de Censos

Por supuesto, Deliberaciones cuenta con su propio [repositorio][dill] de código abierto en GitHub.

### Despliegue

Para deplegar Deliberaciones necesitas generar un .war del proyecto y ejecutarlo en un un servidor Apache Tomcat.

Para su correcta integración y funcionamiento necesitarás simultáneamente hacer lo mismo con el subsistema [Creación y administración de Censos][#], además de correr el subsitema [Autenticación][#] en un servidor Apache.

Sin olvidar estar ejecutando un servidor MySQL para el almacenamiento de los datos.

### Equipo de trabajo

 - Juan García Orozco
 - Juan García-Quismondo Fernández
 - Roberto Jiménez Castillo
 - Francisco José Macías García
 - Alfredo Menéndez Charlo
 - Rubén Ramírez Vera
 

   [dill]: <https://github.com/juagarfer4/Deliberations>
   [#]: <#>


