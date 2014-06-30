import java.util.Comparator;

public final class Point
	implements Comparator<Point>
{
	// Constructs a point at coordinates x and y
	public Point(int x, int y)
	{
		;
	}
	
	// Draws this point
	public void draw()
	{
		;
	}
	
	// Draws a line from this point to that point
	public void drawTo(Point that)
	{
		;
	}
		
	// Calculates the slope from this point to that point
	public double slopeTo(Point that)
	{
		return 0.0;
	}
	
	// Inherited from Comparable
	@Override
	public int compareTo(Point that)
	{
		return -1;
	}
	
	@Override
	public String toString()
	{
		return "";
	}

	
	// Compares points by slope
	public final Comparator<Point> SLOPE_ORDER;
	
	public static void main(String[] args)
	{
		;
	}
}