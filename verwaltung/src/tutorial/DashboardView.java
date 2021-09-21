package tutorial;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ScrollPaneConstants;
import java.awt.Point;

public class DashboardView extends JFrame {
	
	private JPanel dateienUebersichtListe;
	private JScrollPane uebersicht;
	private JPanel[] panelListe;
	
// Startseite zur Ansicht von Tutorials
    public DashboardView() {
    	setTitle("Dashboard");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DashboardView.class.getResource("/tutorial/icons/iconmonstr-school-23-240.png")));
    	getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
    	getContentPane().add(titlePanel);
    	titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	JLabel lblTitle = new JLabel("Verwaltung der Tutorials");
    	lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 19));
    	titlePanel.add(lblTitle);

    	JPanel content = new JPanel();
    	getContentPane().add(content);
    	content.setLayout(new BorderLayout(0, 0));
		
    	JSplitPane splitPane_1 = new JSplitPane();
    	splitPane_1.setResizeWeight(1.0);
    	splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    	content.add(splitPane_1);
    	
    	uebersicht = new JScrollPane();
    	uebersicht.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	uebersicht.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	splitPane_1.setLeftComponent(uebersicht);
    	   	
    	dateienUebersichtListe = new JPanel();
    	dateienUebersichtListe.setLocation(new Point(3, 0));
    	uebersicht.setViewportView(dateienUebersichtListe);
    	dateienUebersichtListe.setLayout(new BoxLayout(dateienUebersichtListe, BoxLayout.Y_AXIS));
    	uebersicht.setPreferredSize(new Dimension(0, 0));

    	    	
    	JPanel panelUntereSeite = new JPanel();
    	splitPane_1.setRightComponent(panelUntereSeite);
    	splitPane_1.setEnabled(false);
    	splitPane_1.setDividerSize(0);
    	
    	JButton btnNeu = new JButton("Neu");
    	btnNeu.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	btnNeu.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			CreatorView createTutorial = new CreatorView();
    			createTutorial.setVisible(true);
    		}
    	});
    	
    	btnNeu.setIcon(new ImageIcon(DashboardView.class.getResource("/tutorial/icons/iconmonstr-plus-circle-thin-16.png")));
    	panelUntereSeite.add(btnNeu);
        
    	JButton btnUpdate = new JButton("Aktualisieren");
    	btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	btnUpdate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			listeTutorials();
    		}
    	});
    	
    	btnUpdate.setIcon(new ImageIcon(DashboardView.class.getResource("/tutorial/icons/iconmonstr-plus-circle-thin-16.png")));
    	panelUntereSeite.add(btnUpdate);
    	
	}
	

	public static void main(String[] args) {
		DashboardView test = new DashboardView();
		test.init();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setLocationRelativeTo(null);
		test.setVisible(true);
		
		test.listeTutorials();
		
	}
	
	public void listeTutorials() {
		File dir = new File("./Tutorials");
		
		File [] dateien = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".tutorial");
			}
		});
		if (dateienUebersichtListe.getComponentCount() > 0) {
			dateienUebersichtListe.removeAll();
		}
		
		panelListe = new JPanel[dateien.length];
		int index = 0;
		for (File datei : dateien) {			
			Tutorial tutorial = new Tutorial();
			tutorial.leseAusDatei(datei);
						
			panelListe[index] = new JPanel();
			panelListe[index].setLayout(new BoxLayout(panelListe[index], BoxLayout.X_AXIS));

			JSplitPane card = new JSplitPane();
			card.setDividerSize(0);
	    	card.setEnabled(false);
	    	card.setResizeWeight(0.9);
	    	panelListe[index].add(card);
	    	
	    	JPanel panel = new JPanel();
	    	card.setLeftComponent(panel);
	    	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    	
	    	JLabel lblNewLabel = new JLabel(tutorial.getTitel(), JLabel.CENTER);
	    	panel.add(lblNewLabel);
	    	
	    	JPanel panel_1 = new JPanel();
	    	card.setRightComponent(panel_1);
	    	panel_1.setLayout(new GridLayout(3, 1, 0, 0));
	    	
			JButton btnOeffnen = new JButton("Öffnen");
			btnOeffnen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TutorialView anleitung = new TutorialView(tutorial);
					
				}
			});
	    	panel_1.add(btnOeffnen);
	    	
			JButton btnEdit = new JButton("Bearbeiten");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EditorView editorView = new EditorView(tutorial);

				}
			});
	    	panel_1.add(btnEdit);
	    	
			JButton btnDelete = new JButton("Entfernen");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delete(datei.getPath());
					listeTutorials();
				}
			});
	    	panel_1.add(btnDelete);
	    	
	    	dateienUebersichtListe.add(panelListe[index]);
	    	index = index + 1;
	    	
		}
    	uebersicht.setViewportView(dateienUebersichtListe);
	}
	
	private void delete(String pfad) {
		try
        {
            Files.deleteIfExists(Paths.get(pfad));
            System.out.println("Datei gelöscht im Pfad: " + pfad);
        }
        catch(NoSuchFileException e)
        {
            System.out.println("Die Datei existiert nicht");
        }
        catch(DirectoryNotEmptyException e)
        {
            System.out.println("Directory is not empty.");
        }
        catch(IOException e)
        {
            System.out.println("Keine Berechtigungen.");
        }
	}

	private void init() {
		setSize(762,400);
		setTitle("Tutorial Verwaltung");
		
		
	}
}