package javainaction.ch5;

import javainaction.ch5.model.Trader;
import javainaction.ch5.model.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Practice {
    public static void practiceStreamsAPI() {
        // 1. Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> transactionsIn2011 = transactions().stream()
                .filter(trx -> trx.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println("Transactions in 2011: " + transactionsIn2011);

        // 2. What are all the unique cities where the traders work?
        List<String> uniqueCities = transactions().stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique cities: " + uniqueCities);

        // Alternative approach
        Set<String> cities = transactions().stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println("Unique cities: " + cities);

        // 3. Find all traders from Cambridge and sort them by name.
        List<Trader> tradersFromCambridge = transactions().stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equalsIgnoreCase("cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println("Traders from Cambridge: " + tradersFromCambridge);

        // 4. Return a string of all traders’ names sorted alphabetically.
        String traderNames = transactions().stream()
                .map(trx -> trx.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .reduce("", String::concat);
        System.out.println("String of all trader's names: " + traderNames);

        // Alternative method
        traderNames = transactions().stream()
                .map(trx -> trx.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println("String of all trader's names: " + traderNames);

        // 5. Are any traders based in Milan?
        boolean isTraderInMilan = transactions().stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println("Are there any traders based in Milan: " + (isTraderInMilan ? "yes" : "no"));

        // 6. Print all transactions’ values from the traders living in Cambridge.
        transactions().stream()
                .filter(trx -> trx.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // Print total transaction values from the traders living in Cambridge.
        int total = transactions().stream()
                .filter(trx -> trx.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
        System.out.println("Total transaction values from the traders living in Cambridge: " + total);

        // 7. What’s the highest value of all the transactions?
        int highestTransValue = transactions().stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .orElseThrow(() -> new RuntimeException("No maximum value"));
        System.out.println("Highest valus of all transactions: " + highestTransValue);

        // 8. Find the transaction with the smallest value.
        Transaction smallestValueTransaction = transactions().stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElseThrow(() -> new RuntimeException("No smallest value transaction"));
        System.out.println("Transaction with the smallest value: " + smallestValueTransaction);
    }

    public static List<Transaction> transactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        return transactions;
    }
}
