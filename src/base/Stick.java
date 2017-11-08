package base;

import java.awt.*;
import java.awt.event.*;

public class Stick implements Entity {
	// POSITION.
	private float x, y;
	// SIZE.
	private int w, h, arcw = 6, arch = 6;
	// SPEED.
	private float dx;
	// TOGGLE DIRECTION.
	private boolean left, right;
	// FROZEN.
	private boolean frozen;
	// SCORE
	private int score;
	private int currentScore;
	// LIVES
	private int lives;
	
	public Stick() {
		this.x = MagicStick.WIDTH / 2;
		this.y = MagicStick.HEIGHT - (MagicStick.HEIGHT / 4);
		this.w = MagicStick.WIDTH / 8;
		this.h = MagicStick.HEIGHT / 24;
		
		this.left = this.right = false;
		this.frozen = false;
		
		this.score = 0;
		this.currentScore = 0;
		
		this.lives = 3;
	}
	
	public Stick(float x, float y) {
		this.x = x;
		this.y = y;
		this.w = MagicStick.WIDTH / 8;
		this.h = MagicStick.HEIGHT / 24;
		
		this.left = this.right = false;
		this.frozen = false;
		
		this.score = 0;
		this.currentScore = 0;
		
		this.lives = 3;
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = antialias(g);
		
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect((int)x, (int)y, 
				w, h, arcw, arch);
	}
	
	public Graphics2D antialias(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		return g2d;
	}
	
	public void update() {
		updateScore();
		checkInput();
		move();
	}
	
	private void checkInput() {
		setLeft(InputManager.keyDown(KeyEvent.VK_LEFT) || 
				InputManager.keyDown(KeyEvent.VK_A));
		setRight(InputManager.keyDown(KeyEvent.VK_RIGHT) || 
				InputManager.keyDown(KeyEvent.VK_D));
		
		if ( (InputManager.keyDown(KeyEvent.VK_LEFT) || 
				InputManager.keyDown(KeyEvent.VK_A)) && 
				(InputManager.keyDown(KeyEvent.VK_RIGHT) || 
				InputManager.keyDown(KeyEvent.VK_D)) ) {
			setLeft(false);
			setRight(false);
		}
	}
	
	public void move() {
		if (frozen) {
			setDX(0);
		} else {
			if (left) {
				setDX(-3);
			} else if (right) {
				setDX(3);
			} else {
				setDX(0);
			}
		}
		
		x += dx;

		// Check if stick's within game bounds.
		if (x < 0) {
			x = 0;
		} else if (x + w > MagicStick.WIDTH) {
			x = MagicStick.WIDTH - w;
		}
	}
	
	private void updateScore() {
		// Gradually increment the final printed score
		// instead of printing it immediately.
		if (score != currentScore) {
			if (score < currentScore) {
				score++;
			}
			if (score > currentScore) {
				score--;
			}
		}
	}
	
	public boolean collides(Drop drop) {
		if ((this.getX() + this.getWidth() > drop.getX()) &&
			(this.getX() < drop.getX() + drop.getRadius()) &&
			(this.getY() + this.getHeight() > drop.getY()) &&
			(this.getY() < drop.getY() + drop.getRadius())) {
			return true;
		}
		
		if (this.box().intersects(drop.box())) {
			return true;
		}
		
		return false;
	}
	
	public void setX(float x) { this.x = x; }
	
	public void setDX(float dx) { this.dx = dx; }
	
	public void setWidth(int w) { this.w = w; }
	
	public void setLeft(boolean left) { this.left = left; }
	
	public void setRight(boolean right) { this.right = right; }
	
	public void addScore(int i) { currentScore += i; }
	
	public void addLife() { lives += 1; }
	
	public void takeLife() { lives -= 1; }
	
	public float getX() { return x; }
	
	public float getY() { return y; }
	
	public float getWidth() { return w; }
	
	public float getHeight() { return h; }
	
	public int life() { return lives; }
	
	public int score() { return score; }
	
	public Rectangle box() { return new Rectangle((int)x, (int)y, 
			(int)w, (int)h); }
}
