# iv1201-application
This is a recruitment application created for the course IV1201 at KTH.
It is deployed on Heroku: https://iv1201-application.herokuapp.com

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

### To run with Heroku Postgres
 #### Prerequisites
 Java 11 Maven 3.8.1 PostgreSql running instance
 
1. Set the application.properties:

spring.datasource.url= {SPRING_DATASOURCE_URL}?user={SPRING_DATASOURCE_USERNAME}&password={SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.generate-ddl=true
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.platform=postgres

2. Set the config ariables on Heroku (same as in application properties):
SPRING_DATASOURCE_URL = your_url
SPRING_DATASOURCE_USERNAME = your_username
SPRING_DATASOURCE_PASSWORD = your_password

3. Initialize schema and sample data using *schema-${spring.datasource.platform}.sql* and *data-${spring.datasource.platform}.sql*

4. Run the application locally using the command *mvn spring-boot:run* 

5. Create an account through a Firefox, Chrome or Edge browser at *http://localhost:8080/create-account* or login at *http://localhost:8080/login?*
