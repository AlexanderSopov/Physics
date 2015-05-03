package Test;

import java.util.Observable;
import java.util.Observer;

import GameObject.Box;
import GameObject.Circle;
import Physics.CollisionStrategy;
import Vector.Vector2D;

public class Physics implements Observer {
	
	private static Circle c1 = Main.c1;
	private static Circle c2 = Main.c2;
	private static Circle c3 = Main.c3;
	private static Box b1 = Main.b1;
	private static Box b2 = Main.b2;
	
	public Physics(){
		c1.setVelocity(22,-5);
		c3.setVelocity(-1, -50);
		b1.setVelocity(new Vector2D(0,-20));
	}
	
	
	
	public void update(Observable arg0, Object arg1) {
		CollisionStrategy strategy = new CollisionStrategy(c1,c2);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();
		
		strategy = new CollisionStrategy(c2,c3);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

		strategy = new CollisionStrategy(c1,c3);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

		
		strategy = new CollisionStrategy(b1,b2);
		if (strategy.areObjectsColliding()){
			strategy.resolveCollision();
		}
			
		
		
	}
}
