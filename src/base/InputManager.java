package base;

import java.awt.event.*;

public class InputManager {
	// KEY CONSTANTS.
	private static final int W = 0;
	private static final int A = 1;
	private static final int S = 2;
	private static final int D = 3;
	private static final int UP = 4;
	private static final int LEFT = 5;
	private static final int RIGHT = 6;
	private static final int DOWN = 7;
	private static final int P = 8;
	
	private static final int NUM_KEYS = 9;
	
	private static final boolean[] keyPrevious = new boolean[NUM_KEYS];
	private static final boolean[] keyNow = new boolean[NUM_KEYS];
	
	// Converts key codes to constants.
	private static int checkKey(int key) {
		int newKey = 0;
		switch(key) {
		case KeyEvent.VK_W:
			newKey = W;
			break;
		case KeyEvent.VK_A:
			newKey = A;
			break;
		case KeyEvent.VK_S:
			newKey = S;
			break;
		case KeyEvent.VK_D:
			newKey = D;
			break;
		case KeyEvent.VK_UP:
			newKey = UP;
			break;
		case KeyEvent.VK_LEFT:
			newKey = LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			newKey = RIGHT;
			break;
		case KeyEvent.VK_DOWN:
			newKey = DOWN;
			break;
		case KeyEvent.VK_P:
			newKey = P;
			break;
		}

		return newKey;
	}
	
	public static void setKey(int key, boolean set) {
		int i = checkKey(key);
		keyNow[i] = set;
	}
	
	public static boolean keyPressed(int key) {
		int i = checkKey(key);
		return keyNow[i];
	}
	
	public static void updateGame() {
		for (int i = 0; i < keyNow.length; i++)
			keyPrevious[i] = keyNow[i];
	}
}
