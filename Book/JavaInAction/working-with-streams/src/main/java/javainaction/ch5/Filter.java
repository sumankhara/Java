package javainaction.ch5;

import javainaction.ch5.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    public static void filteringDemo() {
        // filtering unique elements in a stream
        List<Integer> numbers = Arrays.asList(2,2,3,4,1,5,7,9,8,4,11,1,3,5).stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique elements: " + numbers);

        // filtering unique even elements in a stream
        numbers = Arrays.asList(2,2,3,4,1,5,7,9,8,4,11,1,3,5)
                .stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique even elements: " + numbers);

        // filtering unique odd elements in a stream
        numbers = Arrays.asList(2,2,3,4,1,5,7,9,8,4,11,1,3,5)
                .stream()
                .filter(i -> i % 2 != 0)
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println("Unique odd elements: " + numbers);

        // high calorie dishes
        List<String> highCalorieDishes = Main.menu().stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("High calorie dishes: " + highCalorieDishes);

        // high calorie dishes (skip first two)
        highCalorieDishes = Main.menu().stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("High calorie dishes (skip first two): " + highCalorieDishes);
    }
}
