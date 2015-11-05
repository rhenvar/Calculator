import java.util.Scanner;

public class CalculatorMain {
    public static void main(String[] args) {
	
	Scanner s = new Scanner(System.in);
	System.out.println("Enter an Expression");
	String expression;
	do {
	    System.out.print("~> ");
	    expression = s.nextLine();
	    ExpressionManager expMan = new ExpressionManager();
	    System.out.println(expMan.evaluate(expression));
	} while (!expression.startsWith("q"));
	
	s.close();
    }
}
