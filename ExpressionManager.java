import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// ExpressionManager uses Polish postfix notation to evaluate mathematical expressions
public class ExpressionManager {
    private static final List<String> OPERATORS = new ArrayList<>(Arrays.asList("-", "+", "/", "*", "^"));
    private Stack<Double> postfix;
    private Stack<String> operators;

    // Initialize postfix and operators stack
    public ExpressionManager() {
	operators = new Stack<>();
	postfix = new Stack<>();
    }

    // Evaluates input expression
    public double evaluate(String input) {
	String evaluate = input.replaceAll("\\s+", "");

	// Regex to tokenize our input expression into an array of separate
	// characters and operators
	List<String> tokenized = new ArrayList<>(
		Arrays.asList(evaluate.split("(?:((?<=[+/*^()])|(?=[+-/*^()])))(?!\\A)")));

	for (int i = 0; i < tokenized.size() - 1; i++) {
	    
	    // handling distributive property: 3(4 + 2) and (3)(4)
	    if (tokenized.get(i).matches("\\d+|-\\d+") && tokenized.get(i + 1).equals("(")
		    || tokenized.get(i).equals(")") && tokenized.get(i + 1).equals("(")) {
		tokenized.add(i + 1, "*");
	    }

	    // handling negative number cases: -3 -2
	    if (tokenized.get(i).contains("-") && tokenized.get(i + 1).contains("-")) {
		tokenized.add(i + 1, "+");
		i++;
	    }
	}
	for (int i = 0; i < tokenized.size(); i++) {
	    String token = tokenized.get(i);
	    
	    if (token.matches("\\d+|-\\d+")) {
		postfix.push(convertToDouble(token));
	    }
	    else {
		// (
		if (token.equals("(")) {
		    operators.push(token);
		}
		// )
		else if (token.equals(")")) {
		    while (!operators.isEmpty() && !operators.peek().equals("(")) {
			evaluatePostfix();
		    }
		    operators.pop();
		}
		// -+*/^
		else {
		    while (!operators.isEmpty() && OPERATORS.indexOf(operators.peek()) >= OPERATORS.indexOf(token)) {
			evaluatePostfix();
		    }
		    operators.push(token);
		}
	    }
	}
	// Evaluate remaining operators
	while (!operators.isEmpty()) {
	    evaluatePostfix();
	}
	return postfix.pop();
    }

    // Evaluates and returns 
    private double calculate(double first, double second, String operator) {
	switch (operator) {
	case "^":
	    return Math.pow(first, second);
	case "*":
	    return first * second;
	case "/":
	    return first / second;
	case "-":
	    return first - second;
	default:
	    return first + second;
	}
    }

    // Evaluates first two values from postfix with first value on operator stack
    // Pushes value to postfix
    private void evaluatePostfix() {
	String operator = operators.pop();
	double second = postfix.pop();
	double first = postfix.pop();
	postfix.push(calculate(first, second, operator));
    }

    // Converts string to double
    private double convertToDouble(String number) {
	if (number.contains(".")) {
	    return Double.parseDouble(number);
	} else {
	    return Integer.parseInt(number);
	}
    }
}
