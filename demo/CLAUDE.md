# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Run Commands

```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run tests
./gradlew test

# Run a specific test class
./gradlew test --tests "com.example.demo.DemoApplicationTests"
```

## Project Architecture

This is a Spring Boot 3.1.5 study project demonstrating various Spring ecosystem features:

### Core Stack
- Java 21 with Spring Boot 3.1.5
- Spring Web (MVC), Spring WebFlux (Reactive)
- Spring Data JPA (MySQL), Spring Data MongoDB, Spring Data Elasticsearch
- Spring Data Redis (Session management)
- Spring Security with custom JWT and Session-based authentication
- OAuth2 Client (Google, Naver login)
- WebSocket for real-time chat
- AOP for logging
- Thymeleaf for server-side rendering

### Key Packages
- `com.example.demo.home` - Home page controller
- `com.example.demo.user` - User management (signup, login)
- `com.example.demo.jwt` - JWT authentication filters
- `com.example.demo.mongodb` - MongoDB CRUD operations
- `com.example.demo.mySQL` - MySQL CRUD with transaction support
- `com.example.demo.oauth` - OAuth2 service implementations
- `com.example.demo.redis` - Redis session/token management
- `com.example.demo.webSocket` - STOMP WebSocket chat
- `com.example.demo.filter` - Custom servlet filters
- `com.example.demo.interceptor` - Spring MVC interceptors
- `com.example.demo.AOP` - Aspect-oriented programming aspects
- `com.example.demo.WebFlux` - Reactive programming examples
- `com.example.demo.async` - Async method execution
- `com.example.demo.imageHandling` - File/image upload to database

### Configuration Highlights
- Security configured in `SecurityConfig.java` with custom DSLs for JWT and session authentication
- CORS enabled for cross-origin requests
- MySQL and MongoDB configurations present for multi-database setup
- Log4j2 for logging (excludes default Spring Boot logging)
- DevTools enabled for development
