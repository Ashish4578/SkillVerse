# SkillVerse
An online platform where users can register, browse courses, enroll, and leave ratings/reviews.
SkillVerse Architecture (with Authentication Server)

Service | Responsibilities | DB & Tech
--- | --- | ---
Gateway-Service | Routes API calls, validates OAuth2/JWT tokens | — / Spring Cloud Gateway
Auth-Service | Handles OAuth2 login, token issuing, refresh tokens (Authorization Server) | MySQL / Spring Authorization Server
User-Service | Stores user profiles, registration, roles (acts as resource owner’s data) | MySQL / Spring Boot, JPA
Course-Service | Manages courses, instructors | MySQL / Spring Boot
Enrollment-Service | Manages enrollments (User ↔ Course) | MySQL / Spring Boot
Rating-Service | Handles ratings/reviews | MySQL / Spring Boot
Config-Service | Centralized config (via Git) | Git / Spring Cloud Config
Eureka-Service | Service discovery | — / Spring Cloud Netflix Eureka
