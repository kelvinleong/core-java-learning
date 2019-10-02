import leetcodeChanllenge.BallonProblems;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChanllengeTest {
    @Test
    public void should_CountBallons_when_RunBallonProblems() {
        BallonProblems bp = new BallonProblems();
        int result = bp.howManyBallons("ballonballonballonballonballonballonballonballonballonballon");
        assertEquals(result, 10);
    }
}
