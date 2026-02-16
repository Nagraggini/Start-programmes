# Spring Boot

[Ez alapján csináltam](https://www.youtube.com/watch?v=RK6aAjUMcl0&list=PLg7lel5LdVjyO7jk-4biyr0fqPVygTLOk)

[A fenti youtube videóhoz tartozó repo.](https://github.com/bobbychansfu/springboot-render/tree/7d38d0343465661adceb6372591a8353717dfd5e)

Ha elindítod a java fájlt, akkor a böngészőbe írd be ezt: http://localhost:8080/actuator

A dependenciák közt fel vettek az actuatort, ezzel le tudod csekkolni magad.
Az actuator információt ad az alkalmazás állapotáról és működéséről.

Alapból csak a /actuator endpoint lesz elérhető.

Ha több információt akarsz, a application.properties-ben beállíthatod, pl.:
management.endpoints.web.exposure.include=\*

A static mappában lévő html fájlt, itt meg tudod nézni:
http://localhost:8080/hello.html
http://localhost:8080/boots.html
stb.

BootStrap
https://getbootstrap.com/docs/5.3/layout/grid/

Használhatod a CDN-t a HTML-ben:

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

Előre konfigurált Spring Boot projektet lehet generáltatni, hogy ne kelljen kézzel megírni az összes kezdő fájlt és beállítást:
https://start.spring.io/

# CI/CD (Continuous Integration / Continuous Deployment) folyamat:

Új build indítása – Ez lehet manuális push a GitHub/GitLab branchre, vagy egy automatikus trigger.

Dockerfile feldolgozása – A rendszer végigmegy a Dockerfile-on: letölti a base image-et, telepíti a függőségeket, lefordítja a projektet (pl. mvn package), és létrehozza a végső Docker image-et.

Image létrehozása – Ez a “build” lépés, ahol a kész alkalmazás becsomagolódik egy konténerbe.

Image deployolása – Render.com feltölti az image-et a saját konténer registry-jébe és elindítja az új konténert.

Új konténer futtatása – Az új image futtatása a szerveren, a régi konténer lecserélődik (rolling update).

A folyamat 10 perc körüli időt vesz igénybe, mert a Maven build és a Docker image létrehozása időigényes lehet, főleg ha a projekt nagy vagy sok függőséget használ (Ez 8 db-ot használ).

# Debug

[A videó végén szerepel, hogyan kell debuggolni.](https://www.youtube.com/watch?v=p3AIecyvok4&list=PLg7lel5LdVjyO7jk-4biyr0fqPVygTLOk&index=7)

1. Telepítsd a Docker-t.

2. Projekt buildelése Dockerrel:
   Terminálba írd be: sudo docker build -t renderapp .

- A . a build context, azaz a Docker a projekt gyökérkönyvtárából veszi a Dockerfile-t és a forrásfájlokat.
- A parancs létrehoz egy Docker image-et, ami tartalmazza a Maven build-et és az alkalmazást.

3. Mi történik a háttérben:

- A Docker először letölti a base image-et (pl. Maven + JDK).
- Lefuttatja a mvn clean package parancsot, ami:
  Kitisztítja a korábbi build fájlokat
  Létrehozza a target mappát
  Becsomagolja az alkalmazást futtatható .jar fájlba
- A .jar fájl bekerül a Docker image-be, így az image futásra kész lesz.

Felhasználót hozzáadni a docker csoporthoz (jobb hosszú távon):
Terminálba:
sudo usermod -aG docker $USER
newgrp docker

Ez a parancs elindít egy Docker konténert az általad készített renderapp image-ből, és biztosítja, hogy a konténeren belüli 8080-as portot elérd a saját gépedről a localhost:8080 címen:
docker run -p 8080:8080 renderapp

# Templating with Spring and Thymeleaf

Spring és Thymeleaf használatakor a templatingre azért van szükség, hogy dinamikus funkciókat és adatokat jeleníthessünk meg az oldalon.

A templates mappában találhatók a view-k, vagyis a kinézetek.

A models mappában lévő java fájlokkal kezeljük a felhasználókat. (vagy objektumokat, szolgáltatásokat)
A controllers figyeli a szervert, és lehetővé teszi, hogy információkat kapjunk a felhasználókról.

UserController.java vizuális ábrán:
[Felhasználó / Kliens]
|
| GET http://localhost:8080/users/view
v

---

| UsersController                              |
| -------------------------------------------- |
| @GetMapping("/users/view") --> getAllUsers() |

---

          |
          | Visszatérési érték: "users/showAll"
          v

---

| Thymeleaf Template |
| templates/users/showAll.html |

---

          |
          v

[Böngésző megjeleníti a felhasználónak]

A templates mappában lévő html fájlok rejtve vannak a felhasználó elől, nem úgymint a static mappában lévők.

[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf.html)

# Portok használata

Ezzel kilistázod, hogy melyik app használja a portot.

Terminálban:
sudo lsof -i :8080

A 58608-at írd át arra, amit fentebb kiír.
sudo kill 58608

# Adatbázisok

Az adatbázisokat alapvetően két fő kategóriába soroljuk:

- strukturált adatbázisok
- nem strukturált adatbázisok (A kezdők struktúráltat használnak, lekérdezéshez pedig sql-t.)

Postgresql-t [innen](https://www.postgresql.org/download/) tudod letölteni.

A PostgreSQL működhet:

- szerverként (adatbázis szerver)
- kliensként (adatbázis kezelő eszköz)

Ebben az esetben neked csak a kliensre lesz szükséged, mert az adatbázis szerver a render.com platformon fog futni, és ahhoz távolról fogsz csatlakozni.

A render.com-on hozz létre egy Postgres-t. A név legyen database. A verzió 16-os, a lényeg hogy egyezzen a gépre feltepített verzióval. Region: EU Instance Type: Free -> Create Database

Miután elkészült szükséged lesz az External Database URL-re, Username, Database, Password-re.

Terminálban, csatlakoztasd Postgres-t a render.com-os adatbázissal:
psql -h "@-utáni résztől....frankfurt-postgres.render.com-ig" -U "Username" -d "Database"
Entert nyomj.
pl.: psql -h dpg-d69k87buibrs739i5fu0-a.frankfurt-postgres.render.com -U database_olpd_user -d database_olpd
A jelszónak az oldalon lévő password-t másold be. Nem fogja mutatni. Majd entert nyomj.

Adatbázis létrehozása a terminálban:
CREATE TABLE products (pid SERIAL, name VARCHAR(255), price MONEY, quantity INTEGER);
CREATE TABLE users (uid SERIAL, name VARCHAR(255), size INTEGER, password VARCHAR(255));

\dt-vel kilistázzuk a kettő táblát:

               List of relations

Schema | Name | Type | Owner  
--------+----------+-------+--------------------
public | products | table | database_olpd_user
public | users | table | database_olpd_user
(2 rows)

\d products-vel a tábla struktúráját láthatod:

                                     Table "public.products"

Column | Type | Collation | Nullable | Default  
----------+------------------------+-----------+----------+---------------------------------------
pid | integer | | not null | nextval('products_pid_seq'::regclass)
name | character varying(255) | | |
price | money | | |
quantity | integer | | |

A "q"-val tudsz kilépni belőle.

                                     Table "public.users"

Column | Type | Collation | Nullable | Default  
----------+------------------------+-----------+----------+------------------------------------
uid | integer | | not null | nextval('users_uid_seq'::regclass)
name | character varying(255) | | |
size | integer | | |
password | character varying(255) | | |

Így tudsz hozzáadni plussz sort (objektum):
INSERT INTO products (name,price,quantity) VALUES ('monitor',395.50,12);

Kilistázás:
SELECT \* FROM products;

A vs code-ban a pom.xml-hez adjuk hozzá a postgresql drivert. Jobb klikk a pom.xml-en -> Add Starts.. -> Postgresql Driver -> Enter -> Proceed.

Gyorsabb módszer, hogy módosítsd az adatbázist:
A terminálba másold be a render.com-rül a PSQL Command-ot.

A táblát így tudod csekkolni a terminálban:
\d users
q-val tudsz kilépni.

# Teljes Flow

Browser  
 │  
 │ POST /users/add  
 │ name=Andrea  
 │ password=1234  
 │ size=42  
 ▼  
Controller  
 │  
 │ new User(...)  
 ▼  
Repository  
 │  
 ▼  
Database  
 │  
 ▼  
HTML response: addedUser.html
