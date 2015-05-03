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
		Vector2D normal = getNormal(b.getCenter(), a.getCenter());
		Vector2D closest = normal;
		
		
		
		return null;
	}
	@Override
	public void resolveCollision() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private static Vector2D getNormal(Vector2D v, Vector2D u) {
		Vector2D normal = subtract(v,u);
		normal = normal.makeUnitVector();
		return normal;
	}


	private static void setVelocityToRatio(Circle a, Circle b, Vector2D impulse) {
		Vector2D scaledImpulse = impulse.scale(a.invMass);
		Vector2D newVelocity = a.getVelocity().subtractWith(scaledImpulse);
		a.setVelocity(newVelocity);

		scaledImpulse = impulse.scale(b.invMass);
		newVelocity = b.getVelocity().addWith(scaledImpulse);
		b.setVelocity(newVelocity);
	}


	private static Vector2D subtract(Vector2D b, Vector2D a){
		return  b.subtractWith(a);
	}
}
