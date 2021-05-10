# Feature Request Application


## Getting Started

This application was developed using Spring Boot, H2 Database, JPA and Angular.

## Requirements

Java - 11.0.x

Maven - 4.x.x

Angular - 10.x.x

## Steps to Setup

**1. Install latest version of nodejs from https://nodejs.org/en/

**2. In Eclipse/Spring STS import FeatureRequest Project

Steps : 
    Click on File menu
    Click on Import sub menu
    Search for 'Existing projects into Workspace' option and click next
    Browse folder and click on finish

**4. Build the angular code**

go to EmployeeTimesheet\fecode\employeetimesheet and run below commands in git bash

```bash
```
npm install
ng build --prod

**5. Build and run the app using maven**

go to EmployeeTimesheet folder and run below command in git bash

```bash

mvn clean install

```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

Feature Requests API
GET - /api/v1/employees - Get All Employees
POST - /api/v1/work - To Log work
POST - /api/v1/vacation - To take vacation

You can test them using postman or any other rest client.