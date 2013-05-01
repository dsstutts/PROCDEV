import java.util.Random;
import javax.swing.BorderFactory;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ZoomableChart;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.gui.chart.views.ChartPanel;
import info.monitorenter.util.Range;

// Class: Graph
// Desc: Includes all functionality for creating a graph
public class Graph
{
	private double m_time_increment; // By what value are we incrementing time
	private String m_time_measurement; // Measurement of time in seconds or milliseconds
	protected static ZoomableChart m_graph;
	public Series m_seriesList[] = new Series[Constants.DEF_SERIES_TITLES.length];
	IAxis<?> m_x_axis; // The x axis
	private IAxis<?> m_y_axis; // The y axis
	private int m_numSeries = Constants.DEF_NUM_SERIES; // Defaults to 1
	protected double m_xStartPoint;
	protected double m_yStartPoint;

	/************************************************************
	 * Name: Graph
	 * desc: Initializes values for the graph
	 * Pre: None
	 * Post: Our graph is created with certain default values set
	 *       such as 1 series, an x and y start point, time increment,
	 *       and so forth
	 *************************************************************/
	public Graph()
	{ 
		m_seriesList[0] = new Series(Constants.DEF_SERIES_TITLES[0], Constants.DEF_SERIES_COLORS[0], true);
		m_graph = new ZoomableChart();
		m_x_axis = m_graph.getAxisX();
		m_y_axis = m_graph.getAxisY();		
		setXAxisLabel(Constants.DEF_XAXIS_LABEL);
		setYAxisLabel(Constants.DEF_YAXIS_LABEL);
		m_xStartPoint = Constants.DEF_X_START_VALUE;
		m_yStartPoint = Constants.DEF_Y_START_VALUE;
		
		m_time_increment = Constants.DEF_TIME_INCREMENT;
		m_time_measurement = Constants.DEF_TIME_MEASUREMENT[0]; // default is measuring in seconds
		m_graph.addTrace(m_seriesList[0].getTrace());
		
	}

	// Return the X-axis label
	public String getXAxisLabel()
	{
		return m_x_axis.toString();
	}

	// Sets the X axis label
	public void setXAxisLabel(String Label)
	{
		IAxis.AxisTitle title = new IAxis.AxisTitle(Label);
		title.setTitleFont(new java.awt.Font("SansSerif", 0, 14));
		
		m_x_axis.setAxisTitle(title);
		m_graph.updateUI();
	}

	// Return the Y-axis label
	public String getYAxisLabel()
	{
		return m_y_axis.toString();
	}

	// Sets the y axis label
	public void setYAxisLabel(String Label)
	{
		IAxis.AxisTitle title = new IAxis.AxisTitle(Label);
		title.setTitleFont(new java.awt.Font("SansSerif", 0, 14));
		
		m_y_axis.setAxisTitle(title);
		m_graph.updateUI();
	}

	// Gets the time increment
	public double getTimeIncrement()
	{
		return m_time_increment;
	}

	// Sets the time increment
	public void setTimeIncrement(double increment)
	{
		m_time_increment = increment;
	}

	// Gets the time measurement (s or ms)
	public String getTimeMeasurement()
	{
		return m_time_measurement;
	}

	// Set the time measurement (s or ms)
	public void setTimeMeasurement(String measurement)
	{
		m_time_measurement = measurement;
	}
	
	// Set the number of series
	public void setNumSeries(int numSeries)
	{
		 m_numSeries = numSeries;
	}
	
	// Return the number of series
	public int getNumSeries()
	{
		return m_numSeries;
	}
	
	// Returns a Chart2D which is used to add the graph to the main frame
	public ChartPanel getGraph()
	{
		ChartPanel testPanel = new ChartPanel(m_graph);
		testPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		return testPanel;
	}
	
	// Return the X start value
	public double getXStartPoint()
	{
		return m_xStartPoint;
	}

	// Sets the X start value
	public void setXStartPoint(double xStartPoint)
	{
		//m_x_axis.setRangePolicy(new RangePolicyFixedViewport(new Range(xStartPoint, xStartPoint + 10 )));
		//m_x_axis.setRange(m_x_axis.getRange());
		m_xStartPoint = xStartPoint;	
		m_graph.updateUI();
		
		//System.out.println(m_x_axis.getRange().getMin());
	}

	// Returns the Y start value
	public double getYStartPoint()
	{
		return m_yStartPoint;
	}

	// Sets the Y start value
	public void setYStartPoint(double yStartPoint)
	{
		//m_y_axis.setRangePolicy(new RangePolicyFixedViewport(new Range(yStartPoint, yStartPoint + 10)));
		m_yStartPoint = yStartPoint;
		m_graph.updateUI();
	}
	
	// Add a new series to the graph
	public void addSeries()
	{
		m_seriesList[m_numSeries] = new Series(Constants.DEF_SERIES_TITLES[m_numSeries], Constants.DEF_SERIES_COLORS[m_numSeries], true);
		m_graph.addTrace(m_seriesList[m_numSeries].getTrace());
		
		m_numSeries++;
	}
	
	// Remove a series from the graph
	public void removeSeries()
	{
		m_seriesList[m_numSeries - 1].setTitle("");
		m_graph.removeTrace(m_seriesList[m_numSeries -1].getTrace());
		m_seriesList[m_numSeries -1].removeTrace();
		m_numSeries--;
	}
}



