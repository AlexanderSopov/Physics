package Physics;

import GameObject.BoundingBox;
import GameObject.Circle;


public class Collision {
	
	public static boolean areBBColliding(BoundingBox a, BoundingBox b){
		if (noIntersectOnX(a,b))
			return false;
		if (noIntersectOnY(a,b))
			return false;
		else
			return true;
	}
	
	
	
	
	private static boolean noIntersectOnX(BoundingBox a, BoundingBox b){
		return (a.lowerRightCorner.x < b.upperLeftCorner.x || a.upperLeftCorner.x > b.lowerRightCorner.x);
	}
	
	private static boolean noIntersectOnY(BoundingBox a, BoundingBox b){
		return (a.lowerRightCorner.y < b.upperLeftCorner.y || a.upperLeftCorner.y > b.lowerRightCorner.y);
	}
	
	
	public static void areCirclesColliding(Circle a, Circle b){
		CollisionStrategy strategy = new CollisionStrategy(a,b);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();
	}
}
