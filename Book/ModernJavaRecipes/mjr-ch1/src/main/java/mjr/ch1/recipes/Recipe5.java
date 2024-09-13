package mjr.ch1.recipes;

import java.util.Arrays;
import java.util.List;

//Default methods in interfaces
public class Recipe5 {
    public static void main(String[] args) {
        //Calling default methods
        System.out.println(new Employee() {
            @Override
            public String getFisrt() {
                return null;
            }

            @Override
            public String getLast() {
                return null;
            }
        }.getName());

        List<Integer> nums = Arrays.asList(27,12,5,26,28);
        boolean removed = nums.removeIf(n -> n <= 0);
        System.out.println("Elements were " + (removed ? "" : "NOT") + " removed");
        nums.forEach(System.out::println);
    }
}

interface Employee {
    String getFisrt();

    String getLast();

    default String getName() {
        return "Default FirstName Default LastName";
    }
}
