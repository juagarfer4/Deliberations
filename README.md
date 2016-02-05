# Deliberaciones Agora@US 15-16

Deliberaciones es un subsistema de código abierto del proyecto Agora@US que introduce la funcionalidad de un foro para administrar hilos y mensajes de usuarios votantes. 
Dichos mensajes contendrán información sobre el votante que los haya realizado. Sólo los votantes válidos podrán realizar comentarios, por lo que se deberá consultar al sistema de autenticación.

### Version
v2.0

### Tecnología
Se trata de una aplicación web J2EE que hace uso de una base de datos MySQL con las herramientas Hibernate y Spring para el mapeo de objetos.

Además, se utilizan las siguientes herramientas y frameworks:
- Maven
- JSP
- JPQL
- HTML
- CSS
- JavaScript
- Bootstrap
 
### Integración

Deliberaciones se integra con el subsistema [Autenticación][auth] mediante su API para ofrecer las herramientas de foro a usuarios dados de alta.

### Instalación
La construcción, tests y despliegue se ofrecen de forma automática mediante Jenkins en la máquina virtual de Deliberaciones 2016. Basta con dirigirse a la web UI de Jenkins una vez se ejecuten los servicios Apache, MySQL y Tomcat y lanzar la tarea Auth, la cual ejecutará a su vez Deliberations y levantará ambos subsistemas.

Con Jenkins se asegura la integración continua cada vez que Autenticación o Deliberaciones sufran cambios en sus respectivos repositorios.

### Descarga

 - Entrega 1: [Link][entrega 1]
 - Entrega 2: [Link][entrega 2]

### Equipo de trabajo

 - Juan García Orozco
 - Juan García-Quismondo Fernández
 - Roberto Jiménez Castillo
 - Francisco José Macías García
 - Alfredo Menéndez Charlo
 - Rubén Ramírez Vera
 

   [entrega 1]: <https://github.com/juagarfer4/Deliberations/releases/tag/Entrega1>
   [entrega 2]: <https://github.com/juagarfer4/Deliberations/releases/tag/Entrega2>
   [auth]: <https://github.com/AgoraUS1516/G03>
