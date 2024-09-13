package mjr.ch2.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

//Functions
public class Recipe4 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara", "Zoe", "Jayne", "Simon", "River", "Sheperd Book");

        List<Integer> nameLengths = names.stream()
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                })
                .collect(Collectors.toList());

        System.out.println(nameLengths);

        nameLengths = names.stream()
                .map(s -> s.length())
                .collect(Collectors.toList());
        System.out.println(nameLengths);

        nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(nameLengths);

        nameLengths = names.stream()
                .mapToInt(new ToIntFunction<String>() {
                    @Override
                    public int applyAsInt(String value) {
                        return value.length();
                    }
                })
                .boxed()
                .collect(Collectors.toList());
        System.out.println(nameLengths);
    }
}
