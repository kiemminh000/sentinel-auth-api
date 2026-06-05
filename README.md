# Sentinel Auth API 🛡️

A stateless Security Gateway and Transaction Filtering API built with Spring Boot 3. 
It acts as the first line of defense for a downstream Core Banking system.

## 🚀 Features
* **Stateless Authentication:** JWT-based login and token validation.
* **Security Filter Chain:** Blocks unauthorized requests at the gateway level.
* **Transaction Risk Evaluation:** Intercepts and validates transactions based on business rules (amount limits, IP blacklisting).
* **Global Exception Handling:** Centralized `@ControllerAdvice` for standardized API error responses.

## 🛠️ Tech Stack
* Java 17 | Spring Boot 3.5.x
* Spring Security | Spring Data JPA
* PostgreSQL | JSON Web Tokens (jjwt)
* Swagger UI (OpenAPI)

## ⚙️ Local Setup Instructions

1. **Clone the repository:**
```bash
   git clone [https://github.com/kiemminh000/sentinel-auth-api.git](https://github.com/kiemminh000/sentinel-auth-api.git)
   ```

2. **Database Setup:**
   * Open pgAdmin and create a new database named `sentinel_db`.

3. **Environment Variables (CRITICAL):**
   * Create a file named `.env` in the root directory of the project.
   * Add your database password and a secure JWT secret key:
```env
     DB_PASSWORD=your_postgres_password_here
     JWT_SECRET=YourSuperSecretKeyForJwtGenerationMustBeLongEnough123!
     ```

4. **Run the Application:**
* Use VSCode's Run button or execute the Maven wrapper:
```bash
     ./mvnw spring-boot:run
     ```
   * *Note: A default test user (`admin` / `123456`) will be auto-generated upon the first successful startup.*

5. **API Documentation:**
   * Once the server is running, access Swagger UI at: `http://localhost:8080/swagger-ui/index.html`