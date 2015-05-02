package Test;

import GameObject.Circle;



public class Main  {
	public static GameThread run;
	public static Circle c1;
	public static Circle c2;
	public static Circle c3;
	public static void main(String[] args) {
		GameThread run = new GameThread();
		run.start();
		c1 = new Circle(50 ,150, 50, new Float(0.5), 10);
		c2 = new Circle(500, 20, 150, new Float(0.81),200);
		c3 = new Circle(550, 1500, 150, new Float(0.85), 250);
		run.addObserver(c1);
		run.addObserver(c2);
		run.addObserver(c3);
		run.addObserver(new Physics());
	}
	
	
}
