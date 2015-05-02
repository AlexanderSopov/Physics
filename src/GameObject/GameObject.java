package GameObject;

import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.util.Observable;
import java.util.Observer;

import Physics.Vector2D;

public abstract class GameObject implements Observer {
	private Vector2D velocity; //vector array
	private RectangularShape shape;
	public double restitution;
	public double mass;
	public double invMass;
	//private static final GameThread gt = PhysicsTest.run;
	
	public GameObject(RectangularShape s, double restitution, int mass){
		shape = s;
		this.restitution=restitution;
		this.mass = mass;
		invMass = 1/(double)mass;
	}
	
	public abstract void render(Graphics2D g);
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
	public Vector2D getVelocity(){
		return velocity;
	}
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
	public abstract void setLocation(double x, double y);
	public void setVelocity(double x, double y){
		velocity.setLocation(x,y);
	}
	public void setVelocity(Vector2D v){
		velocity = v;
	}
}
