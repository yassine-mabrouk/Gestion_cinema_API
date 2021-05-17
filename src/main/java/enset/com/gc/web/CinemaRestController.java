package enset.com.gc.web;

import enset.com.gc.dao.FilmRepo;
import enset.com.gc.dao.TicketRepo;
import enset.com.gc.entities.Film;
import enset.com.gc.entities.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@RestController
public class CinemaRestController {
    @Autowired
    FilmRepo filmRepo;
    @Autowired
    TicketRepo ticketRepo;

    @GetMapping("/getAllFilm")
    public List<Film> getAlFilm(){
        return filmRepo.findAll();
    }
   // afficher un document pdf
    @GetMapping(value = "getDoc/{name}",produces = MediaType.APPLICATION_PDF_VALUE)
    public  byte[] getDocument(@PathVariable String name) throws IOException {
        File file =new File(System.getProperty("user.home")+"/cinema/doc/"+name+".pdf");
        Path path =Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

@GetMapping(value = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
 public  byte[] image(@PathVariable Long id) throws IOException {
    Film film=filmRepo.findById(id).get();
    String photoname=film.getPhoto();
    File file=new File(System.getProperty("user.home")+"/cinema/images/"+photoname);
    Path path= Paths.get(file.toURI());
    return Files.readAllBytes(path);
  }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTicks(@RequestBody TicketForm ticketForm){
     List<Ticket> listTikets=new ArrayList<>();
    ticketForm.getTickets().forEach(id->{
        System.out.println(id);
           Ticket ticket =ticketRepo.findById(id).get();
           ticket.setNomClient(ticketForm.getNameClient());
           ticket.setCodePayemet(ticketForm.getCodePayemnt());
           ticket.setReserve(true);
           ticketRepo.save(ticket);
           listTikets.add(ticket);
       });
    return listTikets;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class TicketForm {
    private  String nameClient;
    private List<Long> tickets =new ArrayList<>();
    private Integer codePayemnt;
    }

}
