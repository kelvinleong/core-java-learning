package designPattern.behavioral.composite;

public abstract class UnaryExpression implements Expression {
    public Double value;

    public UnaryExpression(Double value) {
        this.value = value;
    }
}
