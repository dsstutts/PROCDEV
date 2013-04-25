/************************************************************
* Name:    Katie Isbell
* Title:   ArduinoConnection.java
* Purpose: Used for establishing a serial connection between
*          the PC and another device, such as an arduino.
*************************************************************/

/*import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;*/

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public class ArduinoConnection {
	//SerialReader sReader;
	//static SerialPort serialPort;
	String inputString;
    static boolean isConnected = false;
    static Vector<String> availableSerialPorts = new Vector<String>();
    static String connectedSerialPort = Constants.DEF_PORT_NAME;
	
	public ArduinoConnection() {
		//super();
	}
	

	/************************************************************
	* Name:    listPorts
	* Pre:     None
	* Post:    Finds all available serial ports and initializes vector
	*          availableSerialPorts
	*************************************************************/
	/*public static void setAvailableSerialPorts()
    {
		/* Empty our vector and remove all elements in our dropdown menu
		if (!availableSerialPorts.isEmpty()) 
		{	
			if(Toolbar.listSerialPorts_Dropdown.getItemCount() > 0)
			{
				//Toolbar.listSerialPorts_Dropdown.setSelectedIndex(-1);
				Toolbar.listSerialPorts_Dropdown.removeAllItems();
			}
			availableSerialPorts.clear();
		}
		
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while ( portEnum.hasMoreElements() ) 
        {
        	CommPortIdentifier portIdentifier = portEnum.nextElement(); // Get connected ports
        	if (portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) // If it is a serial port
            {
            	//System.out.println(portIdentifier.getName());
        		availableSerialPorts.add(portIdentifier.getName());
        		
        		// Add list of serial ports to dropdown
        		Toolbar.listSerialPorts_Dropdown.addItem(portIdentifier.getName());
            }
        }

		
		// Change dropdown
		// Select 0th index automatically if there are connected serial ports
		if (!availableSerialPorts.isEmpty())
		{
			StatusBar.statusbar.setText(Constants.DEF_STATUSBAR_MESSAGE);
			Toolbar.listSerialPorts_Dropdown.setSelectedIndex(0);
		}
		else
		{
			StatusBar.statusbar.setText(Constants.DEF_WARNING_NO_CONNECTED_PORTS);
		}
    }*/
	


	/************************************************************
	* Name:    connect
	* Pre:     There is an available serial port and the available
	*          serial port connected to the device is selected from
	*          the dropdown menu
	* Post:    A serial connection is established with the device
	*************************************************************/
	void connect ( String portName ) throws Exception
    {
        /*CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        
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
        
        serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams(Constants.DEF_BAUD_RATE,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

        InputStream in = serialPort.getInputStream();
        sReader = new SerialReader(in);
        serialPort.addEventListener(sReader);
        serialPort.notifyOnDataAvailable(true);
        isConnected = true;  // Set isConnected flag*/
    }

	/*public class SerialReader implements SerialPortEventListener {
		/*private InputStream in;
	    int dataIn, decimalPlaces = 0;
		String buffer_string;
		char lineID;
		float xValue, yValue, tempValue = 0;;
		boolean decimalExists = false;

		public SerialReader(InputStream in) {
			this.in = in;
		}*/

		/************************************************************
		* Name:    serialEvent
		* Pre:     A serial connection has been established
		* Post:    Data has been received and displayed on the graph
		*************************************************************/
		/*public void serialEvent(SerialPortEvent arg0) {
			/*try {
				while ((dataIn = in.read()) > -1) {
					if (dataIn == '\n')
						break;
					
					if (Character.isDigit((char)dataIn)) {
						if (tempValue != 0)
							tempValue *= 10;
								
						tempValue += Character.getNumericValue( (char)dataIn );	
						
						if (decimalExists)
							decimalPlaces++;
					}
					
					else if ((char)dataIn == '.')
					{
						decimalExists = true;
					}
				
					
					else if ((char)dataIn == ',')
					{
						xValue = tempValue; // Store the value of X
						for (int i = 0; i < decimalPlaces; i++)
							xValue /=10;
						tempValue = 0;
						decimalExists = false;
						decimalPlaces = 0;
					}
				
					else if(Character.isLetter((char)dataIn) ) {
						yValue = tempValue;  // Store the value of Y
						for (int i = 0; i < decimalPlaces; i++)
							yValue /=10;
						
						decimalExists = false;
						lineID = (char)dataIn;	
					    System.out.println("(" + xValue + ", " + yValue + ") from sensor " + lineID);
						tempValue = 0;
						decimalPlaces = 0;
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
			}
		}*/

	public void printValue(char lineID, int pointVal) {
		/*switch (lineID) {
		  case 'A':
		    break;
		  case 'B':
			  break;
		  case 'C':
			  break;
		  case 'D':
			  break;
		}
	    System.out.println("Line " + lineID + " has value " + pointVal);*/
	}

	
	/************************************************************
	* Name:    disConnect
	* Pre:     A connection is established
	* Post:    The connection is closed, the serial port is closed,
	*          and isConnected is set to false
	*************************************************************/
	public static void disconnect(String portName) throws Exception {	
		/*if (isConnected)
		{
			// preparing inStream and outStream to deactivate before close
			serialPort.removeEventListener();
			serialPort.close();
			isConnected = false;
		}*/
	}

}

