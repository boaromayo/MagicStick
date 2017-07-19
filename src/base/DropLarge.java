package base;

import java.awt.*;

import java.util.*;

public class DropLarge extends Drop {

	//private Image dropLrgImg;
	
	public DropLarge() {
		Random rand = new Random();
		this.x = rand.nextInt(MagicStick.WIDTH - (MagicStick.WIDTH / 2) 
				- (int)this.radius);
		this.y = -10;
		this.radius = 20;
		
		// Set score value.
		setScore(1);
		
		// Set falling speed.
		setDY(2);
	}
	
	public DropLarge(float x) {
		this.x = x;
		this.y = -10;
		this.radius = 20;
		
		// Set score value.
		setScore(1);
		
		// Set falling speed.
		setDY(2);
	}
	
	public DropLarge(float x, float spd, int score) {
		this.x = x;
		this.y = -10;
		
		// Set radius.
		this.radius = 10;
		
		// Set score value.
		setScore(score);
		
		// Set falling speed.
		setDY(spd);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = antialias(g);
		
		// Draw a large green drop.
		g2d.setColor(Color.GREEN);
		g2d.fillOval((int)this.x, (int)this.y, 
				(int)this.radius, (int)this.radius);
	}
}
