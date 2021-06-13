package enset.com.gc.dao;

import enset.com.gc.entities.Categorie;
import enset.com.gc.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface CinemaRepo extends JpaRepository<Cinema,Long> {
}
