# Tech Stack (Backend only)

# Base
Spring Boot
Postgres
Spring security
Eureka
Spring cloud API gateway

# Distributed Tracing
Sleuth
Zipkin

# Testing
Junit 5
AssertJ
Mockito
H2

# Build and deployement
Gradle
Docker 

# How to Build and Run

1. Need to build the 3 projects individually using 'gradlew clean build' (Multi module capability not working as of now)
2. run 'Docker compose up --build' (need to have docker desktop)

Zipkin -> http://localhost:9411/zipkin/
Eureka -> http://localhost:8761/
API Gateway -> http://localhost:8083 

Basic auth user and password -> 'user / user' or 'admin / admin'


List all product pricings -> http://localhost:8083/api/v1/prices/product
List all product pricings by Product -> http://localhost:8083/api/v1/prices/product/{productId}
Show price calculation for a single 1 unit -> http://localhost:8083/api/v1/prices/product/{productId}/unit/{uomId}/qty/{qty}
Show price calculation for a single 1 to n unit -> http://localhost:8083/api/v1/prices/list/product/{productId}/unit/{uomId}/qty/{qty}