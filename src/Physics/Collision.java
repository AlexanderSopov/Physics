package Physics;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.RectangularShape;

import Vector.Vector2D;
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
		double invMassA = 1/(double)a.mass;
		double invMassB = 1/(double)b.mass;
		
		
		Vector2D normal = (subtract(b.getCenter(), a.getCenter())).makeUnitVector();
		Vector2D relativeVelocity = subtract(b.getVelocity(),a.getVelocity());
		
		double velocityAlongNormalVector = relativeVelocity.dotProduct(normal);
		
		
		// Printing for error-checking!
		System.out.println(
				"inverse Mass A = " + invMassA+"\n"+
				"inverse Mass B = " + invMassB+"\n"+
				"aVel = " + a.getVelocity().toString() +"\n"+
				"bVel = " + b.getVelocity().toString()+"\n"+
				"normal = " + normal.toString()+"\n"+
				"relativeVel = " + relativeVelocity.toString() +"\n"+
				"Relative velocity along normal = " + velocityAlongNormalVector
				);
		
		
		
		if (velocityAlongNormalVector == 0)	// true if objects are already moving away from each other
			return; 						// and thus, we break.
		
		
		double e = min(a.restitution, b.restitution); // the object with less "bounciness" wins
		
		
		double j = -(1 + e) * velocityAlongNormalVector; //
		
		System.out.println("j = " + j);
		
		j /= (invMassA + invMassB);
		
		System.out.println("j2 = " + j);
		
		Vector2D impulse = normal.scale(j);
		
		a.setVelocity(a.getVelocity().subtractWith(
				impulse.scale(invMassA)
				));
		
		b.setVelocity(b.getVelocity().addWith(
				impulse.scale(invMassB)
				));
		
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
