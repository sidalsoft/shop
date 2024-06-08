package tj.sidalsoft.shop.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tj.sidalsoft.shop.entities.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {

    Coupon findById(long id);

    Optional<Coupon> findByName(String name);

    boolean existsByName(String name);

}