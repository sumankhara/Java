package jia.ch3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferences {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        //strings.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        strings.sort(String::compareToIgnoreCase);
        System.out.println(strings);

        //Constructor references

        //No-args constructor
        Supplier<Apple> supplier = Apple::new;
        System.out.println(supplier.get());

        //One-arg constructor
        Function<Integer, Apple> intFunction = Apple::new;
        System.out.println(intFunction.apply(150));

        Function<String, Apple> stringFunction = Apple::new;
        System.out.println(stringFunction.apply("green"));

        List<Integer> weights = Arrays.asList(120, 100, 80, 67, 75);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println(apples);

        //Two-args constructor
        BiFunction<String, Integer, Apple> biFunction = Apple::new;
        System.out.println(biFunction.apply("red", 124));

        //Three-args constructor - you have to create your own functional interface
        TriFunction<Integer, Integer, Integer, Color> triFunction = Color::new;
        System.out.println(triFunction.apply(255, 245, 235));
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer e: list) {
            result.add(function.apply(e));
        }
        return result;
    }
}
