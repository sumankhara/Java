package javacookbook.chapter6.datetime;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TestableDateTime {
    private static Clock clock = Clock.systemDefaultZone();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("It is now: " + LocalDateTime.now(clock));
        Thread.sleep(1000);
        System.out.println("It is now: " + LocalDateTime.now(clock));
        Thread.sleep(1000);
        setClock(Clock.fixed(Instant.now(), ZoneId.systemDefault()));
        System.out.println("After setting clock it is now: " + LocalDateTime.now(clock));
        Thread.sleep(1000);
        System.out.println("After setting clock it is now: " + LocalDateTime.now(clock));
    }

    public static void setClock(Clock clock) {
        TestableDateTime.clock = clock;
    }
}
