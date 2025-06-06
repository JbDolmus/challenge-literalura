# 📚 Litera-Challenge

Litera-Challenge es una aplicación de consola desarrollada con **Spring Boot** que permite buscar, registrar y listar libros y autores utilizando datos públicos del sitio [Gutenberg API](https://gutendex.com/). La aplicación persiste la información consultada en una base de datos local.

## 🚀 Características principales

- Buscar libros por título (usando la API de Gutendex).
- Registrar libros y autores en la base de datos.
- Listar libros y autores registrados.
- Filtrar autores que estaban vivos en un año determinado.
- Listar libros según su idioma.

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Base de datos PostgreSQL
- Jackson (para deserialización de JSON)
- API pública [Gutendex](https://gutendex.com/)

## 📁 Estructura del proyecto

com.alura.litera_challenge
├── LiteraChallengeApplication.java # Clase principal
├── principal
│ └── Main.java # Clase de lógica de interacción por consola
├── model
│ ├── Author.java # Entidad JPA de autor
│ ├── Book.java # Entidad JPA de libro
│ ├── DataBook.java # Record para representar un libro desde la API
│ └── DataResponse.java # Record para representar respuesta de la API
├── repository
│ ├── AuthorRepository.java # Interfaz JPA para autores
│ └── BookRepository.java # Interfaz JPA para libros
└── service
├── QueryAPI.java # Clase para llamadas HTTP a la API externa
├── DataConverter.java # Implementación de conversor de JSON a objeto
└── IDataConverter.java # Interfaz del conversor de datos


## 🧠 Ejemplo de flujo

1. Selecciona la opción `1` y busca un libro por título.
2. Si el libro existe y no ha sido guardado aún, se almacenará en la base de datos junto con su autor.
3. Puedes listar libros o autores registrados mediante las opciones 2 y 3.
4. La opción 4 permite filtrar autores vivos en un año específico.
5. La opción 5 filtra libros por idioma (`es`, `en`, `fr`, `pt`).

## ⚠️ Validaciones

- Se captura `InputMismatchException` para evitar errores por tipos de datos incorrectos.
- Se valida que el idioma ingresado sea uno de los permitidos.
- Verifica si un libro ya fue registrado antes de intentar guardarlo de nuevo.

## 📦 Dependencias clave

- `spring-boot-starter-data-jpa`
- `spring-boot-starter`
- `jackson-databind`
- `postgresql`

## 📌 Notas adicionales

- El método `QueryAPI` realiza llamadas HTTP a la API externa para obtener datos de libros.
- `DataConverter` utiliza Jackson para mapear las respuestas JSON a Records (`DataBook`, `DataResponse`).
- La persistencia de datos se hace con `JpaRepository` para facilitar las consultas a la base de datos.

## ✍️ Autor

Desarrollado por Juan Dolmus.

---

¡Gracias por probar **Litera-Challenge**! 📖✨
