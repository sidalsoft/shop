package tj.sidalsoft.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tj.sidalsoft.shop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Product findById(long id);

    boolean existsByName(String name);
}