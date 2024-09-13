package jia.ch3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Runnable r1 = () -> System.out.println("Hello World 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("Hello World 3"));

        System.out.println("First two lines: " + processFile(br -> br.readLine() + br.readLine()));
        System.out.println("First line: " + processFile(br -> br.readLine()));

        Predicate<String> nonEmptyStringPredicate = str -> !str.isEmpty();
        List<String> nonEmptyList = filter(Arrays.asList("this", "is", "a", "list", "of", "strings", "", " "), nonEmptyStringPredicate);
        System.out.println("Non empty list: " + nonEmptyList);

        forEach(Arrays.asList(1,2,3,4,5,6,7,8,9), val -> System.out.println(val));
        forEach(Arrays.asList("this", "is", "a", "list", "of", "strings"), val -> System.out.println(val));

        List<Integer> integerList = map(Arrays.asList("lambda", "in", "action"), s -> s.length());
        System.out.println(integerList);

        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        System.out.println(evenNumbers.test(1000));

        IntBinaryOperator intBinaryOperator = (i, j) -> i * j;
        System.out.println(intBinaryOperator.applyAsInt(12, 10));
    }

    public static void process(Runnable r) {
        r.run();
    }

    public static String processFile(BufferedReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("/Users/sumankhara/Documents/PersonalProjects/Java/Book/JavaInAction/jia-ch3/data.txt"))) {
            return brp.process(br);
        }
    }

    //Using Predicate
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t: list) {
            if(predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    //Using Consumer
    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t: list) {
            consumer.accept(t);
        }
    }

    //Using Function
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for(T t: list) {
            result.add(function.apply(t));
        }
        return result;
    }
}
