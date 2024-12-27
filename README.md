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
```
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