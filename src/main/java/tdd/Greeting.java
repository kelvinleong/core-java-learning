package tdd;

import java.util.Arrays;
import java.util.stream.Collectors;


public class Greeting {
    public String greet(String... names) {
        String format = "Hello, %s.";

        if (names == null) {
            return String.format(format, "my friend");
        } else {
            String upperCasePattern =  "[A-Z\\s]+";

            if (names.length < 2) {
                if (names[0].matches(upperCasePattern)) {
                    format = "HELLO %s!";
                }
                return String.format(format, names[0]);
            } else {
                var lowercaseOrWithSpecialCharNames = Arrays.stream(names)
                        .map(s -> {
                            if (s.contains("\"")) {
                                return new String[]{s.replace("\"", "")};
                            } else {
                                return s.split(", ");
                            }
                        })
                        .flatMap(Arrays::stream)
                        .filter(s -> !s.matches(upperCasePattern))
                        .collect(Collectors.toList());

                String lastElement = lowercaseOrWithSpecialCharNames.get(lowercaseOrWithSpecialCharNames.size() - 1);
                String lowercaseOrWithSpecialCharNamesStr = lowercaseOrWithSpecialCharNames
                        .subList(0, lowercaseOrWithSpecialCharNames.size() - 1)
                        .stream()
                        .reduce((s1, s2) -> s1 + ", " + s2)
                        .map(s -> {
                            if (lowercaseOrWithSpecialCharNames.size() > 2) {
                                return s.concat(", and ").concat(lastElement);
                            } else {
                                return s.concat(" and ").concat(lastElement);
                            }
                        })
                        .orElse("");

                String uppercaseNamesStr = Arrays.stream(names)
                        .filter(s -> s.matches(upperCasePattern))
                        .reduce((s1, s2) -> s1 + s2)
                        .map(s -> " AND HELLO " + s + "!")
                        .orElse("");

                return String.format(format, lowercaseOrWithSpecialCharNamesStr) + uppercaseNamesStr;
            }
        }
    }
}
