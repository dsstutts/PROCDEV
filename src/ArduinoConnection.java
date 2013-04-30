/************************************************************
 * Name:    Katie Isbell
 * Title:   ArduinoConnection.java
 * Purpose: Used for establishing a serial connection between
 *          the PC and another device, such as an arduino.
 * Other:   If using Linux you will connect to ttyACM0      
 *************************************************************/

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

// Class: ArduinoConnection
// Desc: Includes all functionality for arduino communication

enum State {
	SERIES, TIMESTAMP, DATA_POINT, SERIES_NUM,
	SERIES_NAME, SETTING_NUM, SETTING_NAME, SETTING_TYPE,
}

public class ArduinoConnection {
	SerialReader sReader;
	static SerialPort serialPort;
	String inputString;
	static boolean isConnected = false;
	static Vector<String> availableSerialPorts = new Vector<String>();
	static String selectedSerialPort; // The connected serial port that the user
										// has selected from the dropdown menu

	public ArduinoConnection() {
		super();
	}

	/************************************************************
	* Name:    listPorts
	* Pre:     None
	* Post:    Finds all available serial ports and initializes vector
	*          availableSerialPorts
	*************************************************************/
	@SuppressWarnings("unchecked")
	public static void setAvailableSerialPorts() {
		
		// Empty our vector and remove all elements in our dropdown menu 
		if(!availableSerialPorts.isEmpty()) {
			if(Toolbar.listSerialPorts_Dropdown.getItemCount() > 0) {
				//Toolbar.listSerialPorts_Dropdown.setSelectedIndex(-1);
				Toolbar.listSerialPorts_Dropdown.removeAllItems(); 
			}
			availableSerialPorts.clear(); 
		}
		 
		 
		// Add new elements to our dropdown menu
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers(); 
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier portIdentifier =	portEnum.nextElement(); // Get connected ports
			if(portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) { //If it is a serial port 
				//System.out.println(portIdentifier.getName());
				availableSerialPorts.add(portIdentifier.getName());
		 
		 
				// Add list of serial ports to dropdown
				Toolbar.listSerialPorts_Dropdown.addItem(portIdentifier.getName());
			}
		}
		 
		 
		// Change dropdown 
		// Select 0th index automatically if there are connected serial ports 
		if (!availableSerialPorts.isEmpty()) {
			StatusBar.statusbar.setText(Constants.DEF_STATUSBAR_MESSAGE);
			Toolbar.listSerialPorts_Dropdown.setSelectedIndex(0); 
		} else {
			StatusBar.statusbar.setText(Constants.DEF_WARNING_NO_CONNECTED_PORTS); 
		}
	}

	/************************************************************
	* Name:    connect
	* Pre:     There is an available serial port and the available
	*          serial port connected to the device is selected from
	*          the dropdown menu
	* Post:    A serial connection is established with the device
	*************************************************************/
	void connect(String portName) throws Exception {
		
		 CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		 
		 // The port must be closed before being accessed 
		 if ( portIdentifier.isCurrentlyOwned() )
		 {
			 StatusBar.statusbar.setText(Constants.DEF_ERROR_PORT_IN_USE); 
			 return;
		 }
		 
		 CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
		 
		 if ( ! (commPort instanceof SerialPort) )
		 {
			 System.out.println("Error: You are not connected to a serial port.");
			 return; 
		 }
		 
		 StatusBar.statusbar.setText("Receiving data...");
	     menuBar.connectionMenuItems[0].setEnabled(false);
	     menuBar.connectionMenuItems[1].setEnabled(true);
	     Toolbar.buttons[1].setIcon(Toolbar.icons[2]);
	     Toolbar.buttons[1].setToolTipText(Constants.DEF_BUTTON_LABELS[2]);
		 
	     serialPort = (SerialPort) commPort;
	     serialPort.setSerialPortParams(Constants.DEF_BAUD_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

		 
		 InputStream in = serialPort.getInputStream(); 
		 sReader = new SerialReader(in); 
		 serialPort.addEventListener(sReader);
		 serialPort.notifyOnDataAvailable(true); 
		 isConnected = true; // Set isConnected flag
	}

	public class SerialReader implements SerialPortEventListener {
		private InputStream in;
		int dataIn, decimalPlaces = 0;
		StringBuilder buf = new StringBuilder();
		char lineID;
		float xValue, yValue, tempValue = 0;;
		boolean decimalExists = false;
		
		static final boolean debug = false;

		State state;
		char data;
		
		int series;
		int timestamp;
		double data_point;

		int series_num;
		String series_name;

		int setting_num;
		String setting_name;
		int setting_type;

		public SerialReader(InputStream in) {
			this.in = in;
			this.state = State.SERIES;
		}

		/************************************************************
		 * Name: serialEvent Pre: A serial connection has been established Post:
		 * Data has been received and displayed on the graph
		 *************************************************************/
		public void serialEvent(SerialPortEvent arg0) {
			try {
				while ((dataIn = in.read()) > -1) {

					data = (char) dataIn;
					if(data == '\n')
						break;

					if (debug)
						System.out.println("Received: " + data);

					if (data == ',' || data == '\r') {
						if (debug)
							System.out.println("Switching state from "+ state.toString());

						switch (state) {
						case SERIES:
							try {
								series = Integer.parseInt(buf.toString());
							}
							catch(NumberFormatException ex){
								if(debug) System.out.println("Failed to parse data, staying in state " + state.toString());
								if(debug) System.out.println("Failed data: " + buf.toString());
								break;
							}
							
							state = State.TIMESTAMP;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out.println("Read value " + series);
							
							break;
							
						case TIMESTAMP:
							try {
								timestamp = Integer.parseInt(buf.toString());
							}
							catch(NumberFormatException ex) {
								if(debug) System.out.println("Failed to parse data, staying in state " + state.toString());
								break;
							}
							
							state = State.DATA_POINT;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out.println("Read value " + timestamp);
							
							break;
							
						case DATA_POINT:
							if(data == ',') {
								if(debug) System.out.println("Invalid data point, skipping...");
								state = State.SERIES;
								break;
							}
							try {
								data_point = Double.parseDouble(buf.toString());
							}
							catch(NumberFormatException ex) {
								if(debug) System.out.println("Failed to parse data, staying in state " + state.toString());
								break;
							}
							
							if(debug) System.out.println("Data point: " + series + " " + ((double)timestamp)/1000 + " " + data_point);
							//TODO: Breaks if 'series' isn't a valid series 
							ArduinoMain.myGraph.m_seriesList[series].AddDataPoint(((double)timestamp)/1000 ,data_point);
							
							state = State.SERIES;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out.println("Read value " + data_point);
							
							break;
							
						case SERIES_NUM:
							try {
								series_num = Integer.parseInt(buf.toString());
							}
							catch(NumberFormatException ex) {
								if(debug) System.out.println("Failed to parse data, staying in state " + state.toString());
								break;
							}
							
							state = State.SERIES_NAME;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out.println("Read value " + series_num);
							
							break;
							
						case SERIES_NAME:
							if(data == ',') {
								if(debug) System.out.println("Invalid Series data, skipping...");
								state = State.SERIES;
								break;
							}
							series_name = buf.toString();
							state = State.SERIES;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out.println("Read value " + series_name);
							
							break;
							
						case SETTING_NUM:
							try {
								setting_num = Integer.parseInt(buf.toString());
							}
							catch(NumberFormatException ex) {
								if(debug) System.out.println("Failed to parse data, staying in state " + state.toString());
								break;
							}
							
							state = State.SETTING_NAME;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out.println("Read value " + setting_num);
							
							break;
							
						case SETTING_NAME:
							setting_name = buf.toString();					
							state = State.SETTING_TYPE;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out
										.println("Read value " + setting_name);
							
							break;
							
						case SETTING_TYPE:
							if(data == ',') {
								if(debug) System.out.println("Invalid setting data, skippping...");
								state = State.SERIES;
								break;
							}
							try {
								setting_type = Integer.parseInt(buf.toString());
							}
							catch(NumberFormatException ex) {
								if(debug) System.out.println("Failed to parse data, staying in state " + state.toString());
								break;
							}
							
							state = State.SERIES;
							
							if (debug)
								System.out.println("to " + state.toString());
							if (debug)
								System.out
										.println("Read value " + setting_type);
							
							break;
							
						default:
							state = State.SERIES;
							
							if (debug)
								System.out.println("INVALID STATE");
							if (debug)
								System.out.println("to " + state.toString());
							
							break;
						}

						buf = new StringBuilder();
					}
					else {
						buf.append(data);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	public void printValue(char lineID, int pointVal) {
		switch (lineID) {
		case 'A':
			break;
		case 'B':
			break;
		case 'C':
			break;
		case 'D':
			break;
		}
		System.out.println("Line " + lineID + " has value " + pointVal);
	}

	/************************************************************
	 * Name: disConnect Pre: A connection is established Post: The connection is
	 * closed, the serial port is closed, and isConnected is set to false
	 *************************************************************/
	public static void disconnect(String portName) throws Exception {
		if (isConnected) {
			// preparing inStream and outStream to deactivate before close
			serialPort.removeEventListener();
			serialPort.close();
			isConnected = false;

			menuBar.connectionMenuItems[0].setEnabled(true); // connect button (menubar)
			menuBar.connectionMenuItems[1].setEnabled(false); // disconnect button (menubar)
			StatusBar.statusbar.setText("");
			// Change the icon for disconnecting in toolbar
			Toolbar.buttons[1].setIcon(Toolbar.icons[1]);
			Toolbar.buttons[1].setToolTipText(Constants.DEF_BUTTON_LABELS[1]);
		}
	}

}
