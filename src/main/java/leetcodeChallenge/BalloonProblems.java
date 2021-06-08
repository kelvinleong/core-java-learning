package leetcodeChallenge;

import java.util.HashMap;

public class BalloonProblems {
    public int howManyBalloons(String text) {
        var map = new HashMap<String, Integer>();
        map.put("b", 0);
        map.put("a", 0);
        map.put("l", 0);
        map.put("o", 0);

        for (int i = 0; i < text.length(); ++i) {
            map.computeIfPresent(String.valueOf(text.charAt(i)), (key, value) -> value + 1);
        }

        map.computeIfPresent("l", (key, value) -> value /2);
        map.computeIfPresent("o", (key, value) -> value /2);

        return map.values().stream().min(Integer::compareTo).orElse(0);
    }
}
