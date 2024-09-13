package mjr.ch2.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

//Consumers
public class Recipe1 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        strings.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        strings.forEach(s -> System.out.println(s));

        strings.forEach(System.out::println);

        Optional<String> optionalString = Optional.of("Suman Khara");
        optionalString.ifPresent(System.out::println);
    }
}
