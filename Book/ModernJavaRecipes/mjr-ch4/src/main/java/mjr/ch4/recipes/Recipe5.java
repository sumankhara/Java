package mjr.ch4.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Partitioning and Grouping
public class Recipe5 {
    public static void main(String[] args) {
        //Partitioning strings by even or odd lengths
        List<String> strings = Arrays.asList("this", "is", "a", "long", "list", "of", "strings", "to",
                "use", "as", "a", "demo");
        Map<Boolean, List<String>> lengthMap = strings.stream()
                .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key, value) -> System.out.printf("%5s : %s%n", key, value));

        //Grouping strings by length
        Map<Integer, List<String>> groupMap = strings.stream()
                .collect(Collectors.groupingBy(String::length));
        groupMap.forEach((key, value) -> System.out.printf("%5s : %s%n", key, value));
    }
}
