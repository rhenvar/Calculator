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
    // Returns double
    public double evaluate(String input) {
	List<String> tokenized = clean(input);

	for (int i = 0; i < tokenized.size(); i++) {
	    String token = tokenized.get(i);

	    if (isNumber(token)) {
		postfix.push(convertToDouble(token));
	    } else {
		// (
		if (token.equals("(") || operators.isEmpty()) {
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
	if (postfix.size() > 1) {
	    throw new IllegalArgumentException("No operators left to evaluate!");
	}
	return postfix.pop();
    }

    private boolean isNumber(String token) {
	return token.matches("-{0,1}\\d*.{0,1}\\d+");
    }
    
    // Evaluates input data with operator
    // Returns double
    private double calculate(double first, double second, String operator) {
	switch (operator) {
	case "^":
	    return Math.pow(first, second);
	case "*":
	    return first * second;
	case "/":
	    return first / second;
	case "+":
	    return first + second;
	default:
	    return first - second;
	}
    }

    // Evaluates first two values from postfix with first value on operator
    // stack
    // Pushes value to postfix
    private void evaluatePostfix() {
	String operator = operators.pop();
	double second = postfix.pop();
	double first = postfix.pop();
	postfix.push(calculate(first, second, operator));
    }

    // Converts string to double
    // Returns double
    private double convertToDouble(String number) {
	if (number.contains(".")) {
	    return Double.parseDouble(number);
	} else {
	    return Integer.parseInt(number);
	}
    }
    
    // Formats input expression into usable tokens 
    // Returns List of Strings
    private List<String> clean(String input) {
	String evaluate = input.replaceAll("\\s+", "");

	List<String> tokenized = new ArrayList<>(
		Arrays.asList(evaluate.split("(?:((?<=[[+/*^()]&&[^.]])|(?=[[+-/*^()]&&[^.]])))(?!\\A)")));

	for (int i = 0; i < tokenized.size() - 1; i++) {
	    if (isNumber(tokenized.get(i)) && tokenized.get(i + 1).equals("(")
		    || tokenized.get(i).equals(")") && tokenized.get(i + 1).equals("(")) {
		tokenized.add(i + 1, "*");
	    }
	    if (isNumber(tokenized.get(i)) && isNumber(tokenized.get(i + 1))) {
		tokenized.add(i + 1, "+");
		i++;
	    }
	}
	return tokenized;
    }
}
