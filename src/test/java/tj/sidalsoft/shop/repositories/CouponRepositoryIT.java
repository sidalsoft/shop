package tj.sidalsoft.shop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CouponRepositoryIT {

    @Autowired
    CouponRepository repository;

    @Test
    void findById() {
        var result = repository.findById(1);
        assertNotNull(result);
        assertAll(() -> {
            assertNotNull(result.getName());
            assertTrue(result.getAmount() > 0);
        });
    }

    @Test
    void existsByName() {
        var existEntity = repository.findById(1);

        var result = repository.existsByName(existEntity.getName());
        assertTrue(result);
    }
}