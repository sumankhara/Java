package mjr.ch1.recipes;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

//Lambda Expression
public class Recipe1 {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                System.out.println("Inside runnable using an anonymous inner class");
            }
        }).start();

        new Thread(() -> System.out.println("Inside Thread constructor using lambda")).start();

        Runnable r = () -> System.out.println("Lambda expression implementing the run method");
        new Thread(r).start();

        // An anonymous inner class implementation of FilenameFilter
        File directory = new File("/Users/sumankhara/Documents/PersonalProjects/Java/Files");

        String[] names = directory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".java");
            }
        });
        System.out.println(Arrays.asList(names));

        //Lambda expression implementing FilenameFilter
        names = directory.list(((dir, name) -> name.endsWith("java")));

        System.out.println(Arrays.asList(names));

        //A block lambda
        names = directory.list((File dir, String name) -> {
            return name.endsWith("java");
        });

        System.out.println(Arrays.asList(names));
    }
}
