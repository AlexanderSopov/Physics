package GameObject;


import Vector.Vector2D;

/**
*
* @author Alexander Sopov
*/
public class StaticBox extends Box {
	
	public StaticBox(int x, int y, int width, int height, double r, int mass) {
		super(x, y, width, height, r, mass);
	}


	
	@Override
	public void update(){
		setLocation(getLocation().addWith(new Vector2D(0,0)));
	}
	
	public Vector2D getVelocity(){
		return new Vector2D(0,0);
	}

}
