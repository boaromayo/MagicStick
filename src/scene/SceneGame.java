package scene;

import java.awt.*;
import java.util.*;

import base.*;

public class SceneGame extends Scene {
	// STICK OBJECT.
	private Stick stick;
	// DROP ARRAYLIST OBJECT.
	private ArrayList<Drop> drops;
	// PAUSE TOGGLE.
	private boolean paused = false;
	
	public SceneGame(SceneManager sm) {
		sceneMgr = sm;
		
		stick = new Stick();
		drops = new ArrayList<Drop>();
	}
	
	public void updateGame() {
		addDrops();
		checkCollisions();
		
		stick.update(); // Get stick input.
		
		for (int i = 0; i < drops.size(); i++) {
			Drop drop = drops.get(i);
			drop.update(); // Make drops move.
		}
	}
	
	public void renderGame(Graphics g) {
		// Convert to Graphics2D and turn anti-alias on.
		Graphics2D g2d = antialias(g);
		
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, MagicStick.WIDTH, MagicStick.HEIGHT);
				
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		
		// Draw stick.
		stick.render(g2d);
		
		// Draw in drops.
		for (int i = 0; i < drops.size(); i++) {
			Drop drop = drops.get(i);
			drop.render(g2d);
		}
		
		// Draw player's score.
		g2d.setColor(Color.WHITE);
		g2d.drawString(Integer.toString(stick.score()), 
				MagicStick.WIDTH - (MagicStick.WIDTH - 20), 
				MagicStick.HEIGHT - (MagicStick.HEIGHT - 30));
	}
	
	private Graphics2D antialias(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		return g2d;
	}
	
	// TODO: Refine this method in later releases.
	/*private void addDrop(int rand) {
		// Add a new drop if one is taken or is off-screen.
		if (rand % 30 == 0) {
			drops.add(new DropLarge((int)rand % MagicStick.WIDTH - 
					(MagicStick.WIDTH / 2) ));
		}
		if (rand % 100 == 0) {
			drops.add(new Drop((int)rand % MagicStick.WIDTH - 
					(MagicStick.WIDTH / 4) ));
		}
		if (rand % 300 == 0) {
			drops.add(new DropSmall((int)rand % MagicStick.WIDTH - 
					(MagicStick.WIDTH / 8) ));
		}
	}*/
	
	private void addDrops() {
		long tickTime = System.nanoTime();
		double rand = Math.random();
		
		// Start launching the Magic Drops!!
		if (tickTime % 50 == 0) {
			drops.add(new DropLarge((int)rand % MagicStick.WIDTH - 
					(MagicStick.WIDTH / 2) ));
		}
		if (tickTime % 1200 == 0) {
			drops.add(new DropSmall((int)rand % MagicStick.WIDTH - 
					(MagicStick.WIDTH / 8) ));
		}
		if (tickTime % 500 == 0) {
			drops.add(new Drop((int)rand % MagicStick.WIDTH - 
					(MagicStick.WIDTH / 4) ));
		}
		
 	}
	
	private void checkCollisions() {
		for (int i = 0; i < drops.size(); i++) {
			Drop drop = drops.get(i);
			if (stick.collides(drop)) {
				stick.addScore(drop.scoreValue());
				//drop.setVisible(false);
				drops.remove(drop);
			}
		}
	}
	
	public void setPaused(boolean p) { paused = p; }
}
