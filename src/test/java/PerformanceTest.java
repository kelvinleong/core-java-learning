import algorithms.KnapsackProblem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class PerformanceTest {
    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).limit(1).findFirst().getAsInt();

    }

    @Test
    public void knapsackProblemDpVsRecursive10() {
        int length = 10;
        int[] values = new int[length];
        int[] weights = new int[length];

        int w = 0;
        for (int i = 0; i < length; ++i) {
            values[i] = getRandomNumberInRange(1, 100);
            weights[i] = getRandomNumberInRange(10, 1000);
            w += weights[i];
        }

        w = getRandomNumberInRange(w / 2, w - 1);
        KnapsackProblem kp = new KnapsackProblem();

        // DP
        long start = System.currentTimeMillis();
        int max1 = kp.knapsackUsingDp(values, weights, w);
        long end = System.currentTimeMillis();
        System.out.println("Max Value Result: " + max1);
        System.out.println("Time elapsed for DP way: " + (end - start) + "ms");

        // Recursive
        start = System.currentTimeMillis();
        int max2 = kp.knapsack(values, weights, w, length - 1);
        end = System.currentTimeMillis();
        System.out.println("Max Value Result: " + max2);
        System.out.println("Time elapsed for Recursive way: " + (end - start) + "ms");

        Assertions.assertEquals(max1, max2);
    }

    @Test
    public void knapsackProblemDpVsRecursive20() {
        int length = 20;
        int[] values = new int[length];
        int[] weights = new int[length];

        int w = 0;
        for (int i = 0; i < length; ++i) {
            values[i] = getRandomNumberInRange(1, 1000);
            weights[i] = getRandomNumberInRange(10, 1000);
            w += weights[i];
        }

        w = getRandomNumberInRange(w / 2, w - 1);
        KnapsackProblem kp = new KnapsackProblem();

        // DP
        long start = System.currentTimeMillis();
        int max1 = kp.knapsackUsingDp(values, weights, w);
        long end = System.currentTimeMillis();
        System.out.println("Max Value Result: " + max1);
        System.out.println("Time elapsed for DP way: " + (end - start) + "ms");

        // Recursive
        start = System.currentTimeMillis();
        int max2 = kp.knapsack(values, weights, w, length - 1);
        end = System.currentTimeMillis();
        System.out.println("Max Value Result: " + max2);
        System.out.println("Time elapsed for Recursive way: " + (end - start) + "ms");

        Assertions.assertEquals(max1, max2);
    }

    /**
     *  Max Value Result: 19658
     *   Time elapsed for DP way: 4ms
     *   Max Value Result: 19658
     *   Time elapsed for Recursive way: 3349789ms (~55mins)
     */
    @Test
    public void knapsackProblemDpVsRecursive40() {
        int length = 40;
        int[] values = new int[length];
        int[] weights = new int[length];

        int w = 0;
        for (int i = 0; i < length; ++i) {
            values[i] = getRandomNumberInRange(1, 1000);
            weights[i] = getRandomNumberInRange(10, 100);
            w += weights[i];
        }

        w = getRandomNumberInRange(w / 2, w - 1);
        KnapsackProblem kp = new KnapsackProblem();

        // DP
        long start = System.currentTimeMillis();
        int max1 = kp.knapsackUsingDp(values, weights, w);
        long end = System.currentTimeMillis();
        System.out.println("Max Value Result: " + max1);
        System.out.println("Time elapsed for DP way: " + (end - start) + "ms");

        // Recursive
        start = System.currentTimeMillis();
        int max2 = kp.knapsack(values, weights, w, length - 1);
        end = System.currentTimeMillis();
        System.out.println("Max Value Result: " + max2);
        System.out.println("Time elapsed for Recursive way: " + (end - start) + "ms");

        Assertions.assertEquals(max1, max2);
    }
}
