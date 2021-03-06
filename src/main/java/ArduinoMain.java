/************************************************************
 * Name:    Katie Isbell
 * Title:   ArduinoMain.java
 * Purpose: Used for creating a new application window.
 *          This is the main file for creating the GUI window
 *          and it includes a custom exit function
 *************************************************************/

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

class ArduinoMain {
	public static JFrame myFrame;
	static JLabel myStatusbar;
	static JToolBar myToolbar;
	static JScrollPane mySettingsManager;
	static Graph myGraph;
	
	public static void main(String[] args) {

		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});	
	}

	/************************************************************
	 * Name: createAndShowGUI desc: Initializes values for our menu bar,
	 * toolbar, status bar, settingsmanager panel, and graph Pre: None Post: Our
	 * GUI is created and displayed Info: For thread safety, this method is
	 * invoked from the event dispatch thread
	 *************************************************************/
	private static void createAndShowGUI() {

		// Create and set up the window
		myFrame = new JFrame(Constants.DEF_APP_TITLE);

		// Create a menuBar, toolBar, settingsManager panel, status bar, and graph
		menuBar menuBar = new menuBar();
		Toolbar toolBar = new Toolbar();
		SettingsManager settingsManager = new SettingsManager();
		StatusBar statusbar = new StatusBar();
		myGraph = new Graph();

		// Initialize values for the toolbar used
		myToolbar = toolBar.createToolBar();
		mySettingsManager = settingsManager.createSettingsManager();
		myStatusbar = statusbar.createStatusBar();

		// Add the toolbar, menu bar, settingsmanager panel, status bar, and graph to main frame
		myFrame.setJMenuBar(menuBar.createMenuBar());
		myFrame.add(myToolbar, BorderLayout.NORTH);
		myFrame.add(mySettingsManager, BorderLayout.WEST);
		myFrame.add(myStatusbar, BorderLayout.SOUTH);
		myFrame.add(myGraph.getGraph(), BorderLayout.CENTER);

		// Initialize x-axis and y-axis labels on the graph
		myGraph.setXAxisLabel(Constants.DEF_XAXIS_LABEL);
		myGraph.setYAxisLabel(Constants.DEF_YAXIS_LABEL);

		// Initialize values for the main frame (size, visibility, ect)
		myFrame.setSize(Constants.DEF_FRAME_WIDTH, Constants.DEF_FRAME_HEIGHT);
		myFrame.setLocationRelativeTo(null); // Window is Centered
		myFrame.setVisible(true);
		myFrame.getContentPane().setBackground(Constants.DEF_BACKGROUND_COLOR);
		setCustomCloseOperation(); // Custom exit function
	}

	/************************************************************
	 * Name: setCustomCloseOperation Pre: User pressed exit button Post: All
	 * existing connections and open ports are closed and we exit the program
	 *************************************************************/
	public static void setCustomCloseOperation() {
		myFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		myFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				exitApplication();
			}
		});
	}

	/************************************************************
	 * Name: exitApplication Pre: User pressed the close or exit button Post:
	 * All existing connections and open ports are closed and we exit the
	 * program
	 *************************************************************/
	public static void exitApplication() {
		// Close the connection if connection exists
		if (ArduinoConnection.isConnected) {
			try {
				ArduinoConnection.disconnect(ArduinoConnection.selectedSerialPort);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Exit the system
		System.exit(0);
	}
}

