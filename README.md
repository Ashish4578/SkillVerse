# SkillVerse

SkillVerse is a scalable microservices-based online learning platform built using Spring Boot and Spring Cloud.

The platform enables users to register, explore courses, enroll, track learning progress, upload course content, receive notifications, and rate courses using an event-driven architecture.

---

## Microservices Architecture

| Service Name         | Responsibility                                                                          | Tech Stack                |
| -------------------- | --------------------------------------------------------------------------------------- | ------------------------- |
| Auth-Service         | Authentication, JWT generation, refresh tokens, user registration                       | Spring Security, JWT      |
| User-Service         | User profiles, admin management, super-admin management                                 | Spring Boot, MySQL        |
| Course-Service       | Courses, modules, lessons, content, file uploads, progress tracking, rating aggregation | Spring Boot, MySQL, MinIO |
| Enrollment-Service   | Course enrollments and enrollment events                                                | Spring Boot, MySQL        |
| Rating-Service       | Course ratings and reviews                                                              | Spring Boot, MySQL        |
| Notification-Service | Welcome emails, enrollment emails, notifications                                        | Spring Boot, Kafka        |
| API-Gateway          | Routing, JWT validation, header propagation                                             | Spring Cloud Gateway      |
| Config-Service       | Centralized configuration management                                                    | Spring Cloud Config       |
| Eureka-Service       | Service discovery                                                                       | Netflix Eureka            |

---

## Tech Stack

| Layer                       | Technology           |
| --------------------------- | -------------------- |
| Language                    | Java 17              |
| Framework                   | Spring Boot          |
| Microservices               | Spring Cloud         |
| Security                    | Spring Security, JWT |
| Database                    | MySQL                |
| Messaging                   | Apache Kafka         |
| Object Storage              | MinIO                |
| Inter-service Communication | OpenFeign            |
| Service Discovery           | Eureka               |
| Configuration Management    | Spring Cloud Config  |
| Monitoring                  | Spring Actuator      |
| Logging                     | SLF4J + Logback      |

---

## Roles

| Role        | Permissions                                                       |
| ----------- | ----------------------------------------------------------------- |
| STUDENT     | Browse courses, enroll, track progress, rate courses              |
| CREATOR     | Create and manage courses, modules, lessons, content, and uploads |
| ADMIN       | Manage platform users                                             |
| SUPER_ADMIN | Manage admins and platform users                                  |

---

## Event-Driven Architecture

SkillVerse uses Apache Kafka for asynchronous communication between services.

### Producers

* Auth-Service → user-created
* Enrollment-Service → enrollment-events
* Rating-Service → rating-created

### Consumers

* User-Service → consumes user-created
* Notification-Service → consumes enrollment-events
* Notification-Service → consumes user-created
* Course-Service → consumes rating-created

---

## System Flow

```text
User Registration
    ↓
Auth-Service
    ↓ Kafka
User-Service
    ↓ Kafka
Notification-Service
    ↓
Welcome Email

Course Enrollment
    ↓
Enrollment-Service
    ↓ Kafka
Notification-Service
    ↓
Enrollment Confirmation Email

Course Rating
    ↓
Rating-Service
    ↓ Kafka
Course-Service
    ↓
Rating Aggregation Update
```

---

## Core Features

### Authentication

* User Registration
* JWT Login
* Refresh Tokens
* Logout
* Role-Based Access Control

### User Management

* View Profile
* Update Profile
* Delete Profile
* Admin User Management
* Super Admin User Management

### Courses

* Create Course
* Update Course
* Delete Course
* Search Courses
* Course Thumbnail Support
* Pagination Support
* Ownership Validation

### Modules

* Create Module
* Update Module
* Delete Module
* Retrieve Modules By Course

### Lessons

* Create Lesson
* Update Lesson
* Delete Lesson
* Retrieve Lessons By Module

### Lesson Content

Supported Content Types:

* TEXT
* VIDEO
* IMAGE
* PDF
* QUIZ

Operations:

* Create Content
* Update Content
* Delete Content
* Retrieve Content

### File Uploads

* Upload Course Thumbnails
* Upload Learning Resources
* MinIO Object Storage Integration
* Public File URL Generation

### Enrollments

* Enroll In Course
* View My Enrollments
* View Course Enrollments
* Prevent Duplicate Enrollment

### Learning Progress

* Mark Lesson As Completed
* Track Course Progress
* Track Module Progress
* Prevent Duplicate Completion Records

### Ratings

* Rate Course
* Update Rating
* Delete Rating
* View Course Ratings
* View My Ratings
* Course Rating Summary
* Event-Driven Rating Aggregation

### Notifications

* Welcome Email On Registration
* Enrollment Confirmation Email
* User Notification History
* Unread Notification Count
* Mark Notification As Read

---

## Course Structure

```text
Course
 └── Module
      └── Lesson
           └── Content

Content Types:
 - TEXT
 - VIDEO
 - IMAGE
 - PDF
 - QUIZ
```

---

## Architecture Principles

* Database Per Service
* Event-Driven Communication
* Loose Coupling Through Kafka
* API Gateway Security
* Service Discovery With Eureka
* Centralized Configuration
* Eventual Consistency
* Synchronous Communication Via Feign

---

## Development Setup

### Prerequisites

* Java 17+
* Maven 3.8+
* MySQL
* Docker
* Git

---

## Run Order

1. Eureka-Service
2. Config-Service
3. Kafka
4. MinIO
5. Auth-Service
6. User-Service
7. Course-Service
8. Enrollment-Service
9. Rating-Service
10. Notification-Service
11. API-Gateway

---

## Run Application

```bash
mvn spring-boot:run
```

---

## Sample APIs

### Login

```http
POST /skillverse/auth/login
```

### Create Course

```http
POST /skillverse/courses
```

### Create Module

```http
POST /skillverse/courses/{courseId}/modules
```

### Create Lesson

```http
POST /skillverse/courses/modules/{moduleId}/lessons
```

### Upload File

```http
POST /skillverse/courses/files/upload
```

### Enroll Course

```http
POST /skillverse/enrollments
```

### Mark Lesson Complete

```http
POST /skillverse/courses/progress/lessons/{lessonId}/complete
```

### Get Course Progress

```http
GET /skillverse/courses/progress/{courseId}
```

### Rate Course

```http
POST /skillverse/ratings
```

---

## Future Enhancements

* API Rate Limiting
* Kafka Retry & Dead Letter Queue
* Distributed Tracing (OpenTelemetry)
* Course Certificates
* Quiz Engine
* Frontend Application (Angular)

---

## License

Currently under development. Not licensed for public use.

---

## Author

Ashish Gadekar

SkillVerse — Microservices Learning Platform

Version: v1.0.0
