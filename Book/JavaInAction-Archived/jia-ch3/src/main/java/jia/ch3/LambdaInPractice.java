package jia.ch3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaInPractice {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple("green", 155),
                new Apple("yellow", 200),
                new Apple("green", 80),
                new Apple("red", 120),
                new Apple("red", 175),
                new Apple("pink", 167),
                new Apple("brown", 120)
        );

        System.out.println("Before Sorting: " + apples);
        //apples.sort(new AppleComparator());
        /*
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
         */
        //apples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        //apples.sort(Comparator.comparing(apple -> apple.getWeight()));
        apples.sort(Comparator.comparing(Apple::getWeight));
        System.out.println("After Sorting: " + apples);

        //Composing Comparators
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        System.out.println("Reversed sorting: " + apples);

        //Chaining Comparators
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        System.out.println("Sort first by weight and then by color: " + apples);

        //Composing Predicates
        Predicate<Apple> redApplePredicate = (apple -> apple.getColor().equals("red"));
        List<Apple> notRedApples = new ArrayList<>();
        for(Apple apple: apples) {
            if(redApplePredicate.negate().test(apple)) {
                notRedApples.add(apple);
            }
        }
        System.out.println("Not red apples: " + notRedApples);

        Predicate<Apple> redAndHeavyApplePredicate = redApplePredicate.and(a -> a.getWeight() > 120);
        List<Apple> redAndHeavyApples = new ArrayList<>();
        for (Apple apple: apples) {
            if(redAndHeavyApplePredicate.test(apple)) {
                redAndHeavyApples.add(apple);
            }
        }
        System.out.println("Red and heavy apples: " + redAndHeavyApples);

        //Composing Functions
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> 2 * x;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println("g(f(x)): " + h.apply(1));

        h = f.compose(g);
        System.out.println("f(g(x)): " + h.apply(1));

        Function<String, String> addHeader = LambdaInPractice::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(LambdaInPractice::checkSpelling)
                .andThen(LambdaInPractice::addFooter);
        System.out.println(transformationPipeline.apply("labda in practice"));
    }

    public static String addHeader(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text) {
        return text.replaceAll("labda", "lambda");
    }
}
