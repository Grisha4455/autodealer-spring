# AutoDealer - Car Dealership Management System

Full-stack web application for managing a commercial car dealership with public catalog and admin panel.


![Public catalog](docs/catalog.png)

| Home page | Admin dashboard |
|---|---|
| ![Home](docs/home.png) | ![Admin](docs/admin.png) |

## Technology Stack

- **Backend**: Java 17, Spring Boot 3.2.0
- **Database**: MySQL 8 / H2 (in-memory for testing)
- **Frontend**: HTML, CSS, Thymeleaf
- **Security**: Spring Security with BCrypt
- **Build Tool**: Maven

## Features

### Public Interface
- Home page with featured cars
- Catalog with advanced filters (brand, color, power, transmission, doors)
- Car details with specifications, pricing, and suppliers
- Customer request form

### Admin Panel
- Dashboard with statistics
- Car models management (CRUD)
- Suppliers management
- Price list management
- Customers management with purchase history
- Sales processing
- Employee management with roles
- Customer requests handling
- Analytics and reports

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8+ (optional, H2 works by default)

### Run Application

```bash
mvn spring-boot:run
```

Access at: **http://localhost:8081**

### Default Credentials

- Admin: `admin` / `password`
- Manager: `manager` / `password`

Passwords are stored as BCrypt hashes (see `src/main/resources/import.sql`).

## Database Configuration

### H2 In-Memory (Default)
Works out of the box, no setup needed.

### MySQL (Production)
Edit `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/autodealer
spring.datasource.username=root
spring.datasource.password=your_password
```

Or use profile:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

## Project Structure

```
autodealer/
??? src/main/
?   ??? java/com/autodealer/
?   ?   ??? config/
?   ?   ??? controller/
?   ?   ??? entity/
?   ?   ??? repository/
?   ?   ??? service/
?   ?   ??? AutoDealerApplication.java
?   ??? resources/
?       ??? static/css/
?       ??? templates/
?       ?   ??? public/
?       ?   ??? admin/
?       ??? application.properties
?       ??? import.sql
??? database/schema.sql
??? pom.xml
```

## User Roles

- **ADMIN** - Full access to all features
- **EMPLOYEE** - Access to sales and customer requests

## Test Data

Includes:
- 1 admin user
- 1 supplier
- 3 car models (Toyota Camry, RAV4, BMW X5)
- Price list for all models

## Build JAR

```bash
mvn clean package
java -jar target/autodealer-1.0.0.jar
```

## License

Educational project

