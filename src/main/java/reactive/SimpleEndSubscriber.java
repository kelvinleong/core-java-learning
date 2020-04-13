package reactive;

import java.util.*;
import java.util.concurrent.Flow;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleEndSubscriber<T extends Number & Comparable<T>> implements Flow.Subscriber<T> {
    private Flow.Subscription subscription;
    private List<T> list;
    private AtomicBoolean completed = new AtomicBoolean(false);

    SimpleEndSubscriber() {
        list = new ArrayList<>();
    }

    public List<T> getList() {
        return list;
    }

    public Boolean isCompleted() {
        return completed.get();
    }

    private void setCompleted(Boolean completed) {
        this.completed.set(completed);
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        list.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        setCompleted(true);
        list.sort(Comparator.reverseOrder());
        subscription.cancel();
    }
}
