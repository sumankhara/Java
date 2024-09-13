package javainaction.ch5;

import javainaction.ch5.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reduce {
    public static void reducingDemo() {
        // sum/product of elements
        List<Integer> numbers = Arrays.asList(5,27,12,5,28,26,18,2,28);
        int sum = numbers.stream()
                .reduce(0, (a,b) -> a + b);
        System.out.println("Sum: " + sum);

        Optional<Integer> product = numbers.stream()
                .reduce((a,b) -> a * b);
        System.out.println("Product: " + product.get());

        // calculate the maximum
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);

        // calculate the minimum
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        // count the number of dishes in a stream using the map and reduce methods
        int count = Main.menu().stream()
                .map(dish -> 1)
                .reduce(0, (a,b) -> a + b);
        System.out.println("Number of dishes: " + count);
    }
}
