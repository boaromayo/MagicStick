package base;

import java.awt.*;

import java.util.*;

public class Star extends Drop {

	//private BufferedImage starImg;
	
	public Star() {
		// TODO Auto-generated constructor stub
		Random rand = new Random();
		this.x = rand.nextInt(MagicStick.WIDTH - (MagicStick.WIDTH / 2) 
				- (int)this.radius);
		this.y = -10;
		this.radius = 20;
		
		setScore(0);
		
		// Set falling speed.
		setDY(4);
	}

	public Star(float x) {
		this.x = x;
		this.y = -10;
		
		// Set radius.
		this.radius = 20;
		
		setScore(0);
		
		// Set falling speed.
		setDY(4);
	}

	public void render(Graphics g) {
		Graphics2D g2d = antialias(g);
		
		// Draw a flashing-colored star.
		Random randColor = new Random();
		
		drawRandomColors(g2d, randColor);
		
		/*int[] xarr = {(int)this.radius / 2};
		int[] yarr = {};
		
		g2d.drawPolygon(xarr, yarr, 5);*/
		
		g2d.fillRect((int)this.x, (int)this.y,
				(int)this.radius, (int)this.radius);
	}
	
	public void effect(Stick s) {
		s.takeLife();
	}
	
	private void drawRandomColors(Graphics g, Random r) {
		int color = r.nextInt(9);
		
		switch(color) {
		case 0:
			g.setColor(Color.RED);
			break;
		case 1:
			g.setColor(Color.BLUE);
			break;
		case 2:
			g.setColor(Color.GREEN);
			break;
		case 3:
			g.setColor(Color.YELLOW);
			break;
		case 4:
			g.setColor(Color.CYAN);
			break;
		case 5:
			g.setColor(Color.MAGENTA);
			break;
		case 6:
			g.setColor(Color.ORANGE);
			break;
		case 7:
			g.setColor(Color.PINK);
			break;
		default:
			g.setColor(Color.WHITE);
			break;
		}
	}
}
