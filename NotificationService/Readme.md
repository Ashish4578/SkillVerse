UserServices

This service has privilege to handle based on user. By default, is Guest profile is there.
User & Creator can log in or registered it. Admin and SuperAdmin has a separate page. 


Below cmd is used to build api
```` 
mvn clean install 
````

## Kafka Roles in Your System

### Producers (send events)
```
Auth-Service → user-created
Enrollment-Service → enrollment-events
Rating-Service → rating-created
```
### Consumers (receive events)
```
User-Service ← user-created
Notification-Service ← enrollment-events
Course-Service ← rating-created
```
### Not using Kafka
```
API Gateway
Eureka (Service Registry)
Config Server
```

