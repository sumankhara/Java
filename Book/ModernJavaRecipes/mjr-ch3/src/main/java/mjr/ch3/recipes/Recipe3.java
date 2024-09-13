package mjr.ch3.recipes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Reduction Operations Using Reduce
public class Recipe3 {
    public static void main(String[] args) {
        String[] strings = "this is an array of strings".split(" ");
        long count = Arrays.stream(strings)
                .mapToInt(String::length)
                .count();
        System.out.println("There are " + count + " strings");

        int totalLength = Arrays.stream(strings)
                .mapToInt(String::length)
                .sum();
        System.out.println("The total length is " + totalLength);

        OptionalDouble averageLength = Arrays.stream(strings)
                .mapToInt(String::length)
                .average();
        System.out.println("The average length is " + averageLength);

        OptionalInt max = Arrays.stream(strings)
                .mapToInt(String::length)
                .max();

        OptionalInt min = Arrays.stream(strings)
                .mapToInt(String::length)
                .min();
        System.out.println("The max and min lengths are " + max + " and " + min);

        //Basic reduce implementation
        int sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> x + y)
                .orElse(0);
        System.out.println(sum);

        sum = IntStream.rangeClosed(1, 10)
                .reduce((x, y) -> {
                    System.out.printf("x=%d, y=%d\n", x, y);
                    return x + y;
                }).orElse(0);
        System.out.println(sum);

        int doubleSum = IntStream.rangeClosed(1, 10)
                .reduce(0, (x, y) -> x + 2 * y);
        System.out.println(doubleSum);

        sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .reduce(0, Integer::sum);
        System.out.println(sum);

        Integer maxValue = Stream.of(3, 14, 5, 28, 26, 2, 31)
                .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println(maxValue);

        String str = Stream.of("this", "is", "a", "list")
                .reduce("", String::concat);
        System.out.println(str);

        str = Stream.of("this", "is", "a", "list")
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        System.out.println(str);

        str = Stream.of("this", "is", "a", "list")
                .collect(Collectors.joining());
        System.out.println(str);

        //The most general form of reduce
        List<Book> books = Stream.of(
                new Book(1, "Modern Java Recipes"),
                new Book(2, "Spring Start Here"),
                new Book(3, "Spring Security in Action")
        ).collect(Collectors.toList());

        HashMap<Integer, Book> bookHashMap = books.stream()
                .reduce(new HashMap<Integer, Book>(),
                        (map, book) -> {
                            map.put(book.getId(), book);
                            return map;
                        },
                        (map1, map2) -> {
                            map1.putAll(map2);
                            return map1;
                        });
        System.out.println(bookHashMap);
        bookHashMap.forEach((k,v) -> System.out.println(k + ":" + v));
    }
}
