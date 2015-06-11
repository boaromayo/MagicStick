package base;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

public class MagicStick extends JPanel {
	
	/*============================================*/
	/* VARIABLES.
	/* ===========================================*/
	// CONSTANTS FOR WIDTH AND HEIGHT.
	public static final int WIDTH = 480;
	public static final int HEIGHT = 320;
	// CONSTANTS FOR GAME THREAD.
	private final int FPS = 24;
	private long timeTarget = 1000/FPS;
	// DOUBLE BUFFERING VARIABLES.
	private Image dbi;
	private Graphics dbg;
	
	// SCORE.
	private int score;
	
	// STICK OBJECT.
	private Stick stick;
	// DROP ARRAYLIST OBJECT.
	private LinkedList<Drop> drops;
	//private DropLarge dropLrg;
	
	/*====================================*/
	/* CONSTRUCTOR for MagicStick().
	/*====================================*/
	public MagicStick() {
		initGame();
		
		Thread t = new Thread() {
			public void run() {
				try {
					while (true) {
						updateGame();
						renderGame();
						repaint();
						Thread.sleep(timeTarget);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
		};
		
		t.start();
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent kpe) {
				stick.keyPressed(kpe);
				
				switch(kpe.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent kre) {
				stick.keyReleased(kre);
			}
		});
		
		setFocusable(true);
	}
	
	/*=========================================*/
	/* initGame(), updateGame(), renderGame()
	/*=========================================*/
	public void initGame() {
		score = 0;
		
		stick = new Stick();
		drops = new LinkedList<Drop>();
		//dropLrg = new DropLarge();
	}
	
	public void updateGame() {
		addDrops();
		checkCollisions();
	}
	
	public void renderGame() {
		stick.move(); // Get stick input.
		
		for (int drop = 0; drop < drops.size(); drop++) {
			drops.get(drop).move(); // Make drops move.
		}
		
	}
	
	protected void addDrops() {
		long tickTime = System.nanoTime();
		double rand = Math.random();
		
		// Start launching the Magic Drops!!
		if (tickTime % 50 == 0) {
			drops.add(new DropLarge((int)rand % getWidth() - 
					(getWidth() / 2) ));
		}
		if (tickTime % 1000 == 0) {
			drops.add(new DropSmall((int)rand % getWidth() - 
					(getWidth() / 8) ));
		}
		if (tickTime % 500 == 0) {
			drops.add(new Drop((int)rand % getWidth() - 
					(getWidth() / 4) ));
		}
		
 	}
	
	protected void checkCollisions() {
		for (int drop = 0; drop < drops.size(); drop++) {
			if (stick.collides(drops.get(drop))) {
				score += drops.get(drop).scoreValue();
				drops.remove(drops.get(drop));
			}
			
		}
	}
	
	@Override
	public void paint(Graphics g) {
		dbi = createImage(getWidth(), getHeight()); // Create the image.
		dbg = dbi.getGraphics(); // Graphics var gets graphics from image.
		draw(dbg); // Call draw() method.
		g.drawImage(dbi, 0, 0, this); // Draw the image of the graphics
	}
	
	public void draw(Graphics g) {
		// Convert Graphics var to Graphics2D and turn anti-alias on.
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		for (int drop = 0; drop < drops.size(); drop++) {
			drops.get(drop).draw(g2d); // Draw in drops.
		}
		//dropLrg.draw(g2d);
		
		stick.draw(g2d);
		
		g2d.setColor(Color.WHITE);
		
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.drawString(Integer.toString(score), getWidth() - (getWidth() - 20), 
				getHeight() - (getHeight() - 30));
		// Dispose after done with graphics.
		g2d.dispose();
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MagicStick m = new MagicStick(); // call BallDemo object.
				JFrame frm = new JFrame();
				
				frm.setTitle("MagicStick Demo"); // Set the title.
				frm.add(m);
				
				frm.setSize(WIDTH, HEIGHT); // Set size 480 x 320.
				// Close when exit clicked.
				frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frm.setLocationRelativeTo(null); // Center frame.
				frm.setVisible(true);
				frm.setResizable(false); // Do not resize image.
			}
		});
	}
}
