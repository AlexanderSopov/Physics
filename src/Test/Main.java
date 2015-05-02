package Test;

import GameObject.Circle;



public class Main  {
	public static GameThread run;
	public static Circle c1;
	public static Circle c2;
	public static void main(String[] args) {
		GameThread run = new GameThread();
		run.start();
		c1 = new Circle(50 ,150, 50, new Float(1), 10);
		c2 = new Circle(500, 20, 150, new Float(1),200);
		run.addObserver(c1);
		run.addObserver(c2);
		run.addObserver(new Physics());
	}
	
	
}
