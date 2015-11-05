import java.util.Scanner;

// Java expression parser and calculator program
public class CalculatorMain {
    public static void main(String[] args) {
	
	Scanner userIn = new Scanner(System.in);
	System.out.println("Welcome to Garrick's JavaCalc!");
	System.out.println("Sample Expressions: ");
	System.out.println("  4 + 3 * 2");
	System.out.println("  2 + 4(-2 + 1)");
	System.out.println("  4^-2((6 - 2) / 3)");
	System.out.println("  -2(4 * 3^(15 / 2))");
	System.out.println("  2 * (-2 + -2(5)(27))");
	System.out.println();
	System.out.println("Enter an Expression");
	String expression;
	boolean toContinue = true;
	
	while (toContinue) {
	    System.out.print("~> ");
	    expression = userIn.nextLine();
	    if (expression.startsWith("q")) {
		toContinue = false; 
		continue;
	    }
	    
	    ExpressionManager expMan = new ExpressionManager();
	    try {
		System.out.println("  " + expMan.evaluate(expression));
	    }
	    catch (Exception ex) {
		System.out.println("Invalid Expression! ");
		System.out.println("Exception " + ex.toString());
	    }
	} 
	System.out.println("Bye!");
	userIn.close();
    }
}
