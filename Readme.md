# 🚀 Spring Boot REST Client

A practical Spring Boot project demonstrating how to consume external REST APIs using Spring Framework's modern `RestClient`.

This project uses [JSONPlaceholder](https://jsonplaceholder.typicode.com/) as a mock REST API to practice HTTP operations, JSON mapping, request headers, query parameters, and exception handling.

---

## 📌 Table of Contents

- [About the Project](#-about-the-project)
- [Technologies Used](#-technologies-used)
- [What is a REST Client](#-what-is-a-rest-client)
- [REST API vs REST Client](#-rest-api-vs-rest-client)
- [Project Structure](#-project-structure)
- [Prerequisites](#-prerequisites)
- [Getting Started](#-getting-started)
- [External API](#-external-api)
- [Application Architecture](#-application-architecture)
- [Implementation](#-implementation)
  - [DTO Creation](#1-dto-creation)
  - [RestClient Configuration](#2-restclient-configuration)
  - [Service Layer](#3-service-layer)
  - [Controller Layer](#4-controller-layer)
- [HTTP Operations](#-http-operations)
  - [GET All Posts](#1-get-all-posts)
  - [GET Post by ID](#2-get-post-by-id)
  - [POST Create a Post](#3-post-create-a-post)
  - [PUT Update a Post](#4-put-update-a-post)
  - [DELETE a Post](#5-delete-a-post)
- [Query Parameters](#-query-parameters)
- [Request Headers](#-request-headers)
- [Response Handling Methods](#-response-handling-methods)
- [Exception Handling](#-exception-handling)
- [Testing with Postman](#-testing-with-postman)
- [Learning Outcomes](#-learning-outcomes)
- [Practice Tasks](#-practice-tasks)
- [Interview Questions](#-interview-questions)
- [Future Improvements](#-future-improvements)

---

# 📖 About the Project

This project demonstrates how a Spring Boot application can communicate with an external REST API.

Instead of directly interacting with a database, the application sends HTTP requests to the JSONPlaceholder API and processes the returned JSON data.

The project follows a layered architecture:

```text
Controller
    |
    v
Service
    |
    v
RestClient
    |
    v
External REST API
    |
    v
JSON Response
    |
    v
DTO
```

The main objective is to understand how external APIs are consumed in real-world Spring Boot applications.

---

# 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Programming Language |
| Spring Boot | Backend Framework |
| Spring Web | REST API Development |
| RestClient | Synchronous HTTP Client |
| Jackson | JSON Serialization and Deserialization |
| Maven | Dependency Management |
| Postman | API Testing |
| JSONPlaceholder | Mock REST API |
| IntelliJ IDEA / Eclipse | IDE |

---

# 🌐 What is a REST Client?

A REST Client is a component that sends HTTP requests to another application or external service.

For example, our Spring Boot application calls the JSONPlaceholder API to retrieve posts.

```text
Spring Boot Application
          |
          | HTTP GET Request
          v
JSONPlaceholder API
          |
          | JSON Response
          v
Spring Boot Application
```

A REST Client can be used to:

- Fetch data from external APIs.
- Send data to external APIs.
- Update external resources.
- Delete external resources.
- Consume third-party services.
- Communicate between microservices.

---

# 🔄 REST API vs REST Client

| REST API | REST Client |
|----------|-------------|
| Provides endpoints | Consumes endpoints |
| Receives HTTP requests | Sends HTTP requests |
| Processes requests | Calls external services |
| Returns responses | Processes received responses |
| Acts as a server | Acts as a client |

A Spring Boot application can act as both a REST API and a REST Client.

---

# 📁 Project Structure

```text
src
└── main
    └── java
        └── com.example.restclient
            │
            ├── config
            │   └── RestClientConfig.java
            │
            ├── controller
            │   └── PostController.java
            │
            ├── dto
            │   └── PostDto.java
            │
            ├── service
            │   └── PostService.java
            │
            └── RestClientApplication.java
```

### Package Responsibilities

| Package | Responsibility |
|---------|----------------|
| `config` | Configures the RestClient Bean |
| `controller` | Exposes application endpoints |
| `service` | Contains business logic and external API calls |
| `dto` | Represents request and response data |
| Main class | Starts the Spring Boot application |

---

# ✅ Prerequisites

Before running this project, make sure you have:

- Java 17 or later.
- Maven.
- Spring Boot 3.2+ or a compatible Spring Framework version.
- Postman.
- Basic knowledge of Spring Boot.
- Basic knowledge of REST APIs.
- Basic knowledge of HTTP methods.

> `RestClient` was introduced in Spring Framework 6.1.

---

# 🚀 Getting Started

## 1. Clone the Repository

```bash
git clone <your-repository-url>
```

## 2. Navigate to the Project

```bash
cd rest-client
```

## 3. Build the Project

```bash
mvn clean install
```

## 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

---

# 🌍 External API

This project uses JSONPlaceholder as the external REST API.

### Base URL

```text
https://jsonplaceholder.typicode.com
```

### Available Endpoints

| HTTP Method | Endpoint | Description |
|-------------|----------|-------------|
| GET | `/posts` | Fetch all posts |
| GET | `/posts/{id}` | Fetch a specific post |
| POST | `/posts` | Create a post |
| PUT | `/posts/{id}` | Update a post |
| DELETE | `/posts/{id}` | Delete a post |
| GET | `/posts?userId=1` | Filter posts by user ID |

> JSONPlaceholder is a fake REST API. POST, PUT, and DELETE operations return simulated responses and do not permanently modify a real database.

---

# 🏗️ Application Architecture

```text
                   CLIENT
                (Postman)
                    |
                    v
            PostController
                    |
                    v
              PostService
                    |
                    v
              RestClient
                    |
                    v
          JSONPlaceholder API
                    |
                    v
              JSON Response
                    |
                    v
               PostDto
                    |
                    v
               CLIENT
```

### Request Flow

```text
HTTP Request
     |
     v
Controller
     |
     v
Service
     |
     v
RestClient
     |
     v
External API
     |
     v
Response
     |
     v
DTO Mapping
     |
     v
Controller Response
```

---

# 💻 Implementation

## 1. DTO Creation

Create a `PostDto` class to represent the post data returned by the external API.

### `PostDto.java`

```java
package com.example.restclient.dto;

public class PostDto {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public PostDto() {
    }

    public PostDto(
            Integer userId,
            Integer id,
            String title,
            String body
    ) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
```

### Why DTO?

A DTO (Data Transfer Object) is used to transfer data between different layers or applications.

The external API returns JSON data, which Jackson converts into a Java object.

```text
JSON Response
      |
      v
   Jackson
      |
      v
   PostDto
```

---

## 2. RestClient Configuration

Create a reusable `RestClient` Bean.

### `RestClientConfig.java`

```java
package com.example.restclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient restClient() {

        return RestClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }
}
```

### Explanation

#### `@Configuration`

Marks the class as a Spring configuration class.

#### `@Bean`

Registers the `RestClient` object in the Spring IoC container.

#### `baseUrl()`

Defines the common URL for external API requests.

```java
.baseUrl("https://jsonplaceholder.typicode.com")
```

Now this:

```java
.uri("/posts")
```

Will call:

```text
https://jsonplaceholder.typicode.com/posts
```

---

# 🔧 Service Layer

The service layer is responsible for communicating with the external API.

### `PostService.java`

```java
package com.example.restclient.service;

import com.example.restclient.dto.PostDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    private final RestClient restClient;

    public PostService(RestClient restClient) {
        this.restClient = restClient;
    }

    // Methods will be added below.
}
```

The `RestClient` Bean is injected through constructor injection.

---

# 🎮 Controller Layer

The controller exposes endpoints that can be called by Postman or a frontend application.

### `PostController.java`

```java
package com.example.restclient.controller;

import com.example.restclient.dto.PostDto;
import com.example.restclient.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Endpoints will be added below.
}
```

---

# 🔄 HTTP Operations

The following HTTP methods are implemented in this project:

| HTTP Method | Purpose |
|-------------|---------|
| GET | Retrieve data |
| POST | Create data |
| PUT | Update data |
| DELETE | Delete data |

---

# 1. GET All Posts

Fetch all posts from JSONPlaceholder.

### External API

```http
GET https://jsonplaceholder.typicode.com/posts
```

### Service Method

```java
public List<PostDto> getAllPosts() {

    PostDto[] posts = restClient
            .get()
            .uri("/posts")
            .retrieve()
            .body(PostDto[].class);

    return Arrays.asList(posts);
}
```

### Explanation

| Method | Purpose |
|--------|---------|
| `.get()` | Creates a GET request |
| `.uri()` | Specifies the API endpoint |
| `.retrieve()` | Retrieves the response |
| `.body()` | Converts the response body into a Java object |

### Controller Endpoint

```java
@GetMapping
public List<PostDto> getAllPosts() {

    return postService.getAllPosts();
}
```

### Test Request

```http
GET http://localhost:8080/api/posts
```

### Response

```json
[
  {
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat",
    "body": "quia et suscipit"
  }
]
```

The actual API returns multiple posts.

---

# 2. GET Post by ID

Fetch a specific post using its ID.

### External API

```http
GET https://jsonplaceholder.typicode.com/posts/1
```

### Service Method

```java
public PostDto getPostById(Integer id) {

    return restClient
            .get()
            .uri("/posts/{id}", id)
            .retrieve()
            .body(PostDto.class);
}
```

### Path Variable Substitution

```java
.uri("/posts/{id}", id)
```

If:

```java
id = 5;
```

The final URL becomes:

```text
/posts/5
```

### Controller Endpoint

```java
@GetMapping("/{id}")
public PostDto getPostById(
        @PathVariable Integer id
) {

    return postService.getPostById(id);
}
```

### Test Request

```http
GET http://localhost:8080/api/posts/1
```

### Response

```json
{
  "userId": 1,
  "id": 1,
  "title": "sunt aut facere repellat",
  "body": "quia et suscipit"
}
```

---

# 3. POST Create a Post

Send data to the external API to create a simulated post.

### External API

```http
POST https://jsonplaceholder.typicode.com/posts
```

### Request Body

```json
{
  "userId": 1,
  "title": "Learning Spring Boot",
  "body": "Learning REST Client"
}
```

### Service Method

```java
public PostDto createPost(PostDto post) {

    return restClient
            .post()
            .uri("/posts")
            .body(post)
            .retrieve()
            .body(PostDto.class);
}
```

### Explanation

| Method | Purpose |
|--------|---------|
| `.post()` | Creates a POST request |
| `.uri()` | Specifies the endpoint |
| `.body(post)` | Adds the Java object to the request body |
| `.retrieve()` | Retrieves the response |
| `.body(PostDto.class)` | Converts JSON into a PostDto object |

Jackson serializes the Java object into JSON.

### Controller Endpoint

```java
@PostMapping
public PostDto createPost(
        @RequestBody PostDto post
) {

    return postService.createPost(post);
}
```

### Test Request

```http
POST http://localhost:8080/api/posts
```

### Request Body

```json
{
  "userId": 1,
  "title": "Learning Spring Boot",
  "body": "This is my first external API request"
}
```

### Expected Response

```json
{
  "userId": 1,
  "title": "Learning Spring Boot",
  "body": "This is my first external API request",
  "id": 101
}
```

> JSONPlaceholder generally returns a simulated response with ID `101`. The post is not permanently saved.

---

# 4. PUT Update a Post

Use PUT to send a complete updated representation of a post.

### External API

```http
PUT https://jsonplaceholder.typicode.com/posts/1
```

### Service Method

```java
public PostDto updatePost(
        Integer id,
        PostDto post
) {

    return restClient
            .put()
            .uri("/posts/{id}", id)
            .body(post)
            .retrieve()
            .body(PostDto.class);
}
```

### Controller Endpoint

```java
@PutMapping("/{id}")
public PostDto updatePost(
        @PathVariable Integer id,
        @RequestBody PostDto post
) {

    return postService.updatePost(id, post);
}
```

### Test Request

```http
PUT http://localhost:8080/api/posts/1
```

### Request Body

```json
{
  "userId": 1,
  "id": 1,
  "title": "Updated Title",
  "body": "Updated Body"
}
```

---

# 5. DELETE a Post

Send a DELETE request to the external API.

### External API

```http
DELETE https://jsonplaceholder.typicode.com/posts/1
```

### Service Method

```java
public void deletePost(Integer id) {

    restClient
            .delete()
            .uri("/posts/{id}", id)
            .retrieve()
            .toBodilessEntity();
}
```

### Explanation

```java
.toBodilessEntity()
```

Executes the request and returns a `ResponseEntity<Void>` containing response metadata without a response body.

### Controller Endpoint

```java
@DeleteMapping("/{id}")
public String deletePost(
        @PathVariable Integer id
) {

    postService.deletePost(id);

    return "Post deleted successfully";
}
```

### Test Request

```http
DELETE http://localhost:8080/api/posts/1
```

### Response

```text
Post deleted successfully
```

> JSONPlaceholder simulates the DELETE operation. It does not permanently remove a real database record.

---

# 🔍 Query Parameters

Query parameters are used to filter or customize API requests.

For example, fetch posts created by user ID `1`.

### External API

```http
GET https://jsonplaceholder.typicode.com/posts?userId=1
```

### Service Method

```java
public List<PostDto> getPostsByUserId(Integer userId) {

    PostDto[] posts = restClient
            .get()
            .uri(uriBuilder -> uriBuilder
                    .path("/posts")
                    .queryParam("userId", userId)
                    .build())
            .retrieve()
            .body(PostDto[].class);

    return Arrays.asList(posts);
}
```

### Explanation

```java
.queryParam("userId", userId)
```

Adds a query parameter to the URL.

If:

```java
userId = 1;
```

The final URL becomes:

```text
/posts?userId=1
```

### Controller Endpoint

```java
@GetMapping("/user/{userId}")
public List<PostDto> getPostsByUserId(
        @PathVariable Integer userId
) {

    return postService.getPostsByUserId(userId);
}
```

### Test Request

```http
GET http://localhost:8080/api/posts/user/1
```

### Path Variable vs Query Parameter

| Path Variable | Query Parameter |
|---------------|-----------------|
| `/posts/1` | `/posts?userId=1` |
| Identifies a resource | Filters or modifies a request |
| `@PathVariable` | `@RequestParam` |

---

# 🔐 Request Headers

HTTP headers carry additional information with a request.

Common examples:

- Authorization
- Content-Type
- Accept
- Custom application headers

### Example

```java
public PostDto getPostWithHeaders() {

    return restClient
            .get()
            .uri("/posts/1")
            .header(
                    "Authorization",
                    "Bearer YOUR_TOKEN"
            )
            .retrieve()
            .body(PostDto.class);
}
```

### Multiple Headers

```java
public PostDto getPostWithMultipleHeaders() {

    return restClient
            .get()
            .uri("/posts/1")
            .headers(headers -> {

                headers.set(
                        "Authorization",
                        "Bearer YOUR_TOKEN"
                );

                headers.set(
                        "X-Custom-Header",
                        "MyValue"
                );
            })
            .retrieve()
            .body(PostDto.class);
}
```

> JSONPlaceholder does not require authentication. The authorization header is demonstrated for learning purposes only.

---

# 📦 Response Handling Methods

Spring's `RestClient` provides different methods for handling HTTP responses.

Understanding these methods is important when working with external APIs.

---

## 1. `retrieve()`

```java
restClient
        .get()
        .uri("/posts/1")
        .retrieve();
```

### Purpose

`retrieve()` initiates response retrieval and provides methods to extract the response body or convert it into a response entity.

By default, error responses in the 4xx and 5xx range result in exceptions.

---

## 2. `body()`

Used to convert the response body into a Java object.

### Single Object

```java
PostDto post = restClient
        .get()
        .uri("/posts/1")
        .retrieve()
        .body(PostDto.class);
```

### Array of Objects

```java
PostDto[] posts = restClient
        .get()
        .uri("/posts")
        .retrieve()
        .body(PostDto[].class);
```

### Difference

| Code | Meaning |
|------|---------|
| `body(PostDto.class)` | Converts JSON into one PostDto |
| `body(PostDto[].class)` | Converts a JSON array into a PostDto array |

---

## 3. `toEntity()`

Used when you need the response body along with HTTP status and headers.

### Example

```java
import org.springframework.http.ResponseEntity;

public ResponseEntity<PostDto> getPostResponse(
        Integer id
) {

    return restClient
            .get()
            .uri("/posts/{id}", id)
            .retrieve()
            .toEntity(PostDto.class);
}
```

### Accessing Response Details

```java
ResponseEntity<PostDto> response =
        postService.getPostResponse(1);
```

```java
response.getStatusCode();
response.getHeaders();
response.getBody();
```

### ResponseEntity Contains

| Method | Purpose |
|--------|---------|
| `getStatusCode()` | Returns HTTP status |
| `getHeaders()` | Returns response headers |
| `getBody()` | Returns response body |

---

## 4. `toBodilessEntity()`

Used when the response body is not required.

```java
restClient
        .delete()
        .uri("/posts/{id}", id)
        .retrieve()
        .toBodilessEntity();
```

### Return Type

```java
ResponseEntity<Void>
```

This is useful for operations such as DELETE requests where the response body is not needed.

---

## 5. `exchange()`

`exchange()` provides lower-level access to the HTTP response.

It can be used when you need more control over request and response processing.

### Example

```java
import com.example.restclient.dto.PostDto;
import org.springframework.web.client.RestClient;

public PostDto getPostUsingExchange(Integer id) {

    return restClient
            .get()
            .uri("/posts/{id}", id)
            .exchange((request, response) -> {

                if (response.getStatusCode().is2xxSuccessful()) {

                    return restClient
                            .get()
                            .uri("/posts/{id}", id)
                            .retrieve()
                            .body(PostDto.class);
                }

                throw new RuntimeException(
                        "External API request failed"
                );
            });
}
```

> The above example demonstrates the exchange callback concept only. In a real implementation, the response should be decoded directly rather than issuing another HTTP request inside the callback.

### Important Difference

| `retrieve()` | `exchange()` |
|---------------|--------------|
| Convenient response retrieval | Lower-level response handling |
| Automatic default error handling | You control response processing |
| Easy body conversion | Useful for custom response logic |
| Best for common API calls | Best for advanced response handling |

---

# ⚠️ Exception Handling

External APIs can return errors such as:

- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- 500 Internal Server Error

By default, `RestClient` can throw exceptions for HTTP error responses when using `retrieve()`.

---

## Custom Error Handling

You can customize error handling using `onStatus()`.

### Example

```java
import org.springframework.http.HttpStatusCode;

public PostDto getPostByIdWithErrorHandling(
        Integer id
) {

    return restClient
            .get()
            .uri("/posts/{id}", id)
            .retrieve()
            .onStatus(
                    HttpStatusCode::is4xxClientError,
                    (request, response) -> {

                        throw new RuntimeException(
                                "Client error while fetching post"
                        );
                    }
            )
            .onStatus(
                    HttpStatusCode::is5xxServerError,
                    (request, response) -> {

                        throw new RuntimeException(
                                "External server error"
                        );
                    }
            )
            .body(PostDto.class);
}
```

### How It Works

```text
HTTP Request
     |
     v
External API
     |
     v
HTTP Response
     |
     +---- 2xx ----> Convert Response Body
     |
     +---- 4xx ----> Client Error Handling
     |
     +---- 5xx ----> Server Error Handling
```

### Recommended Practice

In a real application:

1. Create custom exceptions.
2. Handle external API failures in the service layer.
3. Use `@RestControllerAdvice` for centralized exception handling.
4. Return meaningful HTTP responses.
5. Avoid exposing sensitive external API details.

Example custom exception:

```java
public class ExternalApiException
        extends RuntimeException {

    public ExternalApiException(String message) {
        super(message);
    }
}
```

---

# 🧪 Testing with Postman

Postman was used to test the application's REST endpoints.

## Base URL

```text
http://localhost:8080/api/posts
```

---

## Test 1: Get All Posts

### Request

```http
GET http://localhost:8080/api/posts
```

### Expected Result

- HTTP status: `200 OK`
- Response: Array of posts.

---

## Test 2: Get Post by ID

### Request

```http
GET http://localhost:8080/api/posts/1
```

### Expected Result

- HTTP status: `200 OK`
- Response: A single post object.

---

## Test 3: Create a Post

### Request

```http
POST http://localhost:8080/api/posts
```

### Headers

```text
Content-Type: application/json
```

### Body

```json
{
  "userId": 1,
  "title": "My New Post",
  "body": "Learning Spring Boot REST Client"
}
```

### Expected Result

- HTTP status: `200 OK`
- Response: Simulated post object.

---

## Test 4: Update a Post

### Request

```http
PUT http://localhost:8080/api/posts/1
```

### Headers

```text
Content-Type: application/json
```

### Body

```json
{
  "userId": 1,
  "id": 1,
  "title": "Updated Post",
  "body": "Updated post body"
}
```

### Expected Result

- HTTP status: `200 OK`
- Response: Updated simulated post.

---

## Test 5: Delete a Post

### Request

```http
DELETE http://localhost:8080/api/posts/1
```

### Expected Result

- HTTP status: `200 OK`
- Response:

```text
Post deleted successfully
```

---

## Test 6: Filter Posts by User ID

### Request

```http
GET http://localhost:8080/api/posts/user/1
```

### Expected Result

- HTTP status: `200 OK`
- Response: Posts associated with user ID `1`.

---

# 📊 HTTP Methods Summary

| HTTP Method | Endpoint | Purpose |
|-------------|----------|---------|
| GET | `/api/posts` | Fetch all posts |
| GET | `/api/posts/{id}` | Fetch post by ID |
| POST | `/api/posts` | Create a post |
| PUT | `/api/posts/{id}` | Update a post |
| DELETE | `/api/posts/{id}` | Delete a post |
| GET | `/api/posts/user/{userId}` | Filter posts by user |

---

# 🧠 Key Concepts Learned

## RestClient

A modern synchronous HTTP client available in Spring Framework 6.1+.

## DTO Mapping

Converting JSON responses into Java objects using Jackson.

## Constructor Injection

Injecting the RestClient Bean into the service layer.

## HTTP Methods

Working with GET, POST, PUT, and DELETE.

## Path Variables

Using dynamic values in URL paths.

## Query Parameters

Passing filters and additional parameters to APIs.

## Request Headers

Sending authorization and custom headers.

## Response Handling

Using:

- `retrieve()`
- `body()`
- `toEntity()`
- `toBodilessEntity()`
- `exchange()`

## Exception Handling

Handling 4xx and 5xx errors from external APIs.

## Layered Architecture

Separating controllers, services, configuration, and DTOs.

---

# 🎯 Practice Tasks

## 🟢 Beginner Level

- [ ] Fetch all posts.
- [ ] Fetch a post by ID.
- [ ] Create a new post.
- [ ] Update a post.
- [ ] Delete a post.
- [ ] Convert JSON responses into DTOs.

## 🟡 Intermediate Level

- [ ] Fetch posts using query parameters.
- [ ] Add custom request headers.
- [ ] Return `ResponseEntity` from a controller.
- [ ] Handle 404 errors.
- [ ] Handle 500 errors.
- [ ] Create custom exceptions.
- [ ] Add centralized exception handling.

## 🔴 Advanced Level

- [ ] Create a reusable external API service.
- [ ] Add request and response logging.
- [ ] Write unit tests for the service layer.
- [ ] Mock RestClient in unit tests.
- [ ] Implement a custom external API error response.
- [ ] Add configuration properties for external API URLs.
- [ ] Implement a local database to save selected external posts.

---

# 💼 Real-World Use Cases

REST Clients are commonly used in:

### Payment Integration

```text
Spring Boot Application
          |
          v
Payment Gateway API
```

### Email Services

```text
Spring Boot Application
          |
          v
Email Service API
```

### Microservices

```text
User Service
     |
     v
REST Client
     |
     v
Order Service
```

### Third-Party Integrations

- Weather APIs
- Social media APIs
- Authentication services
- Shipping APIs
- External product APIs

---

# ❓ Interview Questions

1. What is a REST Client?
2. What is the difference between a REST API and a REST Client?
3. What is `RestClient` in Spring Boot?
4. When was `RestClient` introduced?
5. What is the purpose of `RestClient.builder()`?
6. What is the use of `baseUrl()`?
7. What does `.retrieve()` do?
8. What is the difference between `body(PostDto.class)` and `body(PostDto[].class)`?
9. What is the purpose of `toEntity()`?
10. What is the use of `toBodilessEntity()`?
11. What is the difference between `retrieve()` and `exchange()`?
12. How do you send a POST request using RestClient?
13. How do you send request headers?
14. How do you send query parameters?
15. How do you pass path variables?
16. How does Jackson convert JSON into Java objects?
17. How does RestClient handle 4xx and 5xx errors?
18. How can you customize error handling using `onStatus()`?
19. Why should external API calls be placed in the service layer?
20. What is the difference between a DTO and an Entity?

---

# 🚀 Future Improvements

The following features can be added in future versions of this project:

- [ ] Add request validation.
- [ ] Add global exception handling.
- [ ] Add custom API response objects.
- [ ] Add logging using SLF4J.
- [ ] Add unit testing with Mockito.
- [ ] Add integration testing.
- [ ] Configure external API URLs using `application.properties`.
- [ ] Add retry mechanisms.
- [ ] Configure request timeouts.
- [ ] Explore Spring WebClient.
- [ ] Explore OpenFeign.
- [ ] Add database integration using Spring Data JPA.

---

# 📚 Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/)
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/)
- [RestClient Documentation](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-restclient)
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
- [Postman Documentation](https://learning.postman.com/docs/)

---

# 👨‍💻 Author

**Divanshu Gaur**

This project was created for learning and practicing REST Client integration with Spring Boot.

---

# ⭐ Conclusion

This project demonstrates how to consume external REST APIs using Spring Boot's `RestClient`.

By completing this project, you gain practical knowledge of:

- External API integration.
- HTTP methods.
- JSON serialization and deserialization.
- DTO-based data transfer.
- Query parameters and headers.
- Response handling.
- Basic external API error handling.
- Layered backend architecture.

The next step is to enhance this project with production-level error handling, timeouts, retries, testing, and advanced HTTP clients.