package tutorial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreatorView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7611621889790609376L;
	private JTextField titleField;
	private JTextArea contentArea;

	public CreatorView() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		// Panel für Titel
		JPanel title = new JPanel();
		getContentPane().add(title);
		title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Überschrift
		JLabel lblTutorialBearbeiten = new JLabel("Anleitung erstellen");
		lblTutorialBearbeiten.setHorizontalAlignment(SwingConstants.CENTER);
		lblTutorialBearbeiten.setFont(new Font("Tahoma", Font.PLAIN, 19));
		title.add(lblTutorialBearbeiten);

		// Der Inhalt
		JPanel content = new JPanel();
		getContentPane().add(content);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		// Platzhalter
		JPanel panel_1 = new JPanel();
		content.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

		// Textfeld für den Titel der Anleitung
		titleField = new JTextField();
		titleField.setToolTipText("Titel der Anleitung");

		titleField.addFocusListener(new FocusListener() {

			// Ist kein Text als Titel eingegeben:
			@Override
			public void focusGained(FocusEvent e) {
				if (titleField.getText().equals("Titel der Anleitung")) {
					titleField.setText("");
					titleField.setForeground(Color.BLACK);
				}
			}

			// Wenn das Feld des Titels leer ist
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

		// Panel für den Inhalt
		JPanel panel_2 = new JPanel();
		content.add(panel_2);
		panel_2.setLayout(new BorderLayout(20, 20));

		// Scrollpane damit man scrollen kann und das alles optischer aussieht
		JScrollPane scrollpane = new JScrollPane();
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		// Inhalt-Textarea
		contentArea = new JTextArea();
		scrollpane.setViewportView(contentArea);
		panel_2.add(scrollpane);

		JPanel panel = new JPanel();
		content.add(panel);

		// Button für eine neue Seite
		JButton btnNeueSeite = new JButton("Neue Seite");

		// "Neue Seite" fügt einen Platzhalter hinzu, sodass beim Angucken eine zweite
		// Seite existiert
		btnNeueSeite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = contentArea.getText();
				text += "\n|#####|\n";
				contentArea.setText(text);
			}
		});
		btnNeueSeite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnNeueSeite);

		// Button fürs speichern
		JButton btnSpeichern = new JButton("Speichern");
		btnSpeichern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int titleExists = checkIfTitleExists(titleField.getText());

				switch (titleExists) {
					// Titel existiert und wird �berschrieben
					case 0:
						Tutorial currentTutorial = new Tutorial(titleField.getText(), "Testname",
								"tutorial.test@tutorial.test.de", contentArea.getText());

						currentTutorial.setDateiName(getFileName(titleField.getText()));

						currentTutorial.speichereAlsDatei();
						break;
					// Titel existiert nicht oder existiert, wird aber nicht �berschrieben

					default:
						break;
				}

			}
		});
		btnSpeichern.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnSpeichern);

		init();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void init() {
		setSize(762, 400);
		setTitle("Erstelle eine Anleitung");
	}

	// Methode um alle Dateinamen auszugeben
	private String getFileName(String title) {
		// Erstellt den Ordner --> file sind Dateien und Ordner in java
		File dir = new File("./Tutorials");

		// Liste aller Dateien wird im Array gespeichert
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".tutorial");
			}
		});

		// Die Endung der Datei (xxxxx-tutorial) wird entfernt
		for (File datei : files) {
			String dateiWholeString = new StringBuilder(datei.getName()).reverse().toString();
			String tutorialName = new StringBuilder(dateiWholeString.substring(15)).reverse().toString();
			System.out.println(tutorialName);
			System.out.println(title);

			// Der Dateiname wird ausgegeben
			if (title.equals(tutorialName)) {
				return datei.getName();
			}
		}
		return null;
	}

	// Es wird geprüft, ob die Datei bereits existiert
	private int checkIfTitleExists(String title) {
		// Erstellt den Ordner --> file sind Dateien und Ordner in java
		File dir = new File("./Tutorials");

		// Liste aller Dateien wird im Array gespeichert
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".tutorial");
			}
		});
		// die Variable für das Ergebnis (speichern oder abbrechen) wird deklariert
		int result = 0;

		// Die Endung der Datei (xxxxx-tutorial) wird entfernt
		for (File datei : files) {
			String dateiWholeString = new StringBuilder(datei.getName()).reverse().toString();
			String tutorialName = new StringBuilder(dateiWholeString.substring(15)).reverse().toString();
			System.out.println(tutorialName);
			System.out.println(title);
			// Wenn der Titel einem anderen Dateinamen gleicht soll die Datei nicht einfach
			// gespeichert werden
			if (title.equals(tutorialName)) {
				System.out.println("Tutorial existiert bereits unter diesem Titel");
				// Auswahl für den Benutzer, ob Überschreiben oder abbrechen
				Object[] options = { "\u00d6berschreiben", "Abbrechen" };
				//
				result = JOptionPane.showOptionDialog(null, "Die Anleitung existiert bereits.", "Trotzdem speichern",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				System.out.println(result);
			}
			break;
		}

		// 0 ist speichern, 1 und -1 ist abbrechen
		return result;
	}

}