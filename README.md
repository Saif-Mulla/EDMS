Employee Directory Management System (Spring Boot + Thymeleaf + JPA + PostgreSQL) - A small MVC application that manages Employees and their Addresses with full CRUD, built for an interview task.

Key requirements covered:
  Create / Edit employee with multiple addresses (dynamic add/remove rows).
  Address Type must be PRIMARY or SECONDARY.
  Only one PRIMARY address per employee (client‑side warning + server‑side validation).
  Maintain page that lists all employees and shows Primary address only.
  Delete employee removes all associated addresses (JPA + DB cascade).
  No login/auth.
  Spring MVC with Thymeleaf pages (server‑rendered). Optional REST API endpoints are also provided.

Tech stack
  Java 17 (or 21)
  Spring Boot 3.3.x
  Spring Web (MVC)
  Spring Data JPA (Hibernate)
  Validation (Jakarta Bean Validation)
  Thymeleaf (server‑side rendering)
  PostgreSQL

For Running

Prerequisites
  JDK 17+
  Maven 3.8+
  PostgreSQL

Create Database and configure user in application.properties file.
CREATE DATABASE edms;
spring.datasource.url=jdbc:postgresql://localhost:portNumber/dbName
spring.datasource.username=username
spring.datasource.password=password

URLs
MVC (Thymeleaf) pages
Maintain list: GET /api/employee
Create form: GET /api/employee/new
Create submit: POST /api/employee/add
Edit form: GET /api/employee/{employeeId}/edit
Update submit: POST /api/employee/{employeeId}
Delete: POST /api/employee/{employeeId}/delete

Note: These pages submit HTML forms (no JSON required). The controller returns server‑rendered views.

Optional REST API (JSON)
GET /api/employee/all (list)
POST /api/employee/add (create with addresses)
GET /api/employee/{employeeId} (detail)
PUT /api/employee/{employeeId} (update entity + addresses)
DELETE /api/employee/{employeeId} (delete cascade)

