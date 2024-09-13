package mjr.ch2.recipes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.DoubleSupplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//Suppliers
public class Recipe2 {
    private static final Logger logger = Logger.getLogger(Recipe2.class.getName());

    public static void main(String[] args) {
        DoubleSupplier randomSupplier = new DoubleSupplier() {
            @Override
            public double getAsDouble() {
                return Math.random();
            }
        };

        logger.info(String.valueOf(randomSupplier.getAsDouble()));

        randomSupplier = () -> Math.random();
        logger.info(String.valueOf(randomSupplier.getAsDouble()));

        randomSupplier = Math::random;
        logger.info(String.valueOf(randomSupplier.getAsDouble()));

        //Deferred execution
        List<String> names = Arrays.asList("Mal", "Wash", "Kaylee", "Inara", "Zoe", "Jayne", "Simon", "River", "Sheperd Book");

        Optional<String> first = names.stream()
                .filter(name -> name.startsWith("C"))
                .findFirst();

        System.out.println(first);
        System.out.println(first.orElse("None"));

        System.out.println(first.orElse(String.format("No result found in %s",
                names.stream().collect(Collectors.joining(", ")))));

        System.out.println(first.orElseGet(() ->
                String.format("No result found in %s",
                        names.stream().collect(Collectors.joining(", ")))));
    }
}
