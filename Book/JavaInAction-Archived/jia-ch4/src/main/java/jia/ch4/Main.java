package jia.ch4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static jia.ch4.Menu.*;

public class Main {
    public static void main(String[] args) {
        //Low calorie dishes sorted by calories
        List<String> lowCaloricDishNames =
                menu().stream()
                        .filter(dish -> dish.getCalories() < 400)
                        .sorted(Comparator.comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(Collectors.toList());
        System.out.println(lowCaloricDishNames);

        //Three high calorie dishes
        List<String> highCaloricDishNames =
                menu().stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(Collectors.toList());
        System.out.println(highCaloricDishNames);

        //Top 3 high calorie dishes
        List<String> top3HighCaloricDishNames =
                menu().stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .sorted(Comparator.comparing(Dish::getCalories).reversed())
                        .map(Dish::getName)
                        .limit(3)
                        .collect(Collectors.toList());
        System.out.println(top3HighCaloricDishNames);
    }
}
