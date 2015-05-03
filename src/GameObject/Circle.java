package GameObject;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import Vector.Vector2D;

public class Circle extends Entity {
	
	private Ellipse2D.Double shape;
	private int radius;
	
	
	public Circle(int x, int y, int radius, float restitution, int mass) {
		this(x,y,radius,restitution,new Vector2D(0,0), mass);
		
	}
	
	public Circle(int x, int y, int radius, float restitution,
			Vector2D velocity, int mass) {
	super(new Ellipse2D.Double(x,y,radius*2,radius*2), restitution, mass);
		setRestitution(restitution);
		setVelocity(velocity);
		shape = (Ellipse2D.Double)getShape();
		this.radius = radius;
	}

	
	public void render(Graphics2D g) {
		super.render(g);
		g.fill(getShape());
	}

	public void update() {
		super.update();
		setLocation(getVelocity().addWith(getLocation()));
	}

	public void remove() {
		
		
	}
	
	public int getRadius(){
		return radius;
	}

	public Vector2D getLocation() {
		return new Vector2D(shape.x, shape.y);
	}

	public Vector2D getCenter() {
		double x = shape.x + radius;
		double y = shape.y + radius;
		return new Vector2D(x,y);
	}

	public void setLocation(double x, double y) {
		shape.x=x;
		shape.y=y;
	}
}
