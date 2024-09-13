package javainaction.ch5;

import javainaction.ch5.model.Dish;
import javainaction.ch5.model.Trader;
import javainaction.ch5.model.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        //Filter.filteringDemo();

        //Map.mappingDemo();

        //FindAndMatch.findindAndMatchingDemo();

        //Reduce.reducingDemo();

        // Putting it all into practice

        //Practice.practiceStreamsAPI();

        //NumericStreams.numericStreams();

        StreamCreation.buildingStreams();
    }

    public static List<Dish> menu() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        return menu;
    }
}
