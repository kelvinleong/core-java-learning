package functional;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionTest {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class TestObject {
        String s;
        Map<String, String> p;
    }

    @Test
    public void testMapChainWithoutIfCondition() {
        var o = new TestObject();
        var r = Optional.of(o)
                .map(TestObject::getP)
                .map(m -> m.getOrDefault("k", "v"))
                .orElse("r");

        assertEquals("r", r);
    }

    /*
     *  if (o.getField() != null && o.getField().equals(xx)
     */
    public void check() {
        var o = new TestObject();
        var r = Optional.ofNullable(o)
                .map(TestObject::getP)
                .map(m -> m.getOrDefault("k", "v"))
                .orElse("r");

        assertEquals("r", r);
    }
}
