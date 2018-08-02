# springboot-jwt
SpringBoot Authentication with JWT
This is sample using the Spring Boot with JWT and Spring Security combinedly.

1. Pull Project
2. Run in Netbean (Or something IDE else)
3. Default port is 4257
4. Login with path http://localhost:4257/login . Method POST with body
```
{ 
  "username" : "admin",
  "password" : "123456"
}
```
Respone Example:
```
{
    "code": "00",
    "message": "Login success",
    "data": {
        "username": "admin",
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aWVubWFuaCIsInJvbGUiOiJBRE1JTiIsImV4cCI6MTUzMzE5ODExMH0.NpTAE67IAoKGV1ZwBaDTAjdK2OuD2tegRVwZ7w4dyVw",
        "expiration": 30
    }
}
```
5. Using the token in respone to add into header with Bearer type. I like using the Postman.

![main](https://raw.githubusercontent.com/manhtt23091992/springboot-jwt/master/postman_spring_jwt.PNG)

#Note
You can custom by yourself.
