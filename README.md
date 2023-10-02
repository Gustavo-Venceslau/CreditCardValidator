# Getting Started

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Maven 3](https://maven.apache.org)

## Running the application locally

To run you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

After your application is running you need to run the front-end application to test. Follow the link bellow: https://github.com/Gustavo-Venceslau/CreditCardValidator-FrontEnd

## See the results

After you test you can see your credit card sent in the http://localhost:8080/h2_console.

- connect to the database: creditCardDB.
- click on CREDIT_CARD.
- after the SQL query appears click on run and see your results.