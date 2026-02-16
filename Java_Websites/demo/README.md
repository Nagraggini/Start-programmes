# Spring Boot Render Demo

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
```

## Dynamic Web Application Milestone

My first dynamic web application is now live! 🚀  
Deployed on February 15, 2026 at 10:56 AM.

It was running locally before, and now it's accessible online.

[Visit my site!](https://spring-render-s4f4.onrender.com/boots.html)

# I used:

## Backend (Server-side)

- Spring Boot application (DemoApplication.java)
- Controller classes, e.g., UserController (if present), handling requests like /login, /register, etc.
- Model classes, e.g., User, Product, representing the data.
- H2 database + JPA repositories for storing and querying data.
- Business logic, e.g., checking user.name values or saving users.

## Frontend (User Interface)

- HTML templates (.html files in resources/templates)
- Thymeleaf attributes, e.g., th:text, th:case, for dynamically rendering data from the backend.
- CSS / Bootstrap for styling.
- JavaScript for interactivity.

# Structure

Frontend (HTML + Thymeleaf)  
│  
▼  
Spring Controller  
│  
▼  
Service / Business Logic  
│  
▼  
H2 Database (data)

## Lokális futtatás

### Maven

```bash
./mvnw clean spring-boot:run
```

### Docker használata

```bash
docker build -t springboot-render .
docker run -p 8080:8080 springboot-render
```

## Endpoints

| Endpoint           | Description                              |
| ------------------ | ---------------------------------------- |
| `/`                | Main – start of the website              |
| `/boots.html`      | Example static/dynamic page              |
| `/users/view`      | List of users (if UserController exists) |
| `/actuator/health` | Health check endpoint (Spring Actuator)  |

# Technologies

- Java 17
- Spring Boot
- Thymeleaf
- H2 Database
- Docker

## //////////////////////////////////////////////////////////////////////////////////////////////

Hungarian version - Magyar verzió

# Spring Boot Render Demo

Ez egy egyszerű Spring Boot webalkalmazás, ami Thymeleaf sablonokat és beágyazott H2 adatbázist használ.

A projekt célja, hogy bemutassa a dinamikus webalkalmazás készítését Spring Boot-tal, és hogy deploy-olható legyen Dockerrel Render.com-on.

## Dinamikus webalkalmazás mérföldkő

Az első dinamikus webalkalmazásom most már élőben működik! 🚀
Telepítve: 2026. február 15., 10:56.

Korábban csak lokálisan futott, most már online is elérhető.

[Látogasd meg az oldalam!](https://spring-render-s4f4.onrender.com/boots.html)

# Amit használtam:

## Backend (Szerveroldal)

- Spring Boot alkalmazás (DemoApplication.java)
- Controller osztályok, pl. UserController (ha van), amelyek kezelik a kéréseket, mint például /login, /register stb.
- Model osztályok, pl. User, Product, amelyek az adatokat reprezentálják
- H2 adatbázis + JPA repository-k az adatok tárolásához és lekérdezéséhez
- Üzleti logika, pl. user.name értékek ellenőrzése vagy felhasználók mentése

## Frontend (Felhasználói felület)

- HTML sablonok (.html fájlok a resources/templates mappában)
- Thymeleaf attribútumok, pl. th:text, th:case, az adatok dinamikus megjelenítéséhez a backendből
- CSS / Bootstrap a megjelenés formázásához
- JavaScript az interaktivitáshoz

# Felépítés

Frontend (HTML + Thymeleaf)  
│  
▼  
Spring Controller  
│  
▼  
Service / Üzleti logika  
│  
▼  
H2 adatbázis (adatok)
