// Bekérjük a nevet
const nev = window.prompt("Mi a neved?", "Béla");

// Kiírjuk a <h1>-be
const koszones = document.getElementById("koszones");
koszones.textContent = "Helló " + nev + "!";

// Kiírjuk a konzolra
console.log("Helló " + nev);

// Gomb kattintás esemény
const gomb = document.getElementById("gomb");
gomb.addEventListener("click", function (evt) {
    evt.preventDefault(); // opcionális
    alert("Gombot nyomtál! Helló " + nev + "!");
    console.log("A gombot " + nev + " nyomta meg.");
});
