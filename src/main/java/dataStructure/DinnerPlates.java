package dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DinnerPlates {
    private final int capacity;

    private List<List<Integer>> buckets = new ArrayList();

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
    }

    public void push(int val) {
        if (buckets.size() == 0) {
            createNewBucketAndInsert(val);
        } else {
            buckets.stream()
                    .filter(b -> b.size() < capacity)
                    .findFirst()
                    .map(b -> b.add(val))
                    .or(() -> {
                        createNewBucketAndInsert(val);
                        return Optional.of(true);
                    });
        }
    }

    public int pop() {
        if (buckets.size() == 0) {
            throw new RuntimeException("bucket is empty");
        }

        return popAtStack(buckets.size() - 1);
    }

    public int popAtStack(int index) {
        if (index > buckets.size() -1) {
            throw new IndexOutOfBoundsException("out of index");
        }

        int topValueIndex = buckets.get(index).size() - 1;
        int val = buckets.get(index).get(topValueIndex);

        buckets.get(index).remove(topValueIndex);
        if (buckets.get(index).size() == 0) {
            buckets.remove(buckets.get(index));
        }

        return val;
    }

    private void createNewBucketAndInsert(int val) {
        List<Integer> bucket = new ArrayList<>();
        bucket.add(val);
        buckets.add(bucket);
    }
}
