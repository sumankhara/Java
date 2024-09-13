package mjr.ch4.recipes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Sorting using a Comparator
public class Recipe1 {
    public static void main(String[] args) {
        //Sorting strings lexicographically
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");

        Collections.sort(strings);
        System.out.println(strings);

        strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        List<String> sorted = strings.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(strings);
        System.out.println(sorted);

        System.out.println("lengthSortUsingSorted: " + lengthSortUsingSorted(strings));

        System.out.println("lengthSortUsingComparator: " + lengthSortUsingComparator(strings));

        System.out.println("lengthSortThenAlphaSort: " + lengthSortThenAlphaSort(strings));

        List<Golfer> golfers = Arrays.asList(
                new Golfer("Jack", "Nicklaus", 68),
                new Golfer("Tiger", "Woods", 70),
                new Golfer("Tom", "Watson", 70),
                new Golfer("Ty", "Webb", 68),
                new Golfer("Bubba", "Watson", 70)
        );

        System.out.println("sortByScoreThenLastThenFirst: " + sortByScoreThenLastThenFirst(golfers));
    }

    //Sorting strings by length
    public static List<String> lengthSortUsingSorted(List<String> strings) {
        return strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(Collectors.toList());
    }

    public static List<String> lengthSortUsingComparator(List<String> strings) {
        return strings.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }

    //Sorting by length, then equal lengths lexicographically
    public static List<String> lengthSortThenAlphaSort(List<String> strings) {
        return strings.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    //Sorted by score, then last, then first
    public static List<Golfer> sortByScoreThenLastThenFirst(List<Golfer> strings) {
        return strings.stream()
                .sorted(Comparator.comparingInt(Golfer::getScore)
                        .thenComparing(Golfer::getLast)
                        .thenComparing(Golfer::getFirst))
                .collect(Collectors.toList());
    }
}
