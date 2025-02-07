# Bank Backend Application

This repository contains the backend code for the Bank Backend Application, a secure and robust system built using Spring Boot and Spring Security. The frontend for this application is developed using AngularJS and can be found [here](https://github.com/AlNomanCSE/spring-bank-security-fronten-angularjs.git).

## Description

The Bank Backend Application provides authenticated access to various banking services and public information endpoints. This project demonstrates the implementation of role-based access control, secure authentication mechanisms, and RESTful API endpoints for banking operations.

## Key Features

- **Secure Authentication:** Implements form-based and HTTP Basic authentication.
- **Role-Based Access Control:** Protects endpoints for account details, cards, loans, and balance based on user roles.
- **Public Endpoints:** Provides public access to notices and contact information.
- **RESTful API:** Offers endpoints for various banking operations.
- **Environment-Based Configuration:** Supports configuration through environment variables.
- **Detailed Security Logging:** Configurable logging for security-related events.

## Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Security**
- **Spring Web**
- **Maven**
- **MySQL**

## Configuration

### Project Dependencies
<!-- Core Dependencies -->
spring-boot-starter-security
spring-boot-starter-web
spring-boot-devtools

<!-- Testing Dependencies -->
spring-boot-starter-test
spring-security-test 

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

- **Configuration:** Contains security and application configuration classes.
- **Controllers:** Houses REST controllers for handling HTTP requests.
- **Models:** Defines entity classes representing the data model.
- **Repositories:** Provides data access methods using Spring Data JPA.

## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL

### Installation

1. **Clone the Repository:**
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