package algorithms;

public abstract class Sort<T extends Number & Comparable<T>> {
    private T[] list;

    public Sort(T[] list) {
        this.list = list;
    }

    public void showResult() {
        for(T t: list) {
            System.out.print(t + "  ");
        }
    }
}
