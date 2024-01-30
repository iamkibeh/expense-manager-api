# Expense Manager API

<img src="https://play-lh.googleusercontent.com/nn3jXKaItb-nryaw47o1-6diQ1OckdBnI_TO8MJB4GpMRB6pkmi1SjsVDihqpWqeC9xa" width=50 alt="Project Logo">

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
    - [Authentication](#authentication)
    - [User Profile](#user-profile)
    - [Expense Operations](#expense-operations)
- [Example Requests](#example-requests)
- [Contributing](#contributing)
- [License](#license)

## Introduction

Welcome to the Expense Manager API! This is a simple REST API created using Spring Boot and PostgreSQL. It provides a robust platform for managing expenses, allowing users to authenticate, manage profiles, and perform CRUD operations on expenses. It is designed to be user-friendly and highly customizable.

## Features

- Secure User Authentication
- User Profile Management
- Effortless CRUD Operations for Expenses
- Smart Expense Retrieval Based on Logged-In User:


## Getting Started

### Prerequisites

- [Java](https://www.java.com/) (version 17.0.0)
- [Maven](https://maven.apache.org/) (version 4.0.0)
- [Database](link/to/your/database) (e.g., PostgreSQL, MySQL)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/iamkibeh/expense-manager-api.git
   ```
2. Navigate to the project directory
    ```bash
    cd expense-manager-api
     ```
3. Build the project using Maven
    
    ```bash
    mvn clean install
    ```

### Configuration

- Update `application.properties` with your database and application configuration.
- Customize other configuration files as needed.

## Explore REST APIs endpoints

### User Authentication

| Method  | URL | Description | Return               |
| ------- | --- | ----------- |----------------------|
|POST | api/v1/auth/register | User registration | User object          |
 |POST| api/v1/auth/login | User login | JSON Web Token (jwt) | 
 

### Expenses

| Method | URL                                          | Description                        | Return                         |
|--------|----------------------------------------------|------------------------------------|--------------------------------|
| GET    | api/v1/expenses                              | Get all user expenses              | Array of JSON objects          |
|GET| api/v1/expenses/{id}                         | Get a specific user expenses by id | Single JSON objects            |
|GET| api/v1/expenses/name?name={name}             | Filter expenses by name            | Array of filtered JSON objects |
|GET| api/v1/expenses/category?category={category} | Filter expenses by name            | Array of filtered JSON objects |
| POST   | api/v1/expenses                              | Create a user expenses             | Created JSON objects           |
| PUT    | api/v1/expenses/{id}                         | Update a user expenses             | Updated JSON objects           |
| DELETE | api/v1/expenses/{id}                         | Delete a user expenses             | No content                     |


> ** _NOTE:_ **
  The endpoints of `Expenses` are restricted. To access these endpoints, use the token generated after logging in as the value of Bearer in the Authorization header as follows:
  **"Authorization: Bearer Token_id"**
> 
> You can also retrieve expenses with optional pagination and sorting.
- **Parameters:**
    - `page` (optional): Page number for pagination. Defaults to 0.
    - `size` (optional): Number of items per page. Defaults to a configured value.
    - `sort` (optional): Sorting order. Example: `sort=id,desc`.

    - **Example Request:**
      ```http
      GET /api/v1/expenses?page=0&size=3&sort=id,desc
      ```
        - **Example Response:**
    ```json
    {
      "content": [
         {
            "id": 2,
            "name": "Food",
            "description": "this is expense 2",
            "amount": 200.00,
            "category": "expense",
            "date": "2024-01-29",
            "createdAt": "2024-01-30T05:03:23.710+00:00",
            "updatedAt": "2024-01-30T05:03:23.710+00:00"
        }
        // Additional expenses...
      ],
      "pageable": {
        "pageNumber": 0,
        "pageSize": 3,
        "sort": {
          "sorted": true,
          "unsorted": false,
          "empty": false
        }
      },
      "totalElements": 10,
      "totalPages": 4,
      "last": false,
      "first": true,
      "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
      },
      "numberOfElements": 3,
      "size": 3,
      "number": 0,
      "empty": false
    }
    ```
  
 ## Sample of Request Body

### User - Registration
```bash
    POST api/v1/auth/register
    Content-Type: application/json
   {
    "email": "test@gmail.com",
    "password": "12345678",
    "name": "liam"
    }
```

### User - Login
```bash
    POST api/v1/auth/login
    Content-Type: application/json
   {
    "email": "test@gmail.com",
    "password": "12345678",
    }
```

### User - Profile
```bash
  GET api/v1/profile
  Authorization: Bearer your_auth_token
```

### Expenses
```bash
  POST api/v1/expenses
  Authorization: Bearer your_auth_token
  Content-Type: application/json
  {
    "name": "Expense 1",
    "description": "this is my test expense",
    "amount": 18000,
    "category": "expense",
    "date": "2023-10-30"
  }
```


## Contributing
If you'd like to contribute, please fork the repository and create a new branch. Pull requests are welcome!

## License
This project is licensed under the MIT License.


