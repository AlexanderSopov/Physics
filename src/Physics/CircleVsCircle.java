package Physics;

import Vector.Vector2D;
import GameObject.Circle;
import GameObject.GameObject;

public class CircleVsCircle implements CollisionDetective {
	private final Circle a;
	private final Circle b;
	public CircleVsCircle(Circle a, Circle b){
		this.a=a;
		this.b=b;
	}

	@Override
	public Boolean areObjectsColliding() {

		double r = (a.getRadius() + b.getRadius());
		//For optimization, we exclude the need for expensive square-root calculations by comparing
		//the distances as squared.
		r = r*r;
		Vector2D v = new Vector2D(b.getCenter().subtractWith(a.getCenter()));
		return r > v.lengthSquared();
	}

	@Override
	public void resolveCollision() {
		Vector2D normal = subtract(b.getCenter(), a.getCenter());
		Vector2D relativeVelocity = subtract(b.getVelocity(),a.getVelocity());
		double distance = normal.length();
		double penetration;
		System.out.println(distance);
		System.out.println(a.getRadius()+b.getRadius());
		
		if(distance == 0){
			penetration = a.getRadius();
			normal = new Vector2D(1,0);
		}else{
			penetration = (a.getRadius() + b.getRadius()) - distance;
			normal = normal.scale(1/distance);
		}
		

		double velocityAlongNormalVector = relativeVelocity.dotProduct(normal);
		
		if (velocityAlongNormalVector == 0)	// true if objects are already moving away from each other
			return; 						// and thus, we break.
		
		correctCircles(normal, velocityAlongNormalVector, penetration);


	}
	

	private void correctCircles(Vector2D normal, double velNormal, 
								double penetration) {	
		correctPositions(normal, velNormal, penetration);
		Vector2D impulse = calculateImpulse(normal, velNormal);
		setVelocityToRatio(impulse);

	}

	
	
	private Vector2D calculateImpulse(Vector2D normal, double velNormal) {
		double e = min(a.restitution, b.restitution); // the object with less "bounciness" wins
		double j = -(1 + e) * velNormal; // calculate an impulse scalar
		
		j /= (a.invMass + b.invMass);
		
		return normal.scale(j);
	}

	private void correctPositions(Vector2D normal, double velNormal,
			double penetration) {
		double percent = 0.2;
		double slop = 0.01;
		double corr;
		if (penetration - slop < 0.0)
			corr = 0;
		else
			corr = penetration - slop;
		
		Vector2D correction = normal.scale(percent*corr);
		a.setLocation((correction.subtractWith(a.getLocation())).returnNegative());
		b.setLocation(correction.addWith(b.getLocation()));
	}

	private static Vector2D getNormal(Vector2D v, Vector2D u) {
		Vector2D normal = subtract(v,u);
		normal = normal.makeUnitVector();
		return normal;
	}


	private void setVelocityToRatio(Vector2D impulse) {
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

		

