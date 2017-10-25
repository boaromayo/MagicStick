package scene;

import java.awt.Graphics;

public abstract class Scene {
	protected SceneManager sceneMgr;
	
	public abstract void updateGame();
	public abstract void renderGame(Graphics g);
	
	/*public void delay(int s) {
		int frames = 0;
		int count = 0;
		
		if (count < s) {
			frames++;
			if (frames > 60) {
				frames = 0;
				count++;
				System.out.println(count);
			}
		}
	}*/
}
