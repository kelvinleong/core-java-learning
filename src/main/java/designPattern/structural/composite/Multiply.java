package designPattern.structural.composite;

public class Multiply extends BinaryExpression {

    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return left.calculate() * right.calculate();
    }
}
