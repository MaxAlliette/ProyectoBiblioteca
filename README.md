Proyecto realizado por grupo 6 (Valeria Bustos, Nicolás Contador y Benjamín Figueroa) que consiste en una biblioteca que gestiona:

-Usuarios: permite el registro, consulta, actualización, eliminación y consultas personalizadas de usuarios.

-Libros: permite el registro, consulta, actualización, eliminación y consultas personalizadas de libros.

-Autores y categorías: permite el registro, consulta, actualización, eliminación y consultas personalizadas de autores y categoría, y asociarlas a libros existentes.

-Stock/ejemplares: permite el registro, consulta, actualización, eliminación y consultas personalizadas de ejemplares, esto permite gestionar la cantidad que hay para cada libro y el estado en que se encuentra.

-Préstamos: registra préstamos y devoluciones, asociados a un ejemplar y un usuario.

-Multas: asocia multas a préstamos sin devolver.

-Salas: permite el registro, consulta, actualización, eliminación y consultas personalizadas de salas de estudio.

-Reservas: Gestiona la reserva de una sala de estudio existente como la fecha en que se reserva, y su horario.

Pasos para ejecutar:

-Clonar repositorio o descargar zip

-Crear una base de datos en MySQL llamada db_bibliotecaproyecto (nosotros nos conectamos a través de XAMPP)

-En el IDE correr cada aplicación verificando que los puertos no estén ocupados.

-Probar funcionalidades de cada aplicación a través de postman usando url http://localhost:{numero_de_puerto}/{nombre_ruta_según}
