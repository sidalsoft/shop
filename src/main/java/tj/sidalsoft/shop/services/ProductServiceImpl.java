package tj.sidalsoft.shop.services;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import tj.sidalsoft.shop.entities.Coupon;
import tj.sidalsoft.shop.entities.CouponType;
import tj.sidalsoft.shop.entities.Product;
import tj.sidalsoft.shop.entities.Tax;
import tj.sidalsoft.shop.repositories.CouponRepository;
import tj.sidalsoft.shop.repositories.ProductRepository;
import tj.sidalsoft.shop.repositories.TaxRepository;

import static tj.sidalsoft.shop.utils.Constants.COUPON_NOT_FOUND;
import static tj.sidalsoft.shop.utils.Constants.PRODUCT_NOT_FOUND;
import static tj.sidalsoft.shop.utils.Constants.TAX_NOT_FOUND;
import static tj.sidalsoft.shop.utils.Constants.TAX_NUMBER_IS_INCORRECT;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final TaxRepository taxRepository;

    @Override
    public double calculatePrice(int productId, String taxNumber, String couponCode) {
        var product = getProduct(productId);
        var tax = getTax(taxNumber);
        var prices = calculateCoupon(couponCode, product.getPrice());
        prices += calcTaxPercent(prices, tax.getPercent());
        return prices;
    }

    private double calculateCoupon(String couponCode, double prices) {
        if (Strings.isEmpty(couponCode)){
            return  prices;
        }

        var coupon = getCoupon(couponCode);
        if (coupon.getType() == CouponType.D) {
            prices -= coupon.getAmount();
        } else {
            prices -= calcTaxPercent(prices, coupon.getAmount());
        }
        return prices;
    }

    private double calcTaxPercent(double prices, int percent) {
        return prices * percent / 100;
    }

    private Product getProduct(int productId) {
        return productRepository.findById(productId)
                                .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND));
    }

    private Coupon getCoupon(String couponCode) {
        return couponRepository.findByName(couponCode)
                               .orElseThrow(() -> new RuntimeException(COUPON_NOT_FOUND));
    }

    private Tax getTax(String couponCode) {
        var countryCode = couponCode.substring(0, 2);
        var tax = taxRepository.findByCode(countryCode)
                               .orElseThrow(() -> new RuntimeException(TAX_NOT_FOUND));
        var taxNumber = couponCode.substring(2);
        if (!Pattern.compile(tax.getNumberFormats())
                   .asPredicate()
                   .test(taxNumber)) {
            throw new RuntimeException(TAX_NUMBER_IS_INCORRECT);
        }
        return tax;
    }
}