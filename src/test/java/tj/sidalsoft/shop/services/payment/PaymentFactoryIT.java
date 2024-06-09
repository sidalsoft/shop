package tj.sidalsoft.shop.services.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tj.sidalsoft.shop.utils.Constants;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PaymentFactoryIT {

    @Autowired
    PaymentFactory paymentFactory;

    @Test
    void calculatePrice() {
        assertDoesNotThrow(() -> paymentFactory.getPaymentService(PaymentProcessorType.PAYPAL).pay(10));
    }

    @Test
    void calculatePrice_throw() {
        var exception = assertThrows(Exception.class,
                                     () -> paymentFactory
                                             .getPaymentService(PaymentProcessorType.PAYPAL).pay(100001));
        assertEquals(Constants.AMOUNT_SHOULD_NOT_BE_MORE_THAN_100000, exception.getMessage());
    }
}
