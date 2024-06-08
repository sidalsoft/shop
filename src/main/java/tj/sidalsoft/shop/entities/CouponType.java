package tj.sidalsoft.shop.entities;

public enum CouponType {
    D("фиксированная сумма скидки"),
    P("процент от суммы покупки");

    private final String value;

    CouponType(String str) {
        value = str;
    }
}
