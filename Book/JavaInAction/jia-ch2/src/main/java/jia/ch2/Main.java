package jia.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(
                new Apple("green", 155),
                new Apple("yellow", 200),
                new Apple("green", 80),
                new Apple("red", 120),
                new Apple("red", 175),
                new Apple("pink", 167)
        );

        List<Apple> result = filterApplesByColour(apples, "red");
        System.out.println("Red coloured apples: " + result);
        result = filterApplesByWeight(apples, 150);
        System.out.println("Apples with weight greater than 150 g: " + result);
        result = filterApples(apples, "pink", 0, true);
        System.out.println("Pink coloured apples: " + result);
        result = filterApples(apples, "", 120, false);
        System.out.println("Apples with weight greater than 120 g: " + result);

        result = filterApples(apples, new AppleGreenColourPredicate());
        System.out.println("Green coloured apples: " + result);

        prettyPrintApple(apples, new FancyAppleFormatter());

        //Fifth attempt: using an anonymous class
        result = filterApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColour())
                        && apple.getWeight() > 150;
            }
        });
        System.out.println("Apples red and heavy: " + result);

        //Sixth attempt: using a lambda expression
        result = filterApples(apples, (apple) -> "red".equals(apple.getColour()));
        System.out.println("Red apples: " + result);

        List<Apple> greenApples = filter(apples, apple -> "green".equals(apple.getColour()));
        System.out.println("Green apples: " + greenApples);

        List<Integer> evenNumbers = filter(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14), num -> num % 2 == 0);
        System.out.println("Even numbers: " + evenNumbers);

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        System.out.println("Sorted apples: " + apples);

        apples.sort((apple1, apple2) -> {
            return -1 * apple1.getWeight().compareTo(apple2.getWeight());
        });
        System.out.println("Reverse sorted apples: " + apples);
    }

    //First attempt: filtering green apples
    public static List<Apple> filterGreenApples(List<Apple> apples) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple: apples) {
            if("green".equals(apple.getColour())) {
                result.add(apple);
            }
        }

        return result;
    }

    //Second attempt: parameterizing the colour
    public static List<Apple> filterApplesByColour(List<Apple> apples, String colour) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple: apples) {
            if(apple.getColour().equals(colour)) {
                result.add(apple);
            }
        }

        return result;
    }

    //Second attempt: parameterizing the weight
    public static List<Apple> filterApplesByWeight(List<Apple> apples, int weight) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple: apples) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }

        return result;
    }

    //Third attempt: filtering with every attribute you can think of
    public static List<Apple> filterApples(List<Apple> apples, String colour, int weight, boolean flag) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple: apples) {
            if((flag && apple.getColour().equals(colour)) ||
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }

    //Fourth attempt: filtering by abstract criteria
    public static List<Apple> filterApples(List<Apple> apples, ApplePredicate applePredicate) {
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple: apples) {
            if(applePredicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> apples, AppleFormatter formatter) {
        for (Apple apple: apples) {
            formatter.accept(apple);
        }
    }

    //Seventh attempt: abstracting over List type
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
       List<T> result = new ArrayList<>();
       for(T t: list) {
           if(predicate.test(t)) {
               result.add(t);
           }
       }
       return result;
    }
}
