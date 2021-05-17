package enset.com.gc.service;

import enset.com.gc.dao.*;
import enset.com.gc.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaServiceimpli  implements ICinemaService{
   @Autowired
    private VilleRepo villeRepo;
   @Autowired
   private CinemaRepo cinemaRepo;
   @Autowired
   private SalleRepo salleRepo ;
   @Autowired
    private PlaceRepo placeRepo;
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private SeanceRepo seanceRepo ;
    @Autowired
    private FilmRepo filmRepo;
    @Autowired
    private ProjectionRepo projectionRepo;
    @Autowired
    private CategorieRepo categorieRepo;

    @Override
    public void initVilles() {
        Stream.of("casa","marrakech","Rabat","Tanger").forEach(v->{
                    Ville ville =new Ville();
                    ville.setName(v);
                    villeRepo.save(ville);
                });
    }
    @Override
    public void initCinema() {
     villeRepo.findAll().forEach(v->{
         Stream.of("MagaRama","Imax","Founoun").forEach(nameCinema->{
             Cinema cinema=new Cinema();
             cinema.setName(nameCinema);
             cinema.setVille(v);
             cinema.setNb_salles(3+(int)(Math.random()*7));
             cinemaRepo.save(cinema);
         });
     });
    }

    @Override
    public void initSalles() {
      cinemaRepo.findAll().forEach(c->{
      for(int i=0;i<c.getNb_salles();i++){
          Salle salle=new Salle();
          salle.setName("Salle"+i);
          salle.setCinema(c);
          salle.setNb_place(15+(int)(Math.random()*20));
          salleRepo.save(salle);
      }
      });
    }

    @Override
    public void initPlaces() {
        salleRepo.findAll().forEach(s->{
            for(int i=0;i<s.getNb_place();i++){
                Place place =new Place();
                place.setNumero(i+1);
                place.setSalle(s);
                placeRepo.save(place);
            }
        });
    }
    @Override
    public void initSeances() {
        DateFormat df =new SimpleDateFormat("HH:mm");
         Stream.of("12:00","15:00","18:00").forEach( s->{
             Seance seance=new Seance();
             try {
                 seance.setHeureDebut(df.parse(s));
             } catch (ParseException e) {
                 e.printStackTrace();
             }
             seanceRepo.save(seance);
         });
    }

    @Override
    public void initCategories() {
        Stream.of("Action","Fiction","Drama").forEach(c->{
            Categorie cat =new Categorie();
            cat.setName(c);
            categorieRepo.save(cat);
        });
    }

    @Override
    public void initFilms() {
        double [] duree =new double[]{1,1.5,2.5,3};
        List<Categorie> listCat=categorieRepo.findAll();
        Stream.of("The Pursuit of Happyness","viking","iboy","snowden")
                .forEach(filmName->{
         Film film=new Film();
         film.setTitre(filmName);
         film.setDuree(duree[new Random().nextInt(duree.length)]);
         film.setPhoto(filmName.replaceAll(" ","")+".jpg");
         film.setCategorie(listCat.get(new Random().nextInt(listCat.size())));
         filmRepo.save(film);
        });
    }

    @Override
    public void initProjections() {
        double[] prices =new double[]{30,40,50,45,100};
        villeRepo.findAll().forEach(ville->{
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle->{
                    filmRepo.findAll().forEach(film->{
                        seanceRepo.findAll().forEach(seance->{
                            Projection projection=new Projection();
                            projection.setDateProjection(new Date());
                            projection.setFilm(film);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(salle);
                            projection.setSeance(seance);
                            projectionRepo.save(projection);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepo.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place ->{
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setProjection(p);
                ticket.setReserve(false);
                ticketRepo.save(ticket);
            });
        });

    }
}
