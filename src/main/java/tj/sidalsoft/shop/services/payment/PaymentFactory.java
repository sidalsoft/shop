package tj.sidalsoft.shop.services.payment;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactory {

    private final PaymentService paypalService;
    private final PaymentService stripePaymentService;

    public PaymentFactory(@Qualifier("paypal") PaymentService paypalService,
                          @Qualifier("stripe") PaymentService stripePaymentService) {
        this.paypalService = paypalService;
        this.stripePaymentService = stripePaymentService;
    }

    public PaymentService getPaymentService(PaymentProcessorType name) {
        return switch (name) {
            case PAYPAL -> paypalService;
            case STRIPE -> stripePaymentService;
            default -> throw new IllegalStateException("Unexpected value: " + name);
        };
    }
}
