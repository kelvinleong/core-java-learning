package designPattern.creational;

public class Tuple <T, R>{
    private T t;
    private R r;

    public Tuple(T t, R r) {
       this.t = t;
       this.r = r;
    }

    public T getFirst() { return t; }

    public void setFirst(T t) { this.t = t; }

    public R getSecond() { return r; }

    public void setSecond(R r) { this.r = r; }

    public static class Builder<T, R> {
        private T t;
        private R r;

        private Builder() {}

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder first(T t) {
            this.t = t;
            return this;
        }

        public Builder second(R r) {
            this.r = r;
            return this;
        }

        public Tuple build() {
            return new Tuple(this.t, this.r);
        }
    }
}
