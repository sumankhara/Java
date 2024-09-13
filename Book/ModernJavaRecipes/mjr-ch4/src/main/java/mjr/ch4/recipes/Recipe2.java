package mjr.ch4.recipes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Converting a stream into a Collection
public class Recipe2 {
    public static void main(String[] args) {
        //Creating a list
        List<String> superHeroes = Stream.of("Mr. Furious", "The Blue Raja",
                "The Shoveler", "The Bowler", "Invisible Boy", "The Spleen", "The Sphinx")
                .collect(Collectors.toList());
        System.out.println(superHeroes);

        //Creating a set
        Set<String> villains = Stream.of("Casanova Frankenstein", "The Disco Boys",
                "The Not-So-Goodie Mob", "The Suits", "The Suzies",
                "The Furriers", "The Furriers")
                .collect(Collectors.toSet());
        System.out.println(villains);

        //Creating a linked list
        List<String> actors = Stream.of("Hank Azaria", "Janeane Garofalo", "Paul Reubens",
                "Ben Stiller")
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(actors);

        //Creating an array
        String[] wannabes = Stream.of("The Waffler", "Reverse Psycologist", "PMS Avenger")
                .toArray(String[]::new);
        System.out.println(wannabes);

        //Creating a Map
        List<Golfer> golfers = Arrays.asList(
                new Golfer("Jack", "Nicklaus", 68),
                new Golfer("Tiger", "Woods", 70),
                new Golfer("Tom", "Watson", 70),
                new Golfer("Ty", "Webb", 68),
                new Golfer("Bubba", "Watson", 70)
        );
        Map<String, Integer> golferMap = golfers.stream()
                .collect(Collectors.toMap(golfer -> {
                    return golfer.getFirst() + " " + golfer.getLast();
                }, Golfer::getScore));

        golferMap.forEach((key, value) -> System.out.println(key + " scored " + value));
    }
}
