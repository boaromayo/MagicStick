package scene;

import java.awt.Graphics;

public class SceneManager {
	// SINGLETON OBJECT.
	private static SceneManager singleton = null;
	
	private final int SIZE = 2;
	
	private int index;
	
	private Scene[] scenes;
	
	private Scene currentScene;
	
	private SceneManager() {}
	
	public static SceneManager get() {
		if (singleton == null) {
			singleton = new SceneManager();
			if (singleton == null)
				singleton = new SceneManager();
			
			return singleton;
		}
		
		return null;
	}
	
	public void initGame() {
		scenes = new Scene[SIZE];
		index = -1;
		
		setScene(new SceneGame());
	}
	
	public void setScene(Scene scene) {
		currentScene = scene;
	}
	
	public void saveScene() {
		scenes[++index] = currentScene;
	}
	
	public void removeScene() {
		currentScene = scenes[index];
		scenes[index--] = null;
	}
	
	public Scene getCurrentScene() {
		if (currentScene == null)
			return null;
		
		return currentScene;
	}
	
	public void updateGame() {
		if (currentScene != null)
			currentScene.updateGame();
	}
	
	public void renderGame(Graphics g) {
		if (currentScene != null)
			currentScene.renderGame(g);
	}
	
	public void clear() {
		while (scenes != null || index >= 0) {
			removeScene();
		}
	}
}
