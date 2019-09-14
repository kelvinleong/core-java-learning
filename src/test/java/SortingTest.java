import algorithms.HeapSort;
import algorithms.MergeSort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingTest {
    public <T extends Number & Comparable> void ascSortingCheck(T[] list) {
        var prev = list[0];
        for(int i = 1; i < list.length; i++) {
            assertTrue(prev.compareTo(list[i]) <= 0);
            prev = list[i];
        }
    }

    @Test
    public void shouldHeapSorted() {
        HeapSort<Double> m = new HeapSort<>(new Double[]{1.2, 8.8, 9.9, 9.2, 1.2});
        m.sort();
        ascSortingCheck(m.getSorted());
    }

    @Test
    public void shouldMergeSorted() {
        MergeSort<Double> m = new MergeSort<>(new Double[]{1.2, 8.8, 9.9, 9.2, 1.2});
        m.mergeSort();
        ascSortingCheck(m.getSorted());
    }
}
