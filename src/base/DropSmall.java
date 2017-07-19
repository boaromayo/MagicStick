package base;

import java.awt.*;

import java.util.*;

public class DropSmall extends Drop {
	
	//private Image dropSmlImg;
	
	public DropSmall() {
		Random rand = new Random();
		this.x = (float)rand.nextInt(MagicStick.WIDTH - (MagicStick.WIDTH / 4) 
				- (int)this.radius);
		this.y = -10;
		this.radius = 5;
		
		// Set score value.
		setScore(5);
		
		// Set falling speed.
		setDY(8);
	}
	
	public DropSmall(float x) {
		this.x = x;
		this.y = -10;
		
		// Set radius.
		this.radius = 5;
		
		// Set score value.
		setScore(5);
		
		// Set falling speed.
		setDY(8);
	}
	
	public DropSmall(float x, float spd, int score) {
		this.x = x;
		this.y = -10;
		
		// Set radius.
		this.radius = 5;
		
		// Set score value.
		setScore(score);
		
		// Set falling speed.
		setDY(spd);
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = antialias(g);
		
		// Draw an orange drop.
		g2d.setColor(Color.ORANGE);
		g2d.fillOval((int)this.x, (int)this.y, 
				(int)this.radius, (int)this.radius);
	}
}
