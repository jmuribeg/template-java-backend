RETO 1. CRUD REPOSITORY Y EXCEPCIONES
Configura el acceso a una base de datos en PostgreSQL. Solo necesitas colocar tus datos en el archivo “application.yml”, incluido en el package resources, del proyecto.
datasource: url: jdbc:postgresql://localhost:5432/postgres username: admin password: password
El proyecto se configura para crear la tabla según la definición de la siguiente clase.

Entidad
Product

Atributos
- Long id
- String name
- String label
- Double price
- Date createdAt

Crea un servicio RESTful con métodos de lectura, escritura, actualización y borrado de registros. ￼
Objetivos
➢
Define la entidad Product.
➢
Crea un repositorio JPA para Product.
➢
Implementa un controlador REST con endpoints para crear, consultar, actualizar y eliminar productos.

B) Crea un endpoint GET para consultar los productos por nombre.
Objetivos
➢
El método debe recibir el texto a buscar por nombre.
➢
Las coincidencias deben ordenarse descendentemente.
C) Manejo de excepciones para los endpoints
Objetivo
➢
Valida que los datos de entrada no sean nulos para el método POST
(name, label, price)


