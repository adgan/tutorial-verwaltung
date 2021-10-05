package tutorial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9039353202487879891L;

	private JScrollPane scrollpane;
	private JTextField titleField;
	private JTextArea contentTextArea;
	private Tutorial tutorial;

	public EditorView(Tutorial tutorial) {
		this.tutorial = tutorial;

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		// Setze Titel Panel mit Titel Label
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

		// Setze editor Panel, wo der Editor mit Buttons erscheint
		JPanel editorPanel = new JPanel();
		content.add(editorPanel);
		editorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

		titleField = new JTextField(this.tutorial.getTitel());
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
		titleField.setColumns(40);

		editorPanel.add(titleField);

		JPanel textAreaEditorPanel = new JPanel();
		content.add(textAreaEditorPanel);
		textAreaEditorPanel.setLayout(new BorderLayout(20, 20));

		scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentTextArea = new JTextArea();
		contentTextArea.setText(this.tutorial.getInhalt());

		// Füge TextArea zur Scrollpane und diese zum textAreaPanel hinzu
		scrollpane.setViewportView(contentTextArea);
		textAreaEditorPanel.add(scrollpane);

		JPanel buttonPanel = new JPanel();
		content.add(buttonPanel);

		JButton btnNeueSeite = new JButton("Neue Seite");
		btnNeueSeite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = contentTextArea.getText();
				text += "\n#####\n";
				contentTextArea.setText(text);
			}
		});
		btnNeueSeite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(btnNeueSeite);

		JButton btnSpeichern = new JButton("Speichern");

		//
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Tutorial currentTutorial = null;
				try {
					currentTutorial = new Tutorial(titleField.getText(), "Testname",
							"tutorial.test@tutorial.test.de", contentTextArea.getText());
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				currentTutorial.setDateiName(tutorial.getDateiName());

				currentTutorial.speichereAlsDatei();
			}
		});
		btnSpeichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonPanel.add(btnSpeichern);

		init();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setLocationRelativeTo(null);
		setLocation(100, 100);
		setVisible(true);

	}

	private void init() {
		setSize(762, 400);
		setTitle("Ändere Anleitung");
	}
}