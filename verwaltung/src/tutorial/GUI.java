package tutorial;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;

public class GUI extends JFrame {
	public GUI() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
	}

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.init();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setLocationRelativeTo(null);
		gui.setVisible(true);
		
		
		
	}

	private void init() {
		setSize(762,400);
		setTitle("Tutorial Verwaltung");
		
		
		
		
		
	}
}
