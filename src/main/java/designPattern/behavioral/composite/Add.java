package designPattern.behavioral.composite;

public class Add extends BinaryExpression {

    public Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double calculate() {
        return left.calculate() + right.calculate();
    }
}
