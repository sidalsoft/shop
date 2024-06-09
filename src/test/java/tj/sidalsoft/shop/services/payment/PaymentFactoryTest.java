package tj.sidalsoft.shop.services.payment;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PaymentFactoryTest {

    @Qualifier("paypal")
    @Mock
    PaymentService paypalService;
    @Qualifier("stripe")
    @Mock
    PaymentService stripePaymentService;

    @InjectMocks
    PaymentFactory paymentFactory;

    @Test
    void calculatePrice_PAYPAL() throws Exception {
        var prices = 10;
        assertDoesNotThrow(() -> paymentFactory.getPaymentService(PaymentProcessorType.PAYPAL).pay(prices));
        verify(paypalService, times(1)).pay(prices);
    }

    @Test
    void calculatePrice_STRIPE() throws Exception {
        var prices = 10;
        assertDoesNotThrow(() -> paymentFactory.getPaymentService(PaymentProcessorType.STRIPE).pay(prices));
        verify(stripePaymentService, times(1)).pay(prices);
    }
}
