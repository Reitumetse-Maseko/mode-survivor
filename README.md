MODE-SURVIVOR

PREREQUISITES
Must have the following tools installed on your machine:
Java Development Kit (JDK)
Maven

CLONE THE REPOSITORY
git clone https://github.com/your-username/mode-survivor-api.git
cd mode-survivor-api

CONFIGURE THE DATABASE
The application uses postgresSQL database. If you want to 
use a different database, you can modify the application.properties file 
accordingly.

spring.datasource.url=jdbc:postgresql://localhost:5432/mode-survivor
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.show-sql=true

BUILD AND RUN APPLICAION
mvn clean install
mvn spring-boot:run
The application will be accessible at http://localhost:8080/api/survivors.

TESTING
You can run tests using the following command:
mvn test

