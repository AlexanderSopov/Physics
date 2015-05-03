package GameObject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.Observable;
import java.util.Observer;

import Vector.Vector2D;

public abstract class GameObject implements Observer {
	private RectangularShape shape;
	public double restitution;
	public double mass;
	public double invMass;
	private Color color;
	//private static final GameThread gt = PhysicsTest.run;
	
	public GameObject(RectangularShape s, double restitution, int mass){
		shape = s;
		this.restitution=restitution;
		this.mass = mass;
		setInvMass(mass);
		color = setColor(mass);
	}
	
	private Color setColor(int mass) {
		int red;
		if (mass > 255 || mass == 0)
			red = 255;
		else if (mass < 100)
			red = 100;
		else
			red = mass;
		return new Color(red, 25,10);
	}

	private void setInvMass(int m) {
		if (mass == 0)
			invMass = 0;
		else
			invMass = 1/(double)mass;
	}

	public void render(Graphics2D g){
		g.setColor(color);
	}
	
	
	public void update(Observable o, Object arg){
		try {
			Graphics2D g = (Graphics2D)arg;
			render(g);
			update();
		}catch (IllegalArgumentException e){
			System.out.println("oops!");
		}
	}

	public abstract void update();
	public abstract void remove();
	
	//getters
	public abstract Vector2D getLocation();
	public abstract Vector2D getCenter();

	public double getRestitution(){
		return restitution;
	}
	public RectangularShape getShape(){
		return shape;
	}
	
	
	
	//Setters
	public void setRestitution(float r){
		restitution = r;
	}
	public void setLocation(Point2D p){
		setLocation(p.getX(), p.getY());
	}

	public abstract void setLocation(double x, double y);


}
