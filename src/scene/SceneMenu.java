package scene;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import base.*;

public class SceneMenu extends Scene {
	// IMAGES.
	private BufferedImage title;
	// CHOICES.
	private final int START = 0;
	private final int QUIT = 1;
	private String[] menu = { "START", "QUIT" };
	// CHOICE COUNTER.
	private int menuChoice = 0;
	
	// LOCATIONS FOR ASSETS.
	private final int TITLE_X = 32;
	private final int TITLE_Y = 64;
	private final int MENU_X = (MagicStick.WIDTH / 2);
	private final int MENU_Y = (MagicStick.HEIGHT / 2) + 20;
	
	// CURSOR.
	private int cursorX = MENU_X - 10;
	private int cursorY = MENU_Y + 10;
	private int cursorW = (MagicStick.WIDTH / 8) + 24;
	private int cursorH = MagicStick.HEIGHT / 48;
	private int arc = 2;
	
	// CONSTRUCTOR.
	public SceneMenu(SceneManager sm) {
		sceneMgr = sm;
		
		title = ImageManager.get().
				loadImage("magicstick-title.png");
	}
	
	public void updateGame() {
		if (InputManager.keyPressed(KeyEvent.VK_UP) ||
				InputManager.keyPressed(KeyEvent.VK_LEFT)) {
			if (menuChoice > 0)
				menuChoice--;
			else
				menuChoice = menu.length - 1;
		} else if (InputManager.keyPressed(KeyEvent.VK_DOWN) ||
				InputManager.keyPressed(KeyEvent.VK_RIGHT)) {
			if (menuChoice < menu.length - 1)
				menuChoice++;
			else
				menuChoice = 0;
		} else if (InputManager.keyPressed(KeyEvent.VK_ENTER)) {
			branch(menuChoice);
		}
	}
	
	public void renderGame(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, MagicStick.WIDTH, MagicStick.HEIGHT);
		
		drawTitle(g2d);
		drawMenu(g2d);
		drawCursor(g2d);
	}
	
	private void drawTitle(Graphics g) {
		g.drawImage(title, TITLE_X, TITLE_Y, 
				title.getWidth(), 
				title.getHeight(), 
				null);
	}
	
	private void drawMenu(Graphics g) {
		g.setColor(Color.WHITE);
		for (int i = 0; i < menu.length; i++) {
			g.drawString(menu[i], MENU_X, MENU_Y + (40 * i));
		}
	}
	
	private void drawCursor(Graphics2D g) {
		g.setColor(Color.WHITE);
		if (menuChoice == START)
			g.fillRoundRect(cursorX, cursorY, 
				cursorW, cursorH, 
				arc, arc);
		else if (menuChoice == QUIT)
			g.fillRoundRect(cursorX, cursorY + 40, 
					cursorW, cursorH, 
					arc, arc);
	}
	
	private void branch(int choice) {
		if (choice == START) {
			sceneMgr.setScene(new SceneGame(sceneMgr));
		} else if (choice == QUIT) {
			System.out.println("Shutting down...");
			System.exit(0);
		}
	}
}
