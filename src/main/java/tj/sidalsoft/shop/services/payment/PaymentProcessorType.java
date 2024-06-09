package tj.sidalsoft.shop.services.payment;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PaymentProcessorType {
    PAYPAL,
    STRIPE;

    @JsonCreator
    public static PaymentProcessorType fromValue(String value) {
        for (PaymentProcessorType contact : values()) {
            if (contact.name().equals(value.toUpperCase())) {
                return contact;
            }
        }
        throw new IllegalArgumentException("Invalid value for Payment Processor type Enum: " + value);
    }
}
