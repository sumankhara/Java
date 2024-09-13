package javacookbook.chapter6.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Exercise {
    public static void main(String[] args) {
        String dob = "28/10/1979";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate internalDate = LocalDate.parse(dob, inputFormatter);
        System.out.println("Internal representation of DOB: " + internalDate);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
        System.out.println("Displayed DOB: " + outputFormatter.format(internalDate));

        //Calculate age
        LocalDate now = LocalDate.now();

        Period age = Period.between(internalDate, now);

        System.out.printf("You are %d years %d months and %d days old\n", age.getYears(), age.getMonths(), age.getDays());

        //Upcoming events
        String commencement = "27/11/2011";
        String expiration = "17/11/2032";
        LocalDate commencementDate = LocalDate.parse(commencement, inputFormatter);
        LocalDate expirationDate = LocalDate.parse(expiration, inputFormatter);

        int commencementMonth = commencementDate.getMonthValue();
        int commencementDay = commencementDate.getDayOfMonth();
        int currentYear = now.getYear();

        LocalDate probablePolicyDate = LocalDate.of(currentYear, commencementMonth, commencementDay);
        System.out.println("probable policy date: " + probablePolicyDate);

        Period add = Period.ofDays(31);
        LocalDate withinThirtyDays = now.plus(add);

        System.out.println("withinThirtyDays: " + withinThirtyDays);

        if(probablePolicyDate.isAfter(now) && probablePolicyDate.isBefore(withinThirtyDays)) {
            System.out.println("you have an upcoming policy renewal");
        }
    }
}
