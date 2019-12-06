package leetcodeChallenge;

import java.util.Stack;
import java.util.function.BiFunction;

public class DecodeString {
    public String decode(String exp) {
        Stack<String> values = new Stack<>();
        Stack<String> ops = new Stack<>();

        BiFunction<String, String, String> reverse = (s1, s2) -> s2 + s1;
        BiFunction<String, String, String> repeat = (s1, s2) -> s1.repeat(Integer.valueOf(s2));
        for (int i = 0; i < exp.length(); ++i) {
            StringBuilder sb = new StringBuilder();
            if (Character.isLetter(exp.charAt(i))) {
                while (i < exp.length() && Character.isLetter(exp.charAt(i))) {
                    sb.append(exp.charAt(i++));
                }
                --i;
                values.push(sb.toString());
                sb.setLength(0);
                ops.push("+");
            } else if (exp.charAt(i) == '[') {
                ops.push("[");
            } else if (exp.charAt(i) == ']') {
                ops.pop();
                while (!ops.peek().equals("[")) {
                    ops.pop();
                    values.push(reverse.apply(values.pop(), values.pop()));
                }
                values.push(repeat.apply(values.pop(), values.pop()));
                ops.pop();
                ops.push("+");
            } else {
                while (Character.isDigit(exp.charAt(i))) {
                    sb.append(exp.charAt(i++));
                }
                --i;
                values.push(sb.toString());
                sb.setLength(0);
            }
        }

        while (values.size() > 1) {
            values.push(reverse.apply(values.pop(), values.pop()));
        }

        return values.pop();
    }
}
