/************************************************************
 * Name:    Katie Isbell
 * Title:   Toolbar.java
 * Purpose: Creates a tool bar for the application
 *************************************************************/

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

public class Toolbar implements ActionListener {
	JToolBar toolBar = new JToolBar();
	static ImageIcon[] icons = new ImageIcon[Constants.DEF_ICON_FILES.length];
	static JButton[] buttons = new JButton[Constants.DEF_BUTTON_LABELS.length];
	@SuppressWarnings("rawtypes")
	static JComboBox listSerialPorts_Dropdown;

	
	/************************************************************
	* Name:    createToolBar
	* Pre:     None
	* Post:    All toolbar buttons/icons are set in place
	*************************************************************/
	@SuppressWarnings("rawtypes")
	JToolBar createToolBar()
	{

		// Add toolbar buttons onto the toolbar
		for (int i = 0; i < Constants.DEF_BUTTON_LABELS.length; ++i) {
			icons[i] = new ImageIcon(Constants.DEF_ICON_FILES[i]);
			buttons[i] = new JButton(icons[i]);
			buttons[i].setToolTipText(Constants.DEF_BUTTON_LABELS[i]);
			buttons[i].addActionListener(this);
			
			
			buttons[i].setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			//toolBar.add(Box.createHorizontalGlue());
		
			// We are not adding a separate button for disconnecting
			if (i != 2)
				toolBar.add(buttons[i]);
			
			if (i == 0)
			{
				toolBar.addSeparator();
				toolBar.addSeparator();
			}
			
			// Create our listSerialPorts_Dropdown menu
			if (i == 3)
			{
				StatusBar.statusbar = new JLabel();
				listSerialPorts_Dropdown = new JComboBox();
				
				// Get the list of connected serial ports
				ArduinoConnection.setAvailableSerialPorts();
				listSerialPorts_Dropdown.setToolTipText("Select serial port");
				//listSerialPorts_Dropdown.setMaximumSize(new Dimension(200, 50));
				toolBar.add(listSerialPorts_Dropdown);
			}

		}
		
		toolBar.setFloatable(Constants.DEF_TOOLBAR_MOVABLE);
		return toolBar;
	}


	/************************************************************
	* Name:    actionPerformed
	* Pre:     A button was pressed triggering the action
	* Post:    Proposed action was performed
	*************************************************************/
	public void actionPerformed(ActionEvent event) {
		/*if (event.getSource() == buttons[0]) {
			System.out.println("You Pressed New!");
		} else if (event.getSource() == buttons[1]) {
			System.out.println("You Pressed Open!");
		} else if (event.getSource() == buttons[2]) {
			System.out.println("You Pressed Save!");
		} else if (event.getSource() == buttons[3]) {
			System.out.println("You Pressed Save as!");
		}
		// Print button
		else if (event.getSource() == buttons[4]) {
			System.out.println("You Pressed Print!");

		}else if (event.getSource() == buttons[5]) {
			System.out.println("You Pressed Settings!");
		}
*/
		if (event.getSource() == buttons[0]) {
			System.out.println("You Pressed Export!");
		}
			
		// Buttons 1 & 2: Connecting to the device
		else if (event.getSource() == buttons[1]) {
			if (ArduinoConnection.isConnected == false) // We are not connected
			{
				if (ArduinoConnection.availableSerialPorts.size() != 0) // We can connect to a serial port
				{
					// Establish a connection to the serial port
					ArduinoConnection myArduino = new ArduinoConnection();
					try {
						// Set selected serial port
						ArduinoConnection.selectedSerialPort =(String)listSerialPorts_Dropdown.getSelectedItem();
						
						// Connect to selected serial port
						myArduino.connect(ArduinoConnection.selectedSerialPort);
					} catch (Exception e) {
						
						e.printStackTrace();
					}

				}
			else {
					StatusBar.statusbar.setText("Error: No serial ports are available");
				}
			}
			// We are currently connected
			else {
				try {
					ArduinoConnection.disconnect(ArduinoConnection.selectedSerialPort);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
			}
		}
		// Help
		else if (event.getSource() == buttons[3]) {
			JOptionPane.showMessageDialog(ArduinoMain.myFrame, Constants.DEF_TOOLBAR_ABOUT_MESSAGE,"About",JOptionPane.INFORMATION_MESSAGE);
		}

		// Refresh serial ports list
		else if (event.getSource() == buttons[4]) {
			ArduinoConnection.setAvailableSerialPorts();
		}

	}
}