# iv1201-application
This is a recruitment application created for the course IV1201 at KTH.

## Tools
The following software development tools are used.
* Version control (Git)
* Project management (Maven)
* Test (Spring MVC Test Framework))
* Cloud runtime (Heroku)

## Frameworks
* Java Servlets
* Spring Boot
* Spring Web MVC
* Thymeleaf
* Spring Data (Commons and JPA) and hibernate

## Help
Below follows instructions on how to perform particular tasks.

###To run with Heroku Postgres

1. Set the application.properties:

spring.datasource.url= {SPRING_DATASOURCE_URL}?user={SPRING_DATASOURCE_USERNAME}&password={SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.platform=postgres

2. Set the config ariables on Heroku (same as in application properties):
SPRING_DATASOURCE_URL = your_url
SPRING_DATASOURCE_USERNAME = your_username
SPRING_DATASOURCE_PASSWORD = your_password

3. Start the PostgreSQL client



The application can be run using the command *mvn spring-boot:run* and it is deployed on Heroku: https://iv1201-application.herokuapp.com

