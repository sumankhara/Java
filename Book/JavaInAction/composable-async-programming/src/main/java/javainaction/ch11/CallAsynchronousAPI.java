package javainaction.ch11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CallAsynchronousAPI {
    public static void main(String[] args) {
        // using an asynchronous api
        Shop shop = new Shop("Best Shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyncFactoryMethod("my favourite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        for(int i = 0; i <= 100; i++);

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}
