# Mi is ez?

Létrehoztam a legegyszerűbb spring api alkalmazásomat a gyakorláshoz.

# Kezdőknek

[Saját webfejlesztői alapismeretekről szóló blogom](https://nagraggini-blog.onrender.com/)

# Spring Boot

[Ez alapján csináltam.](https://www.youtube.com/watch?v=Zo9xQzibp4Y&t=301s)

## Projekt létrehozása

Spring Initializer bővítmény legyen fent
felülre írd be: >Spring Initializr
4.0.3
Java
com.example
Artifact id: springapi
Jar
Java 17
Dependecy: Spring Web

# Főoldal

A static mappában hozz létre egy index.html fájlt.
Írd bele az emmet kódot "html:5", amikor megjelenik az ajánlás miközben beírod a szöveget arra nyomj rá.

# Szerver elindítása

SpringapiApplication.java-n nyomj egy start-ot.

Leállítani, úgy kell, hogy alul a terminálba belekattintasz és ctrl+c.

## Postman

cmd-ben:
sudo apt update
sudo apt install snapd
sudo snap install core

sudo snap install postman

Elinditás cmd-ben:
postman

Írd bele: localhost:8080/user
Key oszlopba id
Value oszlopba 1

Majd Send gomb és alul megjelenik a JSON az adatokkal.
