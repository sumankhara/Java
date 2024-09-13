package javainaction.ch5;

import javainaction.ch5.model.Dish;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {
    public static void numericStreams() {
        List<Dish> menus = Main.menu();

        // total calories in menu
        int calories = menus.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("Total calories: " + calories);

        // max calories
        OptionalInt maxCalories = menus.stream().mapToInt(Dish::getCalories).max();
        System.out.println("Max calorie: " + maxCalories.orElse(1));

        // numeric ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println("Total even numbers between 1 and 100: " + evenNumbers.count());

        // Pythagorean triple
        Stream<double[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                    IntStream.range(1, 100)
                            .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)})
                            .filter(t -> t[2] % 1 == 0)

                );
        pythagoreanTriples.limit(10)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
