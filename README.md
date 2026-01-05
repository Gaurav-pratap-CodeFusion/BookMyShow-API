# 🎟️ BookMyShow Backend API (Spring Boot)

A **secure, scalable, and production-ready backend REST API** built using **Java Spring Boot**, inspired by **BookMyShow**.  
This project manages **Movies, Theatres, Screens, Shows, Seats, and Ticket Booking** with **JWT-based authentication and role-based authorization**.

---

## 🚀 Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Security:** Spring Security + JWT
- **ORM:** Spring Data JPA (Hibernate)
- **Database:** MySQL 
- **Build Tool:** Maven
- **API Documentation:** Swagger (OpenAPI 3)
- **IDE:** IntelliJ IDEA

---

## 🔐 Security & Authentication

- JWT based authentication
- Role based authorization (`USER`, `ADMIN`)
- Stateless security using `OncePerRequestFilter`
- Password encryption using `BCryptPasswordEncoder`
- Swagger UI support for JWT login (Authorize 🔒 button)

### 🔑 Roles

- **USER**
    - Browse movies & shows
    - Book tickets
    - Cancel bookings
    - View booking details

- **ADMIN**
    - Add / manage movies
    - Add / manage theatres & screens
    - Create & manage shows
    - View users
    - Promote users to ADMIN

---

## 📁 Project Structure

```text
src/main/java/com/gpcf/BookMyShow
 ├── controller
 │    ├── admin
 │    └── user
 ├── service
 ├── repository
 ├── model
 ├── dto
 ├── security
 ├── config
 ├── bootstrap
 └── exception
