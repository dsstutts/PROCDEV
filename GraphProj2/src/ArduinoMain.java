/************************************************************
 * Name:    Katie Isbell
 * Title:   ArduinoMain.java
 * Purpose: Used for creating a new application window.
 *          This is the main file for creating the GUI window
 *          and it includes a custom exit function
 *************************************************************/

import info.monitorenter.gui.chart.Chart2D;

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

		// Create a menuBar, toolBar, settingsManager panel, and status bar
		menuBar menuBar = new menuBar();
		Toolbar toolBar = new Toolbar();
		SettingsManager settingsManager = new SettingsManager();
		StatusBar statusbar = new StatusBar();
		myGraph = new Graph();

		myToolbar = toolBar.createToolBar();
		mySettingsManager = settingsManager.createSettingsManager();
		myStatusbar = statusbar.createStatusBar();
		// super ( Constants.DEF_APP_TITLE );

		myFrame.setJMenuBar(menuBar.createMenuBar());
		myFrame.add(myToolbar, BorderLayout.NORTH);
		myFrame.add(mySettingsManager, BorderLayout.WEST);
		myFrame.add(myStatusbar, BorderLayout.SOUTH);

		myFrame.add(myGraph.getGraph(), BorderLayout.CENTER);

		myGraph.setXAxisLabel("This is a really long label for an x axis asdfasodfjasdfjas;ldjfalsdfjslkdj");

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
				ArduinoConnection.disconnect(Constants.DEF_PORT_NAME);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Exit the system
		System.out.println("BYE!");
		System.exit(0);
	}
}
