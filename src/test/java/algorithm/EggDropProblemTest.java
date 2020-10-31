package algorithm;

import algorithms.EggDropProblem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EggDropProblemTest {
    @Test
    public void Should_Get3_When_Given2EggsAnd4Floors() {
        EggDropProblem ep = new EggDropProblem();
        int result = ep.dpSolution(2, 4);
        assertEquals(3, result);

        result = ep.recursiveSolution(2, 4);
        assertEquals(3, result);
    }

    @Test
    public void Should_Get5_When_Given2EggsAnd9Floors() {
        EggDropProblem ep = new EggDropProblem();
        int result = ep.dpSolution(2, 9);
        assertEquals(4, result);

        result = ep.recursiveSolution(2, 9);
        assertEquals(4, result);
    }

    @Test
    public void Should_Get5_When_Given2EggsAnd10Floors() {
        EggDropProblem ep = new EggDropProblem();
        int result = ep.dpSolution(2, 9);
        assertEquals(4, result);

        result = ep.recursiveSolution(2, 9);
        assertEquals(4, result);
    }

    @Test
    public void Should_Get14_When_Given2EggsAnd100Floors() {
        EggDropProblem ep = new EggDropProblem();
        int result = ep.dpSolution(2, 100);
        assertEquals(14, result);

        result = ep.recursiveSolution(2, 100);
        assertEquals(14, result);
    }
}
