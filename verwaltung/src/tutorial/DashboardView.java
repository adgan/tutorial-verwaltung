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
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class DashboardView extends JFrame {
// Startseite zur Ansicht von Tutorials
    public DashboardView() {
    	setTitle("Dashboard");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(DashboardView.class.getResource("/tutorial/icons/iconmonstr-school-23-240.png")));
    	getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel title = new JPanel();
    	getContentPane().add(title);
    	title.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	JLabel lblNewLabel = new JLabel("Verwaltung der Tutorials");
    	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
    	title.add(lblNewLabel);

    	JPanel content = new JPanel();
    	getContentPane().add(content);
    	content.setLayout(new BorderLayout(0, 0));
    	
    	JSplitPane splitPane_1 = new JSplitPane();
    	splitPane_1.setResizeWeight(1.0);
    	splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    	content.add(splitPane_1);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	splitPane_1.setLeftComponent(scrollPane);
    	
    	JPanel panel = new JPanel();
    	splitPane_1.setRightComponent(panel);
    	
    	JButton btnNeu = new JButton("Neu");
    	btnNeu.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	btnNeu.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	
    	btnNeu.setIcon(new ImageIcon(DashboardView.class.getResource("/tutorial/icons/iconmonstr-plus-circle-thin-16.png")));
    	panel.add(btnNeu);
    	
    	JButton btnNewButton_1 = new JButton("Bearbeiten");
    	btnNewButton_1.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		}
    	});
    	btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
    	btnNewButton_1.setIcon(new ImageIcon(DashboardView.class.getResource("/tutorial/icons/iconmonstr-edit-5-16.png")));
    	btnNewButton_1.setEnabled(false);
    	panel.add(btnNewButton_1);
        
    }
}