package base;

import java.awt.*;

public interface Entity {

	public void update();
	public void render(Graphics g);
	public Graphics2D antialias(Graphics g);
	
	public void setDX(float dx);
	public float getX();
	public float getY();
	public Rectangle box();
	
	
}
