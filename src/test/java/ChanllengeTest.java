import leetcodeChanllenge.BallonProblems;
import leetcodeChanllenge.LongestChunkedPalindrome;
import leetcodeChanllenge.UniqueOccurences;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChanllengeTest {
    @Test
    public void should_CountBallons_when_RunBallonProblems() {
        BallonProblems bp = new BallonProblems();
        int result = bp.howManyBallons("ballonballonballonballonballonballonballonballonballonballon");
        assertEquals(result, 10);
    }

    @Test
    public void should_CountPalindrome_when_RunParlindrome() {
        LongestChunkedPalindrome lcp = new LongestChunkedPalindrome();
        int result = lcp.count("ghiabcdefhelloadamhelloabcdefghi");
        assertEquals(result, 7);
    }

    @Test
    public void should_FindOccurences_when_RunUniqueOccurences() {
        UniqueOccurences uq = new UniqueOccurences();
        boolean r = uq.uniqueOccurrences(new int[]{1, 3});
        assertEquals(r, false);
    }
}
