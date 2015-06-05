package Physics;

import java.awt.Graphics2D;

import GameObject.Box;
import Vector.Vector2D;

/**
*
* @author Alexander Sopov
*/
public class BoxVsBox implements CollisionDetective {
	private final Box a;
	private final Box b;
	private Vector2D normal;
	private Vector2D velocityNormal;
	private double invasionOnX;
	private double invasionOnY;
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
		invasionOnX = halfWidths - abs(normal.getX());
		invasionOnY = halfHeights - abs(normal.getY());
		return isTherePenetration();
	}
	
	@Override
	public void resolveCollision() {
		velocityNormal = b.getVelocity().addWith(
				a.getVelocity());
		double penetration = getPenetration();
		setNormal();
		double rVelocityLength = velocityNormal.dotProduct(normal);
		if (rVelocityLength == 0)
			return;
		//setVelocityNormal();
		correctBoxes(rVelocityLength, penetration);
		
	}



	private double stop() {
		normal = new Vector2D (0,0);
		velocityNormal = new Vector2D(0,0);
		return 0.0;
	}

	private double getPenetration() {
		if(xInvasionIsSmaller())
			return invasionOnX;
		return invasionOnY;
	}
	
	private void setNormal(){
		if (xInvasionIsSmaller()){
			if(normal.getX() < 0)
				normal = new Vector2D(-1,0);
			else
				normal = new Vector2D(1,0);
		}else{
			if(normal.getY() < 0)
				normal = new Vector2D(0,-1);
			else
				normal = new Vector2D(0,1);
		}
	}
	private boolean xInvasionIsSmaller() {
		return invasionOnX < invasionOnY;
	}

	private Boolean isTherePenetration() {
		return invasionOnX > 0 && invasionOnY > 0;
	}


	private double abs(double x) {
		if(x < 0)
			return -x;
		return x;
	}



	private void correctBoxes(double velocityLength, double penetration) {
		correctPositions(penetration);
		Vector2D impulse = calculateImpulse(normal, velocityLength);
		setVelocityToRatio(impulse);
	}
	

	private void correctPositions(double penetration) {
		double percent = 0.2;
		double slop = 0.01;
		double corr;
		double sumMass = a.mass + b.mass;
		double ratio = a.mass / sumMass;
		if (penetration - slop < 0.0)
			corr = 0;
		else
			corr = penetration - slop;
		
		Vector2D correction = normal.scale(percent*corr*ratio);
		a.setLocation((correction.subtractWith(a.getLocation())).returnNegative());
		ratio = b.mass / sumMass;
		correction = normal.scale(percent*corr*ratio);
		b.setLocation(correction.addWith(b.getLocation()));

		
	}
	
	


	private void setVelocityToRatio(Vector2D impulse) {
		Vector2D scaledImpulse = impulse.scale(a.invMass);
		//System.out.println("scaledImpulse.Y = " + scaledImpulse.getY());
		double frictionSum = 0.9;
		Vector2D newVelocity = a.getVelocity().addWith(scaledImpulse).scale(frictionSum);
		//System.out.println("newVelocity: " + newVelocity.toString());
		a.setVelocity(newVelocity);

		scaledImpulse = impulse.scale(b.invMass);
		newVelocity = b.getVelocity().subtractWith(scaledImpulse).scale(frictionSum);
		b.setVelocity(newVelocity);
	}
	
	
	private Vector2D calculateImpulse(Vector2D velocityNormal, double velocityLength) {
		double e = min(a.restitution, b.restitution); // the object with less "bounciness" wins
		double j = -(1 + e) * (velocityLength); // calculate an impulse scalar
		
		//System.out.println("J = " + j + ". velocityNormal: " + velocityNormal.toString());
		j /= (a.invMass + b.invMass);
		
		return velocityNormal.scale(j);
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
