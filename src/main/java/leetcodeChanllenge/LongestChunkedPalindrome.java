package leetcodeChanllenge;

public class LongestChunkedPalindrome {
    public int count(String s) {
        int length = s.length();

        String l = "";
        String r = "";
        int count = 0;
        for(int i = 0; i < length; ++i) {
            char current = s.charAt(i);
            char symetric = s.charAt(length - i - 1);
            l = current + l;
            r = r + symetric;
            if (l.equals(r)) {
                l = "";
                r = "";
                ++count;
            }
        }

        return count;
    }
}
