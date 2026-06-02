# Sentinel: Stateless Auth & API Gateway Filter

A high-performance, lightweight security boundary backend built with Spring Boot 3. Sentinel is designed to intercept inbound traffic, authenticate users via stateless JWT, and evaluate transaction requests before they reach core business services.

## 🚀 Project Highlights
- **Stateless Architecture:** Fully utilizes JSON Web Tokens (JWT) stored in HTTP-only cookies, eliminating server-side session overhead.
- **Modular Monolith Design:** Domain-driven folder structure ensuring clean boundaries between `auth`, `user`, and `transaction` evaluation domains.
- **Gateway-Level Interception:** Custom Spring Security filter chains (`OncePerRequestFilter`) to block unauthorized access and malicious request vectors instantly.
- **Standardized Error Handling:** Centralized `@ControllerAdvice` to guarantee uniform, predictable REST API responses.

## 📂 Architecture & Folder Structure
This project strictly follows the **Feature-Driven N-Tier Architecture**. Each domain is isolated, maintaining its own Controller, Service, and Data layers.

*See the `src/main/java/com/portfolio/sentinel` directory for the exact implementation.*

## 🛠️ Tech Stack
- **Backend:** Java 17, Spring Boot 3 (Spring Web, Spring Security, Spring Data JPA)
- **Database:** PostgreSQL
- **Authentication:** JWT (io.jsonwebtoken)
- **Build Tool:** Maven

## ⚙️ Quick Start
1. Clone the repository.
2. Configure your PostgreSQL credentials in `application.yml`.
3. Run `mvn clean install` followed by `mvn spring-boot:run`.
