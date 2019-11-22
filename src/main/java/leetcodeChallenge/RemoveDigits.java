package leetcodeChallenge;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class RemoveDigits {
    public String solution0(String num, int k) {
        if (num.length() == k) return "0";
        Stack<String> st = new Stack<>();
        List<String> s = num.codePoints().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());

        StringBuilder n = new StringBuilder();
        int remove = 0;
        for(int i = 0; i < s.size(); ++i) {
            if (remove >= k) {
                st.push(s.get(i));
                continue;
            }

            if (i == s.size() - 1 || (i < s.size() -1 && s.get(i).compareTo(s.get(i + 1)) <= 0) ||
                    (!st.empty() && s.get(i).compareTo(st.peek()) <= 0)) {
                if (!st.empty()) {
                    int count = 0;
                    String head = st.peek();
                    while (head != null && remove < k && num.length() - k - st.size() < num.length() - i) {
                        if (head.compareTo(s.get(i)) > 0) {
                            st.pop();
                            head = st.empty() ? null : st.peek();
                            ++remove;
                            ++count;
                        } else {
                            break;
                        }
                    }

                    if (st.size() < num.length() - k) {
                        st.push(s.get(i));
                    }
                } else {
                    if (st.size() < num.length() - k) st.push(s.get(i));
                }
            } else {
                ++remove;
            }
        }

        while (st.size() > 0) {
            n.append(st.pop());
        }

        String r = n.reverse().toString().replaceFirst ("^0*", "");
        return r.isEmpty() ? "0" : r ;
    }

    public String solution(String num, int k) {
        if (num.length() == k) return "0";
        Stack<String> st = new Stack<>();

        int i = 0;
        while (i < num.length()) {
            while (k > 0 && !st.isEmpty() && st.peek().compareTo(String.valueOf(num.charAt(i))) > 0) {
                st.pop();
                k--;
            }
            st.push(String.valueOf(num.charAt(i)));
            ++i;
        }

        while (k > 0) {
            st.pop();
            --k;
        }

        StringBuilder n = new StringBuilder();
        while(st.size() > 0) {
            n.append(st.pop());
        }

        String r = n.reverse().toString().replaceFirst ("^0*", "");
        return r.isEmpty() ? "0" : r ;
    }
}
