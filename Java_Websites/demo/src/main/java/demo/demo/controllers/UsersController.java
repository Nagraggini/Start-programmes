package demo.demo.controllers;

//ArrayList-hez:
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import demo.demo.models.User;
import demo.demo.models.UserRepository;
import jakarta.servlet.http.HttpServletResponse;

// Ez a kontroller figyeli a szervert, és lehetővé teszi, hogy információkat kapjunk a felhasználókról.
@Controller
public class UsersController {

    @Autowired
    private UserRepository userRepo;

    // Ez a metódus akkor fut le, ha a felhasználó HTTP GET kérést küld a
    // /users/view URL-re.
    // A @GetMapping annotáció egy endpointot definiál, amin keresztül a kliens
    // kapcsolatba léphet a szerverrel.
    // A visszatérési érték "users/showAll" a Thymeleaf template neve, amit a
    // böngészőnek küldünk.
    @GetMapping("/users/view")
    // Model -> org.springframework.ui -> Be is kerül fentre az importokhoz.
    public String getAllUsers(Model model) {
        // Modell: A Spring MVC-ben a Model egy olyan “csomagoló” objektum, amiben
        // adatokat küldünk a HTML template-nek (Thymeleaf, JSP, stb.).
        System.out.println("Getting all users.");

        // Mindegyik felhasználó lekérése az adatbázisból.
        List<User> users = userRepo.findAll();
        // Vége az adatbázis meghívásnak.

        // A modellhez hozzáadjuk az adatokat.
        // "us" – ez a név, amin keresztül a template-ben el tudjuk érni.
        model.addAttribute("us", users);

        // A "users/showAll" a Thymeleaf template neve, amit visszaad a böngészőnek
        // A templates mappában van a showAll.html, ami meg fog jelenni.
        // A return "users/showAll"; csak a template nevét adja vissza, amit a Spring
        // MVC feldolgoz és a böngészőnek renderel.
        return "users/showAll";
        // A böngésző nem kapja közvetlenül a Model objektumot, csak a renderelt HTML-t.
    }

    // Ez a metódus a Spring Boot backend egyik endpointja, amely fogadja a HTML
    // form által küldött adatokat, majd elmenti az adatbázisba.
    @PostMapping("/users/add") // Ez definiál egy HTTP endpointot, ami az add.html fájlban van.

    // @RequestParam Map<String, String> newuser -> Összegyűjti a form mezőket egy
    // Map-be.
    // A newuser -> Egy kulcs-érték lista.
    // HttpServletResponse response -> Ez a HTTP válasz objektum.
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {

        System.out.println("ADD user");

        // A html-ben lévő name attribútumra hivatkozunk a jobb oldalon.
        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        int newSize = Integer.parseInt(newuser.get("size"));

        // Ezzel mentjük az adatbázisba a sort. Kb egy INSERT INTO ...
        // Ez az ORM (Object-Relational Mapping). Az ORM (itt a Spring Data JPA) kezeli
        // az objektum → adatbázis leképezést.
        userRepo.save(new User(newName, newPwd, newSize));
        response.setStatus(201); // Beállítja a HTTP válasz státuszkódját 201-re, vagyis Created.

        // Ez visszaad egy HTML oldalt. A Spring megkeresi ezt: templates/users/
        // Átirányítás a listázó oldalra.
        return "redirect:/users/view";
    }

}
