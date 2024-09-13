package mjr.ch3.recipes;

import java.util.OptionalInt;
import java.util.stream.IntStream;

//Lazy streams
public class Recipe13 {
    public static void main(String[] args) {
        //First double between 200 and 400 divisible by 3
        OptionalInt firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(x -> x * 2)
                .filter(x -> x % 3 == 0)
                .findFirst();
        System.out.println(firstEvenDoubleDivBy3);

        firstEvenDoubleDivBy3 = IntStream.range(100, 200)
                .map(Recipe13::multiplyByTwo)
                .filter(Recipe13::divByThree)
                .findFirst();
        System.out.println(firstEvenDoubleDivBy3);
    }

    public static int multiplyByTwo(int n) {
        System.out.println("Inside multByTwo with arg " + n);
        return n * 2;
    }

    public static boolean divByThree(int n) {
        System.out.println("Inside divByThree with arg " + n);
        return n % 3 == 0;
    }
}
