package enset.com.gc;

import enset.com.gc.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCinemaApplication implements CommandLineRunner {
   @Autowired
    ICinemaService cinemaInit;
    public static void main(String[] args) {
        SpringApplication.run(GestionCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        cinemaInit.initVilles();
        cinemaInit.initCinema();
        cinemaInit.initSalles();
        cinemaInit.initPlaces();
        cinemaInit.initSeances();
        cinemaInit.initCategories();
        cinemaInit.initFilms();
        cinemaInit.initProjections();
        cinemaInit.initTickets();

    }
}
