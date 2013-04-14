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
	
	public String getXAxisLabel()
	{
		return m_x_axis.toString();
	}
	
	public void setXAxisLabel(String Label)
	{
		m_x_axis.setAxisTitle(new IAxis.AxisTitle(Label));
	}
	
	public String getYAxisLabel()
	{
		return m_y_axis.toString();
	}
	
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
	}
}
