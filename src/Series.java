import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

import java.util.Vector;

public class Series extends Graph
{
	protected Vector<TracePoint2D> m_data;
	protected double m_xStartPoint = 0;
	protected double m_yStartPoint = 0;
	private ITrace2D m_trace;
	
	public Series()
	{
	
	}
	
	public Series(String Title, java.awt.Color Color)
	{
		m_trace = new Trace2DSimple(Title);
		m_trace.setColor(Color);
		m_graph.addTrace(m_trace);
		
		m_trace.setVisible(true);
	}
	
	public Series(String Title, java.awt.Color Color, double xStartPoint, double yStartPoint)
	{
		m_trace = new Trace2DSimple(Title);
		m_trace.setColor(Color);
		m_graph.addTrace(m_trace);
		
		m_trace.setVisible(true);
		
		m_xStartPoint = xStartPoint;
		m_yStartPoint = yStartPoint;
	}
	
	public String getTitle()
	{
		return m_trace.getName();
	}
	
	public void setTitle(String Title)
	{
		m_trace.setName(Title);
	}
	
	public java.awt.Color getColor()
	{
		return m_trace.getColor();
	}
	
	public void setColor(java.awt.Color Color)
	{
		m_trace.setColor(Color);
	}
	
	public boolean isHidden()
	{
		return m_trace.isVisible();
	}
	
	public void Hide()
	{
		m_trace.setVisible(false);
	}
	
	public void Show()
	{
		m_trace.setVisible(true);
	}
	
	public Vector<TracePoint2D> getDataPoints()
	{
		return m_data;
	}
	
	public void AddDataPoint(double X, double Y)
	{
		TracePoint2D dataPoint = new TracePoint2D(X, Y);
		
		m_data.add(dataPoint);
		m_trace.addPoint(dataPoint);
	}
	
	public double getXStartPoint()
	{
		return m_xStartPoint;
	}
	
	public void setXStartPoint(double xStartPoint)
	{
		m_xStartPoint = xStartPoint;
	}
	
	public double getYStartPoint()
	{
		return m_yStartPoint;
	}
	
	public void setYStartPoint(double yStartPoint)
	{
		m_yStartPoint = yStartPoint;
	}
}
