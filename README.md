# GAMESTORESV


- **Frontend Laravel:** Estructura robusta con Blade, controladores y rutas.
- **Frontend HTML/jQuery:** Versión ligera para prototipado rápido e integración directa con la API REST.
- **Backend Java:** Lógica de negocio y seguridad, implementado con Spring Boot.

---

## Estructura del Proyecto

### Frontend 1 - Laravel (PHP)
Diseñado para escalabilidad y control completo del flujo de la app.


### Frontend 2 - HTML + jQuery
Enfocado en pruebas rápidas y consumo directo de la API. Archivos ubicados en:

/public/
│
├── admin.html
├── carrito.html
├── catalogo.html
├── index.html
├── login.html
├── rastreo.html
└── js/ → Scripts jQuery para consumo de API


### Backend - Java (Spring Boot)
Estructura completa basada en capas con seguridad, DTOs y API REST:

/controllers/ → Endpoints expuestos
/services/ → Lógica de negocio
/models/ → Entidades y DTOs
/security/ → Configuración JWT y autenticación

---

## 🚀 Funcionalidades

### Implementadas:

- Inicio de sesión con autenticación JWT
- Visualización de catálogo y detalles de productos
- Carrito de compras básico
- Seguimiento de pedidos (rastreo)
- Gestión de usuarios (admin y cliente)
- API REST funcional
- Frontend dual: Laravel + HTML/jQuery

---

## Ramas activas

| Rama | Descripción |
|------|-------------|
| `feature/api-completa` | API REST lista + UI básica conectada |
| `feature/add-dtos` | Integración de objetos DTO |
| `fix/solucion-errores` | Correcciones identificadas en pruebas |

---

## Tecnologías Utilizadas

### Frontend:
- Laravel 10
- PHP 8.x
- HTML5 / CSS3
- jQuery
- Bootstrap

### Backend:
- Java 17
- Spring Boot
- Spring Security
- JWT
- PostgreSQL / MySQL

---
### Laravel (Frontend 1):

```bash
composer install
cp .env.example .env
php artisan key:generate
php artisan migrate
php artisan serve

cd backend/
./mvnw spring-boot:run
