package javainaction.ch5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class StreamCreation {
    public static void buildingStreams() {

        // Streams from values
        Stream<String> stringStream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        // Streams from arrays
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        // Streams from files
        long words = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("/Users/sumankhara/Documents/PersonalProjects/Java/essay.txt"), Charset.defaultCharset())) {
            words = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Number of words: " + words);

        // iterate
        Stream.iterate(0, n ->  n + 2)
                .limit(5)
                .forEach(System.out::println);

        // Fibonacci tuples series
        Stream.iterate(new int[]{0,1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));

        // generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
