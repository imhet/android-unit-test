package im.het.android.unittest.jnit;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void add() {
        Calculator c = new Calculator();

        assertEquals(2 , c.add(1 , 1));
        assertEquals(3 , c.add(1 , 2));
        assertEquals(4 , c.add(0 , 4));
    }

    @Test
    public void multiply() {
        Calculator c = new Calculator();

        assertEquals(1 , c.multiply(1 , 1));
        assertEquals(2 , c.multiply(1 , 2));
        assertEquals(0 , c.multiply(1 , 0));
        assertEquals(6 , c.multiply(2 , 3));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void divide() {
        Calculator c = new Calculator();

        assertEquals(0.5 , c.divide(1, 2) , 0.01);
        assertEquals(0 , c.divide(0, 2) , 0.01);
        assertEquals(0.33 , c.divide(1, 3) , 0.01);

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Dividor cannot be 0");
        exceptionRule.expectMessage(Matchers.startsWith("Dividor"));
        assertEquals(0 , c.divide(1, 0) , 0.01);
    }
}