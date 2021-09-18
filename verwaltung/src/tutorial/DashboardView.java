package tutorial;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Point;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class DashboardView extends JFrame {
	
	private JPanel dateienUebersichtListe;
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
    	
    	JScrollPane uebersicht = new JScrollPane();
    	uebersicht.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	uebersicht.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	splitPane_1.setLeftComponent(uebersicht);
    	   	
    	dateienUebersichtListe = new JPanel();
    	dateienUebersichtListe.setLocation(new Point(3, 0));
    	dateienUebersichtListe.setAutoscrolls(true);
    	uebersicht.setViewportView(dateienUebersichtListe);
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
		
		panelListe = new JPanel[dateien.length];
		
		for (File datei : dateien) {			
			Tutorial tutorial = new Tutorial();
			tutorial.leseAusDatei(datei);
			
			
			
			System.out.println(datei);
			
	    	JPanel testPanel2 = new JPanel();
	    	testPanel2.setLayout(new BoxLayout(testPanel2, BoxLayout.X_AXIS));

			
			JSplitPane card = new JSplitPane();
			card.setDividerSize(0);
	    	card.setEnabled(false);
	    	card.setResizeWeight(0.9);
	    	testPanel2.add(card);
	    	
	    	JPanel panel = new JPanel();
	    	card.setLeftComponent(panel);
	    	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    	
	    	JLabel lblNewLabel = new JLabel(tutorial.getTitel());
	    	panel.add(lblNewLabel);
	    	
	    	JPanel panel_1 = new JPanel();
	    	card.setRightComponent(panel_1);
	    	panel_1.setLayout(new GridLayout(3, 1, 0, 0));
	    	
	    	JButton btnNewButton = new JButton("Öffnen");
	    	panel_1.add(btnNewButton);
	    	
	    	JButton btnNewButton_1 = new JButton("Bearbeiten");
	    	panel_1.add(btnNewButton_1);
	    	
	    	JButton btnNewButton_2 = new JButton("Entfernen");
	    	panel_1.add(btnNewButton_2);
	    	
	    	dateienUebersichtListe.add(testPanel2);
	    	
		}

	}
	

	private void init() {
		setSize(762,400);
		setTitle("Tutorial Verwaltung");
		
		
	}
}