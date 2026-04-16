# SkillVerse 

SkillVerse is a **scalable microservices-based online learning platform** built using **Spring Boot & Spring Cloud**.
It enables users to register, explore courses, enroll, and rate them — designed with **event-driven architecture and service isolation**.

---

##  Microservices Architecture

| Service Name             | Responsibility                                                    | Tech Stack           |
| ------------------------ | ----------------------------------------------------------------- | -------------------- |
| **Auth-Service**         | Handles authentication, JWT generation, and user registration     | Spring Security, JWT |
| **User-Service**         | Manages user profiles and user data                               | Spring Boot, MySQL   |
| **Course-Service**       | Manages courses and maintains aggregated rating stats             | Spring Boot, MySQL   |
| **Enrollment-Service**   | Handles course enrollments and publishes enrollment events        | Spring Boot, MySQL   |
| **Rating-Service**       | Manages course ratings and reviews, publishes rating events       | Spring Boot, MySQL   |
| **Notification-Service** | Consumes events and sends notifications (email/in-app)            | Spring Boot, Kafka   |
| **API-Gateway**          | Central entry point (routing, authentication, header propagation) | Spring Cloud Gateway |
| **Config-Service**       | Centralized configuration                                         | Spring Cloud Config  |
| **Eureka-Service**       | Service discovery                                                 | Netflix Eureka       |

---

##  Tech Stack

| Layer                   | Technology                             |
| ----------------------- | -------------------------------------- |
| **Language**            | Java 17                                |
| **Framework**           | Spring Boot                            |
| **Microservices**       | Spring Cloud (Gateway, Eureka, Config) |
| **Security**            | Spring Security, JWT                   |
| **Database**            | MySQL                                  |
| **Messaging**           | Apache Kafka                           |
| **Inter-service Calls** | OpenFeign                              |
| **Logging**             | SLF4J + Logback                        |
| **Monitoring**          | Spring Actuator                        |

---

##  Event-Driven Architecture (Kafka)

SkillVerse uses **Kafka for asynchronous communication** between services.

###  Producers

* Auth-Service → `user-created`
* Enrollment-Service → `enrollment-events`
* Rating-Service → `rating-created`

### Consumers

* User-Service → consumes `user-created`
* Notification-Service → consumes `enrollment-events`
* Course-Service → consumes `rating-created`

---

##  System Flow

```text
User registers → Auth-Service → Kafka → User-Service DB sync

User enrolls → Enrollment-Service → Kafka → Notification-Service

User rates course → Rating-Service → Kafka → Course-Service updates rating
```

---

##  Core Features

###  Authentication & Users

* JWT-based authentication
* Role-based authorization (STUDENT / CREATOR / ADMIN)
* User profile management

###  Courses

* Create, update, delete courses (CREATOR role)
* Browse and search courses
* Maintains **average rating & total ratings (event-driven)**

### Enrollments

* Enroll in courses
* View enrolled courses
* Prevent duplicate enrollments

###  Ratings & Reviews

* Rate courses (1–5)
* Add reviews
* Prevent duplicate ratings
* Event-driven aggregation in Course-Service

### Notifications

* Triggered on enrollment events
* Email/in-app notification support (extensible)

---

## Architecture Principles

* **Database per service**
* **Loose coupling via Kafka events**
* **API Gateway for centralized security**
* **Feign for synchronous communication**
* **Eventual consistency across services**

---

## Development Setup

### Prerequisites

* Java 17+
* Maven 3.8+
* MySQL
* Docker (for Kafka)
* Git

---

### Run Order

1. Eureka-Service
2. Config-Service
3. Kafka (Docker)
4. Auth-Service
5. User-Service
6. Course-Service
7. Enrollment-Service
8. Rating-Service
9. Notification-Service
10. API-Gateway

---

### Run Command

```bash
mvn spring-boot:run
```

---

##  Sample APIs

### Login

```bash
POST /skillverse/auth/login
```

###  Create Course

```bash
POST /skillverse/courses
```

### Rate Course

```bash
POST /skillverse/ratings
```

---

##  Future Enhancements

* Kafka Retry + DLQ implementation
* Distributed tracing (OpenTelemetry)
* Caching with Redis
* API rate limiting
* Frontend (Angular/React)
* Event versioning

---

## License

Currently under development. Not licensed for public use.

---

## Author

**Ashish Gadekar**
SkillVerse — Microservices Learning Platform
Version: `v1.0.0`
