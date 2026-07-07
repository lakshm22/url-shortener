# URL Shortener 

A backend URL shortening service built using Spring Boot, PostgreSQL, JPA/Hibernate, and REST APIs.

This project was created as a hands-on learning project to understand backend development concepts, including REST APIs, layered architecture, database persistence, dependency injection, and object-relational mapping.

---

## Features

* Shorten long URLs into compact short codes
* Redirect users using generated short codes
* Persistent database storage using PostgreSQL
* Base62 encoding for cleaner URL generation
* REST API endpoints for URL creation and retrieval
* Layered backend architecture

---

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Hibernate
* Maven
* Postman (for API testing)

---

## Project Structure

```text
src/main/java
│
├── controller
│      UrlController.java
│
├── service
│      UrlService.java
│
├── repository
│      UrlRepository.java
│
├── entity
│      UrlMapping.java
│
├── dto
│      ShortenRequest.java
│      ShortenResponse.java
│
└── utility
       Base62Encoder.java
```

---

## Architecture

```text
Postman
   ↓
Controller
   ↓
DTO
   ↓
Service
   ↓
Repository
   ↓
Hibernate/JPA
   ↓
PostgreSQL

Entity → Database model representation
Utility → Shared helper function
```

---

## API Endpoints

### Create Short URL

**POST**

```http
/api/v1/urls
```

Example:

Request Body:

```json
{
   "url":"https://google.com"
}
```

Response:

```json
{
   "shortCode":"a"
}
```

---

### Redirect URL

**GET**

```http
/{code}
```

Example:

```http
localhost:8080/a
```

Redirects user to:

```text
https://google.com
```

---

## Database Schema

Table: `url_mapping`

| Column     | Type   |
| ---------- | ------ |
| id         | Long   |
| short_code | String |
| url        | String |

---

## Concepts Learned

Through this project, I learned:

* REST API development
* GET and POST mappings
* DTOs (Data Transfer Objects)
* Controller layer, Repository layer, and Service layer
* Dependency Injection
* Spring annotations
* JPA and Hibernate
* Repository pattern
* Entity-to-table mapping
* PostgreSQL integration
* Base62 encoding
* Debugging runtime issues
* Layered backend architecture

---

## Future Improvements

* URL click analytics
* Custom aliases
* Expiration support
* URL validation
* User authentication
* Deployment

---

## Run Locally

Clone the repository:

```bash
git clone <repository-url>
```

Configure database properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/urlshortener
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Run:

```bash
mvn spring-boot:run
```

---

## Design Decisions and Technology Choices

This project was built not only to create a URL shortener, but also to understand the architecture and reasoning behind real backend systems.

---

### Why Java + Spring Boot?

I chose Java and Spring Boot because the goal of this project was to learn backend fundamentals in a structured way.

Spring Boot provides:

* Dependency Injection
* REST API support
* Built-in server configuration
* Database integration through JPA/Hibernate
* Layered architecture support

Unlike smaller setups where logic can quickly become tightly coupled, Spring naturally encourages separation of concerns:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

This project helped explore concepts that are widely used in enterprise applications such as:

* Dependency Injection
* Inversion of Control (IoC)
* REST APIs
* ORM (Object-Relational Mapping)
* Repository pattern

I chose Java specifically because of its strong type system and widespread usage in backend systems.

---

### Why PostgreSQL?

Initially, URL mappings were stored using a HashMap:

```text
ShortCode → URL
```

This worked temporarily but introduced a major limitation:

```text
Application restart
        ↓
All data lost
```

I chose PostgreSQL to provide persistent storage, scalability, efficient querying, and ease of maintenance.

Benefits:

* Data survives application restarts
* Supports indexing and efficient lookups
* Relational structure fits URL mapping naturally
* Scales better than in-memory storage
* Integrates smoothly with Spring Data JPA

The URL data eventually becomes:

```text
id | short_code | url
```

Instead of existing only in memory.

---

### Why JPA/Hibernate?

Without an Object-Relational Mapping (ORM), database operations would require manually writing SQL:

```sql
INSERT INTO url_mapping (...);

SELECT * FROM url_mapping
WHERE short_code='abc123';
```

JPA with Hibernate allows working with Java objects instead:

```java
urlRepository.save(mapping);

urlRepository.findByShortCode(code);
```

Hibernate handles the SQL generation internally.

Benefits:

* Less boilerplate code
* Cleaner architecture
* Easier maintenance
* Faster development

---

### Why Base62 Encoding?

The initial implementation generated short codes using:

```java
UUID.randomUUID()
```

While UUID provides uniqueness, it is not ideal for URL shortening because:

* Generated values are relatively long
* URLs become less readable
* Not similar to production URL shorteners

Example:

```text
UUID:
f82d91
9ac72b
```

Instead, I used Base62 encoding.

Base62 uses:

```text
0–9
a–z
A–Z
```

Total:

```text
62 characters
```

The database generates numeric IDs:

```text
1
2
3
...
```

These IDs are converted into compact strings:

```text
1 → 1
10 → a
61 → Z
62 → 10
```

Benefits:

* Shorter URLs
* Human-readable codes
* Deterministic generation
* Similar to real URL shortening services

---

### Why not Node.js + Express + Firebase?

This project could also have been implemented using:

* Node.js
* Express
* Firebase

That approach would likely reduce setup complexity and speed up development.

However, my goal was to understand production-style backend architecture rather than the quickest working solution.

Using Spring Boot + PostgreSQL exposed concepts such as:

* Layered architecture
* Dependency Injection
* ORM frameworks
* Database relationships
* Repository patterns

This is why I chose Spring Boot + PostgreSQL for this particular project.

---

### Why Postman for API Testing?

This project exposes REST API endpoints that need to receive requests and return responses.

During development, I needed a tool to test API behaviour without building a frontend application.

I chose Postman because it allows:

* Sending HTTP requests easily
* Testing GET and POST endpoints
* Passing JSON request bodies
* Viewing response status codes
* Inspecting API responses
* Iterating quickly during debugging

Example request:

```http
POST /api/v1/urls
```

Request body:

```json
{
   "url":"https://google.com"
}
```

Response:

```json
{
   "shortCode":"a"
}
```

Postman also helped me identify and debug issues during development, such as:

* Incorrect JSON field names
* Null request values
* HTTP 404 error code
* Internal server errors
* Request/response mismatches

Without Postman, testing would require either:

* Creating a frontend interface
* Writing additional client code
* Using command-line tools such as curl

Postman provided a faster feedback loop while developing the backend independently.

---

## Usage Notice

This repository is shared for learning, reference, and portfolio purposes only.

Please do not copy, redistribute, or reuse this code in your own projects, assignments, or commercial work.

Feel free to study the implementation and learn from it, but do not submit or present this work as your own.
