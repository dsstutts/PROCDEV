import java.util.Random;
import javax.swing.BorderFactory;
import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ZoomableChart;
import info.monitorenter.gui.chart.rangepolicies.RangePolicyFixedViewport;
import info.monitorenter.gui.chart.views.ChartPanel;
import info.monitorenter.util.Range;


public class Graph
{
	private double m_time_increment; // By what value are we incrementing time
	private String m_time_measurement; // Measurement of time in seconds or milliseconds
	protected static ZoomableChart m_graph;
	public Series m_seriesList[] = new Series[Constants.DEF_SERIES_TITLES.length];
	private IAxis<?> m_x_axis;
	private IAxis<?> m_y_axis;
	private int m_numSeries = Constants.DEF_NUM_SERIES;
	protected double m_xStartPoint;
	protected double m_yStartPoint;

	// Only needs one constructor
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
		
		m_seriesList[0].AddDataPoint(m_xStartPoint,m_yStartPoint);
		m_seriesList[0].AddDataPoint(4,5);
	}

	public String getXAxisLabel()
	{
		return m_x_axis.toString();
	}

	public void setXAxisLabel(String Label)
	{
		IAxis.AxisTitle title = new IAxis.AxisTitle(Label);
		title.setTitleFont(new java.awt.Font("SansSerif", 0, 14));
		
		m_x_axis.setAxisTitle(title);
		m_graph.updateUI();
	}

	public String getYAxisLabel()
	{
		return m_y_axis.toString();
	}

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
	public ChartPanel getGraph()
	{
		ChartPanel testPanel = new ChartPanel(m_graph);
		testPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

		return testPanel;
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
	
	public void addSeries()
	{
		m_seriesList[m_numSeries] = new Series(Constants.DEF_SERIES_TITLES[m_numSeries], Constants.DEF_SERIES_COLORS[m_numSeries], true);
		m_graph.addTrace(m_seriesList[m_numSeries].getTrace());
		m_seriesList[m_numSeries].AddDataPoint(m_xStartPoint,m_yStartPoint);
		Random random = new Random();
		m_seriesList[m_numSeries].AddDataPoint(5,random.nextDouble()*10.0);
		m_numSeries++;
	}
	
	public void removeSeries()
	{
		m_seriesList[m_numSeries - 1].setTitle("");
		m_graph.removeTrace(m_seriesList[m_numSeries -1].getTrace());//m_seriesList[m_numSeries -1].removeTrace(); // remove the object
		m_seriesList[m_numSeries -1].removeTrace();
		m_numSeries--;
	}
}



