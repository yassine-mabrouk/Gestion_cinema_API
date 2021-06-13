package enset.com.gc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.rest.core.config.Projection;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Projection(name = "ticketProj",types = Ticket.class)
public interface TicketsProjection {
    public Long getId();
    public  String getNomClient() ;
    public double getPrix() ;
    public  Integer getCodePayemet() ;
    public boolean getReserve() ;
    public  Place getPlace() ;

}
