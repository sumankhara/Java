package javainaction.ch4;

import javainaction.ch4.model.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // top 3 high calorie dishes
        List<String> threeHighCaloricDishes = menu().stream()
                .filter(dish -> dish.getCalories() > 300)
                .sorted(Comparator.comparingInt(Dish::getCalories).reversed())
                .limit(3)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("Top three high caloric dishes: " + threeHighCaloricDishes);

        // all non-veg dishes
        List<String> allNonVegDishes = menu().stream()
                .filter(dish -> !dish.isVegetarian())
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("All non-veg dishes: " + allNonVegDishes);

        // dishes having fish
        List<String> fishDishes = menu().stream()
                .filter(dish -> dish.getType() == Dish.Type.FISH)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("Fish dishes: " + fishDishes);

        // dishes sorted from low to high calorie
        List<String> lowToHighCalorieDishes = menu().stream()
                .sorted(Comparator.comparingInt(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("Low to high calories dishes: " + lowToHighCalorieDishes);

        // print distinct letters
        List<String> stringList = Stream.of("java8", "in", "action", "lambdas", "stream")
                .map(str -> str.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(stringList);
    }

    public static List<Dish> menu() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        return menu;
    }
}
