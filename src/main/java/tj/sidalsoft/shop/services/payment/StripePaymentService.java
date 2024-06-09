package tj.sidalsoft.shop.services.payment;

import org.springframework.stereotype.Service;

@Service("stripe")
public class StripePaymentService implements PaymentService {

    @Override
    public boolean pay(float prices) {
        return prices < 100;
    }
}
