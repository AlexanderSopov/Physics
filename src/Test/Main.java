package Test;

/**
*
* @author Alexander Sopov
*/

import GameObject.Box;
import GameObject.Circle;
import GameObject.StaticBox;



public class Main  {
	public static GameThread run;
	public static Circle c1;
	public static Circle c2;
	public static Circle c3;
	public static Box b1;
	public static StaticBox b2;
	
	
	public static void main(String[] args) {
		GameThread run = new GameThread();
		run.start();

		c1 = new Circle(0, 1000, 50, new Float(0.5), 75);
		c2 = new Circle(750, 1050, 150, new Float(0.7),1500);
		c3 = new Circle(950, 1150, 150, new Float(0.85), 800);

		
		b1 = new Box(1250,1000,150, 80,  0.3, 180);
		b2 = new StaticBox(50, 1000, 1500, 50, 0.35, 0);


		run.addObserver(b1);
		run.addObserver(b2);

		run.addObserver(c1);
		run.addObserver(c2);
		run.addObserver(c3);
		
		run.addObserver(new Handler());
	}
	
	
}
