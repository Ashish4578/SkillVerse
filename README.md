# SkillVerse

SkillVerse is a microservices-based Learning Management System (LMS) built using Spring Boot and Spring Cloud.

The platform enables creators to build and manage courses, students to enroll and learn, and administrators to manage the platform through secure role-based access control.

The project follows modern microservices principles including service isolation, centralized configuration, service discovery, event-driven communication, and database-per-service architecture.

---

# Architecture

## Microservices

| Service              | Responsibility                                          |
| -------------------- | ------------------------------------------------------- |
| Auth-Service         | Authentication, JWT generation, login and registration  |
| User-Service         | User profile management                                 |
| Course-Service       | Course, module, lesson, content and progress management |
| Enrollment-Service   | Course enrollment management                            |
| Rating-Service       | Course ratings and reviews                              |
| Notification-Service | Email notifications                                     |
| API-Gateway          | Routing, security and request forwarding                |
| Config-Service       | Centralized configuration                               |
| Eureka-Service       | Service discovery                                       |

---

# Technology Stack

| Category                    | Technology           |
| --------------------------- | -------------------- |
| Language                    | Java 17              |
| Framework                   | Spring Boot 3        |
| Microservices               | Spring Cloud         |
| Security                    | Spring Security, JWT |
| Database                    | MySQL                |
| Messaging                   | Apache Kafka         |
| Service Discovery           | Netflix Eureka       |
| API Gateway                 | Spring Cloud Gateway |
| Configuration               | Spring Cloud Config  |
| File Storage                | MinIO                |
| Inter-Service Communication | OpenFeign            |
| Monitoring                  | Spring Boot Actuator |
| Build Tool                  | Maven                |

---

# User Roles

## Student

* Register and login
* Browse available courses
* Enroll in courses
* Access lessons and content
* Track learning progress
* Rate courses

## Creator

* Create courses
* Upload course thumbnails
* Create modules
* Create lessons
* Upload lesson content
* Manage owned courses

## Admin

* Platform administration

## Super Admin

* Administrative user management
* Full platform access

---

# Course Structure

```text
Course
 ├── Module
 │
 ├── Lesson
 │
 ├── Content
 │    ├── VIDEO
 │    ├── IMAGE
 │    ├── PDF
 │    ├── TEXT
 │    └── QUIZ
 │
 └── Progress Tracking
```

---

# Core Features

## Authentication

* JWT-based authentication
* Secure login and registration
* Role-based authorization
* Stateless authentication

## User Management

* User registration
* Profile management
* Creator and student support
* Admin and super admin support

## Course Management

* Create courses
* Update courses
* Delete courses
* Search courses
* Pagination support
* Ownership validation

## Course Thumbnail Support

* Upload images using MinIO
* Store thumbnail URL with course
* Unique file naming strategy

## Module Management

* Create modules within courses
* Update modules
* Delete modules
* Ordered sequencing support

## Lesson Management

* Create lessons within modules
* Update lessons
* Delete lessons
* Ordered sequencing support

## Lesson Content Management

Supported content types:

* VIDEO
* IMAGE
* PDF
* TEXT
* QUIZ

Features:

* Create lesson content
* Update lesson content
* Delete lesson content
* Fetch lesson content

## Enrollment Management

* Student enrollment
* Duplicate enrollment prevention
* Enrollment tracking

## Rating System

* Course ratings
* Rating aggregation
* Average rating calculation
* Total rating count maintenance

## Progress Tracking

Track completed lessons for enrolled students.

Features:

* Mark lesson as completed
* Course-level progress tracking
* Module-level progress tracking

Progress calculation includes:

* Total lessons
* Completed lessons
* Completion percentage

## File Storage

MinIO integration provides:

* Course thumbnail upload
* Lesson resource upload
* Object storage support
* Scalable file management

---

# Event-Driven Architecture

Kafka is used for asynchronous communication between services.

## Producers

* Auth-Service → user-created
* Enrollment-Service → enrollment-events
* Rating-Service → rating-created

## Consumers

* User-Service → user-created
* Notification-Service → user-created
* Notification-Service → enrollment-events
* Course-Service → rating-created

---

# Notification Features

Notification-Service handles asynchronous email delivery.

## Welcome Email

Triggered when:

```text
User Registration
        ↓
Auth-Service
        ↓
Kafka (user-created)
        ↓
Notification-Service
        ↓
Welcome Email
```

## Enrollment Email

Triggered when:

```text
Course Enrollment
        ↓
Enrollment-Service
        ↓
Kafka (enrollment-events)
        ↓
Notification-Service
        ↓
Enrollment Confirmation Email
```

---

# System Flow

## User Registration

```text
User
  ↓
Auth-Service
  ↓
Kafka (user-created)
  ├── User-Service
  └── Notification-Service
          ↓
      Welcome Email
```

## Course Enrollment

```text
Student
  ↓
Enrollment-Service
  ↓
Kafka (enrollment-events)
  ↓
Notification-Service
  ↓
Enrollment Confirmation Email
```

## Course Rating

```text
Student
  ↓
Rating-Service
  ↓
Kafka (rating-created)
  ↓
Course-Service
  ↓
Average Rating Updated
```

---

# Architecture Principles

* Database per service
* Service isolation
* Loose coupling through Kafka
* Eventual consistency
* API Gateway pattern
* Service discovery
* Centralized configuration
* Stateless authentication
* Event-driven communication

---

# Development Setup

## Prerequisites

* Java 17+
* Maven 3.8+
* MySQL
* Docker
* Apache Kafka
* MinIO

---

# Startup Order

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

# Sample APIs

## Authentication

```http
POST /skillverse/auth/login
```

## Create Course

```http
POST /skillverse/courses
```

## Upload File

```http
POST /skillverse/courses/files/upload
```

## Create Module

```http
POST /skillverse/courses/{courseId}/modules
```

## Create Lesson

```http
POST /skillverse/courses/modules/{moduleId}/lessons
```

## Add Lesson Content

```http
POST /skillverse/courses/lessons/{lessonId}/contents
```

## Mark Lesson Complete

```http
POST /skillverse/courses/progress/lessons/{lessonId}/complete
```

## Get Course Progress

```http
GET /skillverse/courses/progress/course/{courseId}
```

## Get Module Progress

```http
GET /skillverse/courses/progress/course/{courseId}/module/{moduleId}
```

## Rate Course

```http
POST /skillverse/ratings
```

---

# Future Enhancements

* Course certificates
* Quiz
* Kafka Retry and DLQ
* API rate limiting
* Angular frontend


---

# Author

Ashish Gadekar

SkillVerse – Microservices Learning Management System

Version: v2.0.0
