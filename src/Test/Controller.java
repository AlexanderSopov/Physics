package Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameObject.Box;
import GameObject.Circle;

public class Controller implements KeyListener {


	public Controller() {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_R:
				reset();
				break;
			case KeyEvent.VK_1:
				reset();
				break;
			case KeyEvent.VK_2:
				heavyMetalBallOfDoomNGlory();
				break;
			case KeyEvent.VK_3:
				boxesShallCollide();
				break;
			case KeyEvent.VK_4:
				otherSide();
				break;
			case KeyEvent.VK_P:
				heavyMetalBallOfDoomNGlory();
				break;
			case KeyEvent.VK_D:
				eraseMe();
				break;
			case KeyEvent.VK_C:
				Main.c1.setMass(1000);
				break;
		}
	}



	private void otherSide() {
		resetMass();
		eraseMe();
		
		Main.c1.setLocation(0,30);
		Main.c2.setLocation(75,500);
		Main.c3.setLocation(950,1150);
		
		Main.c1.setVelocity(9,-1.5);
		Main.c2.setVelocity(8,-8);
		Main.c3.setVelocity(-1.25, -12.5);
	}

	private void boxesShallCollide() {
		eraseMe();
		Main.b1.setLocation(1250,300);
		Main.b2.setLocation(50,600);
		Main.b1.setVelocity(-10,5);
	}

	private void eraseMe() {
		Main.c1.setLocation(0,1030);
		Main.c2.setLocation(75,1000);
		Main.c3.setLocation(950,1000);
		Main.b1.setLocation(0,1000);
		Main.b2.setLocation(75,1000);
		Main.c1.setVelocity(0,0);
		Main.c2.setVelocity(0,0);
		Main.c3.setVelocity(0,0);
		Main.b1.setVelocity(0,0);
	}

	private void heavyMetalBallOfDoomNGlory() {

		eraseMe();
		Main.c1.setMass(1000);
		resetPosition();
		resetVelocity();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Do nothing
	}
	
	private void reset() {
		resetMass();
		resetPosition();
		resetVelocity();
	}

	private void resetMass() {
		Main.c1.setMass(75);
		Main.c2.setMass(750);
		Main.c3.setMass(750);
	}

	private void resetVelocity() {
		Main.c1.setVelocity(5.5,-1.25);
		Main.c2.setVelocity(0,0);
		Main.c3.setVelocity(-1.25, -12.5);
	}

	private void resetPosition() {
		Main.c1.setLocation(0,-30);
		Main.c2.setLocation(750,150);
		Main.c3.setLocation(950,1150);
	}

}

