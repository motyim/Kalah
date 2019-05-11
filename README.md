# Kalah Game
A Java Rest API application that runs the game of 6-stone Kalah. 
For the rules of the game please refer to Wikipedia: https://en.wikipedia.org/wiki/Kalah.

## Stack
* Java 8
* Spring Boot
* Maven
* JUnit + Spring Boot Test
* Swagger
* jacoco

## RUN
 
### Prerequisites
* [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  - Programming language
* [Maven 3.5.0](https://maven.apache.org/download.cgi) - Build tool
* [Git](https://git-scm.com/) - version control

### Steps
* Pull the repo
```
$ git clone https://github.com/motyim/Kalah
$ cd Kalah
```
* Build the project using maven
```
$ mvn clean install 
```
* Application has embedded Tomcat, so the only one thing you need is to run
```
$ java -jar target/kalah-0.0.1-SNAPSHOT.jar  
```

### API documentation & Use
After running the project browse to
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Authors

* **Mohamed MotYim**

## License

This project is licensed under the MIT License 