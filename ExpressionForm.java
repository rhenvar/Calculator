import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExpressionForm extends JFrame {
    
    List<JButton> numbers;
    List<JButton> commands;
    
    JButton exitButton;
    
    static JTextField buildExpression;
    static JTextField output;
    
    public ExpressionForm() {
	numbers = new ArrayList<>();
	
	// numbers
	for (int i = 0; i < 10; i++) {
	    numbers.add(new JButton(Integer.toString(i)));
	}
	
	// commands
	commands = new ArrayList<>();
	commands.add(new JButton("."));
	commands.add(new JButton("-"));
	commands.add(new JButton("+"));
	commands.add(new JButton("*"));
	commands.add(new JButton("/"));
	commands.add(new JButton("^"));
	commands.add(new JButton("("));
	commands.add(new JButton(")"));
	
	
	buildExpression = new JTextField(15);
	buildExpression.setEnabled(false);
	buildExpression.setHorizontalAlignment(JTextField.RIGHT);
	
	output = new JTextField(15);
	output.setEnabled(false);
	output.setHorizontalAlignment(JTextField.RIGHT);
	
	JPanel display = new JPanel();
	display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
	display.setBorder(BorderFactory.createSoftBevelBorder(1));
	
	JPanel textPanel = new JPanel();
	textPanel.setPreferredSize(new Dimension(50, 10));
	textPanel.add(buildExpression);
	textPanel.add(output);
	
	
	JPanel numbersPanel = new JPanel();
	numbersPanel.setLayout(new GridLayout(2, 3, 1, 1));
	numbersPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
	
	JPanel commandsPanel = new JPanel();
	commandsPanel.setLayout(new GridLayout(2, 5, 1, 1));
	commandsPanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 4, 4));
	
	for (final JButton button : numbers) {
	    button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    buildExpression.setText(buildExpression.getText() + button.getText());
		}
	    });
	    numbersPanel.add(button);
	}
	
	for (final JButton command : commands) {
	    command.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    buildExpression.setText(buildExpression.getText() + command.getText());
		}
	    });
	    commandsPanel.add(command);
	}
	
	JButton evaluate = new JButton("Enter");
	evaluate.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		ExpressionManager expMan = new ExpressionManager();
		try {
		    output.setText(Double.toString(expMan.evaluate(buildExpression.getText())));
		    buildExpression.setText("");
		}
		catch (Exception ex) {
		    output.setText("Invalid Expression " + ex.getMessage());
		}
	    }
	});
	commandsPanel.add(evaluate);
	
	JButton clear = new JButton("Clear");
	clear.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		buildExpression.setText("");
	    }
	});
	commandsPanel.add(clear);
	
	display.add(textPanel);
	display.add(numbersPanel);
	display.add(commandsPanel);
	add(display);
    }
}
