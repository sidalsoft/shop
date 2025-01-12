package tj.sidalsoft.shop.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tj.sidalsoft.shop.entities.Tax;

@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {

    Tax findById(long id);

    boolean existsByCountry(String country);

    Optional<Tax> findByCode(String code);
}