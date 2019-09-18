package dataStructure;

public class SegmentTree<T extends Number & Comparable<T>> {
    private final T[] array;
    private T[] segmentTree;

    public SegmentTree(final T[] array) {
        this.array = array;
        initSegmentTree();
    }

    private void initSegmentTree() {
        int length = array.length;
        Double height = Math.ceil(Math.log((double) length) / Math.log(2));
        Double treeSize = 2 * Math.pow(2, height) - 1;
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

    public void buildSegmentTree() {
        constructSegmentTree(0, 0, array.length - 1);
    }

    private T query(int current, int start, int end, int qStart, int qEnd) {
        if (qStart > end || qEnd < start) return (T) Integer.valueOf(Integer.MAX_VALUE);

        if (qStart <= start && qEnd >= end) return segmentTree[current];

        int mid = (start + end) / 2;
        int leftChild = 2 * current + 1;
        int rightChild = 2 * current + 2;

        return min(query(leftChild, start, mid, qStart, qEnd), query(rightChild, mid + 1, end, qStart, qEnd));
    }

    public T query(int start, int end) {
        return query(0, 0, array.length - 1, start, end);
    }

    private T update(int current, int start, int end, int i, T value) {
        if (start == end && i == start) {
            array[i] = value;
            segmentTree[current] = value;
            return value;
        }

        int mid = (start + end) / 2;
        int leftChild = 2 * current + 1;
        int rightChild = 2 * current + 2;

        if (i <= mid) {
            update(leftChild, start, mid, i, value);
        } else {
            update(rightChild, mid + 1, end, i, value);
        }

        segmentTree[current] = min(segmentTree[leftChild], segmentTree[rightChild]);

        return segmentTree[current];
    }

    public void update(int i, T value) {
        update(0, 0, array.length - 1, i, value);
    }
}
