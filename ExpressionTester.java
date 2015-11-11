import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ExpressionTester {
    private static final double DELTA = .000001;
    
    @Test
    public void multiplicationByZero() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("5 * 0 should be 0.0", 0.0, expTestMan.evaluate("5 * 0"), DELTA);
	assertEquals("0 * 3 * 4.0 should be 0.0", 0.0, expTestMan.evaluate("0 * 3 * 4.0"), DELTA);
    }
    
    
    @Test 
    public void divisionWithZero() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("0 / 5 should be 0.0", 0.0, expTestMan.evaluate("0 / 5"), DELTA);
	assertEquals("0 / 3.4 should be 0.0", 0.0, expTestMan.evaluate("0 / 3.4"), DELTA);
    }
    
    
    @Test
    public void multiplication() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("4 * 3 should be 12.0", 12.0, expTestMan.evaluate("4 * 3"), DELTA);
	assertEquals("4 * 2.0 * 3.0 should be 24.0", 24.0, expTestMan.evaluate("4 * 2.0 * 3.0"), DELTA);
	assertEquals("4(3) should be 12.0", 12.0, expTestMan.evaluate("4(3)"), DELTA);
	assertEquals("(4)(2)(3) should be 24.0", 24.0, expTestMan.evaluate("(4)(2)(3)"), DELTA);
    }
    
    @Test
    public void division() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("3 / 4 should be 0.75", 0.75, expTestMan.evaluate("3 / 4"), DELTA);
	assertEquals("3.0 / 0.5 should be 6.0", 6.0, expTestMan.evaluate("3.0 / 0.5"), DELTA);
    }
    
    @Test
    public void addittion() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("2 + 4 + 3 should be 9.0", 9.0, expTestMan.evaluate("2 + 4 + 3"), DELTA);
	assertEquals("5.4 + 2.2 + 1 should be 8.6", 8.6, expTestMan.evaluate("5.4 + 2.2 + 1"), DELTA);
    }
    
    @Test 
    public void subtraction() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("27 - 7 - 5 should be 15.0", 15.0, expTestMan.evaluate("27 - 7 - 5"), DELTA);
	assertEquals("-2 - 4 should be -6.0", -6.0, expTestMan.evaluate("-2 - 4"), DELTA);
	assertEquals("6.0 - 2 - 5.5 should be -1.5", -1.5, expTestMan.evaluate("6.0 - 2 - 5.5"), DELTA);
    }
    
    @Test
    public void exponent() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("4^2 should be 16.0", 16.0, expTestMan.evaluate("4^2"), DELTA);
	assertEquals("4^-2 should be 0.0625", 0.0625, expTestMan.evaluate("4^-2"), DELTA);
	assertEquals("-4^3 should be -64.0", -64.0, expTestMan.evaluate("-4^3"), DELTA);
    }
    
    @Test public void parenthesis() {
	ExpressionManager expTestMan = new ExpressionManager();
	
	assertEquals("(3 + 2) + (4 - 2) should be 7.0", 7.0, expTestMan.evaluate("(3 + 2) + (4 - 2)"), DELTA);
	assertEquals("3(2 + 2) should be 12.0", 12.0, expTestMan.evaluate("3(2 + 2)"), DELTA);
	assertEquals("4^(6 - 5) should be 4.0", 4.0, expTestMan.evaluate("4^(6 - 5)"), DELTA);
	assertEquals("(2 + 2) * ((7 - 5) * 3) should be 24.0", 24.0, expTestMan.evaluate("(2 + 2) * ((7 - 5) * 3)"), DELTA);
	assertEquals("-3(1 + 1) should be -6.0", -6.0, expTestMan.evaluate("-3(1 + 1)"), DELTA);
    }
}









