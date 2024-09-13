package javainaction.ch8.testing;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PointTest {

    @Test
    public void testMoveRightBy() throws Exception {
        Point p1 = new Point(5,5);
        Point p2 = p1.moveRightBy(10);

        assertEquals(15, p2.getX());
        assertEquals(5, p2.getY());
    }

    @Test
    public void testComparingTwoPoints() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(10, 20);

        int result = Point.compareByXAndThenY.compare(p1, p2);

        assertEquals(-1, result);
    }

    @Test
    public void testMoveAllPointsRightBy() {
        List<Point> points = List.of(new Point(5, 5), new Point(10, 5));
        List<Point> expectedPoints = List.of(new Point(15, 5), new Point(20, 5));

        List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);

        assertEquals(expectedPoints, newPoints);
    }
}
