package enset.com.gc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient ;
    private double prix ;
    @Column(unique = true,nullable = true)
    private Integer codePayemet ;
    private boolean reserve ;
    @ManyToOne
    private Place place ;
    @ManyToOne
    private Projection projection ;
}
