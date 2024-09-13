package javainaction.ch5;

import javainaction.ch5.model.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindAndMatch {
    public static void findindAndMatchingDemo() {
        // find out whether the menu has a vegetarian option
        if(Main.menu().stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!");
        }

        // find out whether the menu is healthy (that is, all dishes are below 1000 calories)
        // using allMatch
        boolean isHealthy = Main.menu().stream()
                .allMatch(dish -> dish.getCalories() <= 1000);
        System.out.println("Is menu healthy? " + isHealthy);

        // using noneMatch
        isHealthy = Main.menu().stream()
                .noneMatch(dish -> dish.getCalories() > 1000);
        System.out.println("Is menu healthy? " + isHealthy);

        // find a dish that’s vegetarian
        Optional<Dish> optionalDish = Main.menu().stream()
                .filter(Dish::isVegetarian)
                .findAny();
        optionalDish.ifPresent(System.out::println);

        // find the first square that’s divisible by 3
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        numbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);
    }
}
