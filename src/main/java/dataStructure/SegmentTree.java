package dataStructure;

/***
 *   find the min value given an arrange in the array
 */
public class SegmentTree<T extends Number & Comparable<T>> {
    private final T[] array;
    private T[] segmentTree;

    public SegmentTree(final T[] array) {
        this.array = array;
        initSegmentTree();
    }

    private void initSegmentTree() {
        int length = array.length;
        Double height = Math.ceil(Math.log(length) / Math.log(2));
        Double treeSize = 2 * Math.pow(2, height);
        segmentTree = (T[]) new Number[treeSize.intValue()];
    }

    private T min(T a, T b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    private T constructSegmentTree(int current, int start, int end) {
        if (start == end) {
            segmentTree[current] = array[start];
            return segmentTree[current];
        }

        int mid = (start + end) / 2;
        int leftChild = 2 * current + 1;
        int rightChild = 2 * current + 2;

        segmentTree[current] = min(constructSegmentTree(leftChild, start, mid),
                constructSegmentTree(rightChild, mid + 1, end));

        return segmentTree[current];
    }

    public void recursiveBuildSegmentTree() {
        constructSegmentTree(0, 0, array.length - 1);
    }

    private T recursiveQuery(int current, int start, int end, int qStart, int qEnd) {
        if (qStart > end || qEnd < start) return (T) Integer.valueOf(Integer.MAX_VALUE);

        if (qStart <= start && qEnd >= end) return segmentTree[current];

        int mid = (start + end) / 2;
        int leftChild = 2 * current + 1;
        int rightChild = 2 * current + 2;

        return min(recursiveQuery(leftChild, start, mid, qStart, qEnd), recursiveQuery(rightChild, mid + 1, end, qStart, qEnd));
    }

    public T recursiveQuery(int start, int end) {
        return recursiveQuery(0, 0, array.length - 1, start, end);
    }

    private T recursiveUpdate(int current, int start, int end, int i, T value) {
        if (start == end && i == start) {
            array[i] = value;
            segmentTree[current] = value;
            return value;
        }

        int mid = (start + end) / 2;
        int leftChild = 2 * current + 1;
        int rightChild = 2 * current + 2;

        if (i <= mid) {
            recursiveUpdate(leftChild, start, mid, i, value);
        } else {
            recursiveUpdate(rightChild, mid + 1, end, i, value);
        }

        segmentTree[current] = min(segmentTree[leftChild], segmentTree[rightChild]);

        return segmentTree[current];
    }

    public void recursiveUpdate(int i, T value) {
        recursiveUpdate(0, 0, array.length - 1, i, value);
    }

    // a more intuitive way to construct the tree
    public void iterativeBuildTree() {
        // leaf nodes
        for (int i = array.length, j = 0; j < array.length; ++i, ++j) {
            segmentTree[i] = array[j];
        }

        // construct parent
        for (int i = array.length - 1; i > 0; --i) {
            segmentTree[i] = min(segmentTree[2 * i], segmentTree[2 * i + 1]);
        }
    }

    public void iterativeUpdate(int index, T value) {
        int pos = array.length + index;
        segmentTree[pos] = value;

        while (pos > 1) {
            int left = pos;
            int right = pos;

            if (pos % 2 ==0) {
                // the node at pos is a left child of its parent
                right = pos + 1;
            } else {
                // the node at pos is a right child of its parent
                left = pos - 1;
            }

            segmentTree[pos / 2] = min(segmentTree[left], segmentTree[right]);
            pos /= 2;
        }
    }

    public T iterativeQuery(int left, int right) {
        int l = array.length + left;
        int r = array.length + right;

        T val = segmentTree[l];
        while (l <= r) {
            if (l % 2 == 1) {
                /*
                 *  if the current node is a right child of its parent, meaning current node should be included
                 *  and then move to next element
                 */
                val = min(val, segmentTree[l]);
                ++l;
            }

            if (r % 2 == 0) {
                /*
                 *  if the current node is a left child of its parent, meaning current node should be included
                 *  and then move to previous element
                 */
                val = min(val, segmentTree[r]);
                --r;
            }

            l /= 2;
            r /= 2;
        }

        return val;
    }
}
