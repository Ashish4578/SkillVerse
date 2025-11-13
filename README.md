# SkillVerse 🚀

SkillVerse is an **online learning platform** built using a **reactive microservices architecture**. It allows users to register, browse courses, enroll, and rate/review them. It’s designed for scalability, observability, and maintainability — suitable for deployment in modern cloud environments.

---

## 🧱 Microservices Architecture

| Service Name           | Responsibilities                                                          | Tech / DB                         |
| ---------------------- | ------------------------------------------------------------------------- | --------------------------------- |
| **User-Service**       | Manages users, registration, authentication (via OAuth2 + JWT)            | Spring Boot, MySQL                |
| **Course-Service**     | Manages courses, instructors, and course metadata                         | Spring Boot, MySQL                |
| **Enrollment-Service** | Handles user-course enrollments and relationships                         | Spring Boot, MySQL                |
| **Rating-Service**     | Stores and retrieves ratings and reviews                                  | Spring Boot, MySQL                |
| **Auth-Service**       | Central OAuth2 Authorization Server for token management                  | Spring Boot Security OAuth2       |
| **Gateway-Service**    | Entry point using Spring Cloud Gateway (Routing, Security, Rate Limiting) | Spring Cloud Gateway              |
| **Config-Service**     | Centralized configuration management                                      | Spring Cloud Config (Git backend) |
| **Eureka-Service**     | Service discovery for all registered microservices                        | Netflix Eureka                    |

---

## ⚙️ Tech Stack

| Layer                    | Tech Stack                                              |
| ------------------------ | ------------------------------------------------------- |
| **Language**             | Java 17                                                 |
| **Frameworks**           | Spring Boot, Spring WebFlux (Reactive), Spring Data JPA |
| **Service Discovery**    | Eureka Server / Client                                  |
| **API Gateway**          | Spring Cloud Gateway                                    |
| **Config Management**    | Spring Cloud Config                                     |
| **Security**             | Spring Security, OAuth2, JWT                            |
| **Client Communication** | WebClient (Reactive)                                    |
| **Database**             | MySQL                                                   |
| **Logging**              | SLF4J + Logback                                         |
| **Resilience**           | Resilience4j (Circuit Breaker, Retry, RateLimiter)      |
| **Observability**        | Actuator, Sleuth, Zipkin, OpenTelemetry (planned)       |
| **Testing**              | JUnit 5, Mockito                                        |
| **Documentation**        | Swagger / OpenAPI                                       |
| **CI/CD**                | GitHub Actions / Jenkins (optional)                     |

---

## 🧪 Core Features

### 👥 Authentication & Users

* User registration and login with JWT tokens.
* OAuth2-based authentication via **Auth-Service**.
* Role-based authorization (User / Admin).

### 🎓 Courses

* Browse all available courses.
* Filter courses by category, level, or instructor.
* Admins can add/update/delete courses.

### 📝 Enrollments

* Enroll or drop courses.
* View enrolled courses.

### ⭐ Ratings & Reviews

* Add, update, and view course ratings and reviews.

### 🔍 Search (Optional)

* Implemented via Elasticsearch (planned extension).

### 🔔 Notifications (Optional)

* Email or in-app notifications for enrollments.

---

## 🧩 Architecture Overview

* All services are **Reactive** and communicate via **WebClient**.
* **API Gateway** acts as the single entry point for routing and authentication.
* **Eureka Server** handles service discovery.
* **Spring Cloud Config** manages centralized configurations.
* **Resilience4j** ensures fault tolerance and fallback.
* **Actuator** + **OpenTelemetry** planned for monitoring and tracing.

---

## 🧰 Development Setup

### Prerequisites

* Java 17+
* Maven 3.8+
* MySQL running locally or on Docker
* Git (for Config Server)

### Run Order (Recommended)

1. Service Registry
2. Config-Service
2. Auth-Service
3. All domain services (User, Course, Enrollment, Rating)
4. Gateway-Service

### Example Run Command

```bash
mvn spring-boot:run
```


---

## 🧩 Future Enhancements

* Add OpenTelemetry integration for distributed tracing.
* Add Angular frontend for user and admin panels.
* Integrate notification microservice (email/SMS).
* Implement API rate limiting in Gateway.

---

## 🧾 License

This project is currently under internal development and not yet publicly licensed.

---

**Author:** Ashish Gadekar
**Project:** SkillVerse — Reactive Microservices Learning Platform
**Version:** v1.0.0 (Development Stage)
