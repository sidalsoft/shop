package tj.sidalsoft.shop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TaxRepositoryIT {

    @Autowired
    TaxRepository repository;

    @Test
    void findById() {
        var result = repository.findById(1);
        assertNotNull(result);
        assertAll(() -> {
            assertNotNull(result.getCountry());
            assertTrue(result.getPercent() > 0);
        });
    }

    @Test
    void existsByCountry() {
        var existEntity = repository.findById(1);

        var result = repository.existsByCountry(existEntity.getCountry());
        assertTrue(result);
    }
}