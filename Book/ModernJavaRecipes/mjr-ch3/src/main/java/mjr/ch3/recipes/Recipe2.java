package mjr.ch3.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//Boxed Streams
public class Recipe2 {
    public static void main(String[] args) {
        List<Integer> ints = IntStream.of(27,5,28,2,28)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(ints);

        ints = IntStream.of(5,27,5,28,2,28)
                .mapToObj(Integer::new)
                .collect(Collectors.toList());
        System.out.println(ints);

        ints = IntStream.of(5,27,12,5,28,26,2,28)
                .collect(ArrayList<Integer>::new, ArrayList::add, ArrayList::addAll);
        System.out.println(ints);

        int[] intArray = IntStream.of(5,27,12,5,28,26,2,28).toArray();
        System.out.println(Arrays.stream(intArray).max());
    }
}
