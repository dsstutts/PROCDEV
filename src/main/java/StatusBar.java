/************************************************************
* Name:    Katie Isbell
* Title:   StatusBar.java
* Purpose: Creates a status bar for the application
*************************************************************/

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;


public class StatusBar {

	static JLabel statusbar;

	/************************************************************
	 * Name: createStatusBar
	 * Pre:  None
	 * Post: Creates a status bar for the GUI
	 *************************************************************/
	JLabel createStatusBar()
	{
		statusbar.setPreferredSize(new Dimension(-1, 22));
		statusbar.setBorder(LineBorder.createGrayLineBorder());
		
		return statusbar;
	}
}
