# literAlura

Este proyecto consiste en un sistema que realiza consultas a una API de libros, almacena los datos obtenidos en una base de datos local y permite interactuar con ellos utilizando PostgreSQL y consultas derivadas (`Derived Queries`). Toda la interacción se realiza a través de la consola.

---

## Características del Proyecto

- **Consulta de API:** 
  - Realiza solicitudes a una API externa para obtener información de libros.
  - Los datos obtenidos incluyen información como título, autor, idioma y cantidad de descargas.

- **Almacenamiento en Base de Datos:**
  - Los datos se almacenan en una base de datos local PostgreSQL.
  - Se realiza una normalización básica de los datos al almacenarlos.

- **Interacción con la Base de Datos:**
  - Uso de consultas SQL a través de `jpql` y `derived queries`.
  - Implementación de consultas derivadas en el código para manejar datos de forma más eficiente.

- **Sin Frontend:**
  - Toda la interacción con el sistema se realiza por consola.

---

## Tecnologías Utilizadas

- **Lenguaje de Programación:** Java (Spring Framework)
- **Base de Datos:** PostgreSQL
- **Dependencias:** Spring Data JPA, JDBC, Jackson Databind, APIs externas para datos de libros.
- **IDE:** IntelliJ IDEA

---

## Configuración del Proyecto

### Requisitos Previos

1. **Instalar Java 17 o superior.**
2. **PostgreSQL:** Asegúrate de tener PostgreSQL instalado y un servidor de base de datos en funcionamiento.
3. **Configuración del Archivo `application.properties`:** Actualiza las credenciales de acceso a tu base de datos en el archivo `application.properties`.
