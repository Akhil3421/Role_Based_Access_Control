# Role Based Access Control

This is a Spring Boot-based application that implements Role-Based Access Control (RBAC) using JWT authentication. The application uses different roles such as ADMIN, MODERATOR, and USER to control access to different endpoints. It demonstrates how to secure API endpoints based on the user's role using JWT tokens.

## Features

- **JWT Authentication**: Secure endpoints using JWT tokens.
- **Role-based Access Control**: Secure methods based on roles (ADMIN, MODERATOR, USER).
- **Custom Filter**: A custom filter to authenticate users with JWT tokens and manage their session.
- **Database**: Users are stored in an PostgreSQL database, which includes basic role assignments.
- **Spring Security**: Used for managing authentication and authorization.
- **Custom User Details Service**: Implements user retrieval logic from the database.
- **BCrypt Password Encoding**: Passwords are securely hashed using BCrypt.

## Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- Spring Data JPA
- PostgreSQL
- BCrypt Password Encoder

## Prerequisites

Before running the application, make sure you have the following installed:

- Java 17 or later
- Maven or Gradle for dependency management
- IDE (e.g., IntelliJ IDEA, VSCode, Eclipse)

## Setup Instructions

### Clone the Repository

git clone https://github.com/Akhil3421/Role_Based_Access_Control.git

## Run the Application
You can run the application by executing the following command:

```
mvn spring-boot:run
```
Or if you're using an IDE, you can run the main application class RbacApplication.java.

## Access the Application
Once the application is up and running, you can access the APIs at:

url
```http://localhost:8080/```
## API Endpoints
### User Registration
POST /register

Registers a new user. Requires a username, password, and role (optional, defaults to USER).

Request body:

```
{
  "username": "john",
  "password": "password123",
  "role": "USER"
}
```
### User Authentication (Login)
POST /login

Authenticates a user and returns a JWT token. Requires a username and password.

Request body:

```
{
  "username": "john",
  "password": "password123"
}
```
Response:

```
{
  "token": "your-jwt-token"
}
```
### Protected Endpoints (Role-based Access)
Get All Students (ADMIN)
GET
```
localhost:8080/students
```

Accessible only to users with the ADMIN role. Returns the list of all students.

Get Student Marks (MODERATOR, ADMIN)
GET 
```
localhost:8080/students/marks
```

Accessible only to users with the MODERATOR or ADMIN role. Returns student marks (name + marks).

Get Student Data (USER, MODERATOR, ADMIN)
GET 
```
localhost:8080/students/names
```
Accessible to USER, MODERATOR, and ADMIN. Returns basic student information.

### Example of Access Control in Action
ADMIN can access all data: /admin/*, /moderator/*, /user/*.

MODERATOR can access: /moderator/*, /user/*.

USER can only access /user/*.


## Configuration
The SecurityConfig Class
Configures Spring Security, JWT token handling, and the role-based access control.

JWT Service Configuration
The JWTService class generates JWT tokens for authenticated users and validates them when making requests.

The JwtFilter class is responsible for intercepting HTTP requests and authenticating users based on the JWT token.

Custom UserDetailsService
The MyUserDetailService loads user details from the database and provides them to Spring Security for authentication.

The UserService handles user registration and authentication logic.

## Security
BCrypt Password Encoding: All user passwords are stored securely using BCrypt hashing.

Session Management: Stateless sessions are used with JWT tokens, ensuring that no server-side session is maintained.

CSRF Protection: CSRF is disabled as the application is designed to work with stateless JWT-based authentication.

Database Configuration
Currently, the application uses an PostgreSQL database for storing users and roles.

