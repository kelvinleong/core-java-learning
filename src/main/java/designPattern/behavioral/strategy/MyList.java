package designPattern.behavioral.strategy;

public interface MyList<T extends Object> {
    Boolean add(T e);

    T get(int i);

    Boolean remove(int i);

    int size();
}
