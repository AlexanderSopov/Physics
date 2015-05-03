package Physics;

import java.awt.geom.Point2D;

public class Vector2D extends Point2D {

	private double x;
	private double y;
	
	public Vector2D(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public Vector2D(Point2D p){
		this(p.getX(), p.getY());
	}
	
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
	

	@Override
	public void setLocation(double x, double y) {
		this.x=x;
		this.y=y;
	}

	public void setX(double d){
		x=d;
	}
	
	public void setY(double d){
		y=d;
	}
	
	
	
	//Vector Algebra
	
	public Vector2D scale(double s){
		return new Vector2D(x*s, y*s);
	}
	
	public double length(){
		return distance(0,0);
	}
	
	public double lengthSquared(){
		return x*x+y*y;
	}

	public double dotProduct(Point2D v){
		return x*v.getX() + y*v.getY();
	}
	
	public Vector2D addWith(Point2D p){
		return new Vector2D(x+p.getX(), y+p.getY());
	}
	
	public Vector2D subtractWith(Point2D p){
		return new Vector2D(x-p.getX(), y-p.getY());
	}
	
	public Vector2D returnNegative(){
		return new Vector2D(-x,-y);
	}
	
	public String toString(){
		return "x = " + x + ". y = " + y;
	}
	
	public Vector2D makeUnitVector(){
		double length = length();
		return new Vector2D(x/length, y/length);
	}
	
	
}
