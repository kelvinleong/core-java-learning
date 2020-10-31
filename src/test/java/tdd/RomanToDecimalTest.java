package tdd;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanToDecimalTest {
    private static Map<String, BigDecimal> symbolValues;
    static {
        symbolValues = Map.of("I", new BigDecimal(1),
                                "V", new BigDecimal(5),
                                "X", new BigDecimal(10),
                                "L", new BigDecimal(50),
                                "C", new BigDecimal(100),
                                "D", new BigDecimal(500),
                                "M", new BigDecimal(1000));
    }

    @Test
    public void should_throwIllegalException_when_containingInvalidSymbols() {
        assertThrows(IllegalArgumentException.class, () -> {
            RomanToDecimal r2d = new RomanToDecimal();
            r2d.getDecimalFromRomanSymbols("ABMX");
        });
    }

    @Test
    public void should_returnDecimalValue_when_givenOnlyOneValidSymbol() {
        RomanToDecimal r2d = new RomanToDecimal();
        symbolValues.forEach((k, v) -> {
            BigDecimal result = r2d.getDecimalFromRomanSymbols(k);
            assertEquals(symbolValues.get(k), result);
        });
    }

    @Test
    public void should_calculateDecimalValue_when_givenAdditionOnlySymbolsSequences() {
        RomanToDecimal r2d = new RomanToDecimal();
        BigDecimal result = r2d.getDecimalFromRomanSymbols("MMVI");
        assertEquals(new BigDecimal("2006"), result);
    }

    @Test
    public void should_calculateDecimalValue_when_givenBothAdditionAndSubtractionSymbolsSequences() {
        RomanToDecimal r2d = new RomanToDecimal();
        BigDecimal result = r2d.getDecimalFromRomanSymbols("MCMXLIV");
        assertEquals(new BigDecimal("1944"), result);
    }

    @Test
    public void should_calculateDecimalValue_when_givenMultipleSameSymbol() {
        RomanToDecimal r2d = new RomanToDecimal();
        BigDecimal result = r2d.getDecimalFromRomanSymbols("IIII");
        assertEquals(new BigDecimal("4"), result);
    }

    @Test
    public void should_throwIllegalException_when_validSymbolsAreInCorrectOrders() {
        assertThrows(IllegalArgumentException.class, () -> {
            RomanToDecimal r2d = new RomanToDecimal();
            r2d.getDecimalFromRomanSymbols("CDM");
        });
    }
}
