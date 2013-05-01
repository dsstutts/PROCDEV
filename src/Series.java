import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;

import java.awt.Color;
import java.util.Vector;

public class Series
{
	protected Vector<Point> m_data;
	private ITrace2D m_trace;

	public Series(String title, Color color, boolean isVisible)
	{
		m_data = new Vector<Point>();
		m_trace = new Trace2DSimple();
		m_trace.setName(title);
		m_trace.setColor(color);
		m_trace.setVisible(isVisible);
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

	public void setColor(java.awt.Color color)
	{
		m_trace.setColor(color);
		System.out.println("The color is" + color);
	}

	public boolean isHidden()
	{
		return !(m_trace.isVisible());
	}

	public void Hide()
	{
		m_trace.setVisible(false);
	}

	public void Show()
	{
		m_trace.setVisible(true);
	}

	public ITrace2D getTrace()
	{
		return m_trace;
	}
	
	public void removeTrace()
	{
		m_trace = null;
	}

	public void AddDataPoint(double X, double Y)
	{
		m_trace.addPoint(X,Y);
		m_data.add(new Point(X,Y));
	}
}

