# ChronicTrack

ChronicTrack is a backend application developed using **Spring Boot**. Its main goal is to assist in the tracking and management of chronic diseases, allowing data to be securely stored and processed.

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

## Features

- Developed with Java and Spring Boot.
- Connects to a **MongoDB Atlas** database.
- Email support via Gmail SMTP.
- Follows the MVC (Model-View-Controller) architecture.
- Easily deployable and scalable.
- API documentation available via Swagger.

## Requirements

- Java 17 or later.
- Maven.
- MongoDB Atlas account.
- IDE (e.g., IntelliJ IDEA, Eclipse, VS Code).

## Installation

1. **Clone the repository**

```bash
git clone https://github.com/your-username/chronictrack.git
cd chronictrack
```

2. **Configure MongoDB Connection**

In the `src/main/resources/application.properties` file, set your MongoDB Atlas URI:

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster>.mongodb.net/chronictrack?retryWrites=true&w=majority
```

Replace `<username>`, `<password>`, and `<cluster>` with your MongoDB Atlas credentials.

3. **Configure Email (optional)**

Set your Gmail SMTP details in the same `application.properties`:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

> ⚠️ It's recommended to use environment variables or a secrets manager instead of hardcoding your credentials.

4. **Build and run**

```bash
./mvnw spring-boot:run
```

The application will run on `http://localhost:8080`.

## API Documentation

You can access the API documentation at:

```
http://localhost:8080/swagger-ui/index.html
```

Make sure Swagger is enabled in your Spring Boot configuration.

## Project Structure

```
chronictrack/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/chronictrack/
│   │   │       ├── controllers/
│   │   │       ├── models/
│   │   │       ├── repositories/
│   │   │       └── ChronicTrackApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
├── pom.xml
└── README.md
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.
