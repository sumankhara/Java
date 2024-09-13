package mjr.ch2.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Predicates
public class Recipe3 {
    public static final Predicate<String> LENGTH_FIVE = s -> s.length() == 5;
    public static final Predicate<String> STARTS_WITH_S = s -> s.startsWith("S");

    public static void main(String[] args) {
        String[] names = Stream.of("Mal", "Wash", "Kaylee", "Inara", "Zoe", "Jayne", "Simon", "River", "Sheperd Book")
                        .sorted().toArray(String[]::new);

        System.out.println(getNamesOfLength(4, names));
        System.out.println(getNamesStartingWith("S", names));
        System.out.println(getNamesSatisfyingCondition(LENGTH_FIVE, names));

        //Composed Predicate
        System.out.println(getNamesSatisfyingCondition(LENGTH_FIVE.and(STARTS_WITH_S), names));
        System.out.println(getNamesSatisfyingCondition(LENGTH_FIVE.or(STARTS_WITH_S), names));
        System.out.println(getNamesSatisfyingCondition(LENGTH_FIVE.negate(), names));
    }

    public static String getNamesOfLength(int length, String... names) {
        return Arrays.stream(names)
                .filter(s -> s.length() == length)
                .collect(Collectors.joining(", "));
    }

    public static String getNamesStartingWith(String str, String... names) {
        return Arrays.stream(names)
                .filter(s -> s.startsWith(str))
                .collect(Collectors.joining(", "));
    }

    public static String getNamesSatisfyingCondition(Predicate<String> condition, String... names) {
        return Arrays.stream(names)
                .filter(condition)
                .collect(Collectors.joining(", "));
    }
}
