package tj.sidalsoft.shop.services.payment;

import org.springframework.stereotype.Service;
import tj.sidalsoft.shop.utils.Constants;

@Service("paypal")
public class PaypalService implements PaymentService {

    public void makePayment(Integer prices) throws Exception {
        if (prices > 100000) {
            throw new Exception(Constants.AMOUNT_SHOULD_NOT_BE_MORE_THAN_100000);
        }
    }

    @Override
    public boolean pay(float prices) throws Exception {
        makePayment((int) prices);
        return true;
    }
}
