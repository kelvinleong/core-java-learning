import designPattern.structural.composite.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DesignPatternTest {
    @Test
    public void Should_CalculateCorrectResult_when_GivenArithmeticExpression() {
        Divide exp = new Divide(new Multiply(new Negate(5.0), new Multiply(new Constants(9.0), new Constants(6.0))),
                new Add(new Constants(7.0), new Subtract(new Constants(2.0), new Constants(1.5))));
        assertEquals(exp.calculate(), -36.0, 0.0);
    }
}
