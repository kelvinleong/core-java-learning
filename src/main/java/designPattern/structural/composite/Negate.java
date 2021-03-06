package designPattern.structural.composite;

public class Negate extends UnaryExpression {
    public Negate(Double value) {
        super(value);
    }

    @Override
    public Double calculate() {
        return -value;
    }
}
