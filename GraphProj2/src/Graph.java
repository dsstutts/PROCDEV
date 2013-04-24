import java.awt.Component;
import java.awt.Panel;

import javax.swing.JComponent;
import javax.swing.JFrame;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.axis.AAxis;

public class Graph extends Chart2D
{
	private double m_time_increment; // By what value are we incrementing time
	private String m_time_measurement; // Measurement of time in seconds or milliseconds
	protected static Chart2D m_graph;
	private IAxis<?> m_x_axis;
	private IAxis<?> m_y_axis;
	private AAxis<?> m_sneaky_title;
	private int m_numSeries = Constants.DEF_NUM_SERIES;
	

	// Only needs one constructor
	public Graph()
	{ 
		m_graph = new Chart2D();
		// How to set the title of a graph?
		m_x_axis = m_graph.getAxisX();
		m_y_axis = m_graph.getAxisY();
		m_graph.addAxisXTop(m_sneaky_title);
		setXAxisLabel(Constants.DEF_XAXIS_LABEL);
		setYAxisLabel(Constants.DEF_YAXIS_LABEL);
		m_time_increment = Constants.DEF_TIME_INCREMENT;
		m_time_measurement = Constants.DEF_TIME_MEASUREMENT[0]; // default is measuring in seconds
	}


	//The graph title should be placed above the graph, not in a separate window
	public void setGraphTitle(String Title)
	{
		m_sneaky_title.setAxisTitle(new IAxis.AxisTitle("LOOK!!!"));
	}

	public String getGraphTitle()
	{
		return "";
	}
	
	public String getXAxisLabel()
	{
		return m_x_axis.toString();
	}

	public void setXAxisLabel(String Label)
	{
		m_x_axis.setAxisTitle(new IAxis.AxisTitle(Label));
		m_graph.updateUI();
	}

	public String getYAxisLabel()
	{
		return m_y_axis.toString();
	}

	public void setYAxisLabel(String Label)
	{
		m_y_axis.setAxisTitle(new IAxis.AxisTitle(Label));
		m_graph.updateUI();
	}

	public double getTimeIncrement()
	{
		return m_time_increment;
	}

	public void setTimeIncrement(double increment)
	{
		m_time_increment = increment;
	}

	public String getTimeMeasurement()
	{
		return m_time_measurement;
	}

	public void setTimeMeasurement(String measurement)
	{
		m_time_measurement = measurement;
	}
	
	// Returns a Chart2D which is used to add the graph to the main frame
	public Chart2D getGraph()
	{
	  return m_graph;
	}
	
	public void setNumSeries(int numSeries)
    {
		m_numSeries = numSeries;
    }
    
    public int getNumSeries()
    {
    	return m_numSeries;
    }
}
