package jia.ch5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jia.ch5.Menu.*;

public class Main {
    public static void main(String[] args) {
        //Filtering with a predicate
        List<Dish> vegetarianMenu = menu()
                .stream().filter(dish -> dish.isVegetarian())
                .collect(Collectors.toList());
        System.out.println("Vegetarian Menu: " + vegetarianMenu);

        //Filtering unique elements
        List<Integer> numbers = Arrays.asList(1,2,1,3,3,2,4);
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        numbers.stream()
                .distinct()
                .forEach(System.out::println);

        //Truncating a stream
        List<Dish> dishes = menu().stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(dishes);

        //Skipping elements
        dishes = menu().stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(dishes);

        //First two meat dishes
        dishes = menu().stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(dishes);

        //Mapping
        List<String> words = Arrays.asList("this", "is", "a", "list", "of", "words");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);

        List<Integer> lengthOfDishNames = menu().stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(lengthOfDishNames);

        //Using flatmap
        words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);

        //Matching
        if(menu().stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly");
        }

        boolean isHealthy = menu().stream()
                .allMatch(dish -> dish.getCalories() < 1000);
        System.out.println("The menu is " + (isHealthy ? "healthy" : "not healthy"));

        isHealthy = menu().stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println("The menu is " + (isHealthy ? "healthy" : "not healthy"));

        //Finding
        menu().stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);

        //Find first square divisible by 3
        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);

        //Reducing
        numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);
        System.out.println("Sum = " + sum + ", Product: " + product);

        int max = numbers.stream()
                .reduce(Integer::max)
                .get();
        int min = numbers.stream()
                .reduce(Integer::min)
                .get();
        System.out.println("Max = " + max + ", Min = " + min);
    }
}
