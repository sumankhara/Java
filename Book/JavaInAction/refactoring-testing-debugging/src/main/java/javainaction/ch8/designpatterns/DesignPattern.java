package javainaction.ch8.designpatterns;

import javainaction.ch8.designpatterns.chainofresponsibility.HeaderTextProcessing;
import javainaction.ch8.designpatterns.chainofresponsibility.ProcessingObject;
import javainaction.ch8.designpatterns.chainofresponsibility.SpellCheckerProcessing;
import javainaction.ch8.designpatterns.factory.Product;
import javainaction.ch8.designpatterns.factory.ProductFactory;
import javainaction.ch8.designpatterns.observer.Feed;
import javainaction.ch8.designpatterns.observer.Guardian;
import javainaction.ch8.designpatterns.observer.LeMonde;
import javainaction.ch8.designpatterns.observer.NYTimes;
import javainaction.ch8.designpatterns.strategy.IsAllLowerCase;
import javainaction.ch8.designpatterns.strategy.IsNumeric;
import javainaction.ch8.designpatterns.strategy.Validator;
import javainaction.ch8.designpatterns.template.Customer;
import javainaction.ch8.designpatterns.template.OnlineBanking;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class DesignPattern {
    public static void main(String[] args) {
        // Strategy pattern
        Validator numericValidator = new Validator(new IsNumeric());
        boolean isNumeric = numericValidator.validate("aaaaa");
        System.out.println(isNumeric);

        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        boolean isAllLowerCase = lowerCaseValidator.validate("bbbbb");
        System.out.println(isAllLowerCase);

        numericValidator = new Validator((String s) -> s.matches("\\d+"));
        lowerCaseValidator = new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(numericValidator.validate("1234"));
        System.out.println(lowerCaseValidator.validate("abc23"));

        // Template Method pattern
        new OnlineBanking().processCustomer(1, (Customer c) -> System.out.println("Hello " + c.getName() + ", you have got $5000 bonus."));

        // Observer pattern
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObservers("The queen said her favourite book is Java 8 in Action!");

        feed.registerObserver((String tweet) -> {
            if(tweet != null && tweet.contains("stock")) {
                System.out.println("News from Dalal Street! " + tweet);
            }
        });
        feed.notifyObservers("Indian stock market crashes");

        // Chain of responsibility pattern
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("Aren't labdas really fun!!");
        System.out.println(result);

        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");

        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);

        result = pipeline.apply("Aren't labdas really fun!!");
        System.out.println(result);

        // Factory pattern
        Product p = ProductFactory.createProduct("stock");

        p = ProductFactory.createProductFromMap("fd");
    }
}
