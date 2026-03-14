package hasznosInfok;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Apik {

        // Ez alapján cisnáltam:
        // https://www.youtube.com/watch?v=2StXP1XaU04

        record Car(String type, String make, String model, Integer engineCapacity) {
        }

        public static void main(String[] args) {
                List<Car> cars = List.of(new Car("sedan", "BMW", "530", 1998),
                                new Car("sedan", "Audi", "A5", 1998),
                                new Car("sedan", "Mercedes", "E-Class", 2500),
                                new Car("hatchback", "Skoda", "Octavia", 1600),
                                new Car("hatchback", "Toyota", "TypeR", 1450));

                // A .toList() visszalakítjuk listává az adatfolyamot.
                List<Car> sedanCars = cars.stream().filter(x -> x.type.equals("sedan")).toList();

                // Csinálunk egy listát, amiben csak a kocsik make attribútuma szerepel.
                // map: Egy az egyhez (1:1) transzformáció. Minden autóból egy márkát (String)
                // csinál.
                List<String> carMakeList = cars.stream().map(car -> car.make).toList();

                // Csinálunk egy listát, amiben csak a kocsik make és model attribútumai
                // szerepelnek.
                /*
                 * flatMap: Egy a többhöz (1:N) transzformáció. Itt minden autóból egy Streamet
                 * csinálsz (ami a márkát és a modellt tartalmazza), majd a flatMap ezeket a kis
                 * streameket "kilapítja" egyetlen nagy listává.
                 */
                List<String> carMakeModelList = cars.stream().flatMap(car -> Stream.of(car.make, car.model)).toList();

                Stream<Integer> szamokStream = Stream.of(10, 11, 12, 13, 14);

                /*
                 * Ilyenkor a Java még nem csinál semmit. Nem szűri le a számokat, és nem írja
                 * ki a konzolra a szöveget. Csak "feljegyzi" magának, hogy:
                 * "Majd ha valaki kéri az eredményt, ezeket a számokat így kell szűrnöm."
                 */
                Stream<Integer> szurtSzamokStream = szamokStream.filter(i -> {
                        System.out.println("Vizsgálom a számot: " + i); // Ez megmutatja a "lustaságot".
                        return i % 2 == 0;
                });
                /*
                 * A Stream API olyan, mint egy gyári futószalag. A filter és a map a gépek a
                 * szalagon, de a szalag csak akkor indul el, ha a végén valaki megnyomja a
                 * gombot (ez a terminális művelet, pl. a count vagy a toList).
                 */

                /*
                 * A terminális (záró) művelet (count). A tényleges munka csak itt kezdődik.
                 */
                System.out.println("Ennyi páros szám van:" + szurtSzamokStream.count());

                // ^ Gyűjtők (Collectors), a csoportosítás és a párhuzamos végrehajtás.

                // & 1. Partitioning
                /*
                 * A partitioningBy egy speciális gyűjtő, ami egy feltétel alapján pontosan két
                 * részre osztja a listát: azokra, amikre igaz a feltétel, és azokra, amikre
                 * nem.
                 */

                // Map<Boolean, List<Car>>: A kulcs mindig Boolean (true/false)
                Map<Boolean, List<Car>> autokSzetvalasztasa = cars.stream().collect(
                                Collectors.partitioningBy(car -> car.type.equals("sedan"))

                );

                // true kulcs alatt lesznek a sedanok, false alatt minden más
                System.out.println("Sedanok (true): " + autokSzetvalasztasa.get(true) + "/n");
                System.out.println("Nem sedanok (false): " + autokSzetvalasztasa.get(false));

                // & 2. GroupingBy (Csoportosítás)

                /*
                 * Amikor nem csak két kategóriánk van, a groupingBy segítségével bármilyen
                 * tulajdonság (pl. gyártó vagy típus) alapján csoportosíthatunk.
                 */

                // Csoportosítás: Típus szerint (Map<String, List<Car>>)
                Map<String, List<Car>> tipusSzerint = cars.stream()
                                .collect(Collectors.groupingBy(Car::type));

                // & 3. Összetett csoportosítás (Multi-level Map)
                /*
                 * A videó végén egy komplexebb példa van, ahol nem csak listába gyűjtjük az
                 * autókat, hanem egy Map-et teszünk egy Map-be. Itt azt nézzük meg, hogy
                 * típuson belül melyik modellnek mekkora a motorja.
                 */

                // Map<Típus, Map<Modell, Hengerűrtartalom>>
                Map<String, Map<String, Integer>> csoportositottAutok = cars.stream()
                                .collect(Collectors.groupingBy(
                                                Car::type, // Elsődleges kulcs: típus (pl. sedan)
                                                Collectors.toMap(
                                                                Car::model, // Belső Map kulcsa: modell
                                                                Car::engineCapacity // Belső Map értéke: motor mérete
                                                )));

                // & 4. Párhuzamos Streamek (Parallel Streams)

                /*
                 * Alapértelmezetten a stream egyetlen szálon (Sequential) fut.
                 * 
                 * parallelStream(): A Java felosztja a feladatot több processzorszálra
                 * (úgynevezett ForkJoinPool-t használ).
                 * 
                 * Mikor használd? Csak nagyon nagy adatmennyiségnél, mert a szálak kezelése
                 * plusz erőforrásba kerül.
                 * 
                 * Váltás: Menet közben is válthatsz .parallel() és .sequential() hívásokkal a
                 * láncon belül.
                 */

                // 3. Párhuzamosítás
                // A parallelStream() több szálon futtatja a műveleteket
                cars.parallelStream().forEach(car -> {
                        System.out.println(
                                        "Szál: " + Thread.currentThread().getName() + " dolgozik ezen: " + car.model());
                });
        }

}
