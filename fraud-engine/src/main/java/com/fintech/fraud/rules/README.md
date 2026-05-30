# 🛡️ Fraud Detection System

A real-time, rule-based fraud detection system built with **Java + Spring Boot** that evaluates banking transactions using a modular and extensible decision engine.

This project simulates core concepts used in modern fintech fraud platforms such as **transaction scoring, rule evaluation, and risk-based decision systems**.

---

## 🚀 Overview

The system processes financial transactions in real time and evaluates them through a **pluggable fraud rule engine**. Each transaction is analyzed, scored, and classified into a risk level that determines the final outcome (approved, flagged, or rejected).

The main goal of this project is to demonstrate:

- Clean backend architecture
- Domain-driven design principles
- Extensible rule-based systems
- Real-time transaction processing

---

## ✨ Key Engineering Highlights

- 🧠 **Modular Fraud Rule Engine**  
  Independent rule components (Amount, Country, Velocity) designed for easy extension without modifying core logic.

- ⚡ **Real-Time Transaction Evaluation Pipeline**  
  Transactions are processed and evaluated immediately upon submission.

- 🧩 **Database-Driven Rule Configuration**  
  Fraud rules are stored and managed in the database, allowing runtime changes without redeployment.

- 📊 **Explainable Risk Scoring System**  
  Each decision includes a score and human-readable reasoning for transparency.

- 🚨 **Fraud Alert Generation**  
  High-risk transactions automatically trigger fraud alerts.

- 🏗️ **Clean Layered Architecture**  
  Clear separation between API layer, service layer, fraud engine, and persistence layer.

---

## 🧱 Architecture

### High-Level Flow


TransactionController
        ↓
TransactionService
        ↓
FraudEngine
        ↓
Rule Pipeline
 (Amount / Country / Velocity)
        ↓
Risk Scoring Engine
        ↓
Decision Output
        ↓
Persistence Layer
 (Transactions / Fraud Alerts / Evaluations)

---

## 🧠 Design Philosophy

- Rules are **independent and stateless**, enabling horizontal scalability of logic.
- Fraud evaluation is **deterministic and explainable**, avoiding black-box systems.
- Business logic is separated from infrastructure concerns.
- The system is designed for **modularity, maintainability, and testability**.
- Rule configuration is externalized to allow runtime tuning without redeployment.

---

## 🧩 Fraud Rules

### 💰 Amount Rule
- Transactions above **10,000 → HIGH risk**
- Transactions above **5,000 → MEDIUM risk**

---

### 🌍 Country Rule
- Transactions originating from predefined high-risk countries increase risk score.

---

### ⚡ Velocity Rule
- Detects multiple transactions performed within a short time window to identify abnormal behavior patterns.

---

## 📊 API Endpoints

### 🔹 Process Transaction

**POST** `/transactions`

#### Request

```json
{
  "customerId": "uuid",
  "accountId": "uuid",
  "amount": 12000,
  "currency": "EUR",
  "country": "NG",
  "merchant": "Amazon",
  "ipAddress": "192.168.1.1"
}

```

#### Response

```json
{
  "riskScore": 70,
  "riskLevel": "HIGH",
  "finalStatus": "REJECTED",
  "reasons": [
    "High amount transaction",
    "High-risk country detected"
  ]
}

```

### Get Transactions

- GET /transactions
  Returns all processed transactions with their fraud evaluation results.

### 🗃️ Data Model

- Core entities:

  - Customer
  - Account
  - Transaction
  - FraudAlert
  - FraudEvaluation
  - FraudRuleConfig

### 🐳 Running the Project
- 1. Start PostgreSQL
   docker-compose up -d

- 2.Run Backend
   ./mvnw spring-boot:run

### 📖 API Documentation

- Swagger UI available at: http://localhost:8081/swagger-ui/index.html

### 🛠️ Tech Stack
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Swagger / OpenAPI

### 🎯 Purpose

This project was built as a backend engineering portfolio project to demonstrate:

- System design in Spring Boot
- Rule-based decision engines
- Clean architecture principles
- Real-time transaction processing
- Domain-driven design thinking


### ⚖️ Trade-offs & Simplifications
- Monolithic architecture (no microservices)
- Simplified velocity detection (no event streaming)
- No caching layer for rules (DB-based lookup)
- No distributed processing (kept intentionally simple for clarity)

These trade-offs were made to prioritize readability, maintainability, and engineering clarity.


