package GameObject;

/**
*
* @author Alexander Sopov
*/
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import Vector.Vector2D;

public class Box extends Entity {
	private Rectangle2D.Double shape;
	public Box(int x, int y, int width, int height, double r, int mass) {
		super(new Rectangle2D.Double(x,y,width,height), r, mass);
		setRestitution(r);
		shape = (Rectangle2D.Double)getShape();
		setVelocity(0,0);
	}
	
	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.fill(getShape());
	}

	@Override
	public void update() {
		super.update();
		setLocation(getLocation().addWith(getVelocity()));
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2D getLocation() {
		return new Vector2D(shape.getX(), shape.getY());
	}

	@Override
	public Vector2D getCenter() {
		double x = shape.getX() + (shape.getWidth()/2);
		double y = shape.getY() + (shape.getHeight()/2);
		return new Vector2D(x,y);
	}

	
	public void setLocation(double x, double y) {
		shape.x=x;
		shape.y=y;
	}

}
