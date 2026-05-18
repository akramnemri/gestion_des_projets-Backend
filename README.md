# Gestion des Projets avec Budgets et Ressources

A full-stack **Project Management System** built with **Spring Boot 3.x** and **Angular 17+**, featuring budget tracking, resource allocation, role-based access control, and a sleek dark-themed control-center UI.

![Tech Stack](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot)
![Angular](https://img.shields.io/badge/Angular-17+-DD0031?logo=angular)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql)
![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk)
![TypeScript](https://img.shields.io/badge/TypeScript-5.x-3178C6?logo=typescript)

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Backend Repository](#backend-repository)
  - [Database Setup](#database-setup)
  - [Backend Setup](#backend-setup)
  - [API Documentation](#api-documentation)
- [Frontend Repository](#frontend-repository)
  - [Frontend Setup](#frontend-setup)
- [Running the Application](#running-the-application)
- [Docker Deployment](#docker-deployment)
- [Business Rules](#business-rules)
- [Role-Based Access Control](#role-based-access-control)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This application provides a complete solution for managing projects, tasks, employees, and resources with financial tracking. It supports:

- **Project lifecycle management** with budget control
- **Task assignment** to employees with priority and status tracking
- **Resource allocation** to tasks with quantity and cost calculation
- **Financial reports** per project (budget vs actual costs)
- **Role-based UI** — different controls for managers, workers, and admins
- **Real-time validation** with inline error display
- **Animated starfield background** for a premium control-center feel

---

## Features

### Core Modules
| Module | Description |
|--------|-------------|
| **Dashboard** | System overview with stat cards, task status breakdown, over-budget alerts |
| **Projets** | CRUD projects with budget, dates, status; financial reports; detail panels with inline task creation |
| **Tâches** | CRUD tasks with project/responsable assignment, priority, status, deadline |
| **Employés** | Employee management with roles (Chef de Projet, Développeur, Designer, DevOps, Admin) |
| **Ressources** | Resource inventory with cost and availability tracking |
| **Allocations** | Assign resources to tasks with quantity |

### Validation & Business Rules
- ✅ `dateFin > dateDebut` for projects
- ✅ `deadline >= projet.dateDebut` for tasks
- ✅ `deadline >= today` on task creation
- ✅ `budget > 0`
- ✅ `quantite > 0` for allocations
- ✅ All errors rendered inline in the UI

### Role-Based Access
| Role | Permissions |
|------|------------|
| **ADMIN** | Full CRUD on all entities |
| **CHEF_DE_PROJET** | Create/edit projects, tasks, allocations; view reports |
| **DEVELOPPEUR / DESIGNER / DEVOPS** | Update status of assigned tasks only |

---

## Tech Stack

### Backend
- **Spring Boot 3.x** (Java 21)
- **Spring Data JPA** with Hibernate
- **Spring Validation** (`jakarta.validation`)
- **SpringDoc OpenAPI** 2.8.6 (Swagger UI)
- **MySQL** 8.0 via XAMPP
- **Maven** build system

### Frontend
- **Angular 17+** standalone components (no NgModules)
- **TypeScript 5.x**
- **RxJS** for reactive HTTP
- **CSS3** with custom design system
- **Poppins + Open Sans** typography

### DevOps
- **Docker** + Docker Compose (optional)
- **Nginx** for frontend static serving

---

## Architecture

```
gestion-projets-budgets/
├── backend/
│   └── src/main/java/com/example/demo/
│       ├── entity/          # JPA Entities (Projet, Tache, Employe, Ressource, TacheRessource)
│       ├── dto/             # Data Transfer Objects
│       ├── mapper/          # Manual DTO <-> Entity mappers (no MapStruct, no Lombok)
│       ├── repository/      # Spring Data JPA interfaces
│       ├── service/         # Business logic + validation rules
│       ├── controller/      # REST endpoints with @CrossOrigin
│       ├── exception/       # GlobalExceptionHandler + BusinessValidationException
│       ├── enums/           # Java enums for status, roles, priorities
│       └── GestionDeProjectsApplication.java
│
├── frontend/
│   └── src/app/
│       ├── components/      # Standalone components (Projets, Taches, Employes, etc.)
│       ├── services/        # HTTP services (HttpClient)
│       ├── shared/          # Utils (role helpers, enum formatters)
│       ├── app.component.ts # Root with starfield + tabs
│       ├── app.config.ts    # provideHttpClient()
│       └── styles.css       # Global design system
│
├── docker-compose.yml
└── README.md
```

---

## Prerequisites

| Tool | Version | Purpose |
|------|---------|---------|
| Java JDK | 21 | Backend runtime |
| Maven | 3.9+ | Backend build |
| Node.js | 18+ | Frontend runtime |
| Angular CLI | 17+ | Frontend scaffolding |
| MySQL | 8.0 | Database (via XAMPP or standalone) |
| XAMPP | 3.3+ | Optional: MySQL + phpMyAdmin |

---

## Backend Repository

### Database Setup

#### Option A: XAMPP (Recommended for local dev)

1. Start **Apache** and **MySQL** from XAMPP Control Panel
2. Open [phpMyAdmin](http://localhost/phpmyadmin)
3. Create database: `gestion_projets`
4. Import the schema (or let Hibernate create it):

```sql
CREATE DATABASE IF NOT EXISTS gestion_projets CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

#### Option B: Standalone MySQL

```bash
mysql -u root -p -e "CREATE DATABASE gestion_projets;"
```

#### Database Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_projets
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> **Note:** Set `ddl-auto=update` for first run to auto-create tables, then switch to `none`.

### Backend Setup

```bash
cd backend

# Build
mvn clean install

# Run
mvn spring-boot:run
```

The backend starts on **http://localhost:8081**

Verify:
- API: `curl http://localhost:8081/api/projets`
- Swagger UI: http://localhost:8081/swagger-ui/index.html

### API Documentation

Interactive API docs available via Swagger UI:

**http://localhost:8081/swagger-ui/index.html**

### Endpoints

| Resource | Endpoint | Methods |
|----------|----------|---------|
| Projets | `/api/projets` | GET, POST, PUT `/{id}`, DELETE `/{id}` |
| Tâches | `/api/taches` | GET, POST, PUT `/{id}`, DELETE `/{id}` |
| Employés | `/api/employes` | GET, POST, PUT `/{id}`, DELETE `/{id}` |
| Ressources | `/api/ressources` | GET, POST, PUT `/{id}`, DELETE `/{id}` |
| Allocations | `/api/allocations` | GET, POST, DELETE `/{id}`, GET `/tache/{tacheId}` |
| Rapports | `/api/rapports/projet/{projetId}` | GET |

### Backend Entities

| Entity | Key Fields | Relationships |
|--------|-----------|---------------|
| **Projet** | id, nom, dateDebut, dateFin, budget, statut | One-to-Many → Tache |
| **Tache** | id, description, etat, priorite, deadline | Many-to-One → Projet, Employe |
| **Employe** | id, nom, email, role, equipe | One-to-Many → Tache |
| **Ressource** | id, nom, type, cout, disponibilite | Many-to-Many → Tache (via TacheRessource) |
| **TacheRessource** | id, quantite | Join table |

### Enums

| Enum | Values |
|------|--------|
| `StatutProjet` | PLANIFIE, EN_COURS, EN_PAUSE, TERMINE, ECHOUE |
| `EtatTache` | A_FAIRE, EN_COURS, EN_PAUSE, TERMINE, BLOQUE |
| `PrioriteTache` | BASSE, MOYENNE, HAUTE, CRITIQUE |
| `DisponibiliteRessource` | DISPONIBLE, NON_DISPONIBLE, EN_COURS_DE_LIVRAISON, RESERVEE |
| `RoleEmploye` | CHEF_DE_PROJET, DEVELOPPEUR, DESIGNER, DEVOPS, ADMIN |

---

## Frontend Repository

### Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Start dev server
ng serve
```

The frontend starts on **http://localhost:4200**

### Frontend Structure

| Path | Description |
|------|-------------|
| `src/app/components/` | Standalone feature components (Projets, Taches, Employes, Ressources, Allocations, Dashboard) |
| `src/app/services/` | HTTP services for each entity |
| `src/app/shared/utils.ts` | Role helpers, enum formatters, badge colors |
| `src/app/app.component.ts` | Root shell with starfield canvas, top bar, tab navigation |
| `src/styles.css` | Global design system with CSS custom properties |

### Design System

- **Typography**: Poppins (headings) + Open Sans (body)
- **Palette**: Deep slate, charcoal, cyan accent, steel blue
- **Background**: Animated starfield with shooting stars
- **Layout**: Centered container with glass-morphism panels

---

## Running the Application

1. Ensure MySQL is running
2. Start the backend (`mvn spring-boot:run` in `backend/`)
3. Start the frontend (`ng serve` in `frontend/`)
4. Open http://localhost:4200 in your browser
5. Select a user from the top-right dropdown to begin

---

## Docker Deployment

### Build & Run with Docker Compose

```bash
# From project root
docker-compose up --build
```

Services:
- **MySQL**: `localhost:3306`
- **Backend**: `localhost:8081`
- **Frontend**: `localhost:4200` (or `80` with Nginx)

### Dockerfiles

#### Backend (`backend/Dockerfile`)
```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### Frontend (`frontend/Dockerfile`)
```dockerfile
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist/frontend/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
```

---

## Business Rules

Validation is enforced **both frontend and backend**:

| Rule | Scope | Error Message Key |
|------|-------|-------------------|
| Budget > 0 | Projet | `budget` |
| Date fin > Date début | Projet | `dateFin` |
| Deadline ≥ Date début projet | Tâche | `deadline` |
| Deadline ≥ Aujourd'hui (create) | Tâche | `deadline` |
| Quantité > 0 | Allocation | `quantite` |

Errors are returned as `Map<String, String>` with HTTP 400 and rendered inline in the UI.

---

## Role-Based Access Control

No Spring Security — access control is handled via **frontend user selection**:

1. Select a user from the top-bar dropdown
2. UI controls show/hide based on role
3. Backend validates business rules regardless of role

| Role | Can Create | Can Edit | Can Delete |
|------|-----------|----------|------------|
| ADMIN | All | All | All |
| CHEF_DE_PROJET | Projets, Tâches, Allocations | Projets, Tâches, Allocations | — |
| WORKER | — | Own task status only | — |

---

## Coding Standards

- **No Lombok** — all getters/setters written manually
- **No MapStruct/ModelMapper** — manual static mappers
- **No JSP/JSTL** — pure REST API + Angular SPA
- **No manual .jar files** — Maven dependencies only
- **Angular standalone components** — no NgModules
- **ChangeDetectorRef.detectChanges()** after every HTTP subscribe

---

## Troubleshooting

### Backend won't start
- Check MySQL is running on port 3306
- Verify `application.properties` credentials
- Ensure database `gestion_projets` exists

### Frontend shows CORS errors
- Verify `@CrossOrigin(origins = "http://localhost:4200")` on controllers
- Check backend is running on port 8081

### Validation errors not showing
- Ensure backend returns HTTP 400 with `Map<String,String>` body
- Check frontend `handleError()` catches `err.error` correctly
- Verify `GlobalExceptionHandler` catches `BusinessValidationException`

### "Row was already updated" Hibernate error
- Ensure `entity.setId(null)` before `repo.save()` on create operations

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feat/amazing-feature`)
3. Commit with conventional messages:
   - `feat: add new feature`
   - `fix: resolve bug`
   - `chore: update config`
   - `docs: update README`
4. Push to branch and open a Pull Request

---

## License

This project is built for educational purposes as part of a university JEE course evaluation.
