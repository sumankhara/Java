package mjr.ch4.recipes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Sorting Maps
public class Recipe4 {
    public static void main(String[] args) {
        //Reading the dictionary file (/usr/share/dict/words) into a Map
        System.out.println("Number of words of each length:");
        try(Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/words"))) {
            lines.filter(line -> line.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()))
                    .forEach((len, num) -> System.out.printf("%d : %d%n", len, num));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Sorting the map by key
        System.out.println("\nNumber of words of each length (desc order):");
        try(Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/words"))) {
            Map<Integer, Long> map = lines.filter(l -> l.length() > 20)
                    .collect(Collectors.groupingBy(String::length, Collectors.counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d : %2d words%n", e.getKey(), e.getValue()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
