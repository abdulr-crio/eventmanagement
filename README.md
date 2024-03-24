# EVENTS API

## Description

A RESTful event management service developed using Spring Boot and MongoDB. It provides a platform for users to manage and organize events efficiently. The service offers functionalities for user authentication using JWT tokens, role-based authentication, and email notifications on successful registration.

## Endpoints

### Authentication

- **POST /register**: Registers a new user.
- **POST /login**: Authenticates an existing user.

### Events

- **GET /events**: Retrieves all events.
- **POST /events**: Adds a new event.
- **PUT /events/{eventId}**: Updates an existing event.
- **DELETE /events/{eventId}**: Deletes an event.
- **POST /events/{eventId}/register**: Registers for an event. (Accessible by users with the role "USER")
- **DELETE /events/{eventId}/register**: Deregisters from an event. (Accessible by users with the role "USER")

### User

- **GET /registrations**: Retrieves all events registered by the user.

## Technologies Used

- Spring Boot
- MongoDB
- JSON Web Tokens (JWT)
- JavaMail API (for sending emails)

## Dependencies

- **Spring Boot Starter Data MongoDB**: `org.springframework.boot:spring-boot-starter-data-mongodb`
- **Spring Boot Starter Security**: `org.springframework.boot:spring-boot-starter-security`
- **Spring Boot Starter Web**: `org.springframework.boot:spring-boot-starter-web`
- **Spring Boot Starter Mail**: `org.springframework.boot:spring-boot-starter-mail`
- **JWT API**: `io.jsonwebtoken:jjwt-api:0.11.5`
- **JWT Implementation**: `io.jsonwebtoken:jjwt-impl:0.11.5`
- **JWT Jackson**: `io.jsonwebtoken:jjwt-jackson:0.11.5`
- **Spring Boot Starter Validation**: `org.springframework.boot:spring-boot-starter-validation`
- **Lombok**: `org.projectlombok:lombok`

## Setup Instructions

1. Clone the repository.
2. Configure MongoDB connection settings in application.properties.
3. Add Google application password and username for sending emails in application.properties.
4. Build and run the project using the following command:
```./gradlew bootrun```

## Usage

1. Register a new user using the `/register` endpoint to obtain a JWT token.   
OR Login using the `/login` endpoint to obtain a JWT token.
2. Use the obtained token to access protected endpoints.
3. Perform event-related operations using the `/events` endpoints.
4. View registered events using the `/registrations` endpoint.

## Postman Collections
[Event Management API - Postman Collection](https://www.postman.com/science-geologist-14754374/workspace/dev)
