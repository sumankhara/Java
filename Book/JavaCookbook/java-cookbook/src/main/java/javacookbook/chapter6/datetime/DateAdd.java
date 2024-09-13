package javacookbook.chapter6.datetime;

import java.time.LocalDate;
import java.time.Period;

public class DateAdd {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();

        Period period = Period.ofDays(700);

        LocalDate then = now.plus(period);

        System.out.printf("700 days from %s is %s\n", now, then);

        then = now.plus(Period.ofMonths(55));

        System.out.printf("55 months from %s is %s\n", now, then);
    }
}
