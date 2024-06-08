package tj.sidalsoft.shop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tj.sidalsoft.shop.controllers.dto.CalculatePriceRequestDto;
import tj.sidalsoft.shop.controllers.dto.CalculatePriceResponseDto;
import tj.sidalsoft.shop.services.ProductService;

@RestController
@RequiredArgsConstructor
public class CoreController {

    private final ProductService productService;

    @PostMapping(path = "/calculate-price",
                 consumes = APPLICATION_JSON_VALUE,
                 produces = APPLICATION_JSON_VALUE)
    public CalculatePriceResponseDto calculatePrice(@Valid @RequestBody CalculatePriceRequestDto request) {
        var price = productService.calculatePrice(request.product(), request.taxNumber(), request.couponCode());
        return new CalculatePriceResponseDto(price);
    }
}