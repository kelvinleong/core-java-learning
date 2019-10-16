import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tdd.Greeting;

public class GreetingTest {
    @Test
    public void should_interpolates_when_givenBob() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Bob");
        Assertions.assertEquals( "Hello, Bob.", result);
    }

    @Test
    public void should_handleStandin_when_givenNull() {
        Greeting greeting = new Greeting();
        String result = greeting.greet(null);
        Assertions.assertEquals("Hello, my friend.", result);
    }

    @Test
    public void should_shoutUppercase_when_givenUppercaseName() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("JERRY");
        Assertions.assertEquals("HELLO JERRY!", result);
    }

    @Test
    public void should_concatNames_when_givenTwoNames() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Jill", "Jane");
        Assertions.assertEquals( "Hello, Jill and Jane.", result);
    }

    @Test
    public void should_concatNames_when_givenMoreThanTwoNames() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Amy", "Brian", "Charlotte");
        Assertions.assertEquals("Hello, Amy, Brian, and Charlotte.", result);
    }

    @Test
    public void should_separateUppercaseLowerCase_when_givenNamesOfMixedType() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Amy", "BRIAN", "Charlotte");
        Assertions.assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN!", result);
    }

    @Test
    public void should_splitNames_when_givenNamesContainingComma() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Bob", "Charlie, Dianne");
        Assertions.assertEquals("Hello, Bob, Charlie, and Dianne.", result);
    }

    @Test
    public void should_avoidSplitIntentionalComma_when_givenNamesSurroundingDoubleQuotes() {
        Greeting greeting = new Greeting();
        String result = greeting.greet("Bob", "\"Charlie, Dianne\"");
        Assertions.assertEquals("Hello, Bob and Charlie, Dianne.", result);
    }
}
