package tj.sidalsoft.shop.services;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tj.sidalsoft.shop.utils.Constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ProductServiceIT {

    Map<Integer, String> error = Map.of(1, Constants.PRODUCT_NOT_FOUND,
                                        2, Constants.TAX_NOT_FOUND,
                                        3, Constants.TAX_NUMBER_IS_INCORRECT,
                                        4, Constants.COUPON_NOT_FOUND);

    @Autowired
    ProductService productService;

    @Test
    void calculatePrice() {
        var expectedResult = 91.8;
        var result = productService.calculatePrice(1, "DE123456789", "D15");
        assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @CsvSource({"112, DE123456789, D15, 1",
                "1, AA123456789, D15, 2",
                "1, DE1234567894, D15, 3",
                "1, DE123456789, P50, 4"
    })
    void calculatePrice_throw(int product, String taxNumber, String couponCode, int errorCode) {
        var exception = assertThrows(RuntimeException.class,
                                     () -> productService.calculatePrice(product, taxNumber, couponCode));

        assertEquals(error.get(errorCode), exception.getMessage());
    }
}