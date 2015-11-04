import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;

public class ExpressionManager {
    private static final List<String> OPERATORS = new ArrayList<>(Arrays.asList("-", "+", "/", "*", "^"));
    private Stack<Double> postfix;
    private Stack<String> operators;

    public ExpressionManager() {
	operators = new Stack<>();
	postfix = new Stack<>();
    }

    public double evaluate(String input) {
	String evaluate = input.replaceAll("\\s+", "");

	// Super fancy regex to basically tokenize our input expression int an
	// array of separate
	// characters and operators
	List<String> tokenized = new ArrayList<>(
		Arrays.asList(evaluate.split("(?:((?<=[+-/*^()])|(?=[+-/*^()])))(?!\\A)")));

	// handling distributive property 3(4 + 2), (4 + 2)3, and (3)(4)
	for (int i = 0; i < tokenized.size() - 1; i++) {
	    if (tokenized.get(i).matches("\\d+") && tokenized.get(i + 1).equals("(")
		    || tokenized.get(i).equals(")") && tokenized.get(i + 1).matches("\\d+")
		    || tokenized.get(i).equals(")") && tokenized.get(i + 1).equals("(")) {
		tokenized.add(i + 1, "*");
	    }
	}
	System.out.println(tokenized);
	for (int i = 0; i < tokenized.size(); i++) {
	    System.out.println(postfix);
	    String token = tokenized.get(i);

	    // numbers
	    if (token.matches("\\d+")) {
		postfix.push(convertToDouble(token));
	    }
	    // operators
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

	while (!operators.isEmpty()) {
	    evaluatePostfix();
	}
	return postfix.pop();
    }

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

    private void evaluatePostfix() {
	try {
	    String operator = operators.pop();
	    double second = postfix.pop();
	    double first = postfix.pop();
	    postfix.push(calculate(first, second, operator));
	} catch (Exception ex) {
	    System.out.println("Expression is Invalid!");
	}
    }

    private double convertToDouble(String number) {
	if (number.contains(".")) {
	    return Double.parseDouble(number);
	} else {
	    return Integer.parseInt(number);
	}
    }
}
