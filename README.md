# Spring MVC Web Application

A Spring Boot web application developed as part of my Web Services coursework at HVL. The project demonstrates server-side web development using Spring MVC, Thymeleaf, JPA and Spring Security. It uses login, with authentication, authorization for user and admin access, and security for network and hosting configurations. Sorry but im not giving you the ip or port number :)

<img width="542" height="581" alt="image" src="https://github.com/user-attachments/assets/bbd32119-b9ad-4071-868f-bbf1e62aaf5d" />


## Technologies

- Java 17
- Spring Boot
- Spring MVC
- Thymeleaf
- Spring Data JPA
- Spring Security
- H2 Database
- Maven

## Features

- Server-side rendered web application with Thymeleaf
- MVC architecture using Spring Boot
- Database integration with JPA and H2
- Authentication and authorization with Spring Security
- Unit testing with Spring Boot Test
- Maven-based build system

## CI/CD

The project includes a GitHub Actions CI/CD pipeline that automatically:

- Compiles the application
- Runs unit tests
- Builds a production JAR
- Deploys the application to an NREC Ubuntu server via SSH
- Restarts the deployed Spring Boot application

## Deployment

The application is deployed to a Linux virtual machine hosted on NREC. GitHub Actions handles the deployment process automatically whenever changes are pushed to the `main` branch.

## Local Development

Clone the repository and run the application using the Maven Wrapper:

```bash
# For mac
./mvnw spring-boot:run

# For windows
mvnw.cmd spring-boot:run
```

The application runs on:
http://localhost:8090

