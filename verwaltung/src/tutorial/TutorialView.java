package tutorial;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Component;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

// Das ist die Ansicht von einem Tutorial, wenn man es geöffnet hat
public class TutorialView extends JFrame {

	private Tutorial tutorial;

	private JScrollPane scrollpane;
	private JPanel titlePanel;
	private JPanel contentPanel;

	private JLabel titelLabel;
	
	public TutorialView(Tutorial tutorial) {
		this.tutorial = tutorial;

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		titlePanel = new JPanel();
		contentPanel = new JPanel();
		
		
		titelLabel = new JLabel(tutorial.getTitel(), JLabel.CENTER);
		titlePanel.add(titelLabel);
	
		scrollpane = new JScrollPane();
    	scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


		init();
		scrollpane.setViewportView(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		scrollpane.setColumnHeaderView(titlePanel);
		
		getContentPane().add(scrollpane);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	
	}
	
	private void init() {
		setSize(762,400);
		setTitle("Anleitung: " + tutorial.getTitel());
		
		String inhalt = tutorial.getInhalt();
	
		Scanner scanner = new Scanner(inhalt);
		while (scanner.hasNextLine()) {
		  String zeile = scanner.nextLine();
		  if (zeile.contains("|#####|")) {
			  contentPanel.add(Box.createVerticalStrut(20));
		  } else {
			  JTextArea test = new JTextArea(zeile);
			  test.setEditable(false);
			  test.setDisabledTextColor(Color.black);
			  contentPanel.add(test);
		  }
		}
		scanner.close();
	}
}