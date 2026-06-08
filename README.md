# Trade Settlement System – Cloud Native Microservices POC

## Overview

This project is a cloud-native trade settlement Proof of Concept (POC) built using AWS serverless and container technologies.

The system demonstrates:
- Serverless APIs using AWS Lambda
- DynamoDB integration
- Java Spring Boot matching engine
- Docker containerization
- ECS Fargate deployment
- Lambda to ECS microservice communication

---

# Architecture

```text
Client
   ↓
API Gateway
   ↓
Lambda Order Service (Node.js)
   ↓
DynamoDB
   ↓
HTTP Call
   ↓
Java Matching Engine
   ↓
Docker + ECS Fargate
```

---

# Tech Stack

| Area | Technology |
|---|---|
| Backend APIs | Node.js |
| Matching Engine | Java Spring Boot |
| Database | DynamoDB |
| Serverless | AWS Lambda |
| API Layer | API Gateway |
| Containerization | Docker |
| Container Registry | Amazon ECR |
| Container Hosting | ECS Fargate |
| IaC | AWS SAM |

---

# Features

## Order Service
- Create Order API
- Get All Orders API
- Get Order By ID API
- DynamoDB persistence
- Input validation and error handling

## Matching Engine
- BUY/SELL order processing
- REST API based matching
- Heap/Priority Queue based matching logic
- Dockerized Spring Boot service

## Cloud Features
- Lambda → ECS communication
- ECS deployment using Fargate
- Docker image deployment through ECR
- CloudWatch logging

---

# API Examples

## Create Order

POST /order

### Request

```json
{
  "type": "BUY",
  "price": 100,
  "quantity": 2,
  "userId": "U1"
}
```

### Response

```json
{
  "message": "Order created successfully",
  "order": {
    "orderId": "123",
    "type": "BUY",
    "price": 100,
    "quantity": 2
  },
  "matchingResult": {
    "message": "Order processed successfully"
  }
}
```

---

# Project Structure

```text
project-root/
│
├── infra/
│   └── template.yaml
│
├── services/
│   ├── order-service/
│   └── matching-engine/
│
└── README.md
```

---

# Key Learnings

- Serverless vs containerized applications
- Docker image lifecycle
- ECS Fargate deployment flow
- Security groups and networking
- Microservice communication
- Infrastructure as Code using SAM
- Distributed system basics

---

# Challenges Solved

## ECS ARM/AMD64 Issue

Apple Silicon Docker image compatibility issue fixed using:

```bash
docker buildx build --platform linux/amd64 -t matching-engine .
```

## ECS Networking

Resolved ECS public access issue by configuring Security Group inbound rules for port 8080.

## Lambda → ECS Communication

Implemented REST-based communication between serverless and containerized services.

---

# Future Improvements

- Add Application Load Balancer (ALB)
- Add authentication and authorization
- Add asynchronous communication
- Persist matching engine state
- Add CI/CD pipeline
- Add unit/integration tests

---

# Cleanup

To avoid AWS billing after testing:

```bash
sam delete
```

Delete:
- ECS Service
- ECS Cluster
- ECR Repository

---

# Conclusion

This project demonstrates a practical cloud-native microservice architecture using AWS serverless and container services while exploring real-world deployment, networking, and distributed system concepts.
