package javacookbook.chapter6.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConversions {
    public static void main(String[] args) {
        Instant epochSec = Instant.ofEpochSecond(1000000000L);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime then = ZonedDateTime.ofInstant(epochSec, zoneId);
        System.out.println("The epoch was a billion seconds old on " + then);

        long epochSecond = ZonedDateTime.now().toEpochSecond();
        System.out.println("Current epoch seconds = " + epochSecond);
        epochSecond = ZonedDateTime.now().toInstant().getEpochSecond();
        System.out.println("Current epoch seconds = " + epochSecond);

        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime there = now.atZone(ZoneId.of("Canada/Pacific"));
        System.out.printf("When it's %s here, it's %s in Vancouver\n", now, there);

        now = LocalDateTime.now(ZoneId.of("Canada/Pacific"));
        System.out.printf("When it's %s here, it's %s in Vancouver\n", now, there);
    }
}
