/************************************************************
* Name:    Katie Isbell
* Title:   Constants.java
* Purpose: Creates constants for default values used in the
*          application
*************************************************************/
import java.awt.Color;

class Constants {
	/********************************* Arduino Settings ************************/
	public static final int    DEF_BAUD_RATE = 9600;
	
	/********************************* Frame Settings ****************************/
	public static final String DEF_APP_TITLE = "Troika Animated Graphing Software";
	public static final int    DEF_FRAME_WIDTH = 900;
	public static final int    DEF_FRAME_HEIGHT = 600;
	public static final Color  DEF_BACKGROUND_COLOR = Color.white;

	/******************************* Menu Bar Constants **************************/
	public static final String[] DEF_MENUS = { "File", "View", "Connections", "Help" };

	public static final String[] DEF_FILE_MENU_ITEMS = {//"New","Open","Close","Save","Save As","Print",
		"Export to CSV", "Exit" };
	public static final String[] DEF_FILE_MENU_ICONS = {// "resources/new.png", "resources/open.png", "resources/Delete.png",
												//	"resources/save.png", "resources/save.png", "resources/Print.png",
													"resources/Go forward.png", "resources/Exit.png"};

	public static final String[] DEF_EDIT_MENU_ITEMS = {};
	public static final String[] DEF_EDIT_MENU_ICONS = {};
	
	public static final String[] DEF_VIEW_MENU_ITEMS = {"Tool bar", "Settings manager", "Status bar"};
	public static final String[] DEF_VIEW_MENU_ICONS = {"resources/new.png", "resources/open.png", "resources/Delete.png"};
	public static final boolean  DEF_VIEW_MENU_STATE = true;
	
	public static final String[] DEF_SETTINGS_MENU_ITEMS = {"Graph settings"};
	public static final String[] DEF_SETTINGS_MENU_ICONS = {"resources/settings.png"};
	
	public static final String[] DEF_CONNECTION_MENU_ITEMS = {"Connect", "Disconnect"};
	public static final String[] DEF_CONNECTION_MENU_ICONS = {"resources/start.png", "resources/stop.png"};
	
	
	public static final String[] DEF_HELP_MENU_ITEMS = {"About"};
	public static final String[] DEF_HELP_MENU_ICONS = {"resources/Help book 3d.png"};
	
	/************************ Status Bar Constants *********************************/
	public static final String   DEF_STATUSBAR_MESSAGE = "";
	public static final String   DEF_WARNING_NO_CONNECTED_PORTS = "Warning: There are currently no connected serial ports.";
	public static final String   DEF_ERROR_PORT_IN_USE ="Error: Port is currently in use. Disconnect usb cable from the COM port and try again.";
	
	/********************************* Toolbar Constants *************************/
	public static final String[] DEF_ICON_FILES = { 
												//"resources/new.png", "resources/open.png", "resources/save.png",
												//"resources/saveAs.png", "resources/Print.png", "resources/settings.png",
												"resources/Go forward.png", "resources/start.png", "resources/stop.png",
												"resources/Help book 3d.png", "resources/Refresh.png",};
	
	public static final String[] DEF_BUTTON_LABELS = { //"New", "Open", "Save", "Save as", "Print", "Settings",
												"Export to CSV", "Start", "Stop", "About","Rescan serial ports" };
	
	
	public static final boolean  DEF_TOOLBAR_MOVABLE = false;
	public static final String   DEF_TOOLBAR_ABOUT_MESSAGE = "Troika Animated Graphing Software is an open source project intended to \n" +
															"serve as an easier alternative than using the command-line interface. \n\n" +
															"Credits:\n" +
															"RXTX library: http://www.cloudhopper.com/opensource/rxtx/ \n" +
															"Icons:  www.aha-soft.com";
	
	/********************************* Graph Constants ****************************/
	public static final String   DEF_XAXIS_LABEL = "Time";
	public static final String   DEF_YAXIS_LABEL = "Amplitude";
	public static final double   DEF_X_START_VALUE = 0;
	public static final double   DEF_Y_START_VALUE = 0;
	
	/********************************* Series Constants ****************************/
	public static final short    DEF_NUM_SERIES = 1;     // Start with one series
	public static final short    DEF_MAX_NUM_SERIES = 6; // Maximum number of 6 series
	public static final String[] DEF_SERIES_TITLES = {"Series 1:    ", "Series 2: ", "Series 3: ", "Series 4: ", "Series 5: ", "Series 6: "};
	public static final Color[]  DEF_SERIES_COLORS = {Color.BLACK, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.PINK};
	public static final String   DEF_ADDSERIES_LABEL = "Add Series"; // Add new series button
	public static final double   DEF_TIME_INCREMENT = 1;
	public static final String   DEF_TIME_MEASUREMENT[] = {"s", "ms"}; 
	public static final String   DEF_SERIES_SHOW_ICON_FILE = "resources/EyeOpen.png";
	public static final String   DEF_SERIES_HIDE_ICON_FILE = "resources/EyeClosed.png";

	/************************ Settings Manager Constants *************************/
	public static final int      DEF_SETTINGSMANAGER_TEXTBOX_WIDTH = 10; // Default width of each text box	
	public static final String[] DEF_SETTINGSMANAGER_LABELS = {"X Axis Label: ",  // Labels
		"Y Axis Label: ", "X Start Value: ", "Y Start Value: ", "Time Increment: "};
	public static final Object[] DEF_SETTINGSMANAGER_FIELDS = {DEF_XAXIS_LABEL, // Text fields
		DEF_YAXIS_LABEL, DEF_X_START_VALUE, DEF_Y_START_VALUE, DEF_TIME_INCREMENT};
	public static final Color    DEF_SETTINGSMANAGER_BACKGROUNDCOLOR = new Color(220, 220, 244);
	public static final String[] DEF_TIMEMEASUREMENT_BOXITEMS = {"s","ms"};
	public static final String   DEF_SETTINGSMANAGER_ADD_SERIES_BUTTON = "Add";
	public static final String   DEF_SETTINGSMANAGER_REMOVE_SERIES_BUTTON = "Remove";
}
