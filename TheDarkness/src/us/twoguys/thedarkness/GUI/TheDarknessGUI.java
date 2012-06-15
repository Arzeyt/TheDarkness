package us.twoguys.thedarkness.GUI;

	import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

	import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
	import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

	public class TheDarknessGUI{

	    // Definition of global values and items that are part of the GUI.
	int redScoreAmount = 0;
	int blueScoreAmount = 0;
	
	static int maxX=600;
	static int maxY=600;
	
	JPanel titlePanel, selectPanel, parameterPanel, subCategoryPanel;
	JLabel firstLabel;
	JList mainList, subCategoryList;
	JComboBox subCategoryBox;
	JScrollPane subScrollPane;
	
	String[] main = new String[]{"Effects", "Mobs", "Mirages"};
	String[] effects = new String[]{"Life Drain","Potion", "Precipitation", "Time", "Toch Consume"};
	String[] mobs = new String[]{"Chicken", "Cow", "Mooshroom", "Ocelot", "Pig", "Sheep", "Squid","Villager","Endermen",
			"Wolf", "Zombie Pigman", "Blaze", "Cave Spider", "Creeper", "Ghast", "Magma Cube", "Silver Fish", "Skeleton",
			"Slime", "Spider", "Zombie", "Snow Golem", "Iron Golem"};
	String[] mirages = new String[]{"Spread"};
	String[] customMirages;
	
	
	public JPanel createContentPane(){
	
	    // We create a bottom JPanel to place everything on.
	    JPanel totalGUI = new JPanel();
	    totalGUI.setLayout(null);
	
	    // title panel
	    titlePanel = new JPanel();
	    titlePanel.setLayout(null);
	    titlePanel.setLocation(0, 0);
	    titlePanel.setSize(maxX, 30);
	    totalGUI.add(titlePanel);
	
	    firstLabel = new JLabel("Select an category to add");
	    firstLabel.setLocation(0, 0);
	    firstLabel.setSize(150, 30);
	    firstLabel.setHorizontalAlignment(0);
	    firstLabel.setForeground(Color.BLACK);
	    titlePanel.add(firstLabel);
	
	    //List panel
	    selectPanel = new JPanel();
	    selectPanel.setLayout(null);
	    selectPanel.setLocation(0,22);
	    selectPanel.setSize(maxX,170);
	    totalGUI.add(selectPanel);
	    
	    mainList = new JList(main);
	    mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    mainList.setLayoutOrientation(JList.VERTICAL);
	    mainList.addListSelectionListener(new MainListSelectionListener(this));
		
		JScrollPane mainScrollPane = new JScrollPane(mainList);
		mainScrollPane.setLocation(10,30);
		mainScrollPane.setSize(140,100);
		selectPanel.add(mainScrollPane);

		
	    //parameter panel
	    parameterPanel = new JPanel();
	    parameterPanel.setLayout(null);
	    parameterPanel.setLocation(0,300);
	    parameterPanel.setSize(maxX,30);
	    totalGUI.add(parameterPanel);
	    
	    totalGUI.setOpaque(true);
	    return totalGUI;
	}
	
	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            createAndShowGUI();
	        }
	    });
	}
	
	private static void createAndShowGUI() {
	
	    JFrame.setDefaultLookAndFeelDecorated(false);
	    JFrame frame = new JFrame("The Darkness Config Helper");
	
	    //Create and set up the content pane.
	        TheDarknessGUI demo = new TheDarknessGUI();
	        frame.setContentPane(demo.createContentPane());
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(maxX, maxY);
	        frame.setVisible(true);
	    }
	}