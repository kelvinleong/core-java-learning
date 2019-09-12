package designPattern.structural.composite;

public class Subtract extends BinaryExpression {

    public Subtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return left.calculate() - right.calculate();
    }
}
