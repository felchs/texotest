# texotest
<h1> The project description</h1>
This is an test of texo given the a simple structure of a Movie to be selected and filterd from a in-memory database

This project uses:

- Eclipse Version: 2020-09 (4.17.0), Build id: 20200910-1200
- Spring Boot 2.4.4
  - spring-boot-starter-data-jpa
  - spring-boot-starter-web
  - spring-boot-devtools
  - com.h2database
 
In order to run it you'll need to install the 
- Eclipse Web Developer Tools 3.19
- Spring Tools 4 (aka Spring Tool Suite 4) 4.10.0 RELEASE


<h1>Execution of the web service</h1>
- First download the do the project via git: https://github.com/felchs/texotest.git

 To run the project you'll need to initialize the:
 - TexotestApplication.java
 
 So you can do it by debuggind directly via eclipse using
 > Run Ass >Spring Boot App
 
 or you can run it directly via command line executing the java app TextotestApplication.java
 
 <h1>API Calls</h1>
 After the project to be initialized, some requested calls by the api are the following:
 
  - http://localhost:8080/api/v1/movies
 This call just lists all the movies from the in memory database
 
 - http://localhost:8080/create-movie
  This call creates a new Move in the in memory database
   
 - http://localhost:8080/api/v1/productor-with-interval
 This call do the main aim of the test retrinving the productor given some min and max intervals
 
 <h1>Junit Integration Tests</h1>
 Some implementatino of Junit Tests were done in the project proving some kind of execution examples and tests.
 
