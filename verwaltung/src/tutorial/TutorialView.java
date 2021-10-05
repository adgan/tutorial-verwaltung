package tutorial;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.ScrollPaneConstants;

// Das ist die Ansicht von einem Tutorial, wenn man es geöffnet hat
public class TutorialView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8050593799400025027L;

	private Tutorial tutorial;

	private JPanel contentPanel;

	public TutorialView(Tutorial tutorial) {
		this.tutorial = tutorial;

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel();

		contentPanel = new JPanel();

		JLabel titleLabel = new JLabel(tutorial.getTitel(), JLabel.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		titlePanel.add(titleLabel);

		// Erstelle Scrollpane, damit man alles an Inhalt sehen kann
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		init();

		// Erstelle das contentPanel, wo der Text erscheint in der scrollPane
		// so dass man scrollen kann
		scrollpane.setViewportView(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		// Setze Titel als Kopf der Scroll-Ansicht
		scrollpane.setColumnHeaderView(titlePanel);

		getContentPane().add(scrollpane);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	private void init() {
		setSize(762, 400);
		setTitle("Anleitung: " + tutorial.getTitel());

		String[] seiten = tutorial.listeSeiten();

		for (String seite : seiten) {
			JTextArea test = new JTextArea(seite);
			test.setLineWrap(true);
			test.setWrapStyleWord(false);
			test.setEditable(false);
			test.setDisabledTextColor(Color.black);
			contentPanel.add(test);

			contentPanel.add(Box.createVerticalStrut(20));

		}
	}
}