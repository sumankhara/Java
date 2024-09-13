package mjr.ch3.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Counting elements
public class Recipe7 {
    public static void main(String[] args) {
        long count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5).count();
        System.out.println("There are " + count + " elements in the stream");

        count = Stream.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 11, 21).collect(Collectors.counting());
        System.out.println("There are " + count + " elements in the stream");

        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        Map<Boolean, Long> numberLengthMap = strings.stream()
                .collect(Collectors.partitioningBy(
                        s -> s.length() % 2 == 0,
                        Collectors.counting()
                ));
        System.out.println(numberLengthMap);
    }
}
