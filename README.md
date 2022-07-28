# Spring Boot Resilience4j Example
This is a sample Spring Boot application that demonstrates the Rate Limiter functionality of `Resilience4j` library.

### How to Run
Start the application with the command `mvn spring-boot:run` and test the following endpoints for rate-limiting:

* http://localhost:8080/messageA - 1 req / 5 sec
* http://localhost:8080/messageB - 2 req / 5 sec
* http://localhost:8080/messageC - 3 req / 5 sec

