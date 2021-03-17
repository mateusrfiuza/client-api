# Customer API


### Dependencies
* JDK 15
* Docker

### How to run tests
* Run commands on terminal inside project folder:
    ```
    ./gradlew clean test
    ```
    
### How to build and run application    

   
#### Docker
* Using Gradle to build and Docker to run application:
    1. Build with Gradle
        ```
        ./gradlew clean build bootJar
       ```
    2. Run on Docker
        1. Run docker compose
        ```
        docker-compose up
        ```
    

#### Basic Authentication
- username: admin
- password: admin    
    
#### Accessing API documentation
* URL: `http://localhost:8080/swagger-ui/`

#### Evolutions
* Add redis cache to save wishlist
* Add logs

            
    
