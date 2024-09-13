package javainaction.ch5;

import javainaction.ch5.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Map {

    public static void mappingDemo() {
        // first two meat dishes
        List<String> firstTwoMeatDishes = Main.menu().stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("First two meat dishes: " + firstTwoMeatDishes);

        // dish name lengths
        List<Integer> dishNameLengths = Main.menu().stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Dish name lengths: " + dishNameLengths);

        // square of numbers
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9 ,10);
        List<Integer> squares = numbers.stream()
                .map(x -> x * x)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squares);

        // pairs of numbers
        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(4,5);

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j})
                )
                .collect(Collectors.toList());
        pairs.forEach(pair -> {
            System.out.println("(" + pair[0] + "," + pair[1] + ")");
        });

        System.out.println();

        // pairs of numbers whose sum is divisible by three
        pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(pair -> {
            System.out.println("(" + pair[0] + "," + pair[1] + ")");
        });
    }
}
