package reactive;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class SimpleCounterSubscriberTest {
    private SubmissionPublisher<String> publisher;
    private SimpleCounterSubscriber subscriber;

    @Before
    public void init() {
        this.publisher = new SubmissionPublisher<>();
        this.subscriber = new SimpleCounterSubscriber();
        this.subscriber.setCompleted(false);
        publisher.subscribe(subscriber);
    }

    @Test
    public void should_publish10Items() throws InterruptedException {
        IntStream.range(0, 10)
                .forEach(s -> publisher.submit(String.valueOf(s)));
        publisher.close();

        while (!subscriber.isCompleted()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }

        assertEquals(10, subscriber.getCount());
    }
}
