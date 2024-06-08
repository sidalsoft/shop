package tj.sidalsoft.shop.services;

public interface ProductService {

    /**
     * @return prices
     */
    double calculatePrice(int product, String taxNumber, String couponCode);

}