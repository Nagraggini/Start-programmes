package demo.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//Automatikusan létrehoz neked egy komplett adatbázis-kezelő réteget a User entitáshoz — anélkül, hogy SQL-t kellene írni.
//JpaRepository<EntitásTípus, PrimaryKeyTípus>
public interface UserRepository extends JpaRepository<User, Integer> {
    // Listákba lementjük az adatokat az adatbázisból.
    // A változók az User.java fájlból vannak.
    List<User> findBySize(int size);

    List<User> findByNameAndPassword(String name, String password);
}
