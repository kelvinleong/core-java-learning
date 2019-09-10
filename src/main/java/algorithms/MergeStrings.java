package algorithms;

import java.util.Map;
import java.util.TreeMap;

public class MergeStrings {
    public MergeStrings() {}

    public String merge(String[] words) {
        Map<String, Integer> occurences = new TreeMap<>();

        for(String s: words) {
            for(int i = 0; i < s.length(); i++) {
                var c = Character.toString(s.charAt(i));
                occurences.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        occurences.forEach((key, value) -> {
            for(int i = 0; i < value; i++) {
                sb.append(key);
            }
        });

        return sb.toString();
    }
}
