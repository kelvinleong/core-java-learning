package math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DoubleCalculation {
    @Test
    public void showCalculationResult() {
        // exact calculation: 131.7 * 0.95 = 125.115
        // double result: 125.11499999999998 (floating representation)
        double d1 = 131.7;
        double d2 = 0.95;

        // DECIMAL64 is to increase the precision, without it the result can be poor
        var r1 = new BigDecimal(d1, MathContext.DECIMAL64).multiply(new BigDecimal(d2, MathContext.DECIMAL64));
        var r2 = new BigDecimal("131.7").multiply(new BigDecimal("0.95"));

        Locale locale = Locale.ENGLISH;
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        ((DecimalFormat) numberFormat).applyPattern("###.##");
        System.out.println("result1 = " + r1.setScale(2, RoundingMode.HALF_UP));
        System.out.println("result2 = " + r2.setScale(2, RoundingMode.HALF_UP));
    }
}
