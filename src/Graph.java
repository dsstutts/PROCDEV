<<<<<<< HEAD
import javax.swing.JFrame;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;

public class Graph 
{
	public enum TimeIncrement {MS, S};
	
	private double m_increment;
	private TimeIncrement m_time_Increment;
	protected Chart2D m_graph;
	private JFrame m_frame;
	private IAxis<?> m_x_axis;
	private IAxis<?> m_y_axis;
	
	public Graph() { }
	
	public Graph(String Title)
	{
		m_graph = new Chart2D();
		m_frame = new JFrame(Title);
		m_frame.setSize(400,200);
		m_frame.setVisible(true);
		
		m_x_axis = m_graph.getAxisX();
		m_y_axis = m_graph.getAxisY();
	}
	
	public Graph(String Title, String XAxisLabel, String YAxisLabel)
	{
		m_graph = new Chart2D();
		m_frame = new JFrame(Title);
		m_frame.setSize(400,200);
		m_frame.setVisible(true);
		
		m_x_axis = m_graph.getAxisX();
		m_y_axis = m_graph.getAxisY();
		
		m_x_axis.setAxisTitle(new IAxis.AxisTitle(XAxisLabel));
		m_y_axis.setAxisTitle(new IAxis.AxisTitle(YAxisLabel));
	}
	
	public Graph(String Title, String XAxisLabel, String YAxisLabel, double TimeInc, TimeIncrement TimeMeasure)
	{
		m_graph = new Chart2D();
		m_frame = new JFrame(Title);
		m_frame.setSize(400,200);
		m_frame.setVisible(true);
		
		m_x_axis = m_graph.getAxisX();
		m_y_axis = m_graph.getAxisY();
		
		m_x_axis.setAxisTitle(new IAxis.AxisTitle(XAxisLabel));
		m_y_axis.setAxisTitle(new IAxis.AxisTitle(YAxisLabel));
		
		m_increment = TimeInc;
		m_time_Increment = TimeMeasure;
	}
	
	public JFrame getFrame()
	{
		return m_frame;
	}
	
	public void setGraphTitle(String Title)
	{
		m_frame.setTitle(Title);
	}
	
	public String getGraphTitle()
	{
		return m_frame.getTitle();
	}
	
=======
import java.awt.List;
import java.util.Hashtable;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.util.Range;

public class Graph
{
	private double m_time_increment; // By what value are we incrementing time
	private String m_time_measurement; // Measurement of time in seconds or milliseconds
	protected static Chart2D m_graph;
	private Hashtable<String, Series> m_seriesList;
	private IAxis<?> m_x_axis;
	private IAxis<?> m_y_axis;
	protected double m_xStartPoint;
	protected double m_yStartPoint;
	private int m_numSeries = Constants.DEF_NUM_SERIES;

	// Only needs one constructor
	public Graph()
	{ 
		m_graph = new Chart2D();
		// How to set the title of a graph?
		m_x_axis = m_graph.getAxisX();
		m_y_axis = m_graph.getAxisY();
		setXAxisLabel(Constants.DEF_XAXIS_LABEL);
		setYAxisLabel(Constants.DEF_YAXIS_LABEL);
		
		m_time_increment = Constants.DEF_TIME_INCREMENT;
		m_time_measurement = Constants.DEF_TIME_MEASUREMENT[0]; // default is measuring in seconds
	}


	/*
	// The graph title should be placed above the graph, not in a separate window
	public void setGraphTitle(String Title)
	{
		//m_frame.setTitle(Title);
	}

	public String getGraphTitle()
	{
		//return m_frame.getTitle();
	}
*/
>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public String getXAxisLabel()
	{
		return m_x_axis.toString();
	}
<<<<<<< HEAD
	
	public void setXAxisLabel(String Label)
	{
		m_x_axis.setAxisTitle(new IAxis.AxisTitle(Label));
	}
	
=======

	public void setXAxisLabel(String Label)
	{
		IAxis.AxisTitle title = new IAxis.AxisTitle(Label);
		title.setTitleFont(new java.awt.Font("SansSerif", 0, 14));
		
		m_x_axis.setAxisTitle(title);
		m_graph.updateUI();
	}

>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public String getYAxisLabel()
	{
		return m_y_axis.toString();
	}
<<<<<<< HEAD
	
	public void setYAxisLabel(String Label)
	{
		m_y_axis.setAxisTitle(new IAxis.AxisTitle(Label));
	}
	
	public double getTimeIncrementNum()
	{
		return m_increment;
	}
	
	public void setTimeIncrementNum(double Increment)
	{
		m_increment = Increment;
	}
	
	public TimeIncrement getTimeIncrement()
	{
		return m_time_Increment;
	}
	
	public void setTimeIncrement(TimeIncrement time)
	{
		m_time_Increment = time;
=======

	public void setYAxisLabel(String Label)
	{
		IAxis.AxisTitle title = new IAxis.AxisTitle(Label);
		title.setTitleFont(new java.awt.Font("SansSerif", 0, 14));
		
		m_y_axis.setAxisTitle(title);
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
	
	public void setNumSeries(int numSeries)
	{
		 m_numSeries = numSeries;
	}
	
	public int getNumSeries()
	{
		return m_numSeries;
	}
	
	// Returns a Chart2D which is used to add the graph to the main frame
	public Chart2D getGraph()
	{
	  return m_graph;
	}
	
	public double getXStartPoint()
	{
		return m_xStartPoint;
	}

	public void setXStartPoint(double xStartPoint)
	{
		m_x_axis.setRangePolicy(new RangePolicyFixedViewport(new Range(xStartPoint, Double.MAX_VALUE)));
		m_xStartPoint = xStartPoint;
		
		m_graph.updateUI();
		
		System.out.println(m_x_axis.getRange().getMin());
	}

	public double getYStartPoint()
	{
		return m_yStartPoint;
	}

	public void setYStartPoint(double yStartPoint)
	{
		m_yStartPoint = yStartPoint;
	}
	
	public void addSeries(Series series)
	{
		m_seriesList.put(series.getTitle(), series);
	}
	public Hashtable<String, Series> getSeries()
	{
		return m_seriesList;
>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	}
}
