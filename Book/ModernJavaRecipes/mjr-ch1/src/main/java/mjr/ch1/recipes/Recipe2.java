package mjr.ch1.recipes;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Method Reference
public class Recipe2 {
    public static void main(String[] args) {
        Stream.of(3, 1, 28, 27, 26, 5, 2)
                .forEach(x -> System.out.println(x));

        Stream.of(3, 1, 28, 27, 26, 5, 2)
                .forEach(System.out::println);

        Consumer<Integer> printer = System.out::println;
        Stream.of(3, 1, 28, 27, 26, 5, 2)
                .forEach(printer);

        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        List<String> sortedLambda = strings.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());
        System.out.println(sortedLambda);

        List<String> sortedMethodRef = strings.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println(sortedMethodRef);

        strings.stream()
                .map(String::length)
                .forEach(System.out::println);

        //Lambda expression equivalent
        strings.stream()
                .map(s -> s.length())
                .forEach(x -> System.out.println(x));
    }
}
