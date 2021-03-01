# iv1201-application
This is a recruitment application created for the course IV1201 at KTH.
It is deployed on Heroku: https://iv1201-application.herokuapp.com

## Tools
The following software development tools are used.
* Version control (Git)
* Continous integration, CI (Git Actions)
* Project management (Maven)
* Test (Spring MVC Test Framework))
* Cloud runtime (Heroku)

## Frameworks
* Java Servlets
* Spring core technologies, in particular the IoC container
* Spring Boot
* Spring Web MVC
* Thymeleaf
* Spring Data (Commons and JPA) and hibernate

## Help
Below follows instructions on how to perform particular tasks.

### To run with Heroku Postgres
 #### Prerequisites
 Java 11 Maven 3.8.1 PostgreSql installed locally

1. Install the Heroku CLI according to the instructions here: https://devcenter.heroku.com/articles/heroku-cli
 
2. Create an heroku app by running the command: heroku create <your_app_name>. This will automatically add a git remote named "heroku" pointing at this URL: https://git.heroku.com/your_app_name.git
 
3. Set the application.properties:

spring.datasource.url= {SPRING_DATASOURCE_URL}?user={SPRING_DATASOURCE_USERNAME}&password={SPRING_DATASOURCE_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.hbm2ddl.auto=create-drop
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.generate-ddl=true
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.platform=postgres

The datasource url, username and password can be found on your applications Heroku Postgres instance under settings -> Database credentials 

4. Set the config variables on Heroku (same as in application properties):
SPRING_DATASOURCE_URL = your_url
SPRING_DATASOURCE_USERNAME = your_username
SPRING_DATASOURCE_PASSWORD = your_password

5. To interact with the existing database run *heroku pg:psql --app heroku_app_name* 

6. Run the application locally using the command *mvn spring-boot:run* 

7. Create an account through a Firefox, Chrome or Edge browser at *http://localhost:8080/create-account* or login at *http://localhost:8080/login?*
