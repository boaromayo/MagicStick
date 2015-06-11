package base;

import java.awt.*;

import java.util.*;

public class Drop implements Entity {
	
	protected float x, y, dx, dy, radius;
	protected int score;
	//private Image dropImg;
	
	public Drop() {
		Random rand = new Random();
		this.x = (float)rand.nextInt(MagicStick.WIDTH - (MagicStick.WIDTH / 4) 
				- (int)this.radius);
		this.y = -10;
		
		// Set radius.
		this.radius = 10;
		
		// Set score value.
		setScore(2);
		
		// Set the directions.
		setDY(4);
	}
	
	public Drop(float x) {
		this.x = x;
		this.y = -10;
		
		// Set radius.
		this.radius = 10;
		
		// Set score value.
		setScore(2);
		
		// Set the directions.
		setDY(4);
	}
	
	public void move() {
		// Move the ball.
		x += dx;
		y += dy;

		// Determine bounds.
		/* X-COORDINATE */
		if (x >= MagicStick.WIDTH - radius) {
			setDX(-Math.abs(getDX()));
		} else if (x < radius - (radius / 2)) {
			setDX(Math.abs(getDX()));
		}
				
		/* Y-COORDINATE */
		if (y >= MagicStick.HEIGHT - radius - (radius / 2)) {
			Random rand = new Random();
			this.x = (float)rand.nextInt(MagicStick.WIDTH - (int)this.radius);
			this.y = 0;
		} else if (y < radius - (radius * 2)) {
			setDY(Math.abs(getDY()));
		}
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2d = antialias(g);
		
		g2d.setColor(Color.CYAN);
		g2d.fillOval((int)this.x, (int)this.y, 
				(int)this.radius, (int)this.radius);
	}
	
	public Graphics2D antialias(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		return g2d; // Return Graphics2D object.
	}
	
	public void setDX(float dx) { this.dx = dx; }
	
	public void setDY(float dy) { this.dy = dy; }
	
	public void setScore(int s) { this.score = s; }
	
	public int scoreValue() { return score; }
	
	public float getX() { return x; }
	
	public float getY() { return y; }
	
	public float getDX() { return dx; }
	
	public float getDY() { return dy; }
	
	public float getRadius() { return radius; }
	
	public Rectangle box() { return new Rectangle((int)this.x, (int)this.y, 
			(int)this.radius, (int)this.radius); }
}
