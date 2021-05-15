package enset.com.gc.dao;

import enset.com.gc.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategorieRepo extends JpaRepository<Categorie,Long> {
}
