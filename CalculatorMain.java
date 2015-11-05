import java.util.Scanner;

public class CalculatorMain {
    public static void main(String[] args) {
	
	Scanner userIn = new Scanner(System.in);
	System.out.println("Enter an Expression");
	String expression;
	
	do {
	    System.out.print("~> ");
	    expression = userIn.nextLine();
	    ExpressionManager expMan = new ExpressionManager();
	    try {
		System.out.println(expMan.evaluate(expression));
	    }
	    catch (Exception ex) {
		System.out.println("Invalid Expression!");
	    }
	} while (!expression.startsWith("q"));
	userIn.close();
    }
}
