package mjr.ch3.recipes;

import java.util.stream.IntStream;

//Debugging streams with peek
public class Recipe5 {
    public static void main(String[] args) {
        int i = IntStream.range(100, 120)
                .map(n -> {
                    System.out.println(n);
                    return n;
                })
                .map(n ->  n * 2)
                .filter(n ->  n % 3 == 0)
                .sum();
        System.out.println(i);

        int x = IntStream.rangeClosed(100, 120)
                .peek(n -> System.out.printf("Original: %d%n", n))
                .map(n -> n * 2)
                .peek(n -> System.out.printf("Doubled: %d%n", n))
                .filter(n -> n % 3 == 0)
                .peek(n -> System.out.printf("Filtered: %d%n", n))
                .sum();
        System.out.println(x);
    }
}
