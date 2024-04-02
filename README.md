## Requirements
- Java 17
- PostgreSql
- Postman

## Setup
- Clone the repository
- Create local postgres database named store26
- Fill in the username and password fields in application.properties file with your postgres credentials
- Start the app. If no error is present you succesfully connected to the database.

## JWT Authentication/Authorization
- On application starting users table should be created automatically and one user inserted with role ADMIN.
- Access POST: http://localhost:8000/api/auth/register to register new users. In the request body provide firstName, lastName, email, password as JSON format. The user should be inserted in the table with role USER.
- Access POST: http://localhost:8000/api/auth/login to login an existing user. In the request body provide email, password as JSON format. On response you should get two tokens:standard and refresh token.
- Access POST: http://localhost:8000/api/auth/refresh to get new token. Copy the refresh token from the login response and send it in the request body with key token as JSON format. On response you should receive new standard token and the same refresh token.
- User with role ADMIN can access all endpoints starting with http://localhost:8000/api/admin by copying the token from the login response and putting it in the Authorization part of the request with type Bearer Token.
- User with role USER can access all endpoints starting with http://localhost:8000/api/user by copying the token from the login response and putting it in the Authorization part of the request with type Bearer Token.
- Only permitted endpoints for non logged in users are the ones starting with /api/auth.
- Test the endpoints using Postman.
  
