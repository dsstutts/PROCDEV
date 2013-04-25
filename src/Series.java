import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import info.monitorenter.util.Range;

import java.util.Vector;

public class Series extends Graph
{
	protected Vector<TracePoint2D> m_data;
	private ITrace2D m_trace;

	public Series()
	{
		m_trace = new Trace2DSimple();
		setTitle(Constants.DEF_SERIES_TITLES[0]);
		setColor(Constants.DEF_SERIES_COLORS[0]);
		setXStartPoint(Constants.DEF_X_START_VALUE);
		setYStartPoint(Constants.DEF_Y_START_VALUE);
		
		m_graph.addTrace(m_trace);
	}

	public void Kill()
	{
		m_graph.removeTrace(m_trace);
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

	public ITrace2D getTrace()
	{
		return m_trace;
	}

	public void AddDataPoint(double X, double Y)
	{
		TracePoint2D dataPoint = new TracePoint2D(X, Y);

		m_data.add(dataPoint);
		m_trace.addPoint(dataPoint);
	}
}


