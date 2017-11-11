package base;

import java.awt.*;

public abstract class Powerup implements Entity {

	protected float x, y, dx, dy, width, height;
	protected int duration;
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public Graphics2D antialias(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		return g2d; // Return Graphics2D object.
	}
	
	public abstract void effect(Stick s);
	
	public void setDX(float dx) { this.dx = dx; }
	
	public void setDY(float dy) { this.dy = dy; }
	
	public void setDuration(int d) { duration = d; }
	
	public float getX() { return x; }
	
	public float getY() { return y; }
	
	public float getDX() { return dx; }
	
	public float getDY() { return dy; }
	
	public Dimension getSize() { return new Dimension((int)width, (int)height); }
	
	public float getWidth() { return getSize().width; }
	
	public float getHeight() { return getSize().height; }
	
	public int getDuration() { return duration; }
	
	public Rectangle box() { return new Rectangle((int)x, (int)y, 
			(int)width, (int)height); }
}
