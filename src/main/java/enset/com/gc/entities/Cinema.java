package enset.com.gc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name ;
    private double longitude, latitude,altitude;
    private int nb_salles;
    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles=new ArrayList<>();
    @ManyToOne
    private Ville ville ;
}
