# Task Management Application

## Overview
This project is a basic Task Management Application that allows users and admins to manage tasks. The application consists of a frontend built with React and Vite, a backend API developed in Java, and a MySQL database.

## Architecture
The application architecture is divided into three main components:

1. **Frontend**
   - **Framework**: React with Vite
   - **Hosting**: AWS Amplify
   - **Features**: 
     - User authentication (login/register)
     - Task creation, update, and deletion
     - Task completion status management (complete/incomplete)

2. **Backend**
   - **Language**: Java
   - **Framework**: Spring Boot
   - **Hosting**: Google Cloud
   - **API Endpoints**:
     - User Authentication: `/api/auth/login`, `/api/auth/register`
     - Task Management: `/api/tasks`, `/api/tasks/{id}`, `/api/tasks/{id}/complete`, `/api/tasks/{id}/incomplete`

3. **Database**
   - **Type**: MySQL
   - **Hosting**: AWS RDS

## Technologies Used

### Frontend
- **React**: A JavaScript library for building user interfaces, enabling the creation of reusable UI components.
- **Vite**: A fast build tool that provides a development environment with hot module replacement and optimized production builds.
- **Axios**: A promise-based HTTP client for making API requests to the backend.
- **React Router**: A library for handling routing and navigation within the React application.
- **Bootstrap**: A CSS framework for creating responsive and visually appealing designs.

### Backend
- **Java**: The programming language used for developing the backend API.
- **Spring Boot**: A framework that simplifies the process of building and deploying Java applications, particularly web applications.
- **Spring Data JPA**: A part of the Spring framework that simplifies database interactions by providing a standard way to manage data.
- **Spring Web**: A module that provides the functionality to create web applications, including RESTful APIs.
- **Spring Security**: A powerful framework for securing Java applications, providing authentication and authorization features.
- **ModelMapper**: A library that simplifies the mapping of objects in Java applications.
- **MySQL**: A relational database management system (RDBMS) used to store user and task data.
- **JJWT (Java JWT)**: A library for creating and verifying JSON Web Tokens (JWT) to handle authentication.
- **JPA (Java Persistence API)**: An API for managing relational data in Java applications, providing an object-relational mapping (ORM) solution.

### Database
- **AWS RDS**: Amazon Web Services Relational Database Service, used to host the MySQL database securely and reliably.

## Accessing the Application

The frontend of the Task Management Application can be accessed temporarily at the following URL:

- [Task Management Application](https://master.d281cg07p0wk8v.amplifyapp.com/)

Feel free to explore the application and its features while it's available. Note that this link is temporary and may change in the future.

## API Documentation

### User Authentication
- **POST** `/api/auth/login`
  - **Description**: Authenticates a user and returns a JWT token along with user information.
  - **Request Body**:
    ```json
    {
      "usernameOrEmail": "actual_username_or_email", // The username or email registered by the user
      "password": "your_password"
    }
    ```
  - **Response**:
    - **200 OK**: Returns a JWT token and user details in the following format:
    ```json
    {
      "accessToken": "your_jwt_token",
      "tokenType": "Bearer",
      "role": "ROLE_USER",  // This will depend on the user's role (e.g., ROLE_ADMIN, ROLE_USER)
      "username": "actual_username" // The username of the authenticated user
    }
    ```
    - **401 Unauthorized**: Invalid credentials.

- **POST** `/api/auth/register`
  - **Description**: Registers a new user.
  - **Request Body**:
    ```json
    {
      "name": "New User",                // The full name of the user
      "username": "new_user",            // The desired username
      "email": "new_user@example.com",   // The user's email address
      "password": "your_password"        // The desired password
    }
    ```
  - **Response**:
    - **201 Created**: User registered successfully with a confirmation message.
    - **400 Bad Request**: Validation errors or username/email already exists.

### Task Management
- **POST** `/api/tasks`
  - **Description**: Adds a new task. The user must be an admin to create a task.
  - **Request Body**:
    ```json
    {
      "title": "Task Title",                // The title of the task
      "description": "Task Description",     // A brief description of the task
      "completed": false                     // Indicates whether the task is completed (default: false)
    }
    ```
  - **Response**:
    - **201 Created**: Returns the created task details.
    ```json
    {
      "id": 1,                               // The auto-generated ID of the new task
      "title": "Task Title",
      "description": "Task Description",
      "completed": false
    }
    ```
    - **403 Forbidden**: The user does not have permission to create a task.

- **GET** `/api/tasks/{id}`
  - **Description**: Retrieves a specific task by ID. Accessible by both users and admins.
  - **Response**:
    - **200 OK**: Returns the task details.
    ```json
    {
      "id": 1,                               // The ID of the task
      "title": "Task Title",
      "description": "Task Description",
      "completed": false
    }
    ```
    - **404 Not Found**: The task with the specified ID does not exist.

- **PUT** `/api/tasks/{id}`
  - **Description**: Updates an existing task by ID. The user must be an admin to update a task.
  - **Request Body**:
    ```json
    {
      "title": "Updated Task Title",         // The updated title of the task
      "description": "Updated Task Description", // The updated description of the task
      "completed": true                       // Updated completion status of the task
    }
    ```
  - **Response**:
    - **200 OK**: Returns the updated task details.
    ```json
    {
      "id": 1,                               // The ID of the updated task
      "title": "Updated Task Title",
      "description": "Updated Task Description",
      "completed": true
    }
    ```
    - **403 Forbidden**: The user does not have permission to update the task.
    - **404 Not Found**: The task with the specified ID does not exist.

- **DELETE** `/api/tasks/{id}`
  - **Description**: Deletes a task by ID. The user must be an admin to delete a task.
  - **Response**:
    - **200 OK**: Confirmation message that the task was deleted successfully.
    ```json
    {
      "message": "Task deleted successfully!"
    }
    ```
    - **403 Forbidden**: The user does not have permission to delete the task.
    - **404 Not Found**: The task with the specified ID does not exist.

- **PATCH** `/api/tasks/{id}/complete`
  - **Description**: Marks a task as completed by ID. Accessible by both users and admins. No body content is required.
  - **Response**:
    - **200 OK**: Returns the updated task details with completion status set to true.
    ```json
    {
      "id": 1,                               // The ID of the completed task
      "title": "Task Title",
      "description": "Task Description",
      "completed": true
    }
    ```

- **PATCH** `/api/tasks/{id}/incomplete`
  - **Description**: Marks a task as incomplete by ID. Accessible by both users and admins. No body content is required.
  - **Response**:
    - **200 OK**: Returns the updated task details with completion status set to false.
    ```json
    {
      "id": 1,                               // The ID of the incomplete task
      "title": "Task Title",
      "description": "Task Description",
      "completed": false
    }
    ```

