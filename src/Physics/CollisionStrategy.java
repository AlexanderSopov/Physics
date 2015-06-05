package Physics;

import GameObject.Box;
import GameObject.Circle;

/**
*
* @author Alexander Sopov
*/
public class CollisionStrategy {
	CollisionDetective detective;

	public CollisionStrategy(Circle a, Circle b){
		detective = new CircleVsCircle(a,b);
	}
	public CollisionStrategy(Box a, Box b){
		detective = new BoxVsBox(a,b);
	}
	
	public Boolean areObjectsColliding(){
		return detective.areObjectsColliding();
	}
	
	public void resolveCollision(){
		detective.resolveCollision();
	}
	
}
