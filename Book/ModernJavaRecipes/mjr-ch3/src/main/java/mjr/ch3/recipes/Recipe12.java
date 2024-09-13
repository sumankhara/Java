package mjr.ch3.recipes;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Concatenating streams
public class Recipe12 {
    public static void main(String[] args) {
        Stream<String> first = Stream.of("a", "b", "c");
        Stream<String> second = Stream.of("X", "Y", "Z");

        List<String> strings = Stream.concat(first, second)
                .collect(Collectors.toList());
        System.out.println(strings);

        first = Stream.of("a", "b", "c");
        second = Stream.of("X", "Y", "Z");
        Stream<String> third = Stream.of("alpha", "beta", "gamma");

        strings = Stream.concat(Stream.concat(first, second), third)
                .collect(Collectors.toList());
        System.out.println(strings);

        //Concatenating with reduce
        first = Stream.of("a", "b", "c");
        second = Stream.of("X", "Y", "Z");
        third = Stream.of("alpha", "beta", "gamma");
        Stream<String> fourth = Stream.empty();

        strings = Stream.of(first, second, third, fourth)
                .reduce(Stream.empty(), Stream::concat)
                .collect(Collectors.toList());
        System.out.println(strings);

        //Using flatMap to concatenate streams
        first = Stream.of("a", "b", "c");
        second = Stream.of("X", "Y", "Z");
        third = Stream.of("alpha", "beta", "gamma");
        fourth = Stream.empty();

        strings = Stream.of(first, second, third, fourth)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
        System.out.println(strings);
    }
}
