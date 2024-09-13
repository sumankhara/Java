package javainaction.ch11;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

public class CallSynchronousAPI {
    private final List<Shop> shops = List.of(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("Flipkart"),
            new Shop("Amazon"),
            new Shop("Myntra"),
            new Shop("RelianceMart"),
            new Shop("BigBasket"),
            new Shop("Blinkit"));

    // A custom Executor fitting our best-price-finder application
    private final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = newThread(r);
                    t.setDaemon(true);
                    return t;
                }
            });

    public static void main(String[] args) {

        System.out.println("Number of processors: " + Runtime.getRuntime().availableProcessors());

        CallSynchronousAPI callSynchronousAPI = new CallSynchronousAPI();

        // Checking findPrices correctness and performance
        long start = System.nanoTime();
        List<String> prices = callSynchronousAPI.findPrices("myPhone27S");
        System.out.println(prices);
        long duration = ((System.nanoTime() - start) / 1000000);
        System.out.println("Sequential processing done in " + duration + " msecs");

        // Checking findPricesParallel correctness and performance
        start = System.nanoTime();
        prices = callSynchronousAPI.findPricesParallel("myPhone27S");
        System.out.println(prices);
        duration = ((System.nanoTime() - start) / 1000000);
        System.out.println("Parallel processing done in " + duration + " msecs");

        // Checking findPricesCompletableFuture correctness and performance
        start = System.nanoTime();
        prices = callSynchronousAPI.findPricesCompletableFuture("myPhone27S");
        System.out.println(prices);
        duration = ((System.nanoTime() - start) / 1000000);
        System.out.println("Completable Future processing done in " + duration + " msecs");

        // Checking findPricesUsingDiscountService correctness and performance
        start = System.nanoTime();
        prices = callSynchronousAPI.findPricesUsingDiscountService("myPhone27S");
        System.out.println(prices);
        duration = ((System.nanoTime() - start) / 1000000);
        System.out.println("Synchronous Discount service processing done in " + duration + " msecs");

        // Checking findPricesUsingDiscountServiceWithCompletableFuture correctness and performance
        start = System.nanoTime();
        prices = callSynchronousAPI.findPricesUsingDiscountServiceWithCompletableFuture("myPhone27S");
        System.out.println(prices);
        duration = ((System.nanoTime() - start) / 1000000);
        System.out.println("Discount service with CompletableFuture processing done in " + duration + " msecs");
    }

    // A findPrices implementation sequentially querying all the shops
    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    // Parallelizing the findPrices method
    public List<String> findPricesParallel(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }

    // Making asynchronous requests with CompletableFutures
    public List<String> findPricesCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product), executor))
                )
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    // Using the Discount service
    public List<String> findPricesUsingDiscountService(String product) {
        return shops.stream()
                .map(shop -> shop.getPriceWithDiscountInfo(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }

    // Implementing the findPrices method with CompletableFuture
    public List<String> findPricesUsingDiscountServiceWithCompletableFuture(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceWithDiscountInfo(product)))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote))))
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
