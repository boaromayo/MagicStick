package base;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class Stick implements Entity {
	
	private float x, y;
	private float w, h;
	private float dx;
	
	public Stick() {
		this.x = MagicStick.WIDTH / 2 - w;
		this.y = MagicStick.HEIGHT - (MagicStick.HEIGHT / 4);
		this.w = MagicStick.WIDTH / 8;
		this.h = MagicStick.HEIGHT / 24;
		
	}
	
	public Stick(float x, float y) {
		this.x = x;
		this.y = y;
		this.w = MagicStick.WIDTH / 8;
		this.h = MagicStick.HEIGHT / 24;
		
	}
	
	public Stick(float x, float y, float w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = MagicStick.HEIGHT / 30;
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = antialias(g);
		
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect((int)this.x, (int)this.y, 
				(int)this.w, (int)this.h, 6, 6);
	}
	
	public Graphics2D antialias(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		return g2d;
	}
	
	public void move() {
		this.x += dx;

		if (this.x < 0) {
			this.x = 0;
		} else if (this.x + this.w > MagicStick.WIDTH) {
			this.x = MagicStick.WIDTH - this.w;
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
	
	public void setWidth(float w) { this.w = w; }
	
	public float getX() { return x; }
	
	public float getY() { return y; }
	
	public float getWidth() { return w; }
	
	public float getHeight() { return h; }
	
	public Rectangle box() { return new Rectangle((int)this.x, (int)this.y, 
			(int)this.w, (int)this.h); }
	
	public void keyPressed(KeyEvent kpe) {
		switch (kpe.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			// MOVE LEFT
			setDX(-6);
			break;
		case KeyEvent.VK_RIGHT:
			// MOVE RIGHT
			setDX(6);
			break;
		}
	}
		
	public void keyReleased(KeyEvent kre) {
		switch (kre.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			// STOP
			setDX(0);
			break;
		case KeyEvent.VK_RIGHT:
			// STOP
			setDX(0);
			break;
		}
	}
	
}
