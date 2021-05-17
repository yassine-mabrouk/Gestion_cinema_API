package enset.com.gc.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre ;
    private String description ;
    private String realisateur ;
    private Date date_sortie ;
    private double duree;
    private String photo;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;
    @ManyToOne
    private Categorie categorie;

}
