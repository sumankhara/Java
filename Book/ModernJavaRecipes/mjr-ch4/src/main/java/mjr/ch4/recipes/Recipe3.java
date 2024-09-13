package mjr.ch4.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//Adding a linear collection to Map
public class Recipe3 {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book(1, "Modern Java Recipes", 49.99),
                new Book(2, "Java 8 in Action", 49.99),
                new Book(3, "Java SE8 for the Really Impatient", 39.99),
                new Book(4, "Functional Programming in Java", 27.64),
                new Book(5, "Making Java Groovy", 45.99),
                new Book(6, "Five Lines of Code", 19.99)
        );

        //Adding the books to a Map
        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, b -> b));
        bookMap.forEach((key, value) -> System.out.println(key + ": " + value));

        bookMap = books.stream()
                .collect(Collectors.toMap(Book::getId, Function.identity()));
        bookMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
