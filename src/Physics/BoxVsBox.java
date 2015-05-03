package Physics;

import Vector.Vector2D;
import GameObject.Box;

public class BoxVsBox implements CollisionDetective {
	private final Box a;
	private final Box b;
	private Vector2D normal;
	private double penetration;
	private double aHalfWidth;
	private double bHalfWidth;
	private double aHalfHeight;
	private double bHalfHeight;
	private double halfHeights;
	private double halfWidths;
	
	public BoxVsBox(Box a, Box b){
		this.a=a;
		this.b=b;
		aHalfWidth = (double)(a.getShape().getWidth())/2;
		bHalfWidth = (double)(b.getShape().getWidth())/2;
		aHalfHeight = (double)(a.getShape().getHeight())/2;
		bHalfHeight =(double)( b.getShape().getHeight())/2;
		halfHeights = aHalfHeight + bHalfHeight;
		halfWidths = aHalfWidth + bHalfWidth;
	}
	
	@Override
	public Boolean areObjectsColliding() {
		normal = subtract(b.getCenter(), a.getCenter());
		double xOverlap = halfWidths - abs(normal.getX());
		
		if(xOverlap > 0){
			
			double yOverlap = halfHeights - abs(normal.getY());

			
			if(yOverlap > 0){
				if(xOverlap > yOverlap){
					if (normal.getX() < 0)
						normal = new Vector2D( -1, 0 );
					else
						normal = new Vector2D( -1, 0 );
					penetration = xOverlap;
					return true;
				}else{
					if (normal.getY() < 0 )
						normal = new Vector2D(0,-1);
					else
						normal = new Vector2D(0,1);
					penetration = yOverlap;
					return true;
				}
			}
		}
		return false;
	}
	
	

	private double abs(double x) {
		if(x < 0)
			return -x;
		return x;
	}

	@Override
	public void resolveCollision() {
		
		Vector2D relativeVelocity = subtract(b.getVelocity(), a.getVelocity());
		double velocityAlongNormal = relativeVelocity.dotProduct(normal);

		System.out.println(velocityAlongNormal);
		//if (velocityAlongNormal == 0)
			//return;
		
		correctBoxes(1);
		
	}

	private void correctBoxes(double velNormal) {
		//correctPositions(normal, velNormal, penetration);
		Vector2D impulse = calculateImpulse(normal, velNormal);
		setVelocityToRatio(impulse);
	}

	private void correctPositions(Vector2D normal, double velNormal,
			double penetration) {
		System.out.println(penetration);
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
	
	
	private void setVelocityToRatio(Vector2D impulse) {
		Vector2D scaledImpulse = impulse.scale(a.invMass);
		Vector2D newVelocity = a.getVelocity().subtractWith(scaledImpulse);
		a.setVelocity(newVelocity);

		scaledImpulse = impulse.scale(b.invMass);
		newVelocity = b.getVelocity().addWith(scaledImpulse);
		b.setVelocity(newVelocity);
	}
	
	
	private Vector2D calculateImpulse(Vector2D normal, double velNormal) {
		double e = min(a.restitution, b.restitution); // the object with less "bounciness" wins
		double j = -(1 + e) * velNormal; // calculate an impulse scalar
		
		j /= (a.invMass + b.invMass);
		
		return normal.scale(j);
	}
	
	private static double min(double a, double b){
		if(a>b)
			return b;
		else
			return a;
	}
	
	
	private static Vector2D subtract(Vector2D a, Vector2D b){
		return  a.subtractWith(b);
	}
}
