package algorithms;

import java.util.Map;
import java.util.TreeMap;

public class MergeStrings {
    public MergeStrings() {}

    public String merge(String[] words) {
        Map<String, Integer> occurrence = new TreeMap<>();

        for (String s: words) {
            for (int i = 0; i < s.length(); i++) {
                var c = Character.toString(s.charAt(i));
                occurrence.compute(c, (k, v) -> (v == null) ? 1 : v + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        occurrence.forEach((key, value) -> sb.append(String.valueOf(key).repeat(Math.max(0, value))));

        return sb.toString();
    }
}
