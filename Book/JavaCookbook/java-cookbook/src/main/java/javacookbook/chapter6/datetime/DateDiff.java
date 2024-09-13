package javacookbook.chapter6.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class DateDiff {
    public static void main(String[] args) {
        LocalDate endOf20thCentury = LocalDate.of(2000, 12, 31);
        LocalDate now = LocalDate.now();
        if(now.getYear() > 2100) {
            System.out.println("The 21st century is over!");
            return;
        }

        Period diff = Period.between(endOf20thCentury, now);

        System.out.printf("The 21st century (upto %s) is %s old\n", now, diff);

        System.out.printf("The 21st century is %d years, %d months and %d days old\n", diff.getYears(), diff.getMonths(), diff.getDays());

        System.out.println(ChronoUnit.DAYS.between(LocalDate.parse("2015-10-05"), LocalDate.now()));

        System.out.println(ChronoUnit.YEARS.between(LocalDate.parse("2015-10-05"), LocalDate.now()));
    }
}
