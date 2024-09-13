package javainaction.ch3;

import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>(List.of(
                new Apple("red", 150),
                new Apple("red", 168),
                new Apple("green", 120),
                new Apple("pink", 175),
                new Apple("green", 117)
        ));

        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };

        Comparator<Apple> reverseByWeight = (a1, a2) -> -1 * (a1.getWeight().compareTo(a2.getWeight()));

        System.out.println("Before sort: " + apples);
        apples.sort(byWeight);
        System.out.println("After sort: " + apples);

        apples.sort(reverseByWeight);
        System.out.println("Reverse sort: " + apples);

        List<Apple> greenApples = filter(apples, apple -> "green".equalsIgnoreCase(apple.getColor()));
        System.out.println("Green Apples: " + greenApples);

        List<Apple> pinkApples = filter(apples, apple -> "pink".equalsIgnoreCase(apple.getColor()));
        System.out.println("Pink Apples: " + pinkApples);

        forEach(apples, System.out::println);
        forEach(Arrays.asList(7,3,5,9,2,8,4,6,0,1), (i) -> System.out.println(i));

        List<Integer> stringLength = map(Arrays.asList("Java", "in", "action"),
                (s) -> s.length());
        System.out.println("String lengths: " + stringLength);

        IntBinaryOperator intBinaryOperator = (a, b) -> a * b;
        System.out.println(intBinaryOperator.applyAsInt(20, 40));
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for(T t: list) {
            if(predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t: list) {
            consumer.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for(T t: list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
