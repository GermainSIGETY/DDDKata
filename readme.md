# Domain Driven Design Spring Boot Kata - a TODO list to Get Things Done

## Presentation 
    
A simple Rest API for a Todo list management, developped with Spring Boot and DDD concepts


## Article

Please read https://medium.com/@gsigety/domain-driven-design-spring-boot-kata-1-8a85034babec

## Architecture

Maven modules, responsibilities, dependencies

### GetThingsDone-domain

**The hexagon**
- contains all business logic; validation rules of users inputs ; mandatory information for a Todo, format of fields and so on => it validates invariants of value objects entering into the system
- contains domain objects. Business concepts and words have their objects in your code (ubiquitous language). Here one domain object which is a Todo
- transaction boundaries : manage database transactions ; decide whether actions results should be persisted or rollbacked.

**dependencies :** (almost) nothing. The upper module. Javax.transaction for transactions declaration. See pom.xml.

### GetThingsDone-infra

**Infrastructure layer**
- contains implementation of means for hexagon to communicate with the outside world
- here GetThingsDone-infra is responsible of one major topic ; manage persistence of Todos with a persistent storage.


But it could be many others responsibilities ;  sending email, SMS, push notification, read configuration info etc.

**dependencies** : GetThingsDone-domain, and many Spring/boot and persistence libraries stuff

### GetThingsDone-app

**UI and final runnable artifact**
- contains java main Class : entry point to launch the whole stuff
- application packaging : an auto-executable jar
- user interface : user interface is a HTTP Rest/JSON API, but it could be html pages, CLI etc.

**dependencies** : GetThingsDone-domain, ddd-infra, and many Spring/boot stuff for Rest JSON


## Bibliography

**Hexagonal architecture :**

https://blog.octo.com/en/hexagonal-architecture-three-principles-and-an-implementation-example/

**DDD quickly :**

https://www.infoq.com/minibooks/domain-driven-design-quickly

**Tell, Don't ask :**

https://www.martinfowler.com/bliki/TellDontAsk.html

**Implementing DDD, Vaughn Vernon**

https://www.oreilly.com/library/view/implementing-domain-driven-design/9780133039900/

## Usage :

endpoints URL is

http://localhost:8080/todos

### Create 

```
curl -X POST \
  http://localhost:8080/todos/ \
  -H 'Content-Type: application/json' \
  -d '{
   "title":"plant a tree",
   "description" : "because green is pleasing",
   "dueDate": 1557847007
}'
```

### list

``` 
curl -X GET \
  http://localhost:8080/todos \
```

... and use PUT, DELETE HTTP verbs to update and delete todos (see source code for details)