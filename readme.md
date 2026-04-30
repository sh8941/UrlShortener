# 🔗 URL Shortener Service

A scalable and secure URL Shortener built with Spring Boot.
This project demonstrates backend engineering best practices including caching, rate limiting, authentication, and clean architecture.

---

## 🚀 Features

### 🔹 URL Shortening

* Generate unique short URLs for long links
* Redirect users to original URLs efficiently

### ⚡ Caching (Redis)

* Cache frequently accessed URLs
* Reduces database load
* Improves performance using cache-first strategy

### 🚦 Rate Limiting

* Implemented using filter-based approach
* Prevents API abuse and traffic spikes
* Ensures system stability under load

### 🔐 Authentication & Security

* JWT-based authentication
* Custom security filters
* Secure API endpoints

### 👤 User Management

* User registration & login
* Role-based structure using `UserEntity` and `RoleEntity`

### 🗄️ Database Integration

* MySQL with Spring Data JPA
* Optimized data access using repositories

### 🧱 Clean Architecture

* Layered structure (Controller → Service → Repository)
* DTO + Mapper pattern for separation of concerns

### 🐳 Docker Support

* Fully containerized setup
* Includes:

    * Application service
    * MySQL
    * Redis

---

## 📦 Project Structure

```
com.haider.UrlShortener
│
├── Base62
│   └── Base62Util.java              # Utility for Base62 encoding/decoding (short URL generation)
│
├── Config
│   ├── RateLimitingConfig.java      # Configuration for rate limiting
│   ├── RedisConfig.java             # Redis cache configuration
│   ├── SecurityConfig.java          # Spring Security configuration
│   ├── SecurityUtils.java           # Helper utilities for security context
│   ├── SwaggerConfig.java           # API documentation configuration
│   └── UrlAuthorization.java        # URL access control rules
│
├── controller
│   ├── AdminController.java         # Admin-related APIs
│   ├── AuthController.java          # Authentication (login/register)
│   ├── GoToController.java          # Handles redirection to original URLs
│   ├── HelloController.java         # Test/health check endpoints
│   ├── UrlController.java           # URL shortening & management APIs
│   └── UserController.java          # User management APIs
│
├── dtos
│   ├── request
│   │   ├── AuthRequest.java         # Login/Register request payload
│   │   ├── UrlRequest.java          # URL creation request
│   │   └── UserRequest.java         # User-related request data
│   │
│   └── response
│       ├── CustomPage.java          # Pagination response wrapper
│       ├── UrlResponse.java         # URL response payload
│       └── UserResponse.java        # User response payload
│
├── entity
│   ├── RoleEntity.java              # Role model (ADMIN, USER, etc.)
│   ├── UrlEntity.java               # URL database entity
│   └── UserEntity.java              # User database entity
│
├── Exception
│   ├── GlobalExceptionHandler.java  # Centralized exception handling
│   └── ResourceNotFoundException.java # Custom exception
│
├── filter
│   ├── JwtAuthFilter.java           # JWT authentication filter
│   └── RateLimitFilter.java         # Request rate limiting filter
│
├── mapper
│   ├── UrlMapper.java               # Entity ↔ DTO conversion (URL)
│   └── UserMapper.java              # Entity ↔ DTO conversion (User)
│
├── repo
│   ├── RoleRepo.java                # Role repository (JPA)
│   ├── UrlRepo.java                 # URL repository
│   └── UserRepo.java                # User repository
│
└── service
    ├── CustomUserDetailsService.java # Spring Security user details service
    ├── JwtService.java               # JWT token handling
    ├── UrlService.java               # Business logic for URLs
    └── UserService.java              # Business logic for users
```


## ⚙️ Tech Stack

* Java 21+
* Spring Boot
* Spring Security (JWT)
* Spring Data JPA
* Redis (Caching)
* MySQL (Database)
* Docker & Docker Compose

---

## 🔄 Request Flow

### URL Redirection Flow:

1. Request enters system
2. `RateLimitFilter` validates request
3. `JwtAuthFilter` authenticates user (if required)
4. System checks Redis cache
5. If not found → fetch from MySQL
6. Response returned to user

---

## 🧠 Key Concepts Implemented

* Cache-aside pattern
* Filter-based rate limiting
* JWT authentication
* Layered architecture
* DTO + Mapper pattern
* Global exception handling

---

## 🐳 Running with Docker

```bash
docker-compose up --build
```

### Services:

* App → http://localhost:8080
* MySQL → localhost:3306
* Redis → localhost:6379

---

## 🔧 Configuration

Update your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://mysql:3306/URL_SHORTENER
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.redis.host=redis
spring.redis.port=6379
```

---

## 📌 Future Improvements

* Custom short URL aliases
* URL expiration feature
* Analytics dashboard
* Distributed rate limiting (Redis-based)
* CI/CD pipeline integration
* Kubernetes deployment

---

## 🤝 Contribution

Feel free to fork and improve the project.

---

## 📄 License

This project is open-source and available under the MIT License.
