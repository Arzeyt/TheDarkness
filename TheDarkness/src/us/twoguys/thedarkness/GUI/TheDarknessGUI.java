package us.twoguys.thedarkness.GUI;

	import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

	import java.awt.Color;
import java.awt.FlowLayout;
	import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

	public class TheDarknessGUI implements  ActionListener{

	    // Definition of global values and items that are part of the GUI.
	int redScoreAmount = 0;
	int blueScoreAmount = 0;
	
	static int maxX=600;
	static int maxY=600;
	
	JPanel titlePanel, selectPanel, scorePanel, buttonPanel, parameterPanel;
	JLabel firstLabel, blueLabel, redScore, blueScore;
	JButton redButton, blueButton, resetButton;
	JList mainList;
	
	String[] main = new String[]{"Effects", "Mobs", "Mirages", "Custom Mirages"};
	//commit test
	
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
	    selectPanel.setLocation(0,10);
	    selectPanel.setSize(maxX,170);
	    totalGUI.add(selectPanel);
	    
	    mainList = new JList(main);
	    mainList.setLocation(10,30);
	    mainList.setSize(maxX/4, 100);
	    mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    mainList.setLayoutOrientation(JList.VERTICAL);
	    selectPanel.add(mainList);
	    
	    //parameter panel
	    parameterPanel = new JPanel();
	    parameterPanel.setLayout(null);
	    parameterPanel.setLocation(0,300);
	    parameterPanel.setSize(maxX,30);
	    totalGUI.add(parameterPanel);
	    
	    blueLabel = new JLabel("Parameters");
	    blueLabel.setLocation(0, 300);
	    blueLabel.setSize(300, 30);
	    blueLabel.setHorizontalAlignment(0);
	    blueLabel.setForeground(Color.blue);
	    parameterPanel.add(blueLabel);
	
	    // score panel
	    scorePanel = new JPanel();
	    scorePanel.setLayout(null);
	    scorePanel.setLocation(10, 40);
	    scorePanel.setSize(260, 30);
	   // totalGUI.add(scorePanel);
	
	    redScore = new JLabel(""+redScoreAmount);
	    redScore.setLocation(0, 0);
	    redScore.setSize(120, 30);
	    redScore.setHorizontalAlignment(0);
	    scorePanel.add(redScore);
	
	    blueScore = new JLabel(""+blueScoreAmount);
	    blueScore.setLocation(130, 0);
	    blueScore.setSize(120, 30);
	    blueScore.setHorizontalAlignment(0);
	    scorePanel.add(blueScore);
	
	    // Creation of a Panel to contain all the JButtons.
	    buttonPanel = new JPanel();
	    buttonPanel.setLayout(null);
	    buttonPanel.setLocation(10, 80);
	    buttonPanel.setSize(260, 70);
	   // totalGUI.add(buttonPanel);
	
	    // We create a button and manipulate it using the syntax we have
	    // used before. Now each button has an ActionListener which posts 
	    // its action out when the button is pressed.
	    
	    redButton = new JButton("Red Score!");
	    redButton.setLocation(0, 0);
	    redButton.setSize(120, 30);
	    redButton.addActionListener(this);
	    buttonPanel.add(redButton);
	
	    blueButton = new JButton("Blue Score!");
	    blueButton.setLocation(130, 0);
	    blueButton.setSize(120, 30);
	    blueButton.addActionListener(this);
	    buttonPanel.add(blueButton);
	
	    resetButton = new JButton("Reset Score");
	    resetButton.setLocation(0, 40);
	    resetButton.setSize(250, 30);
	    resetButton.addActionListener(this);
	    buttonPanel.add(resetButton);
	    
	    totalGUI.setOpaque(true);
	    return totalGUI;
	}
	
	// This is the new ActionPerformed Method.
	// It catches any events with an ActionListener attached.
	// Using an if statement, we can determine which button was pressed
	// and change the appropriate values in our GUI.
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == redButton)
	    {
	        redScoreAmount = redScoreAmount + 1;
	        redScore.setText(""+redScoreAmount);
	    }
	    else if(e.getSource() == blueButton)
	    {
	        blueScoreAmount = blueScoreAmount + 1;
	        blueScore.setText(""+blueScoreAmount);
	    }
	    else if(e.getSource() == resetButton)
	    {
	        redScoreAmount = 0;
	        blueScoreAmount = 0;
	        redScore.setText(""+redScoreAmount);
	        blueScore.setText(""+blueScoreAmount);
	    }
	}
	
	public void listSelection(ListSelectionEvent e){
		//if(e.)
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