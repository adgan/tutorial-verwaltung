package tutorial;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
// Das ist die Ansicht von einem Tutorial, wenn man es geöffnet hat
public class TutorialView extends JFrame {
	private JTextField textField;
	public TutorialView() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		textField = new JTextField();
		getContentPane().add(textField);
		textField.setColumns(1);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut_1);
		
		JTextArea textArea = new JTextArea();
		getContentPane().add(textArea);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		getContentPane().add(verticalStrut);
		
		JTextArea textArea_1 = new JTextArea();
		getContentPane().add(textArea_1);
	}
    
}