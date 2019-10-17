package tdd;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RomanToDecimal {
    private Map<String, Integer> symbolValues;

    public RomanToDecimal() {
        symbolValues = Map.of("I", 1,
                            "V", 5,
                            "X", 10,
                            "L", 50,
                            "C", 100,
                            "D", 500,
                            "M", 1000);
    }

    private void checkIfLegalSymbols(String exp) {
        String validSymbols = "[IVXLCDM\\s]+";
        if (!exp.matches(validSymbols)) {
            throw new IllegalArgumentException(exp + " contains invalid roman characters");
        }

        List<Integer> list = exp.codePoints().mapToObj(c -> String.valueOf((char) c))
                .map(s -> symbolValues.get(s))
                .collect(Collectors.toList());

        Integer currentMax = list.get(0);
        for(int i = 1; i < list.size(); ++i) {
            if (list.get(i) > currentMax) {
                throw new IllegalArgumentException(exp + " invalid orders");
            }

            if (list.get(i) > list.get(i -1)) {
                currentMax = list.get(i);
            }
        }
    }

    public BigDecimal getDecimalFromRomanSymbols(String exp) {
        checkIfLegalSymbols(exp);
        List<String> expSymbols = exp.codePoints().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
        Integer sum = 0;
        for (int i = 0; i < expSymbols.size(); ++i) {
           if (i + 1 < expSymbols.size() &&
                   symbolValues.get(expSymbols.get(i)) < symbolValues.get(expSymbols.get(i + 1))) {
               sum -= symbolValues.get(expSymbols.get(i));
           } else {
               sum += symbolValues.get(expSymbols.get(i));
           }
        }

        return new BigDecimal(sum);
    }
}
