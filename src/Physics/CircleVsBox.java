package Physics;

import GameObject.StaticBox;
import GameObject.Circle;

public class CircleVsBox implements CollisionDetective {
	private final Circle a;
	private final StaticBox b;
	public CircleVsBox(Circle a, StaticBox b){
		this.a=a;
		this.b=b;
	}
	@Override
	public Boolean areObjectsColliding() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void resolveCollision() {
		// TODO Auto-generated method stub
		
	}
	
}
