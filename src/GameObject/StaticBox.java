package GameObject;

import Vector.Vector2D;

public class StaticBox extends Box {
	
	public StaticBox(int x, int y, int width, int height, float r, int mass) {
		super(x, y, width, height, r, mass);
	}

	@Override
	public void update(){
		setVelocity(new Vector2D(0,-1));
		getLocation().addWith(getVelocity());
	}

}
