package tj.sidalsoft.shop.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductRepositoryIT {

    @Autowired
    ProductRepository repository;

    @Test
    void findById() {
        var res = repository.findById(1);
        assertTrue(res.isPresent());
        var result = res.get();
        assertNotNull(result);
        assertAll(() -> {
            assertNotNull(result.getName());
            assertTrue(result.getPrice() > 0);
        });
    }

    @Test
    void existsByName() {
        var existEntity = repository.findById(1).get();

        var result = repository.existsByName(existEntity.getName());
        assertTrue(result);
    }
}