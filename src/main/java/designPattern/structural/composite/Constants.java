package designPattern.structural.composite;

public class Constants extends UnaryExpression{
    public Constants(Double value) {
        super(value);
    }

    @Override
    public Double calculate() {
        return value;
    }
}
