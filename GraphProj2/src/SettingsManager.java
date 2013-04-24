/************************************************************
 * Name:    Katie Isbell
 * Title:   SettingsManager.java
 * Purpose: Holds a form for changing various graph settings
 *          such as graph title, x and y axis labels, and so
 *          forth.
 *************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class SettingsManager extends JFrame implements PropertyChangeListener, ActionListener {
	JPanel settingsManagerPanel = new JPanel(); // Holds both panels
	JPanel graphPanel = new JPanel();
	JPanel seriesPanel = new JPanel();

	// Values for text fields
	GridBagConstraints gridConstraints = new GridBagConstraints();

	JLabel header = new JLabel("Graph Settings:");
	JLabel timeMeasurementLabel = new JLabel("Time Measurement: ");
	JComboBox timeMeasurement_Dropdown = new JComboBox(
			Constants.DEF_TIMEMEASUREMENT_BOXITEMS);
	Font labelFont = new Font("Times New Roman", Font.BOLD, 14);
	Font headerFont = new Font("Serif", Font.BOLD, 16);

	JLabel[] settingsManagerLabels = new JLabel[Constants.DEF_SETTINGSMANAGER_LABELS.length];
	JFormattedTextField[] settingsManagerFields = new JFormattedTextField[Constants.DEF_SETTINGSMANAGER_FIELDS.length];

	JFormattedTextField[] seriesFields = new JFormattedTextField[Constants.DEF_SERIES_TITLES.length];
	JLabel[] seriesLabels = new JLabel[Constants.DEF_SERIES_TITLES.length];
	JButton seriesColorButton[] = new JButton[Constants.DEF_SERIES_TITLES.length];
	JButton seriesShowHideButton[] = new JButton[Constants.DEF_SERIES_TITLES.length];
	ImageIcon showIcon = new ImageIcon(Constants.DEF_SERIES_SHOW_ICON_FILE);
	ImageIcon hideIcon = new ImageIcon(Constants.DEF_SERIES_HIDE_ICON_FILE);

	JButton addSeriesButton;
	JButton removeSeriesButton;

	public SettingsManager() {
		graphPanel.setBackground(Constants.DEF_SETTINGSMANAGER_BACKGROUNDCOLOR);
		graphPanel.setBorder(new EmptyBorder(5, 15, 15, 15));
		graphPanel.setLayout(new GridBagLayout());
		gridConstraints.gridwidth = 2; // Header will span 2 columns
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		
		// Add our Header: "Settings Manager"
		header.setFont(headerFont);
		graphPanel.add(header, gridConstraints);

		gridConstraints.anchor = GridBagConstraints.WEST; // Left align cell contents
		gridConstraints.gridwidth = 1; // Labels and fields each span 1 column

		// Initialize labels and fields
		// Lay out the labels and text fields in an nx2 Grid
		for (int i = 0; i < Constants.DEF_SETTINGSMANAGER_LABELS.length; i++) {
			gridConstraints.gridy++;
			gridConstraints.gridx = 0;

			// Add labels for text fields
			settingsManagerLabels[i] = new JLabel(Constants.DEF_SETTINGSMANAGER_LABELS[i]);
			settingsManagerLabels[i].setFont(labelFont);
			graphPanel.add(settingsManagerLabels[i], gridConstraints);

			// Add text fields
			gridConstraints.gridx = 1;
			settingsManagerFields[i] = new JFormattedTextField(Constants.DEF_SETTINGSMANAGER_FIELDS[i]);
			settingsManagerFields[i].setColumns(Constants.DEF_SETTINGSMANAGER_TEXTBOX_WIDTH); // Set textbox width
			settingsManagerFields[i].addPropertyChangeListener("value", this);

			graphPanel.add(settingsManagerFields[i], gridConstraints); // Add to graphPanel
		}

		/**************** Add Time measurement dropdown menu **********************/
		gridConstraints.gridy++;
		gridConstraints.gridx = 0;
		timeMeasurementLabel.setFont(labelFont);
		graphPanel.add(timeMeasurementLabel, gridConstraints); // Label
		gridConstraints.gridx++;
		timeMeasurement_Dropdown.setPreferredSize(new Dimension(115, 20));
		graphPanel.add(timeMeasurement_Dropdown, gridConstraints);
		timeMeasurement_Dropdown.addActionListener(this);

		// SERIES PANEL
		JPanel seriesPanel = new JPanel();
		seriesPanel.setBorder(new EmptyBorder(5, 15, 15, 15));

		seriesPanel.setLayout(new GridBagLayout());
		seriesPanel.setBackground(Constants.DEF_SETTINGSMANAGER_BACKGROUNDCOLOR);

		
		/**************************Section for Modifying Series ************************/
		// Add series title
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.gridwidth = 4; // series Label will span 4 columns
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		JLabel seriesLabel = new JLabel("Series:");
		seriesLabel.setFont(new Font("Serif", Font.BOLD, 16));
		seriesPanel.add(seriesLabel, gridConstraints);

		gridConstraints.anchor = GridBagConstraints.WEST;
		gridConstraints.gridwidth = 1;
		gridConstraints.gridy++;

		// TODO: Working here

		// Buttons to Add / Remove Series
		addSeriesButton = new JButton(Constants.DEF_SETTINGSMANAGER_ADD_SERIES_BUTTON);
		addSeriesButton.addActionListener(this);
		seriesPanel.add(addSeriesButton, gridConstraints);

		gridConstraints.gridx++;
		removeSeriesButton = new JButton(Constants.DEF_SETTINGSMANAGER_REMOVE_SERIES_BUTTON);
		removeSeriesButton.addActionListener(this);
		removeSeriesButton.setEnabled(false);
		seriesPanel.add(removeSeriesButton, gridConstraints);
		
		// "Series" Label, Change Title, Color, Hide/Show Series
		for (int i = 0; i < Constants.DEF_SERIES_TITLES.length; i++) {
			gridConstraints.gridy++;
			gridConstraints.gridx = 0;
			
			// "Series" Label
			seriesLabels[i] = new JLabel(Constants.DEF_SERIES_TITLES[i]);
			seriesLabels[i].setFont(labelFont);
			seriesPanel.add(seriesLabels[i], gridConstraints);
			gridConstraints.gridx++;

			// Series change Title
			seriesFields[i] = new JFormattedTextField(Constants.DEF_SERIES_TITLES[i]);
			seriesFields[i].setColumns(Constants.DEF_SETTINGSMANAGER_TEXTBOX_WIDTH);
			seriesFields[i].addPropertyChangeListener("value", this);
			seriesPanel.add(seriesFields[i], gridConstraints);
			gridConstraints.gridx++;
			
			// Series change Color
			seriesColorButton[i] = new JButton("");
			seriesColorButton[i].setBackground(Constants.DEF_SERIES_COLORS[i]);
			seriesColorButton[i].setPreferredSize(new Dimension(25, 25));
			seriesColorButton[i].addActionListener(this);
			seriesColorButton[i].setToolTipText("Set series color");
			seriesPanel.add(seriesColorButton[i], gridConstraints);
			gridConstraints.gridx++;
			
			// Hide/Show Series on graph
			seriesShowHideButton[i] = new JButton(showIcon);
			seriesShowHideButton[i].setToolTipText("Show/Hide Series");
			seriesShowHideButton[i].addActionListener(this);
			seriesShowHideButton[i].setBorder(BorderFactory.createEmptyBorder());
			seriesShowHideButton[i].setBackground(Constants.DEF_SETTINGSMANAGER_BACKGROUNDCOLOR);
			seriesShowHideButton[i].setPreferredSize(new Dimension(40, 30));
			seriesPanel.add(seriesShowHideButton[i], gridConstraints);
					
			// Set Visibility of series label, changeTitle box, and color
			if (i >= Constants.DEF_NUM_SERIES) // 1
			{
				seriesLabels[i].setVisible(false);
				seriesFields[i].setVisible(false);
				seriesColorButton[i].setVisible(false);
				seriesShowHideButton[i].setVisible(false);
			}
			
			
			
		}
		

		/***************************************************************************************/
		// SETTINGS MANAGER PANEL
		settingsManagerPanel.setBackground(Constants.DEF_SETTINGSMANAGER_BACKGROUNDCOLOR);
		settingsManagerPanel.setLayout(new GridBagLayout());
		gridConstraints.gridx = 0;
		gridConstraints.gridy = 0;
		settingsManagerPanel.add(graphPanel, gridConstraints);
		gridConstraints.gridy++;
		settingsManagerPanel.add(seriesPanel, gridConstraints);
		gridConstraints.gridy++;
		// In order to push all components to the top of our screen, we alter
		// the constraint of the component in the bottom most row, setting its
		// weight to 1.
		gridConstraints.weighty = 1;
		JLabel footer = new JLabel("");
		settingsManagerPanel.add(footer, gridConstraints);
	}

	/************************************************************
	 * Name: actionPerformed Pre: Either we changed a dropdown menu item or the
	 * color chooser button was pressed for changing the color of a series Post:
	 * A new window appears allowing the user to select a precise color for
	 * their graph. If the color was changed, the button's color as well as the
	 * corresponding series values on the graph
	 *************************************************************/
	public void actionPerformed(ActionEvent event) {
		// Time Measurement Dropdown
		if (event.getSource() == timeMeasurement_Dropdown) {
			if (timeMeasurement_Dropdown.getSelectedItem() == "s") {
				System.out.println("You selected s");
			} else if (timeMeasurement_Dropdown.getSelectedItem() == "ms") {
				System.out.println("You selected ms");
			}
		}
		// Remove Series Button
		else if (event.getSource() == removeSeriesButton) {
			int numSeries = ArduinoMain.myGraph.getNumSeries();
			
			// Decrement the number of series
			if (numSeries == Constants.DEF_NUM_SERIES + 1)
				removeSeriesButton.setEnabled(false);
			else if (numSeries == Constants.DEF_MAX_NUM_SERIES)
				addSeriesButton.setEnabled(true);
			// Set visible old series row
			seriesLabels[numSeries-1].setVisible(false);
			seriesFields[numSeries-1].setVisible(false);
			seriesColorButton[numSeries-1].setVisible(false);
			seriesShowHideButton[numSeries-1].setVisible(false);
			ArduinoMain.myGraph.setNumSeries(--numSeries);
			System.out.println("Num series is " + ArduinoMain.myGraph.getNumSeries());
		}
		
		// Add Series Button
		else if (event.getSource() == addSeriesButton) {
			int numSeries = ArduinoMain.myGraph.getNumSeries();
			if (numSeries == Constants.DEF_NUM_SERIES)
				removeSeriesButton.setEnabled(true);
			if (numSeries == Constants.DEF_MAX_NUM_SERIES - 1) // 6
				addSeriesButton.setEnabled(false);
			// Set visible new series row
			seriesLabels[numSeries].setVisible(true);
			seriesFields[numSeries].setVisible(true);
			seriesColorButton[numSeries].setVisible(true);
			seriesShowHideButton[numSeries].setVisible(true);
			
			ArduinoMain.myGraph.setNumSeries(++numSeries);
			System.out.println("Num series is " + ArduinoMain.myGraph.getNumSeries());
		}
		// Series Hide/Show Button
		
		//TODO: Call isHidden functions for these
		else if (event.getSource() == seriesShowHideButton[0]) {
			seriesShowHideButton[0].setIcon(hideIcon);
		}
		else if (event.getSource() == seriesShowHideButton[1]) {
			seriesShowHideButton[1].setIcon(hideIcon);
		}
		else if (event.getSource() == seriesShowHideButton[2]) {
			seriesShowHideButton[2].setIcon(hideIcon);
		}
		else if (event.getSource() == seriesShowHideButton[3]) {
			System.out.println("hi");
		}
		else if (event.getSource() == seriesShowHideButton[4]) {
			System.out.println("hi");
		}
		else if (event.getSource() == seriesShowHideButton[5]) {
			System.out.println("hi");
		}
		
		// Series Color Button
		else {
			Color initialcolor = Color.red;
			Color color = JColorChooser.showDialog(this, "Select a color", Color.red);

			if (color != null) {
				if (event.getSource() == seriesColorButton[0]) {
					seriesColorButton[0].setBackground(color);
					System.out.println("The color is " + color);
				}

				else if (event.getSource() == seriesColorButton[1]){
					seriesColorButton[1].setBackground(color);
				}

				else if (event.getSource() == seriesColorButton[2]){
					seriesColorButton[2].setBackground(color);
				}

				else if (event.getSource() == seriesColorButton[3]) {
					seriesColorButton[3].setBackground(color);
				}

				else if (event.getSource() == seriesColorButton[4]) {
					seriesColorButton[4].setBackground(color);
				}

				else if (event.getSource() == seriesColorButton[5]) {
					seriesColorButton[5].setBackground(color);
				}
			}
		}

	}

	/************************************************************
	 * Name: propertyChange Pre: One of the fields "value" property was changed
	 * Post: If the new value is of incorrect type (ex. change a float to a
	 * String), nothing will happen. Else, the new value will be set and stored
	 *************************************************************/
	public void propertyChange(PropertyChangeEvent e) {
		Object source = e.getSource();

		// Change graph title
		if (source == settingsManagerFields[0]) {
			System.out.println("Changed graph title");
		}
		// Change x axis label
		else if (source == settingsManagerFields[1]) {
			ArduinoMain.myGraph.setXAxisLabel((String) settingsManagerFields[1].getValue());
			
		}
		// Change y axis label
		else if (source == settingsManagerFields[2]) {
			ArduinoMain.myGraph.setYAxisLabel((String) settingsManagerFields[2]
					.getValue());
			System.out.println("Changed Y Axis Label to "
					+ settingsManagerFields[2].getValue());
		}
		// Change x start value
		else if (source == settingsManagerFields[3]) {
			ArduinoMain.myGraph.setXStartPoint((Double)settingsManagerFields[3].getValue());
			System.out.println("Changed X Start Value");
		}
		// Change Y start value
		else if (source == settingsManagerFields[4]) {
			System.out.println("Changed Y Start Value");
		}
		// Change time increment
		else if (source == settingsManagerFields[5]) {
			System.out.println("Changed Time Increment");
		}
		// Series Labels
		else if (source == seriesFields[0]) {
			System.out.println("Changed Series 1");
		} else if (source == seriesFields[1]) {
			System.out.println("Changed Series 2");
		} else if (source == seriesFields[2]) {
			System.out.println("Changed Series 3");
		} else if (source == seriesFields[3]) {
			System.out.println("Changed Series 4");
		} else if (source == seriesFields[4]) {
			System.out.println("Changed Series 5");
		} else if (source == seriesFields[5]) {
			System.out.println("Changed Series 6");
		}
	}

	/************************************************************
	 * Name: createSettingsManager 
	 * Pre: None 
	 * Post: Creates a form to allow the user to change the graph settings
	 *************************************************************/
	JScrollPane createSettingsManager() {
		JScrollPane scroll = new JScrollPane(settingsManagerPanel);
		scroll.setPreferredSize(new Dimension(300, 100));
		return scroll;
	}
}
