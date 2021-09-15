package tutorial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateView extends JFrame {
	
	private JTextField titleField;
	private JTextArea contentArea;
	
// Ansicht zum Editieren von Tutorials
    public CreateView() {
    	getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    	
    	JPanel title = new JPanel();
    	getContentPane().add(title);
    	title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	JLabel lblTutorialBearbeiten = new JLabel("Tutorial bearbeiten");
    	lblTutorialBearbeiten.setHorizontalAlignment(SwingConstants.CENTER);
    	lblTutorialBearbeiten.setFont(new Font("Tahoma", Font.PLAIN, 19));
    	title.add(lblTutorialBearbeiten);
    	
    	JPanel content = new JPanel();
    	getContentPane().add(content);
    	content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		content.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
		
		titleField = new JTextField();
		titleField.setToolTipText("Titel der Anleitung");

		titleField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (titleField.getText().equals("Titel der Anleitung")) {
					titleField.setText("");
					titleField.setForeground(Color.BLACK);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (titleField.getText().isEmpty()) {
					titleField.setForeground(Color.GRAY);
					titleField.setText("Titel der Anleitung");
				}
			}
			});

		titleField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(titleField);
    	titleField.setColumns(40);
    	
    	JPanel panel_2 = new JPanel();
    	content.add(panel_2);
    	panel_2.setLayout(new BorderLayout(20, 20));
		
    	contentArea = new JTextArea();
    	panel_2.add(contentArea);
    	
    	JPanel panel = new JPanel();
    	content.add(panel);
    	
    	JButton btnNewButton = new JButton("Neue Seite");
    	btnNewButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {

                

    		}
    	});
    	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	panel.add(btnNewButton);
    	
    	JButton btnNewButton_1 = new JButton("Speichern");
    	btnNewButton_1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				
    			Tutorial currentTutorial = new Tutorial( titleField.getText() , "Testname", "tutorial.test@tutorial.test.de", contentArea.getText() );
    			
				currentTutorial.speichereAlsDatei();
    		}
    	});
    	btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	panel.add(btnNewButton_1);
        
	}
	public static void main(String[] args) {
		EditorView test = new EditorView();
		test.init();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setLocationRelativeTo(null);
		test.setVisible(true);
		
		
		
	}

	private void init() {
		setSize(762,400);
		setTitle("Tutorial Verwaltung");
		
		
		
		
		
	}
}