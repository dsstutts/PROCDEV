/************************************************************
* Name:    Katie Isbell
* Title:   menuBar.java
* Purpose: Creates a menu bar for the application
*************************************************************/
import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
public class menuBar  implements ActionListener{
	JMenuBar bar = new JMenuBar();
	JMenu menus[] = new JMenu[Constants.DEF_MENUS.length];
	JMenuItem fileMenuItems[] = new JMenuItem[Constants.DEF_FILE_MENU_ITEMS.length];
	JMenuItem editMenuItems[] = new JMenuItem[Constants.DEF_EDIT_MENU_ITEMS.length];
	JCheckBoxMenuItem viewMenuItems[] = new JCheckBoxMenuItem[Constants.DEF_VIEW_MENU_ITEMS.length];
	JMenuItem settingsMenuItems[] = new JMenuItem[Constants.DEF_SETTINGS_MENU_ITEMS.length];
	static JMenuItem connectionMenuItems[] = new JMenuItem[Constants.DEF_CONNECTION_MENU_ITEMS.length];
	JMenuItem helpMenuItems[] = new JMenuItem[Constants.DEF_HELP_MENU_ITEMS.length];
	
	/************************************************************
	* Name:    createMenuBar
	* Pre:     None
	* Post:    A menu bar is created with menu items and submenu
	*          items
	*************************************************************/
	JMenuBar createMenuBar()
	{	
		// Add Menus to the menu bar (File, Edit, Options, Help...)
		for (int i = 0; i < Constants.DEF_MENUS.length; i++)
		{
			menus[i] = new JMenu(Constants.DEF_MENUS[i]);
			bar.add(menus[i]);
		}
		
		// FILE menu - Add menu items to the file menu
		for (int i = 0; i < Constants.DEF_FILE_MENU_ITEMS.length; i++)
		{
			fileMenuItems[i] = new JMenuItem(Constants.DEF_FILE_MENU_ITEMS[i], new ImageIcon(Constants.DEF_FILE_MENU_ICONS[i]));
			menus[0].add(fileMenuItems[i]);
			fileMenuItems[i].addActionListener(this);
			if ( i == 2 || i == 4 || i == 6)
			{
				menus[0].addSeparator();
			}
		}
		
		/* EDIT menu - Add menu items to the edit menu
		for (int i = 0; i < Constants.DEF_EDIT_MENU_ITEMS.length; i++)
		{
			editMenuItems[i] = new JMenuItem(Constants.DEF_EDIT_MENU_ITEMS[i], new ImageIcon(Constants.DEF_EDIT_MENU_ICONS[i]));
			menus[1].add(editMenuItems[i]);	
		}
		*/
		// VIEW menu - Add menu items to the view menu
		for (int i = 0; i < Constants.DEF_VIEW_MENU_ITEMS.length; i++)
		{
			viewMenuItems[i] = new JCheckBoxMenuItem(Constants.DEF_VIEW_MENU_ITEMS[i], Constants.DEF_VIEW_MENU_STATE);
			viewMenuItems[i].addActionListener(this);
			menus[1].add(viewMenuItems[i]);
		}
		/*
		// SETTINGS menu - Add menu items to the settings menu
		for (int i = 0; i < Constants.DEF_SETTINGS_MENU_ITEMS.length; i++)
		{
			settingsMenuItems[i] = new JMenuItem(Constants.DEF_SETTINGS_MENU_ITEMS[i], new ImageIcon(Constants.DEF_SETTINGS_MENU_ICONS[i]));
			settingsMenuItems[i].addActionListener(this);
			menus[3].add(settingsMenuItems[i]);
		}
		*/
		
		// Connection menu - Add menu items to the connection menu
		for (int i = 0; i < Constants.DEF_CONNECTION_MENU_ITEMS.length; i++)
		{
			connectionMenuItems[i] = new JMenuItem(Constants.DEF_CONNECTION_MENU_ITEMS[i], new ImageIcon(Constants.DEF_CONNECTION_MENU_ICONS[i]));
			connectionMenuItems[i].addActionListener(this);
			menus[2].add(connectionMenuItems[i]);
		}
		connectionMenuItems[1].setEnabled(false); // Disable disconnect

		// HELP menu - Add menu items to the help menu
		for (int i = 0; i < Constants.DEF_HELP_MENU_ITEMS.length; i++)
		{
			helpMenuItems[i] = new JMenuItem(Constants.DEF_HELP_MENU_ITEMS[i], new ImageIcon(Constants.DEF_HELP_MENU_ICONS[i]));
			helpMenuItems[i].addActionListener(this);
			menus[3].add(helpMenuItems[i]);
		}
		return bar;
	}

	/************************************************************
	* Name:    actionPerformed
	* Pre:     User pressed one of the menu items, triggering an
	*          action
	* Post:    A corresponding action was performed
	*************************************************************/
	public void actionPerformed(ActionEvent event) {	
		// File Menu
		
		// Export
		if (event.getSource() == fileMenuItems[0])
		{
			JFileChooser fc = new JFileChooser();
			int ret = fc.showSaveDialog(null);
			if (ret == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				CSVExporter exporter = new CSVExporter(ArduinoMain.myGraph.m_seriesList);
				try {
					exporter.export(file.getPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		/*else if (event.getSource() == fileMenuItems[1])
		{
			System.out.println("You Pressed Open");
		}
		else if (event.getSource() == fileMenuItems[2])
		{
			System.out.println("You Pressed Close");
		}
		else if (event.getSource() == fileMenuItems[3])
		{
			System.out.println("You Pressed Save");
		}
		else if (event.getSource() == fileMenuItems[4])
		{
			System.out.println("You Pressed Save As");
		}
		else if (event.getSource() == fileMenuItems[5])
		{
			System.out.println("You Pressed Print");
		}
		else if (event.getSource() == fileMenuItems[6])
		{
			System.out.println("You Pressed Export");
		}*/
		else if (event.getSource() == fileMenuItems[1]) // Exit
		{
			ArduinoMain.exitApplication();
		}
		// View Menu - Used to show/hide elements
		else if (event.getSource() == viewMenuItems[0]) // Toolbar
		{
			if (viewMenuItems[0].getState() == true){
				ArduinoMain.myToolbar.setVisible(true);
				ArduinoMain.myGraph.m_graph.updateUI();
			}
			else {
				ArduinoMain.myToolbar.setVisible(false);
				ArduinoMain.myGraph.m_graph.updateUI();
			}
		}

		else if (event.getSource() == viewMenuItems[1]) // Settings manager
		{
			if (viewMenuItems[1].getState() == true)
			{
				ArduinoMain.mySettingsManager.setVisible(true);
				ArduinoMain.myGraph.m_graph.updateUI();
			}
			else{
				ArduinoMain.mySettingsManager.setVisible(false);
				ArduinoMain.myGraph.m_graph.updateUI();
			}
		}
		
		else if (event.getSource() == viewMenuItems[2]) // Status bar
		{
			if (viewMenuItems[2].getState() == true) {
				ArduinoMain.myStatusbar.setVisible(true);
				ArduinoMain.myGraph.m_graph.updateUI();
				}
			else {
				ArduinoMain.myStatusbar.setVisible(false);
				ArduinoMain.myGraph.m_graph.updateUI();
			}
		}
		
		// Connection Menu
		else if (event.getSource() == connectionMenuItems[0])
		{
			if (ArduinoConnection.availableSerialPorts.size() != 0) // We can connect to a serial port
			{
				// Establish a connection to the serial port
				ArduinoConnection myArduino = new ArduinoConnection();
				try {
					// Set selected serial port
					ArduinoConnection.selectedSerialPort =(String)Toolbar.listSerialPorts_Dropdown.getSelectedItem();
					
					// Connect to selected serial port
					myArduino.connect(ArduinoConnection.selectedSerialPort);
				} catch (Exception e) {	e.printStackTrace(); }
			}
			else {
				StatusBar.statusbar.setText("Error: No serial ports are available");
			}
		}
		else if (event.getSource() == connectionMenuItems[1])
		{
			try {
				ArduinoConnection.disconnect(ArduinoConnection.selectedSerialPort);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// Settings Menu
		/*else if (event.getSource() == settingsMenuItems[0])
		{
			System.out.println("Pressed Settings");
		}*/
		
		
		// Help Menu - Used to provide information about product
		else if (event.getSource() == helpMenuItems[0]) // About
		{
			JOptionPane.showMessageDialog(ArduinoMain.myFrame, Constants.DEF_TOOLBAR_ABOUT_MESSAGE,"About",JOptionPane.INFORMATION_MESSAGE);
		}
	}
}