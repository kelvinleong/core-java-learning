package algorithms;

public class HeapSort<T extends Number & Comparable<T>> extends Sort<T> {
    private T[] list;

    public HeapSort(T[] list) {
        super(list);
        this.list = list;
    }

    private void swap(int a, int b) {
        T temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }

    private void buildMaxHeap() {
        for (int i = list.length / 2; i >= 0; --i) {
            maxHeapify(i, list.length);
        }
    }

    private void maxHeapify(int i, int l) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        int largest = i;
        if (left < l && list[left].compareTo(list[largest]) > 0) largest = left;

        if (right < l && list[right].compareTo(list[largest]) > 0) largest = right;

        if (largest != i) {
            swap(i, largest);
            maxHeapify(largest, l);
        }
    }

    public void sort() {
        buildMaxHeap();

        for (int i = list.length - 1; i >= 0; --i) {
            swap(0, i);
            maxHeapify(0, i);
        }
    }
}
