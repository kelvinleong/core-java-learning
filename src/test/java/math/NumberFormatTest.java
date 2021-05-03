package math;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberFormatTest {
    @Test
    public void should_roundup() {
        Locale locale = Locale.ENGLISH;
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        ((DecimalFormat) numberFormat).applyPattern("###.####");
        var r1 = numberFormat.format(3.0086);
        assertEquals("3.0086", r1);

        ((DecimalFormat) numberFormat).applyPattern("###.####");
        var r2 = numberFormat.format(3.001);
        assertEquals("3.001", r2);

        ((DecimalFormat) numberFormat).applyPattern("###.0000");
        var r3 = numberFormat.format(3.001);
        assertEquals("3.0010", r3);
    }
}
