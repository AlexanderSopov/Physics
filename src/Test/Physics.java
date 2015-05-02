package Test;

import java.util.Observable;
import java.util.Observer;

import GameObject.Circle;
import Physics.Collision;
import Physics.CollisionStrategy;
import Physics.Vector2D;

public class Physics implements Observer {
	
	private static Circle c1 = Main.c1;
	private static Circle c2 = Main.c2;
	private static Circle c3 = Main.c3;
	
	
	public Physics(){
		c1.setVelocity(18,-4);
		c3.setVelocity(-1, -45);
	}
	
	
	
	public void update(Observable arg0, Object arg1) {
		CollisionStrategy strategy = new CollisionStrategy(c2,c1);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();
		
		strategy = new CollisionStrategy(c3,c2);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

		strategy = new CollisionStrategy(c1,c3);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

	}
}
