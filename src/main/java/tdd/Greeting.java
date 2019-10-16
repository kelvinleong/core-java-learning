package tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Greeting {
    public String greet(String... names) {
        String format = "Hello, %s.";

        if (names == null) {
            return String.format(format, "my friend");
        } else {
            String pattern =  "[A-Z\\s]+";

            if (names.length < 2) {
                if (names[0].matches(pattern)) {
                    format = "HELLO %s!";
                }
                return String.format(format, names[0]);
            } else {
                List<String> temp = new ArrayList<>();
                for(int i = 0; i < names.length - 1; ++i) {
                    temp.add(names[i]);
                }

                String namesStr = String.join(", ", temp);
                namesStr = namesStr + " and " + names[names.length - 1];

                return String.format(format, namesStr);
            }
        }
    }
}
