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

## 🏗️ Project Structure

```
src/
├── entity/                
│   ├── UrlEntity
│   ├── UserEntity
│   └── RoleEntity
│
├── repo/                  
│   ├── UrlRepo
│   ├── UserRepo
│   └── RoleRepo
│
├── service/               
│   ├── UrlService
│   ├── UserService
│   ├── JwtService
│   └── CustomerUserDetailsService
│
├── filter/                
│   ├── JwtAuthFilter      
│   └── RateLimitFilter    
│
├── mapper/                
│   ├── UrlMapper
│   └── UserMapper
│
├── exception/             
│   ├── GlobalExceptionHandler
│   └── ResourceNotFound
│
├── dto/                   
│   └── UserResponse
│
└── UrlShortenerApplication
```

---

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
