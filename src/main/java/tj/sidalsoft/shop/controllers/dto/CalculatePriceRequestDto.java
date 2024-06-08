package tj.sidalsoft.shop.controllers.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static tj.sidalsoft.shop.utils.Constants.INVALID_COUPON_CODE;
import static tj.sidalsoft.shop.utils.Constants.INVALID_TAX_CODE;

public record CalculatePriceRequestDto(@NotNull
                                       Integer product,

                                       @Pattern(regexp = "^[a-zA-Z]+\\d+$", message = INVALID_TAX_CODE)
                                       String taxNumber,

                                       @Pattern(regexp = "^([DP]){1}\\d+$", message = INVALID_COUPON_CODE)
                                       String couponCode) {
}
