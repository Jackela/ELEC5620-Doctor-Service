# Doctor Service (ELEC5620-Microservices)

Part of a medical application built on a microservices architecture.

## 📖 Project Goal & Motivation

This project is a key component of a larger medical application, designed and built based on a microservices architecture. The goal was to create a robust and scalable backend service specifically for doctor-related functionalities within the system.

## 🏗️ Architecture & Technical Highlights

*   **Microservices Architecture**: Implemented as a distinct microservice, adhering to the principles of loose coupling and high cohesion.
*   **Database-per-Service Pattern**: Utilizes a dedicated database (likely **DynamoDB** as per the overall project description) for its domain, ensuring data independence and flexibility.
*   **API-Driven**: Designed to be orchestrated by a frontend application via an **API Gateway**, providing a clear interface for interaction.
*   **Java & Spring Boot**: Developed using **Java** and the **Spring Boot** framework, leveraging its capabilities for rapid development and robust enterprise-grade applications.
*   **AI Integration**: Incorporates **Langchain4j** for integrating AI capabilities, enhancing the service with intelligent features.

## 👤 My Role & Contributions

As a key backend developer and Agile Project Manager, I utilized Jira to facilitate team collaboration. My technical contributions included developing significant portions of both the Patient-Service and Doctor-Service using Java and Spring Boot, and integrating AI capabilities with Langchain4j.

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Langchain4j](https://img.shields.io/badge/Langchain4j-007BFF?style=for-the-badge)

## 🚀 Installation & Usage

### 1. Prerequisites

*   Java Development Kit (JDK) 17 or higher.
*   Maven or Gradle (depending on project build system).
*   AWS CLI configured (if interacting with AWS services directly).

### 2. Build the Project

Navigate to the `doctorService` subdirectory (or the root of the service if it's the top-level) and build the project:

```bash
# If using Gradle
./gradlew build
```

### 3. Run the Service

```bash
# If using Gradle
./gradlew bootRun
```

### 4. API Endpoints

Once running, the service will expose RESTful API endpoints. Refer to the project's API documentation (if available) for details on how to interact with it.

## 📄 License

This project is licensed under the MIT License.

---

**MIT License**

Copyright (c) 2024 Weixuan Kong

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.