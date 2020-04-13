package reactive;

import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleCounterSubscriber implements Subscriber<String> {
    private Subscription subscription;
    private AtomicBoolean completed = new AtomicBoolean(false);
    private int count;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    public Boolean isCompleted() {
        return completed.get();
    }

    public void setCompleted(Boolean completed) {
        this.completed.set(completed);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void onNext(String item) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ++count;
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        setCompleted(true);
        subscription.cancel();
        System.out.println("Done");
    }
}
