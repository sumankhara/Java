package mjr.ch3.recipes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

//Creating streams
public class Recipe1 {
    public static void main(String[] args) {
        String names = Stream.of("Gomez", "Morticia", "Wednesday", "Pugsley")
                .collect(Collectors.joining("\n"));
        System.out.println(names);

        String[] monsters = {"Herman", "Lily", "Eddie", "Marilyn", "Grandpa"};
        names = Arrays.stream(monsters)
                .collect(Collectors.joining(", "));
        System.out.println(names);

        List<BigDecimal> nums = Stream.iterate(BigDecimal.ONE, n -> n.add(BigDecimal.ONE))
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(nums);

        Stream.iterate(LocalDate.now(), ld -> ld.plusDays(1L))
                .limit(10)
                .forEach(System.out::println);

        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);

        List<Integer> ints = IntStream.range(1, 10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(ints);

        List<Long> longs = LongStream.rangeClosed(1, 10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longs);
    }
}
