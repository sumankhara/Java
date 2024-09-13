package mjr.ch3.recipes;

import java.util.DoubleSummaryStatistics;
import java.util.stream.DoubleStream;

//Summary statistics
public class Recipe8 {
    public static void main(String[] args) {
        DoubleSummaryStatistics stats = DoubleStream.generate(Math::random)
                .limit(100000)
                .summaryStatistics();

        System.out.println(stats);

        System.out.println("count: " + stats.getCount());
        System.out.println("min: " + stats.getMin());
        System.out.println("max: " + stats.getMax());
        System.out.println("sum: " + stats.getSum());
        System.out.println("average: " + stats.getAverage());
    }
}
