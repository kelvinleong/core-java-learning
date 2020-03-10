package interviews;

import interviews.Agoda;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgodaInterviewTest {

    @Test
    public void should_inChronologicalOrder_whenGetAscendingDates() {
        Agoda agoda = new Agoda();
        List<String> input = List.of("05 Dec 2018", "07 Aug 2019", "15 Aug 2020", "01 Jun 2047");
        var r = agoda.getAscendingDates(input);
        for (int i = 0; i < r.size(); ++i) {
            assertTrue(input.get(i).equals(r.get(i)));
        }
    }

    @Test
    public void should_inChronologicalOrderWith1965_whenGetAscendingDates() {
        Agoda agoda = new Agoda();
        List<String> input = List.of("05 Dec 2018", "07 Aug 2019", "15 Aug 2020", "01 Jun 1965");
        var r = agoda.getAscendingDates(input);
        assertEquals(r.size(), input.size());
        assertEquals(input.get(3), r.get(0));
        for (int i = 1; i < r.size(); ++i) {
            assertTrue(input.get(i - 1).equals(r.get(i)));
        }
    }

    @Test
    public void should_inChronologicalOrderWithChaos_whenGetAscendingDates() {
        Agoda agoda = new Agoda();
        List<String> input = List.of("05 Dec 2015", "07 Aug 2019", "15 Aug 1970", "01 Jun 1984");
        var r = agoda.getAscendingDates(input);
        assertEquals(r.size(), input.size());
        assertEquals(input.get(2), r.get(0));
        assertEquals(input.get(3), r.get(1));
        assertEquals(input.get(0), r.get(2));
        assertEquals(input.get(1), r.get(3));
    }

    @Test
    public void should_reverserAString_whenInvokeReverse() {
        Agoda agoda = new Agoda();
        var r = agoda.reverse("HONG KONG AND SHANG HAI");
        assertEquals("HAI SHANG AND KONG HONG", r);
    }

    @Test
    public void should_getTwoElements_whenInvokeFindTwoOddOccurrence() {
        Agoda agoda = new Agoda();
        var r = agoda.findTwoOddOccurrence(Arrays.asList(18, 18, 29, 30, 7, 7, 2, 2, 6, 6));
        assertEquals(2, r.size());
        assertTrue(r.contains(29));
        assertTrue(r.contains(30));
    }
}
