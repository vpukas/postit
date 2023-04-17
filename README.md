# PostIt Notes

## Table of Contents
* [Technologies Used](#technologies-used)
* [Setup](#setup)
* [Features](#features)
* [Project Status](#project-status)
* [Contact](#contact)

## Technologies Used
- React JS - version 18.2
- Spring Boot - version 3.0.5
- PostgreSQL
- Java - version 17.0.2
- Apache Maven - version 3.8.7 
- NPM - version 8.10.0

## Setup
1. You can run application using Docker(frontend 9090, backend 8080): 
   change data in .env file according to your database credentials and in main app directory run
   ```
   docker-compose up --build
   ```
   
    OR
    
    
   
2. Prepare PostgreSQL database and change values in application.properties file according to your database and user
3. Start Spring Boot application in backend directory, port 8080
 ```
   ./mvnw spring-boot:run
    or
    mvn spring-boot:run
  ```
4. Start React.js application in frontend directory port 3000
  ```
   npm install
   then
   npm start
   ```

## Features
List the ready features here:
- Adding notes
- Deleting notes
- Editing notes
- Viewing all notes


## Project Status
Project is: _in progress_.

## Contact
Created by Valerii Pukas - feel free to contact me!
