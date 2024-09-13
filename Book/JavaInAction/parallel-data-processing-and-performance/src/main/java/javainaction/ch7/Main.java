package javainaction.ch7;

import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStreams::sequentialSum, 1_000_000) + " msecs");

        System.out.println("Iterative sum done in: " + measureSumPerf(ParallelStreams::iterativeSum, 1_000_000) + " msecs");

        System.out.println("Parallel sum done in: " + measureSumPerf(ParallelStreams::parallelSum, 1_000_000) + " msecs");

        System.out.println("ForkJoin sum done in: " + measureSumPerf(ParallelStreams::forkJoinSum, 1_000_000) + " msecs");
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for(int i =0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if(duration < fastest) {
                fastest = duration;
            }
        }
        return fastest;
    }
}
