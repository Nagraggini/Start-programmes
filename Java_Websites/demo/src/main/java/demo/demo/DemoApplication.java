package demo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://localhost:8080/boots.html
//Ha ezt látod: Completed initialization Akkor indult el a local szerver.

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// A Spring, csak meg hívja ezt az osztályt.
		// Ez elindít egy: embedded Tomcat szervert
		SpringApplication.run(DemoApplication.class, args);
	}

}
