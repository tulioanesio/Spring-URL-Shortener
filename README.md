# Spring URL Shortener - Serverless AWS Edition

![Java](https://img.shields.io/badge/Java-21-blue) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green) ![AWS Lambda](https://img.shields.io/badge/AWS-Lambda-orange) ![API Gateway](https://img.shields.io/badge/AWS-API_Gateway-purple) ![DynamoDB](https://img.shields.io/badge/AWS-DynamoDB-blueviolet) ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D)

A 100% serverless, high-performance URL shortening service built with Spring Boot and deployed on the AWS ecosystem. This application leverages AWS Lambda for compute, API Gateway for request handling, and DynamoDB for scalable, low-latency data storage.

## Technology Stack

-   Java 21
-   Spring Boot 3
-   AWS DynamoDB
-   AWS Lambda
-   AWS API Gateway
-   Swagger

## Running the Application

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/tulioanesio/Spring-URL-Shortener.git
    cd Spring-URL-Shortener
    ```
    
2.  **Configure your environment:**
    Create `application.properties` based on the application.properties.example.

3.  **Run the application:**
    The application will be available at `http://localhost:8080`.

## API Documentation

The API is documented using Swagger. Once the application is running, you can access the interactive UI to explore and test the endpoints.

-   **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/tulioanesio/Spring-URL-Shortener/blob/master/LICENSE) for more information.
