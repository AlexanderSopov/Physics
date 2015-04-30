package Test;

import java.util.Observable;
import java.util.Observer;

import GameObject.Circle;
import Physics.Collision;
import Vector.Vector2D;

public class Physics implements Observer {
	private static final Vector2D gravity = new Vector2D(0, 0.05);
	private static Circle c1 = Main.c1;
	private static Circle c2 = Main.c2;
	private static int update = 0;
	public void update(Observable arg0, Object arg1) {
		if(Collision.areCirclesColliding(c1, c2))
			Collision.resolveCollision(c1,c2);
		c1.setVelocity(c1.getVelocity().addWith(gravity));
		c2.setVelocity(c2.getVelocity().addWith(gravity));
	}
}
