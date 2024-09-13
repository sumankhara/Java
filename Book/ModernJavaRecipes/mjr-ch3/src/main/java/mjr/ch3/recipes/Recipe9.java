package mjr.ch3.recipes;

import java.util.Optional;
import java.util.stream.Stream;

//Finding the first element in a stream
public class Recipe9 {
    public static void main(String[] args) {
        Optional<Integer> firstEven = Stream.of(3,1,4,1,5,9,2,6,5)
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEven);

        Optional<Integer> firstEvenGT10 = Stream.of(3,1,4,1,5,9,2,6,5)
                .filter(n -> n % 2 == 0)
                .filter(n ->  n > 10)
                .findFirst();
        System.out.println(firstEvenGT10);

        firstEven = Stream.of(3,1,4,1,5,9,2,6,5)
                .parallel()
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println(firstEven);
    }
}
