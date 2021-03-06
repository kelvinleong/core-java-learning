package reactive;

import algorithm.SortingTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexStreamTest extends SortingTest {
    @Test
    public void should_GetReversedOrderList() throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
        SimpleTransformer<String, Integer> transformer = new SimpleTransformer<>(Integer::valueOf);
        SimpleEndSubscriber<Integer> endSubscriber = new SimpleEndSubscriber<>();

        publisher.subscribe(transformer);
        transformer.subscribe(endSubscriber);

        // publish
        IntStream.range(0, 10).forEach(i -> publisher.submit(String.valueOf(i)));
        publisher.close();

        while (!endSubscriber.isCompleted()) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        assertEquals(10, endSubscriber.getList().size());
        descSortingCheck(endSubscriber.getList().toArray(new Integer[10]));
    }
}
