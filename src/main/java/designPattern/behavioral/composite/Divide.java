package designPattern.behavioral.composite;

public class Divide extends BinaryExpression {

    final double THRESHOLD = .0001;

    public Divide(Expression left, Expression right) {
        super(left, right);

        if (Math.abs(right.calculate() - 0.0) < THRESHOLD) {
            throw new IllegalArgumentException("Divider is equal to 0.");
        }
    }

    @Override
    public Double calculate() {
        return left.calculate() / right.calculate();
    }
}
