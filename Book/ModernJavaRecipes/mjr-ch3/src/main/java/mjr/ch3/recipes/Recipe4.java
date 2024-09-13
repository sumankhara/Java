package mjr.ch3.recipes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Check sorting using reduce
public class Recipe4 {
    public static void main(String[] args) {
        BigDecimal total = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                .reduce(BigDecimal.ZERO, (acc, val) -> acc.add(val));
        System.out.println("The total is: " + total);

        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        List<String> sorted = strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Sorted list: " + sorted);

        strings.stream()
                .reduce((prev, curr) -> {
                    assert prev.length() <= curr.length();
                    return curr;
                });
    }
}
