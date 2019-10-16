import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tdd.Greeting;

public class GreetingTest {
    @Test
    public void should_interpolates_when_givenBob() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Bob");
        Assertions.assertEquals(result, "Hello, Bob.");
    }

    @Test
    public void should_handleStandin_when_givenNull() {
        Greeting greeting = new Greeting();
        String result = greeting.greet(null);
        Assertions.assertEquals(result, "Hello, my friend.");
    }

    @Test
    public void should_shoutUppercase_when_givenUppercaseName() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("JERRY");
        Assertions.assertEquals(result, "HELLO JERRY!");
    }

    @Test
    public void should_concatNames_when_givenTwoNames() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Jill", "Jane");
        Assertions.assertEquals(result, "Hello, Jill and Jane.");
    }

    @Test
    public void should_concatNames_when_givenMoreThanTwoNames() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Amy", "Brian", "Charlotte");
        Assertions.assertEquals(result, "Hello, Amy, Brian, and Charlotte.");
    }
}
