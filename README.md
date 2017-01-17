# Tigr
Tigr is project created for PA165 seminar.

Wiki page: https://github.com/Furynka/Tigr/wiki

####Start Web:

  $ cd  spring-mvc && mvn tomcat7:run

  Application is available on URL http://localhost:8080/pa165/

####Authentication

There are two users with roles WORKER and ADMIN

 ROLE    | EMAIL           | PASSWORD
-------- | --------------- | --------
 admin   | admin@test.com  | password
 worker  | worker@test.com | password


We have created REST API for Worker entity.

Accessible by URL http://localhost:8080/pa165/rest/

* GET `/worker/` - lists all workers
* GET `/worker/{id}` - finds worker by given id
* POST `/worker/` - creates worker
* PUT `/worker/` - updates worker
* DELETE `/worker/` - deletes worker
