package javacookbook.chapter6.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParse {
    public static void main(String[] args) {
        String armisticeDate = "1914-11-11";
        LocalDate localDate = LocalDate.parse(armisticeDate);
        System.out.println("Date: " + localDate);

        String armisticeDateTime = "1914-11-11T11:11";
        LocalDateTime localDateTime = LocalDateTime.parse(armisticeDateTime);
        System.out.println("Date/Time: " + localDateTime);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM uuuu");
        String anotherDate = "27 Jan 2011";
        LocalDate random = LocalDate.parse(anotherDate, df);
        System.out.println(anotherDate + " parses as " + random);

        System.out.println(armisticeDateTime + " formats as " + df.format(localDateTime));
    }
}
