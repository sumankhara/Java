package javainaction.ch6;

import javainaction.ch6.model.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Dish> menus = MenuGenerator.menu();

        //collectors(menus);

        //grouping(menus);

        //partitioning(menus);

        // Partitioning numbers into prime and nonprime
        //System.out.println("Primes: " + partitionPrimes(100));

        // Custom Collector
        List<Dish> dishes = menus.stream().collect(new ToListCollector<Dish>());
        System.out.println(dishes);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, 200)
                .boxed()
                .collect(Collectors.partitioningBy(Main::isPrime));
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static void partitioning(List<Dish> menus) {
        Map<Boolean, List<Dish>> partitionedMenu = menus.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println("Partitioned menu: " + partitionedMenu);

        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        System.out.println("Vegetarian dishes: " + vegetarianDishes);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menus.stream()
                .collect(Collectors.partitioningBy(
                        Dish::isVegetarian,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        )
                ));
        System.out.println("Most caloric dish among both vegetarian and nonvegetarian dishes: " + mostCaloricPartitionedByVegetarian);
    }

    public static void grouping(List<Dish> menus) {
        Map<Dish.Type, List<Dish>> dishesByType =
                menus.stream()
                        .collect(Collectors.groupingBy(Dish::getType));
        System.out.println("Dishes by type: " + dishesByType);

        Map<Dish.CaloricLevel, List<Dish>> dishesByCaloricLevel =
                menus.stream()
                        .collect(Collectors.groupingBy(
                                dish -> {
                                    if (dish.getCalories() <= 400) {
                                        return Dish.CaloricLevel.DIET;
                                    } else if (dish.getCalories() <= 700) {
                                        return Dish.CaloricLevel.NORMAL;
                                    } else {
                                        return Dish.CaloricLevel.FAT;
                                    }
                                }
                        ));
        System.out.println("Dishes by caloric level: " + dishesByCaloricLevel);

        // Multilevel grouping
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menus.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.groupingBy(
                                        dish -> {
                                            if (dish.getCalories() <= 400) {
                                                return Dish.CaloricLevel.DIET;
                                            } else if (dish.getCalories() <= 700) {
                                                return Dish.CaloricLevel.NORMAL;
                                            } else {
                                                return Dish.CaloricLevel.FAT;
                                            }
                                        }
                                )));
        System.out.println("Dishes by type and caloric level: " + dishesByTypeCaloricLevel);

        // Collecting data in subgroups
        Map<Dish.Type, Long> typesCount = menus.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println("Number of dishes by types: " + typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByTypeOptional = menus.stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("Highest caloric dish by type: " + mostCaloricByTypeOptional);

        // Adapting the collector result to a different type
        Map<Dish.Type, Dish> mostCaloricByType = menus.stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get
                                )
                        )
                );
        System.out.println("Highest caloric dish by type: " + mostCaloricByType);

        Map<Dish.Type, Set<Dish.CaloricLevel>> caloricLevelsByType = menus.stream()
                .collect(
                        Collectors.groupingBy(
                                Dish::getType,
                                Collectors.mapping(
                                        dish -> {
                                            if (dish.getCalories() <= 400) {
                                                return Dish.CaloricLevel.DIET;
                                            } else if (dish.getCalories() <= 700) {
                                                return Dish.CaloricLevel.NORMAL;
                                            } else {
                                                return Dish.CaloricLevel.FAT;
                                            }
                                        },
                                        Collectors.toSet()
                                )
                        ));
        System.out.println("Caloric levels by type: " + caloricLevelsByType);
    }

    public static void collectors(List<Dish> menus) {
        //Reducing and summarizing
        long howManyDishes = menus.stream().count();
        System.out.println("Number of dishes: " + howManyDishes);

        // Finding maximum and minimum in a stream of values
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menus.stream()
                .collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println("Highest calorie dish: " + mostCalorieDish.orElse(null));

        System.out.println("Lowest calorie dish: " + menus.stream().min(Comparator.comparingInt(Dish::getCalories)).orElse(null));

        // Summarization
        int totalCalories = menus.stream()
                .collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("Total calories: " + totalCalories);

        double averageCalories = menus.stream()
                .collect(Collectors.averagingDouble(Dish::getCalories));
        System.out.println("Average calories: " + averageCalories);

        IntSummaryStatistics menuStatistics = menus.stream()
                .collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("Menu statistics: " + menuStatistics);
        System.out.println("Highest calorie: " + menuStatistics.getMax());

        // Joining Strings
        String shortMenu = menus.stream()
                .map(Dish::getName)
                .collect(Collectors.joining());
        System.out.println("Short menu: " + shortMenu);

        shortMenu = menus.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Short menu: " + shortMenu);

        // Generalized summarization with reduction
        totalCalories = menus.stream()
                .collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println("Total calories: " + totalCalories);

        Optional<Dish> lowestCalorieDish = menus.stream()
                .collect(Collectors.reducing((d1, d2) -> d1.getCalories() < d2.getCalories() ? d1 : d2));
        System.out.println("Lowest calorie dish: " + lowestCalorieDish.orElse(null));
    }
}
