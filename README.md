# Project Title

## Overview
A Spring Boot application using Java 21 and Gradle with MongoDB as the database. It features a `members` collection.

## Requirements
- Java 21
- Gradle
- MongoDB (running on `mongodb://localhost:27017`)

## Setup

1. **Install Prerequisites**:
   - [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
   - [Gradle](https://gradle.org/install/)
   - [MongoDB](https://www.mongodb.com/try/download/community)

2. **Create `members` Collection**:
   ```bash
   mongo
   use mydatabase
   db.createCollection("members")
