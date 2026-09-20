# RestAssured API Automation Framework

[![Language](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![Build Tool](https://img.shields.io/badge/Build-Maven-C71A36.svg)](https://maven.apache.org/)
[![API Automation](https://img.shields.io/badge/API%20Automation-Rest%20Assured-4E9A06.svg)](https://rest-assured.io/)
[![Test Runner](https://img.shields.io/badge/Test%20Runner-TestNG-red.svg)](https://testng.org/)
[![Assertions](https://img.shields.io/badge/Assertions-Hamcrest-blue.svg)](https://hamcrest.org/)
[![Build Status](https://img.shields.io/badge/Build%20Status-Maven%20configured-lightgrey.svg)](https://github.com/KunjMaheshwari/RestAssured-Learning)
[![CI/CD](https://img.shields.io/badge/CI%2FCD-Not%20configured-lightgrey.svg)](https://github.com/KunjMaheshwari/RestAssured-Learning)

A Java-based REST API automation project demonstrating scalable API testing practices with REST Assured, TestNG, Hamcrest assertions, dynamic test data, request chaining, authentication strategies, payload construction, and response validation.

---

## Project Overview

This project is designed as an API automation learning and demonstration framework for validating RESTful services across common HTTP operations:

- GET
- POST
- PUT
- DELETE

It demonstrates the core workflow used in API automation:

```text
Prepare request → Send HTTP request → Validate response → Extract data → Chain subsequent requests
```

The codebase covers API request construction, payload serialization, headers and cookies, path/query parameters, authentication methods, response parsing, CRUD operations, and inter-test data sharing through TestNG `ITestContext`.

The project is particularly relevant for demonstrating foundational SDET capabilities:

- API contract validation
- HTTP protocol knowledge
- Positive and negative response assertions
- Authentication testing
- Dynamic test data generation
- Request dependency management
- Test execution through Maven and TestNG
- Response logging and test reporting

---

## Architecture & Design Patterns

### REST Assured Fluent API

Requests use REST Assured’s fluent `given() → when() → then()` syntax:

```java
given()
    .contentType("application/json")
    .body(payload)
.when()
    .post(endpoint)
.then()
    .statusCode(201)
    .body("name", equalTo("Kunj"));
```

This provides readable separation between:

1. Request specification
2. HTTP execution
3. Response validation

### Request Specification Abstraction

The framework follows REST Assured’s request specification model through reusable concepts such as:

- Headers
- Cookies
- Authentication
- Content type
- Path parameters
- Query parameters
- Request body

The current implementation demonstrates these concepts directly inside test classes. These components can be extracted into reusable base specifications as the framework evolves.

### Payload Construction Strategies

The project demonstrates multiple ways to create request payloads:

- `HashMap`
- `org.json.JSONObject`
- External JSON files
- POJO-based payloads as a planned/extensible pattern

Example payload construction approaches are demonstrated in:

- `DifferentWaysToCreatePostRequest.java`
- `HTTPRequests.java`

### Response Parsing and Data Extraction

Responses are handled through multiple mechanisms:

- REST Assured JSON path extraction
- `Response` object inspection
- `JSONObject` parsing
- Header and cookie extraction
- TestNG `ITestContext` for passing values between tests

### API Chaining

The `day7` request flow demonstrates API chaining:

```text
POST request
   ↓
Extract generated userId
   ↓
Store userId in ITestContext
   ↓
GET / PUT / DELETE using the stored userId
```

This is implemented through:

```java
context.setAttribute("userId", id);
```

and later retrieved using:

```java
int id = (Integer) context.getAttribute("userId");
```

---

## Tech Stack & Libraries

### Core Language

- Java
- The generated Maven metadata indicates Java 8 source and target compatibility.
- The root `pom.xml` does not currently declare the compiler source level explicitly.

### API Automation

- REST Assured 6.0.1
  - HTTP request execution
  - Response validation
  - JSON path and XML path processing
  - Authentication support
  - Schema validation dependency

### Testing Engine

- TestNG
  - `@Test` annotations
  - Test priorities
  - Method dependencies
  - `ITestContext` for inter-test data sharing
  - XML suite execution

The generated Maven metadata under `target` references TestNG 7.11.0. TestNG is used extensively in the test source, although it is not explicitly declared in the current root `pom.xml`.

### Assertion Libraries

- Hamcrest Matchers
  - `equalTo`
  - JSON response field assertions
  - Header and status code assertions
- TestNG Assert
  - Programmatic response assertions using `Assert.assertEquals`

### Payload and Data Libraries

- `org.json` 20250517
  - Dynamic JSON object construction
  - JSON response parsing
- `JavaFaker` 1.0.2
  - Dynamic names
  - Email addresses
  - Randomized test data generation

### REST Assured Modules

Configured REST Assured modules include:

- `rest-assured`
- `json-path`
- `xml-path`
- `json-schema-validator`
- `spring-mock-mvc`
- `spring-web-test-client`
- `scala-support`
- `kotlin-extensions`
- `rest-assured-all`

### Build Tool

- Apache Maven
- Project descriptor: `pom.xml`
- Artifact version: `0.0.1-SNAPSHOT`

### Reporting and Logging

The repository contains generated TestNG output under `test-output`, including:

- `index.html`
- `emailable-report.html`
- `testng-results.xml`
- `testng-failed.xml`
- JUnit-style reports

The generated Maven metadata also references:

- Log4j 2.25.1
- ExtentReports 5.1.2

However, these libraries are not present in the current root `pom.xml` and should be treated as planned or previously generated configuration rather than guaranteed current dependencies.

### CI/CD

- No GitHub Actions workflow is currently configured.
- The project can be integrated into GitHub Actions, Jenkins, GitLab CI, Azure DevOps, or similar pipelines using Maven commands.

---

## Project Structure

```text
RestAssured-Learning/
├── pom.xml
│   └── Maven dependencies and project configuration
│
├── java/
│   ├── HTTPRequest.java
│   ├── day2/
│   │   └── DifferentWaysToCreatePostRequest.java
│   ├── day3/
│   │   ├── CookiesAndHeaders.java
│   │   └── PathAndQueryParameters.java
│   ├── day5/
│   │   └── ParingReponseBody.java
│   ├── day6/
│   │   └── Authentication.java
│   └── day7/
│       └── Chaining.java
│
├── src/
│   └── test/
│       └── java/
│           ├── Day1/
│           │   └── HTTPRequests.java
│           └── java/
│               ├── HTTPRequest.java
│               ├── day2/
│               │   └── DifferentWaysToCreatePostRequest.java
│               ├── day3/
│               │   ├── CookiesAndHeaders.java
│               │   └── PathAndQueryParameters.java
│               ├── day5/
│               │   └── ParingReponseBody.java
│               ├── day6/
│               │   └── Authentication.java
│               ├── day7/
│               │   ├── PostRequest.java
│               │   ├── GetRequest.java
│               │   ├── PutRequest.java
│               │   ├── DeleteRequest.java
│               │   └── testng.xml
│
├── test-output/
│   ├── index.html
│   ├── emailable-report.html
│   ├── testng-results.xml
│   ├── testng-failed.xml
│   └── junitreports/
│
├── target/
│   └── Maven-generated compiled classes and reports
│
├── .classpath
├── .project
├── .gitignore
└── .settings/
```

> The repository currently contains both top-level Java examples under `java/` and Maven test sources under `src/test/java/`. The Maven-oriented execution path is based on `src/test/java`.

---

## Key Engineering Highlights

### 1. API Request and Response Validation

The test suite validates:

- HTTP status codes
- JSON response fields
- Response headers
- Cookies
- JSON array values
- Content types
- Authentication response fields

Example:

```java
.then()
    .statusCode(200)
    .body("authenticated", equalTo(true))
    .log().all();
```

### 2. Payload Serialization

The project demonstrates payload creation using:

```java
HashMap<String, Object> payload
```

and:

```java
JSONObject payload
```

It also reads payloads from external JSON files using `JSONTokener` and `FileReader`.

This provides a foundation for moving toward:

- Dedicated payload builders
- POJO request models
- Jackson or Gson serialization
- Centralized test data factories
- Environment-specific payload templates

### 3. Response Deserialization and Parsing

Response data is extracted using:

```java
response.jsonPath().getInt("id");
```

and parsed manually using:

```java
JSONObject jsonObject = new JSONObject(response.toString());
```

This supports both lightweight field extraction and structured response traversal.

The current `pom.xml` also includes JSON path and XML path support, enabling response parsing for both JSON and XML APIs.

### 4. Schema Validation Capability

The Maven configuration includes:

```xml
<artifactId>json-schema-validator</artifactId>
```

This enables REST Assured JSON schema validation.

A production implementation can validate responses using:

```java
.then()
    .body(matchesJsonSchemaInClasspath("schemas/user-response.json"));
```

The current repository demonstrates response field and header validation, while schema validation is available as an extensibility point.

### 5. Authentication Coverage

Authentication examples include:

- Basic authentication
- Digest authentication
- Preemptive basic authentication
- Bearer token authentication

Example:

```java
given()
    .auth()
    .preemptive()
    .basic("username", "password")
.when()
    .get(endpoint)
.then()
    .statusCode(200);
```

For enterprise usage, credentials should be moved out of source code and injected through:

- Environment variables
- Maven system properties
- CI/CD secrets
- Secure configuration providers

### 6. Environment Configuration

The current tests use inline URLs and placeholder credentials. For maintainable enterprise automation, the recommended configuration model is:

```text
config/
├── qa.properties
├── staging.properties
└── production.properties
```

Typical runtime parameters:

```bash
mvn test \
  -Denv=qa \
  -DbaseUrl=https://qa.example.com \
  -DapiToken=$API_TOKEN
```

This avoids hard-coded endpoints, tokens, usernames, and passwords.

### 7. Dynamic Test Data

JavaFaker is used to create dynamic test data:

```java
Faker faker = new Faker();

String firstName = faker.name().firstName();
String email = faker.internet().emailAddress();
```

Dynamic data reduces collisions and supports repeatable API test execution across environments.

### 8. API Chaining with TestNG Context

The request flow under `day7` demonstrates dependent API calls:

1. Create a resource.
2. Extract its generated identifier.
3. Store the identifier in `ITestContext`.
4. Reuse it in GET, PUT, and DELETE operations.

This pattern is useful for validating realistic business workflows rather than isolated endpoint behavior.

### 9. Logging and Diagnostics

REST Assured logging is used to print request and response details:

```java
.log().all();
```

This supports troubleshooting during local execution. For production-grade execution, logging should be controlled through Log4j configuration and should mask:

- Authorization headers
- Bearer tokens
- Passwords
- Session cookies
- Personal or sensitive data

### 10. Parallel Execution

The TestNG suite defines:

```xml
<test thread-count="5" name="Test">
```

The suite includes:

- `PostRequest`
- `GetRequest`
- `PutRequest`
- `DeleteRequest`

Because these tests share data through `ITestContext`, they represent a sequential chained workflow and should not be parallelized without carefully controlling data dependencies.

Independent endpoint tests can be parallelized after:

- Removing shared mutable state
- Using isolated test data
- Avoiding static request configuration
- Introducing thread-safe request specifications
- Separating workflow tests from standalone smoke tests

---

## Prerequisites

### Required Software

- Java Development Kit 8 or later
- Apache Maven 3.8+
- Git
- Internet access for Maven dependency resolution
- Access to the target API environment

The generated Maven metadata indicates Java 8 compatibility. The root `pom.xml` does not currently enforce a compiler version, so the installed JDK should be aligned with the project’s CI runtime.

### Verify Installation

```bash
java -version
mvn -version
git --version
```

---

## Installation

Clone the repository:

```bash
git clone https://github.com/KunjMaheshwari/RestAssured-Learning.git
cd RestAssured-Learning
```

Resolve Maven dependencies:

```bash
mvn dependency:resolve
```

Compile the project:

```bash
mvn clean compile
```

Compile test sources:

```bash
mvn test-compile
```

---

## Execution Guide

### Execute the Maven Test Lifecycle

```bash
mvn clean test
```

> The current repository uses TestNG classes and contains a suite file at `src/test/java/java/day7/testng.xml`. The root Maven configuration does not currently configure the Surefire plugin to use this suite file, so suite-specific execution should be used when running the chained API workflow.

### Execute the TestNG Chaining Suite

```bash
mvn clean test \
  -Dsurefire.suiteXmlFiles=src/test/java/java/day7/testng.xml
```

### Execute a Specific Test Class

```bash
mvn test \
  -Dtest=Day1.HTTPRequests
```

For the Maven test package:

```bash
mvn test \
  -Dtest=java.day7.PostRequest
```

### Execute Multiple Test Classes

```bash
mvn test \
  -Dtest=java.day7.PostRequest,java.day7.GetRequest,java.day7.PutRequest,java.day7.DeleteRequest
```

### Execute TestNG Groups

The current test classes do not define `@Smoke`, `@Regression`, or other TestNG groups. Once groups are added, they can be executed with:

```bash
mvn test \
  -Dgroups=Smoke
```

or:

```bash
mvn test \
  -Dgroups=Regression
```

Recommended classification model:

```java
@Test(groups = {"Smoke", "Regression"})
```

### Execute Tests with Environment Parameters

```bash
mvn clean test \
  -Denv=qa \
  -DbaseUrl=https://qa.example.com \
  -DapiToken="$API_TOKEN"
```

### Parallel Execution

For independent tests, TestNG can be configured with:

```xml
<suite name="Parallel Suite" parallel="methods" thread-count="5">
```

Then execute:

```bash
mvn test \
  -Dsurefire.suiteXmlFiles=src/test/java/java/day7/testng.xml
```

Do not run dependent API-chain tests in parallel unless each test flow uses isolated test data and independent context objects.

---

## Reporting

### TestNG HTML Reports

TestNG generates reports under:

```text
test-output/
```

Important files include:

```text
test-output/index.html
test-output/emailable-report.html
test-output/testng-results.xml
test-output/testng-failed.xml
```

Open the main report locally:

```bash
open test-output/index.html
```

On Windows:

```powershell
start test-output/index.html
```

On Linux:

```bash
xdg-open test-output/index.html
```

### Maven Surefire Reports

When Surefire is configured and tests are discovered, reports are generated under:

```text
target/surefire-reports/
```

Typical outputs include:

```text
target/surefire-reports/
├── TEST-*.xml
├── *.txt
└── index.html
```

### Logging

REST Assured currently uses `.log().all()` for request and response diagnostics.

For CI execution, logging should be enhanced with:

- Log levels by environment
- Request/response correlation IDs
- Secret masking
- Failure-only response logging
- Artifact retention

### Allure and Extent Reports

Allure is not currently configured in the repository.

ExtentReports is referenced in generated Maven metadata but is not present in the current root `pom.xml`. If enterprise reporting is required, it can be added through a TestNG listener and Maven dependency.

---

## GitHub Actions Example

The repository does not currently include a GitHub Actions workflow. A basic Maven pipeline can be configured as follows:

```yaml
name: API Automation Tests

on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main
  workflow_dispatch:

jobs:
  api-tests:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout source
        uses: actions/checkout@v4

      - name: Set up Java
        uses: actions/setup-java@v4
        with:
          distribution: temurin
          java-version: '8'
          cache: maven

      - name: Execute API test suite
        run: |
          mvn clean test \
            -Dsurefire.suiteXmlFiles=src/test/java/java/day7/testng.xml
        env:
          API_TOKEN: ${{ secrets.API_TOKEN }}

      - name: Upload TestNG reports
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: test-reports
          path: |
            test-output/
            target/surefire-reports/
```

Recommended CI pipeline stages:

```text
Checkout
   ↓
Install JDK and Maven
   ↓
Resolve dependencies
   ↓
Run smoke tests
   ↓
Run regression tests
   ↓
Publish TestNG/Surefire reports
   ↓
Archive logs and failed-test artifacts
```

---

## Recommended Enterprise Enhancements

The current project provides a strong foundation for API automation. The following improvements would help evolve it into a production-grade framework:

- Add a dedicated `BaseTest` or `RequestSpecBuilder` abstraction.
- Centralize base URLs and environment configuration.
- Move credentials and tokens to environment variables or CI secrets.
- Add explicit TestNG dependency to the root `pom.xml`.
- Configure Maven Surefire for the TestNG suite.
- Add `@Smoke`, `@Regression`, and `@Sanity` groups.
- Add JSON schema files under `src/test/resources/schemas`.
- Introduce POJO request and response models.
- Add Jackson or Gson-based serialization/deserialization.
- Add reusable API client/service classes.
- Add retry handling only for explicitly transient failures.
- Add failure screenshots or request/response attachments where applicable.
- Add structured Log4j configuration with sensitive-data masking.
- Add GitHub Actions or Jenkins pipeline integration.
- Exclude generated `target/` and report artifacts from source control.
- Separate learning examples from the maintained regression framework.
- Replace placeholder URLs and credentials with environment-driven configuration.
- Add negative, boundary, authorization, contract, and data-driven tests.

---

## Author

### Kunj Maheshwari

Java | API Automation | REST Assured | TestNG | SDET Practices

- GitHub: [KunjMaheshwari](https://github.com/KunjMaheshwari)
- Repository: [RestAssured-Learning](https://github.com/KunjMaheshwari/RestAssured-Learning)

This project demonstrates hands-on experience with REST API automation, HTTP validation, authentication strategies, payload engineering, response parsing, API chaining, dynamic test data, and Maven-based test execution.

---

## Contributions

Contributions and improvement suggestions are welcome.

When contributing:

1. Create a feature branch.
2. Add or update automated tests.
3. Keep credentials and environment-specific values out of source control.
4. Execute the relevant Maven test suite locally.
5. Include test evidence or report output in the pull request.
6. Submit a focused pull request with a clear description.
