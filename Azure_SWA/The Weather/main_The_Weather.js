//Kikeresi a HTML-ből azt az elemet, amelynek az id-je "weather" (jelen esetben span), és eltárolja a weather változóban.
//Ebbe fogjuk kiírni az időjárási adatot.
const Budapest_weather = document.getElementById("Budapest_weather");
const London_weather = document.getElementById("London_weather");
const Tokyo_weather = document.getElementById("Tokyo_weather");

//Ugyanez, csak a "btn" id-jű elemet (pl. egy gombot) menti el a btn változóba.
const btn = document.getElementById("btn");

//Létrehoz egy URL-t az Open-Meteo API-hoz, ami London (51.5074, -0.1278) aktuális időjárási adatait fogja visszaadni.
let Budapest_url =
  "https://api.open-meteo.com/v1/forecast?latitude=47.4979&longitude=19.0402&current_weather=true&timezone=auto";

let London_url =
  "https://api.open-meteo.com/v1/forecast?latitude=51.5074&longitude=-0.1278&current_weather=true&timezone=auto";

let Tokyo_url =
  "https://api.open-meteo.com/v1/forecast?latitude=35.6895&longitude=139.6917&current_weather=true&timezone=auto";

//Lekéri az adatokat az API-tól (fetch(url)).
//Az első .then() átalakítja a választ JSON objektummá.
//A második.then() a visszakapott adatokból(data) kiveszi az aktuális hőmérsékletet(data.current_weather.temperature)
//és kiírja a weather elem szövegébe, pl. 21°C.

function setWeather(element, data) {
  if (
    data.current_weather &&
    typeof data.current_weather.temperature !== "undefined"
  ) {
    element.textContent = data.current_weather.temperature + "°C";
  } else {
    element.textContent = "N/A";
    console.error("No current_weather data:", data);
  }
}

fetch(Budapest_url)
  .then((response) => response.json())
  .then((data) => setWeather(Budapest_weather, data))
  .catch((err) => {
    Budapest_weather.textContent = "Error";
    console.error(err);
  });

fetch(London_url)
  .then((response) => response.json())
  .then((data) => setWeather(London_weather, data))
  .catch((err) => {
    London_weather.textContent = "Error";
    console.error(err);
  });

fetch(Tokyo_url)
  .then((response) => response.json())
  .then((data) => setWeather(Tokyo_weather, data))
  .catch((err) => {
    Tokyo_weather.textContent = "Error";
    console.error(err);
  });

//Ez egy függvény, ami akkor fut le, ha a gombot megnyomod.
//Először feldob egy üzenetet (alert("Button clicked!")).
//Ezután a gomb (btn) háttérszínét kékre állítja, a szöveg színét pedig fehérre.
/*
function clicked() {
  //alert("Button clicked!");
  btn.style.backgroundColor = "darkblue";
  btn.style.color = "white";
}
  */
