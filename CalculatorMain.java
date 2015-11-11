import javax.swing.JFrame;
import java.awt.*;

// Java expression parser and calculator program
public class CalculatorMain extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -5823462180730666165L;

    public static void main(String[] args) {
	
	ExpressionForm expForm = new ExpressionForm();
	expForm.setTitle("JavaCalc");
	expForm.setPreferredSize(new Dimension(400, 300));
	expForm.pack();
	expForm.setLocationRelativeTo(null);
	expForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	expForm.setVisible(true);

    }
}
