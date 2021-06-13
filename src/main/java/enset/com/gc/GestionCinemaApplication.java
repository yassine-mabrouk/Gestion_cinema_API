package enset.com.gc;

import enset.com.gc.entities.Film;
import enset.com.gc.entities.Salle;
import enset.com.gc.entities.Ticket;
import enset.com.gc.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class GestionCinemaApplication implements CommandLineRunner {
   @Autowired
    ICinemaService cinemaInit;
   @Autowired
   public RepositoryRestConfiguration restConfig;
    public static void main(String[] args) {

        SpringApplication.run(GestionCinemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
     restConfig.exposeIdsFor(Film.class);
     restConfig.exposeIdsFor(Salle.class);
        restConfig.exposeIdsFor(Ticket.class);

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
