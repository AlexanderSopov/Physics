package GameObject;

import java.awt.geom.RectangularShape;

import Vector.Vector2D;

/**
*
* @author Alexander Sopov
*/
public abstract class Entity extends GameObject {
	private Vector2D velocity; //vector array
	private static final Vector2D gravity = new Vector2D(0, 0.075);
	
	public Entity(RectangularShape s, double r, int mass){
		super(s,r,mass);
	}


	@Override
	public void update() {
		setVelocity(gravity.addWith(getVelocity()));		
	}
	public Vector2D getVelocity(){
		return velocity;
	}
	public void setVelocity(double x, double y){
		setVelocity(new Vector2D(x,y));
	}
	public void setVelocity(Vector2D v){
		velocity = v;
	}

}
