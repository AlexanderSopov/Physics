package Physics;

import GameObject.BoundingBox;
import GameObject.Circle;


public class Collision {
	
	public static boolean isBBColliding(BoundingBox a, BoundingBox b){
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

	
	public static boolean areCirclesColliding(Circle a, Circle b){
		double r = (a.getRadius() + b.getRadius());
		//For optimization, we exclude the need for expensive square-root calculations by comparing
		//the distances as squared.
		r = r*r;
		Vector2D v = new Vector2D(b.getCenter().subtractWith(a.getCenter()));
		return r > v.lengthSquared();
	}


	public static void resolveCollision(Circle a, Circle b){
		
		
		Vector2D normal = getNormal(b.getCenter(), a.getCenter());
		Vector2D relativeVelocity = subtract(b.getVelocity(),a.getVelocity());
		
		double velocityAlongNormalVector = relativeVelocity.dotProduct(normal);
		
		
		if (velocityAlongNormalVector == 0)	// true if objects are already moving away from each other
			return; 						// and thus, we break.
		
		
		double e = min(a.restitution, b.restitution); // the object with less "bounciness" wins
		
		double j = -(1 + e) * velocityAlongNormalVector; // calculate an impulse scalar
		
		
		j /= (a.invMass + b.invMass);
		
		//System.out.println("j2 = " + j);
		
		Vector2D impulse = normal.scale(j);
		
		setVelocityToRatio(a,b,impulse);
		
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
	
	private static double min(double a, double b){
		if(a>b)
			return b;
		else
			return a;
	}
}
