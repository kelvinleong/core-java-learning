package algorithms;

import java.util.ArrayList;
import java.util.List;

public class KmpPattern {
    public List<Integer> kmpSearch(String s, String p) {
        List<Integer> indexes = new ArrayList<>();

        int[] lps = computeLps(p);
        int i = 0;
        int j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                ++i;
                ++j;
            }

            if (j == p.length()) {
                indexes.add(i - j);
                j = lps[j - 1];
            } else if (i < s.length() && s.charAt(i) != p.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    ++i;
                }
            }
        }

        return indexes;
    }

    private int[] computeLps(String pattern) {
        int len = 0;
        int i = 1;

        int[] lps = new int[pattern.length()];
        lps[0] = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i] = len;
                ++i;
                ++len;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    ++i;
                }
            }
        }

        return lps;
    }
}
