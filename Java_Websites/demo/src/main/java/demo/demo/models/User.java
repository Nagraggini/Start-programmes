package demo.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//A models mappában lévő java fájlokkal kezeljük a felhasználókat.(vagy objektumokat,szolgáltatásokat)
//Az annotációk megmondja, hogy van egy ilyen táblánk az adatbázisban.
@Entity
@Table(name = "users")
public class User {

    // Ezek az elsődleges kulcs létrehozásához kellenek. A users adattáblában van
    // egy uid oszlopunk.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Lefogja generálni az értékét.
    private int uid;

    // Változók:
    private String name;
    private String password;
    private int size;

    // Jobb klikk itt Source Actions -> Generate Constructor
    // konstruktorok, getter/setterek
    public User() {
    }

    public User(String name, String password, int size) {
        this.name = name;
        this.password = password;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // Jobb klikk itt Source Actions -> Generate Constructor
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
