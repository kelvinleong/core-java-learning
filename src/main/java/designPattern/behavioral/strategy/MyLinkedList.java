package designPattern.behavioral.strategy;

public class MyLinkedList<T> implements MyList<T>{
    private Node<T> head;
    private Node<T> current;
    private int size;

    public MyLinkedList() {
        head = new Node<>();
        current = head;
        size = 0;
    }

    @Override
    public Boolean add(T e) {
        ++this.size;
        Node<T> node = new Node<>();
        node.setValue(e);
        current.setNext(node);
        node.setPrev(current);
        current = current.getNext();
        return true;
    }

    @Override
    public T get(int i) {
        if (i > size - 1) {
            throw new IndexOutOfBoundsException("out of index");
        }

        var temp = head.getNext();
        for (int n = 0; n < i; ++n) {
            temp = temp.getNext();
        }

        return temp.getValue();
    }

    @Override
    public Boolean remove(int i) {
        if (i > size - 1) {
            throw new IndexOutOfBoundsException("out of index");
        }

        --this.size;
        var temp = head.getNext();
        for (int n = 0; n < i; ++n) {
            temp = temp.getNext();
        }

        temp.getPrev().setNext(temp.getNext());

        if(temp.getNext() != null) temp.getNext().setPrev(temp.getPrev());

        temp.setPrev(null);
        temp.setNext(null);
        temp.setValue(null);

        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    private static class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T value;

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
