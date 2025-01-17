# Bank Backend Application

A secure banking application backend built with Spring Boot and Spring Security, providing authenticated access to banking services and public information endpoints.


## Related Repositories

- Bank Frontend Application: [Bank Frontend Angular]()


## Features

- Secure authentication for banking services
- Public access to notices and contact information
- Role-based access control
- RESTful API endpoints for various banking operations
- Environment variable based configuration
- Detailed security logging

## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Web
- Maven

## Security Features

- Protected endpoints for account details, cards, loans, and balance
- Public endpoints for notices and contact information
- Form-based authentication
- HTTP Basic authentication support
- Configurable security credentials through environment variables

## Configuration

### Project Dependencies
<!-- Core Dependencies -->
spring-boot-starter-security
spring-boot-starter-web
spring-boot-devtools

<!-- Testing Dependencies -->
spring-boot-starter-test
spring-security-test 
 ```

### Application Properties

```properties
spring.application.name=${SPRING_APP_NAME:springsecsection1}
logging.level.org.springframework.security=${SPRING_SECURITY_LOG_LEVEL:TRACE}
logging.pattern.console=${LOGPATTERN_CONSOLE:%green(%d{HH:mm:ss.SSS}) %clr(%-5level){ERROR=magenta,INFO=blue} %red([%thread]) %yellow(%logger{15}) - %msg%n}
```

### Environment Variables

The application supports the following environment variables:
- `SPRING_APP_NAME`: Application name 
- `SPRING_SECURITY_LOG_LEVEL`: Security log level (default: TRACE)
- `LOGPATTERN_CONSOLE`: Console logging pattern with color coding


## Project Structure

```
com.noman.BankBackendApplication
├── config
│   └── ProjectSecurityConfig.java
├── controller
│   ├── AccountController.java
│   ├── BalanceController.java
│   ├── CardsController.java
│   ├── ContactController.java
│   ├── LoansController.java
│   └── NoticesController.java
└── BankBackendApplication.java
```

## Package Overview for Bank Backend Application

1. **config**:
   - Contains configuration classes for setting up security, application properties, and other configurations necessary for the application to run.

2. **controller**:
   - Houses the REST controllers that handle incoming HTTP requests and return responses. This includes endpoints for customer registration, account management, loans, and more.

3. **events**:
   - Likely contains classes related to event handling within the application, such as publishing and listening to application events.

4. **exceptionhandling**:
   - Contains classes for handling exceptions throughout the application, providing a centralized way to manage errors and return appropriate responses to clients.

5. **filter**:
   - This package likely includes filters that can intercept requests and responses, allowing for functionalities like logging, authentication, or modifying requests.

6. **model**:
   - Contains the entity classes that represent the data model of the application, such as Loans, Contact, Notice, etc. These classes are typically annotated with JPA annotations to map them to database tables.

7. **repository**:
   - Houses the repository interfaces that provide methods for data access, typically extending Spring Data JPA's JpaRepository or similar interfaces for CRUD operations.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- IntelliJ IDEA

### Installation

1. Clone the repository
```bash
git clone <repository-url>
```

2. Navigate to project directory
```bash
cd bank-backend-application
```

3. Set environment variables (optional - defaults will be used if not set)
```bash
export SPRING_APP_NAME=yourappname
export SECURITY_USERNAME=yourusername
export SECURITY_PASSWORD=yourpassword
export SPRING_SECURITY_LOG_LEVEL=DEBUG
```

4. Build the project
```bash
mvn clean install
```

5. Run the application
```bash
mvn spring-boot:run
```

## API Endpoints

### Protected Endpoints (Requires Authentication)
- `/myAccount` - Get account details
- `/myCards` - Get cards information
- `/myBalance` - Check balance
### Summary
This README file provides a comprehensive overview of your Bank Backend Application, including its features, technologies, configuration, package structure, and instructions for getting started. 

If you need any further adjustments or additional sections, just let me know!
- `/myLoans` - Get loans information

### Public Endpoints
- `/notices` - Get public notices
- `/contact` - Contact information

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details