package Physics;

import GameObject.Circle;
import GameObject.StaticBox;

public class BoxVsBox implements CollisionDetective {
	private final StaticBox a;
	private final StaticBox b;
	
	public BoxVsBox(StaticBox a, StaticBox b){
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
