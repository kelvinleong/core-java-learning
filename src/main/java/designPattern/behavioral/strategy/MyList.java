package designPattern.behavioral.strategy;

public interface MyList<T> {
    Boolean add(T e);

    T get(int i);

    Boolean remove(int i);

    int size();
}
