import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.TracePoint2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
<<<<<<< HEAD
=======
import info.monitorenter.util.Range;
>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1

import java.util.Vector;

public class Series extends Graph
{
	protected Vector<TracePoint2D> m_data;
<<<<<<< HEAD
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
=======
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
>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	}
	
	public String getTitle()
	{
		return m_trace.getName();
	}
<<<<<<< HEAD
	
=======

>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public void setTitle(String Title)
	{
		m_trace.setName(Title);
	}
<<<<<<< HEAD
	
=======

>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public java.awt.Color getColor()
	{
		return m_trace.getColor();
	}
<<<<<<< HEAD
	
=======

>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public void setColor(java.awt.Color Color)
	{
		m_trace.setColor(Color);
	}
<<<<<<< HEAD
	
=======

>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public boolean isHidden()
	{
		return m_trace.isVisible();
	}
<<<<<<< HEAD
	
=======

>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public void Hide()
	{
		m_trace.setVisible(false);
	}
<<<<<<< HEAD
	
=======

>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
	public void Show()
	{
		m_trace.setVisible(true);
	}
<<<<<<< HEAD
	
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
=======

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


>>>>>>> c3cb0b5e6b93d61dbafc367d3ecb26adb9189ab1
