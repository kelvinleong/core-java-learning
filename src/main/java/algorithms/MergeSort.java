package algorithms;

public class MergeSort<T extends Number & Comparable<T>> extends Sort<T> {
    private T[] list;

    public MergeSort(T[] list) {
        super(list);
        this.list = list;
    }

    //[l, m] & [m + 1, r]
    private void merge(T[] list, int l, int m, int r) {
        int l1 = m - l + 1;
        int l2 = r - m;

        T[] L = (T[]) new Number[l1];
        T[] R = (T[]) new Number[l2];

        for (int i = 0; i < l1; ++i) L[i] = list[l + i];
        for (int i = 0; i < l2; ++i) R[i] = list[m + 1 + i];

        int i = 0, j = 0, k = l;
        while (i < l1 && j < l2) {
            if (L[i].compareTo(R[j]) < 0) {
                list[k] = L[i];
                i++;
            } else {
                list[k] = R[j];
                j++;
            }
            ++k;
        }

        // if remaining in L[]
        while (i < l1) {
            list[k] = L[i];
            ++i;
            ++k;
        }

        // if remaining in R[]
        while (j < l2) {
            list[k] = R[j];
            ++j;
            ++k;
        }
    }

    private void sort(T[] list, int l, int r) {
        if (l < r) {
            int m =  (r + l) / 2;
            sort(list, l, m);
            sort(list, m + 1, r);
            merge(list, l, m, r);
        }
    }

    public void mergeSort() {
        sort(list, 0, list.length - 1);
    }
}
