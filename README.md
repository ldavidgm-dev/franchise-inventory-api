# 🏪 Franchise Inventory API

API REST desarrollada con **Spring Boot** para gestionar un sistema de franquicias con sucursales y productos. Permite crear, actualizar, eliminar y consultar inventario, incluyendo la consulta del producto con mayor stock por sucursal dentro de una franquicia.

---

## 📋 Tabla de contenidos

- [Tecnologías](#-tecnologías)
- [Arquitectura](#-arquitectura)
- [Modelo de datos](#-modelo-de-datos)
- [Endpoints](#-endpoints)
- [Ejecución local](#-ejecución-local)
- [Variables de entorno](#-variables-de-entorno)
- [Despliegue en la nube](#-despliegue-en-la-nube)
- [Manejo de errores](#-manejo-de-errores)
- [Estructura del proyecto](#-estructura-del-proyecto)

---

## 🛠 Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| Spring Boot | 4.0.6 | Framework backend |
| Spring Data JPA | - | Persistencia y ORM |
| PostgreSQL | 16 | Base de datos relacional |
| Maven | 3.9.14 | Gestión de dependencias |
| Docker | 29.4.1 | Empaquetado y contenedores |
| Render | - | Despliegue en la nube |

---

## 🏗 Arquitectura

```
Cliente HTTP
    │
    ▼
┌─────────────────────────────┐
│      Spring Boot API        │
│  Controller → Service →     │
│  Repository                 │
└────────────┬────────────────┘
             │
             ▼
┌─────────────────────────────┐
│       PostgreSQL DB         │
│   franchise / branch /      │
│   product                   │
└─────────────────────────────┘

Empaquetado con Docker (multi-stage build)
Desplegado en Render + PostgreSQL en la nube
```

---

## 📐 Modelo de datos

```
┌─────────────────┐       ┌──────────────────┐       ┌─────────────────┐
│   franchise     │       │     branch       │       │    product      │
├─────────────────┤       ├──────────────────┤       ├─────────────────┤
│ id (PK)         │──┐    │ id (PK)          │──┐    │ id (PK)         │
│ name            │  └───▶│ franchise_id(FK) │  └───▶│ branch_id (FK)  │
└─────────────────┘       │ name             │       │ name            │
                          └──────────────────┘       │ stock           │
                                                      └─────────────────┘
```

- Una **franquicia** tiene muchas **sucursales**
- Una **sucursal** tiene muchos **productos**

---

## 📡 Endpoints

### Base URL local
```
http://localhost:8080/api/v1
```

### Base URL producción (Cloud)
```
https://franchise-inventory-api.onrender.com/api/v1
```

---

### Franquicias

#### Crear franquicia
```http
POST /api/v1/franchises
Content-Type: application/json

{
  "name": "Red Financiera Colombia"
}
```
```json
// 201 Created
{
  "id": 1,
  "name": "Red Financiera Colombia"
}
```

#### Actualizar nombre de franquicia
```http
PATCH /api/v1/franchises/{idFranchise}
Content-Type: application/json

{
  "name": "Nuevo Nombre"
}
```

---

### Sucursales

#### Agregar sucursal a una franquicia
```http
POST /api/v1/franchises/{idFranchise}/branches
Content-Type: application/json

{
  "name": "Sucursal Centro"
}
```
```json
// 201 Created
{
  "id": 1,
  "name": "Sucursal Centro",
  "franchiseId": 1
}
```

#### Actualizar nombre de sucursal
```http
PATCH /api/v1/branches/{idBranch}
Content-Type: application/json

{
  "name": "Nuevo Nombre"
}
```

---

### Productos

#### Agregar producto a una sucursal
```http
POST /api/v1/branches/{idBranch}/products
Content-Type: application/json

{
  "name": "Microcréditos",
  "stock": 100
}
```
```json
// 201 Created
{
  "id": 1,
  "name": "Microcréditos",
  "stock": 100,
  "branchId": 1
}
```

#### Eliminar producto
```http
DELETE /api/v1/products/{idProduct}
```
```
// 204 No Content
```

#### Modificar stock de un producto
```http
PATCH /api/v1/products/{idProduct}/stock
Content-Type: application/json

{
  "stock": 250
}
```

#### Actualizar nombre de producto
```http
PATCH /api/v1/products/{idProduct}
Content-Type: application/json

{
  "name": "Nuevo Nombre"
}
```

---

### Consultas

#### Producto con mayor stock por sucursal de una franquicia
```http
GET /api/v1/franchises/{idFranchise}/products/top
```
```json
// 200 OK
[
  {
    "branchId": 1,
    "branchName": "Sucursal Centro",
    "productId": 3,
    "productName": "Microcréditos",
    "stock": 200
  },
  {
    "branchId": 2,
    "branchName": "Sucursal Norte",
    "productId": 7,
    "productName": "Créditos empresariales",
    "stock": 350
  }
]
```

---

### Resumen de endpoints

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/api/v1/franchises` | Crear franquicia |
| `POST` | `/api/v1/franchises/{id}/branches` | Agregar sucursal |
| `POST` | `/api/v1/branches/{id}/products` | Agregar producto |
| `PATCH` | `/api/v1/franchises/{id}` | Actualizar nombre franquicia |
| `PATCH` | `/api/v1/branches/{id}` | Actualizar nombre sucursal |
| `PATCH` | `/api/v1/products/{id}` | Actualizar nombre producto |
| `PATCH` | `/api/v1/products/{id}/stock` | Modificar stock |
| `GET` | `/api/v1/franchises/{id}/products/top` | Top producto por sucursal |
| `DELETE` | `/api/v1/products/{id}` | Eliminar producto |

---

## 🚀 Ejecución local

### Prerrequisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y en ejecución
- [Git](https://git-scm.com/)

### Opción 1 — Docker Compose ✅ Recomendada

Levanta la API y la base de datos PostgreSQL con un solo comando, sin necesidad de instalar Java ni configurar nada.

```bash
# 1. Clonar el repositorio
git clone <url-del-repositorio>
cd franchise-inventory-api

# 2. Levantar todos los servicios
docker compose up --build
```

Accesos disponibles:

| Servicio | URL |
|---|---|
| API | http://localhost:8080 |
| PostgreSQL | localhost:5432 |

Para detener los servicios:
```bash
docker compose down
```

Para detener y eliminar los datos persistidos:
```bash
docker compose down -v
```

---

### Opción 2 — Ejecución sin Docker

Requiere tener instalado: Java 21, Maven y PostgreSQL 16.

```bash
# 1. Crear la base de datos
psql -U postgres -c "CREATE DATABASE franchise_db;"

# 2. Configurar credenciales en application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/franchise_db
spring.datasource.username=postgres
spring.datasource.password=tu_password

# 3. Compilar y ejecutar
mvn clean install
mvn spring-boot:run
```

---

## 🔐 Variables de entorno

La aplicación se configura completamente mediante variables de entorno, lo que permite ejecutarla en cualquier entorno sin modificar el código.

| Variable | Descripción | Ejemplo |
|---|---|---|
| `SPRING_DATASOURCE_URL` | URL de conexión a la BD | `jdbc:postgresql://db:5432/franchise_db` |
| `SPRING_DATASOURCE_USERNAME` | Usuario de la BD | `postgres` |
| `SPRING_DATASOURCE_PASSWORD` | Contraseña de la BD | `admin1234` |

En Docker Compose estas variables se inyectan automáticamente desde el archivo `docker-compose.yml`.

---

## ☁️ Despliegue en la nube

La solución está completamente desplegada en la nube:

| Componente | Servicio | Descripción |
|---|---|---|
| API Spring Boot | Render | Despliega desde GitHub con Dockerfile |
| Base de datos | Render PostgreSQL | BD en la nube, siempre disponible |

### Variables de entorno configuradas en Render

```
SPRING_DATASOURCE_URL      → URL del PostgreSQL en la nube
SPRING_DATASOURCE_USERNAME → Usuario
SPRING_DATASOURCE_PASSWORD → Contraseña
```

El despliegue es automático con cada `git push` a la rama principal.

---

## ⚠️ Manejo de errores

La API implementa manejo global de excepciones con `@RestControllerAdvice`, retornando respuestas estructuradas y códigos HTTP apropiados.

| Código | Situación |
|---|---|
| `201 Created` | Recurso creado exitosamente |
| `200 OK` | Consulta exitosa |
| `204 No Content` | Eliminación exitosa |
| `400 Bad Request` | Datos de entrada inválidos |
| `404 Not Found` | Recurso no encontrado |

Ejemplo de respuesta de error:
```json
{
  "status": 404,
  "message": "Franquicia con id 99 no encontrada"
}
```

Las validaciones de entrada se aplican con `@Valid`, `@NotBlank` y `@Min`, retornando mensajes descriptivos ante datos incorrectos.

---

## 📁 Estructura del proyecto

```
src/main/java/com/luisgarcia/franchise/
│
├── controller/
│
├── service/
│
├── repository/
│
├── model/
│
├── dto/
│   ├── request/
│   └── response/
│
├── exception/
│
└── FranchiseInventoryApiApplication.java
```

---

## 👤 Autor

**Luis Garcia**
Proyecto desarrollado como prueba técnica — evaluación de habilidades en desarrollo backend con Spring Boot, PostgreSQL, Docker y despliegue en la nube.