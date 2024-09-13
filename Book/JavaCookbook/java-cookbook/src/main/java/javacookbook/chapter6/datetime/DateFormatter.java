package javacookbook.chapter6.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static void main(String[] args) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(df.format(LocalDateTime.now()));

        System.out.println(LocalDate.parse("1979/10/28", df));

        DateTimeFormatter nTZ = DateTimeFormatter.ofPattern("d MMMM, yyyy");
        System.out.println(ZonedDateTime.now().format(nTZ));

        System.out.println(nTZ.format(LocalDate.now()));
    }
}
