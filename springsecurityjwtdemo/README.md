# Spring Security Demo Application with JWT

This project is a Spring Boot application demonstrating advanced Spring Security concepts, specifically focusing on **JWT (JSON Web Token)** based authentication and authorization. It provides RESTful APIs for managing `Alien` and `Users` entities, secured by a stateless authentication mechanism.

## Table of Contents
1.  [Project Overview](#project-overview)
2.  [Features](#features)
3.  [Key Components and Their Working](#key-components-and-their-working)
    *   [Entities (Model)](#entities-model)
    *   [Repositories](#repositories)
    *   [Services](#services)
    *   [Controllers](#controllers)
    *   [Security Configuration](#security-configuration)
    *   [JWT Implementation](#jwt-implementation)
4.  [Workflow](#workflow)
    *   [Application Startup](#application-startup)
    *   [Registration & Login (Getting the Token)](#registration--login-getting-the-token)
    *   [Accessing Protected Resources](#accessing-protected-resources)
5.  [How to Run](#how-to-run)
6.  [Testing with Postman](#testing-with-postman)

---

## Project Overview

This application showcases how to secure a Spring Boot REST API using JWTs. Unlike traditional session-based authentication, this project uses a stateless approach where the server does not keep track of user sessions. Instead, a signed token is issued upon login, which the client must present to access protected resources.

## Features

*   **JWT Authentication**: Secure, stateless authentication using JSON Web Tokens.
*   **Role-Based Authorization**: Fine-grained access control for `USER` and `ADMIN` roles.
*   **Public Endpoints**: Open registration and login endpoints.
*   **Hybrid Password Handling**: Supports both plain text (legacy/initial data) and BCrypt hashed passwords.
*   **H2 Database**: In-memory database for easy setup and testing.
*   **CRUD Operations**: Manage `Alien` and `Users` entities.

## Key Components and Their Working

### Entities (Model)

*   **`Alien.java`**: Represents an alien entity with `id`, `name`, and `tech`.
*   **`Users.java`**: Represents a user with `id`, `username`, `password`, and `role`.
*   **`UserPrincipal.java`**: Implements `UserDetails`. It adapts the `Users` entity for Spring Security, handling role prefixing (adding "ROLE_") and authority mapping.

### Repositories

*   **`AlienRepository.java`**: Standard JPA repository for `Alien`.
*   **`UsersRepository.java`**: JPA repository for `Users`, including `findByUsername` for authentication.

### Services

*   **`AlienService.java`**: Business logic for `Alien` management.
*   **`UserService.java`**: Business logic for `Users`. Handles password encoding (BCrypt) when creating or updating users.
*   **`MyUserDetailsService.java`**: Loads user data from the database during the authentication process.
*   **`JWTTokenService.java`**: **(New)** Handles all JWT operations:
    *   **Generating Tokens**: Creates a signed JWT containing the username, issuance time, and expiration time (30 minutes).
    *   **Validating Tokens**: Verifies the token's signature and checks if it has expired.
    *   **Extracting Claims**: Retrieves the username and other data from the token.
    *   **Secret Key**: Uses a generated HMAC-SHA256 key for signing.

### Controllers

*   **`AlienController.java`**: Protected endpoints for `Alien` resources.
*   **`UserController.java`**:
    *   `/users/login`: **Public** endpoint to authenticate credentials and receive a JWT.
    *   `/users/addUser`: **Public** endpoint to register a new user.
    *   Other endpoints are protected based on roles.

### Security Configuration

*   **`SecurityConfig.java`**:
    *   **`SecurityFilterChain`**:
        *   Disables CSRF.
        *   Sets session policy to `STATELESS`.
        *   **Public Access**: Allows unauthenticated access to `/users/login`, `/users/addUser`, and `/h2-console/**`.
        *   **Protected Access**:
            *   `GET` requests: `ADMIN` or `USER`.
            *   `POST`, `PUT`, `DELETE` requests: `ADMIN` only.
        *   **Filter**: Adds the custom `JwtFilter` *before* the standard `UsernamePasswordAuthenticationFilter`.
    *   **`AuthenticationManager`**: Exposed as a bean to handle authentication requests in the login controller.

### JWT Implementation

*   **`JwtFilter.java`**: A custom filter that executes once per request.
    1.  Intercepts HTTP requests.
    2.  Checks for the `Authorization` header starting with `Bearer `.
    3.  Extracts the JWT token.
    4.  Uses `JWTTokenService` to extract the username and validate the token.
    5.  If valid, it loads the user details and manually sets the `Authentication` object in the `SecurityContextHolder`.
    6.  This effectively logs the user in for that specific request.

## Workflow

### Application Startup

1.  Spring Boot starts.
2.  H2 database initializes with data from `data.sql` (users with plain text passwords).
3.  Security configuration loads, setting up the filter chain.

### Registration & Login (Getting the Token)

1.  **Registration**: A user sends a `POST` request to `/users/addUser` with their details. The server saves the user (hashing the password).
2.  **Login**:
    *   The user sends a `POST` request to `/users/login` with `username` and `password`.
    *   The `UserController` (via `AuthenticationManager`) authenticates the credentials.
    *   If successful, `JWTTokenService` generates a JWT string.
    *   The server returns this token to the client.

### Accessing Protected Resources

1.  The client wants to access a protected resource (e.g., `GET /aliens/allAliens`).
2.  The client adds the **Authorization** header to the request:
    `Authorization: Bearer <your_jwt_token_here>`
3.  **`JwtFilter`** intercepts the request.
4.  It validates the token.
5.  If valid, it sets the user's authentication in the security context.
6.  Spring Security then checks if the user has the required role (e.g., `ROLE_USER` or `ROLE_ADMIN`).
7.  If authorized, the request reaches the controller, and the response is returned.

## How to Run

### Prerequisites
*   Java 17 or higher
*   Maven

### Build and Run
1.  Clone the repository.
2.  Build:
    ```bash
    mvn clean install
    ```
3.  Run:
    ```bash
    mvn spring-boot:run
    ```

## Testing with Postman

### 1. Login (Get Token)
*   **URL**: `http://localhost:8080/users/login`
*   **Method**: `POST`
*   **Body** (JSON):
    ```json
    {
        "username": "dinesh",
        "password": "1234@"
    }
    ```
*   **Response**: You will receive a long string. This is your **JWT Token**. Copy it.

### 2. Access Protected API
*   **URL**: `http://localhost:8080/aliens/allAliens`
*   **Method**: `GET`
*   **Headers**:
    *   Key: `Authorization`
    *   Value: `Bearer <paste_your_token_here>`
*   **Response**: List of aliens (if the token is valid).

### 3. Add New User (Register)
*   **URL**: `http://localhost:8080/users/addUser`
*   **Method**: `POST`
*   **Body** (JSON):
    ```json
    {
        "username": "devil",
        "password": "devil",
        "role": "ADMIN"
    }
    ```
*   **Note**: You can now login with `devil`/`devil` to get a new token with ADMIN privileges.
