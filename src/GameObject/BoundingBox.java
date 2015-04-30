package GameObject;

import java.awt.Point;
import java.awt.Rectangle;

public class BoundingBox {
	public Point upperLeftCorner;
	public Point lowerRightCorner;
	
	public BoundingBox(Rectangle r){
		this
		(r.getLocation(), getLowerRight(r));
	}
	
	public BoundingBox(Point x, Point y){
		upperLeftCorner = x;
		lowerRightCorner = y;
	}
	
	private static Point getLowerRight(Rectangle r){
		Point upperLeft = r.getLocation();
		int width = (int)r.getWidth();
		int height = (int) r.getHeight();
		int x = upperLeft.x + width;
		int y = upperLeft.y + height;
		return new Point(x, y);
	}
}
