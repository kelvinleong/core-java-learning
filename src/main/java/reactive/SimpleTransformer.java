package reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class SimpleTransformer<T, R extends Number & Comparable> extends SubmissionPublisher<R> implements Flow.Processor<T, R> {
    private Flow.Subscription subscription;
    private Function<T, R> function;

    public SimpleTransformer(Function<T, R> function) {
        this.function = function;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit(function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }
}
