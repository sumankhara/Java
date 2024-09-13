package javainaction.ch11;

import java.security.PublicKey;

public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PALTINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    private static double apply(double price, Code code) {
        delay();
        return price * (100 - code.percentage) / 100;
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
