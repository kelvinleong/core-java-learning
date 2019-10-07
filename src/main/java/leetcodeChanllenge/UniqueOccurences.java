package leetcodeChanllenge;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UniqueOccurences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occurences = new HashMap<>();

        for(int val: arr) {
            occurences.put(val, occurences.getOrDefault(val, 0) + 1);
        }

        return arr.length == new HashSet<>(occurences.values()).size();
    }
}
