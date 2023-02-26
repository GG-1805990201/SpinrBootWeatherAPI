# WeatherAPI
Created WeatherAPIs using Token Based Authentication to get weather deatils using LocationKey, Cityname and Address.

## Auth Model
### AuthN
The authentication for the application is handled using JWTs. All incoming requests must bear a Bearer Token corresponding to a user account , generated via a password-verification.

## API Endpoints
| HTTP Verb | Endpoints | Action |
|  :---:         |     :---:      |           :---: |
| GET     | /getWeather         | Fetch weather details using Location key, City and Pincode    |
| GET     | /getForecast        | Fetch weather forecast for n days using Location key, City and Pincode       |
| GET     | /getWeatherNearestCity       | Fetch weather details using address      |

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

> Example : WeatherAPI-0.0.1-SNAPSHOT.jar

If the build has succeeded, you can proceed to next step, i.e. starting the web server

### Starting a web server
To start the webserver, execute the jar, by running the following

```
java -jar WeatherAPI-0.0.1-SNAPSHOT.jar
```
Pass API key for Open cage API as environment variable.

api_key=9df551d9b73743dd94fdac2ea461e1fe

A Tomcat Server should spin up and start listening for requests on port 8081

