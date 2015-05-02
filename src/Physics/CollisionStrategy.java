package Physics;

import GameObject.Circle;

public class CollisionStrategy {
	CollisionDetective detective;

	public CollisionStrategy(Circle a, Circle b){
		detective = new CircleVsCircle(a,b);
	}
	
	public Boolean areObjectsColliding(){
		return detective.areObjectsColliding();
	}
	
	public void resolveCollision(){
		detective.resolveCollision();
	}
	
}
