package enset.com.gc.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "p1",types = enset.com.gc.entities.Projection.class)
public interface ProjectionProd {
    public  Long getId();
    public double getPrix();
    public Date getDateProjection();
    public  Film getFilm();
    public  Salle getSalle() ;
    public Seance getSeance();
    public Collection<Ticket> getTickets();
}

