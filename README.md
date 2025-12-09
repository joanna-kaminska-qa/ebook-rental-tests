# EBook Rental – Automated Test Project

![Java](https://img.shields.io/badge/Java-17%2F21-blue)
![Gradle](https://img.shields.io/badge/Gradle-8-green)
![Selenide](https://img.shields.io/badge/Selenide-6.17.2-orange)
![Postman](https://img.shields.io/badge/Postman-API-red)
![JUnit](https://img.shields.io/badge/JUnit-5.10-purple)
![License: MIT](https://img.shields.io/badge/License-MIT-yellow)
![Status: Completed](https://img.shields.io/badge/Status-Completed-brightgreen)

Automated testing project for the **EBook Rental** application — a library system enabling users to register, log in, browse book catalogues, manage book copies, and handle rentals.

The project contains:
- Frontend UI automated tests (Selenide, Java)
- Backend API tests (Postman collection)
- Documentation of the application, test cases, and testing approach

Designed as the final project for the **Kodilla Automated Tester** course.

---

## Description

This repository demonstrates a complete end-to-end testing workflow for a web application:

### Backend (API) Testing – Postman

Located in `backend-tests-postman/`  
Includes:
- Postman collection covering:
    - User registration
    - User authentication
    - CRUD operations on Books
    - CRUD operations on Copies
    - Rental operations
- Prepared test documentation: `Test Cases.pdf` and `Test Cases.xlsx`

### Frontend (UI) Testing – Selenide

Located in `frontend-tests-selenide/`  
Covers full UI workflow:
- Login & Registration
- Managing book titles and copies
- Performing rentals
- Negative test cases (validation, incorrect data)

Includes:
- Base test architecture (`BaseTest`, `AuthenticatedBaseTest`)
- Data preparation steps (creating book, creating copy, renting)
- Automatic browser setup using WebDriverManager
- Prepared test documentation: `Test Cases.pdf` and `Test Cases.xlsx`

---

## Project Structure
```
ebook-rental-tests/
│ LICENSE
│ structure.txt
│
├── backend-tests-postman/
│ ├── Ebook Rental Tests.postman_collection.json
│ ├── Test Cases.pdf
│ └── Test Cases.xlsx
│
└── frontend-tests-selenide/
│ build.gradle
│ gradlew
│ gradlew.bat
│ settings.gradle
│
├── src/main/java/com/github/joannakaminska/
│ └── Main.java
│
└── src/test/java/com/github/joannakaminska/
│ AuthenticatedBaseTest.java
│ BaseTest.java
│ BaseTestWithBook.java
│ BaseTestWithBookAndCopy.java
│ BaseTestWithBookAndCopyAndRent.java
│
├── login/
│ ├── LoginWithInvalidCredentials.java
│ └── LoginWIthValidCredentials.java
│
├── register/
│ ├── RegisterNewUser.java
│ ├── RegisterUserWithoutFullData.java
│ └── RegisterUserWithTakenLogin.java
│
├── titlesCatalog/
│ ├── AddBook.java
│ ├── EditBook.java
│ ├── EditBookWithInvalidDataTest.java
│ ├── DeleteBook.java
│ └── DeleteBookWithCopy.java
│
├── copiesList/
│ ├── AddCopy.java
│ ├── EditCopy.java
│ ├── DeleteCopy.java
│ └── DeleteCopyWithRentHistory.java
│
└── rentsList/
├── AddRent.java
├── AddRentWithIncompleteData.java
├── EditRent.java
└── DeleteRent.java
```
---

## Getting Started

### Requirements

- Java 17 or 21
- Gradle (wrapper included)
- IntelliJ IDEA
- Chrome browser

### Running Frontend Tests (Selenide)

**Linux / macOS**
```bash
./gradlew clean test
```
**Windows**
```bash
gradlew clean test
```

*Selenide + WebDriverManager will automatically manage browser drivers.*

### Running Backend Tests (Postman)

1. Open `backend-tests-postman/Ebook Rental Tests.postman_collection.json`
2. Import into Postman
3. Configure environment variables (base URL, credentials)
4. Run the collection using:
    - Postman Collection Runner
    - or Newman (optional)

---

## Dependencies (Frontend – build.gradle)

```gradle
plugins {
    id 'java'
}

group = 'com.github.joannakaminska'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation platform('org.junit:junit-bom:5.10.0')
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'com.codeborne:selenide:6.17.2'
    testImplementation 'io.github.bonigarcia:webdrivermanager:5.8.0'
}

test {
    useJUnitPlatform()
}
```

Key technologies:
- **Selenide** – UI automation
- **WebDriverManager** – automatic driver handling
- **JUnit 5** – test framework

---

## Test Suites Overview

### Login
- Valid login
- Invalid login

### Registration
- Register new user
- Register with missing data
- Register with an existing username

### Titles Catalog (Books)
- Add new book
- Add book with invalid data
- Edit book
- Delete book
- Prevent deleting book with copies

### Copies List
- Add copy
- Edit copy
- Delete copy
- Prevent deleting copy with rental history

### Rentals
- Add new rent
- Add rent with incomplete data
- Edit rent
- Delete rent

### Backend Test Overview (Postman)
Covers all API operations:  
`/users`, `/login`, `/titles`, `/copies`, `/rents`

Each request includes:
- Preconditions
- Validations
- Positive & negative scenarios

---

## Version History

| Version | Changes |
|---------|---------|
| 0.1     | Initial upload |
| 0.2     | Added full frontend Selenide tests + Postman tests |
| 0.3     | Added documentation & README |

---

## Author

Joanna Kamińska  
GitHub: [joanna-kaminska-qa](https://github.com/joanna-kaminska-qa)

---

## License


This project is licensed under the MIT License — see the LICENSE file for details.

---

## Acknowledgments

- Kodilla Automated Tester course
- Selenide documentation
- Postman documentation
- WebDriverManager project
- Stack Overflow community
- IntelliJ IDEA

