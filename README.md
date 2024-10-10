# User Task Completion Checker

This project is designed to calculate and identify users from the city **FanCode** who have completed more than 50% of their tasks. The data is fetched from a REST API (`https://jsonplaceholder.typicode.com/todos`), and the program filters the users based on the percentage of completed tasks and whether they belong to the city of **FanCode**.

## Scenario

- **Given**: The user has tasks from the `/todos` API.
- **And**: The user belongs to the city **FanCode**.
- **Then**: The user's completed tasks should be more than 50%.

## Functionality

- **User Data**: Fetch user and task information from the `/todos` endpoint.
- **City Filter**: Filter users based on their city (in this case, **FanCode**).
- **Task Completion**: For each user from **FanCode**, calculate the percentage of tasks completed. The program checks all Fancode city users should have completed more than 50% of their tasks.

## Prerequisites

Before running the program, ensure you have the following tools and libraries installed:

- **Java**: Version 8 or later
- **Maven**: For managing dependencies and building the project
- **RestAssured**: For making API calls and interacting with the REST endpoints
- **Jackson**: For converting JSON responses into Java objects (POJOs)
- **JUnit/Cucumber**: For running test cases using the Cucumber framework

## How to Run the Program

Follow these steps to run the program from the command line:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo-url.git
   cd your-repo-directory
2. **Build the Project: Use Maven to build the project and resolve dependencies.**
   mvn clean install
3.Run the Program: You can run the program using Maven to execute the tests and trigger the Cucumber runner.

For running Cucumber tests:
mvn test

**Project Structure:**

src/test/java:
Contains Cucumber test cases for validating the functionality.
features:
Cucumber feature files that define the scenarios for task completion and filtering users from FanCode.

**How It Works**
API Call: The program sends a GET request to the /todos endpoint using RestAssured.
POJO Deserialization: The response is deserialized into a list of Todo objects using Jackson.
Filtering Users:
The user IDs are filtered based on the city FanCode.
The program calculates the percentage of tasks completed for each user by counting completed tasks and dividing them by the total tasks for that user.
Result: Fancode Users should have completed more than 50% of their tasks.
