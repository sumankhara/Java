package javainaction.ch8.designpatterns.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {
    private final static Map<String, Supplier<Product>> productMap = new HashMap<>();

    static {
        productMap.put("stock", Stock::new);
        productMap.put("fd", FixedDeposit::new);
    }

    public static Product createProduct(String name) {
        switch (name) {
            case "stock":
                return new Stock();
            case "fd":
                return new FixedDeposit();
            default: throw new RuntimeException("No such product " + name);
        }
    }

    public static Product createProductFromMap(String name) {
        Supplier<Product> p = productMap.get(name);
        if(p != null) {
            return p.get();
        }
        throw new RuntimeException("No such product " + name);
    }
}
