package designPattern.behavioral.strategy;

import java.math.BigDecimal;

public interface Disconter {
    BigDecimal applyDiscount(BigDecimal amount);
}
