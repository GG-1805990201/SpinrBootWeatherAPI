# WeatherAPI
Created WeatherAPIs using Token Based Authentication to get weather deatils using LocationKey, Cityname and Address.

## Auth Model
### AuthN
The authentication for the application is handled using JWTs. All incoming requests must bear a Bearer Token corresponding to a user account , generated via a password-verification.

## API Endpoints
| HTTP Verb | Endpoints | Action |
|  :---:         |     :---:      |           :---: |
| POST     | /createTask      | Post Endpoint to create task and saving to database.    |
| GET     | /getAllTasks        | Get Endpoint to fetch all tasks from DB       |
| GET     | /getTaskById       | Get Endpoint to fetch task for the given TaskId      |
| PUT     | /updateTaskById       | Put endpoint to update the existing task attributes for the given taskId.      |
| GET     | /getAuditDetails       | Get Endpoint to fetch AuditDetails for the given taskId.    |
| DELETE     | /deleteTaskById       | Delete endpoint which deletes the particular task with taskId as input.      |

## Testing
The APIs can be tested by running the spring application, by building and executing the project jar.
Below steps describe how to build the spring project.

### Building Spring Project

Ensure that all the required dependencies (JDK17, and Maven) are installed.

To build the Spring Project, execute the following command, in the same directory as pom.xml
```
mvn clean package
```
If the build succeeds, a jar file in ./target directory should be created.

> Example : taskmanager 0.0.1-SNAPSHOT.jar

If the build has succeeded, you can proceed to next step, i.e. starting the web server

### Starting a web server
To start the webserver, execute the jar, by running the following

```
java -jar taskmanager 0.0.1-SNAPSHOT.jar
```

A Tomcat Server should spin up and start listening for requests on port 6060

