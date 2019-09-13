package designPattern.behavioral.strategy;

public class MyArrayList<T> implements MyList<T> {
    private final int DEFAULT_SIZE = 10;

    private T[] array;
    private int size;

    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public Boolean add(T e) {
        ++this.size;

        // rearrange once the array is full!
        if (size > array.length) {
            T[] temp = (T[]) new Object[array.length + DEFAULT_SIZE];
            System.arraycopy(array, 0, temp, 0, array.length);
            array = temp;
        }

        array[size -1] = e;

        return true;
    }

    @Override
    public T get(int i) {
        if (i > size - 1) {
            throw new ArrayIndexOutOfBoundsException("out of index");
        }

        return array[i];
    }

    @Override
    public Boolean remove(int i) {
        if (i > size - 1) {
            throw new ArrayIndexOutOfBoundsException("out of index");
        }

        --this.size;
        System.arraycopy(array,i + 1, array, i,size - i);
        array[size] = null;

        return true;
    }

    @Override
    public int size() {
        return this.size;
    }
}
