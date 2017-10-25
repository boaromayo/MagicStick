package base;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;

import scene.*;

public class MagicStick extends JPanel {
	// SIZE CONSTANTS.
	public static final int WIDTH = 480;
	public static final int HEIGHT = 320;
	
	// CONSTANTS FOR GAME THREAD.
	private final int FPS = 24;
	private long timeTarget = 1000/FPS;
	
	// DRAWING VARIABLES.
	private Image dbi;
	private Graphics dbg;
	
	// SCENE MANAGER.
	private SceneManager sceneMgr;
	
	// GAME CHECKERS.
	private boolean running = false;
	
	/*====================================*/
	/* CONSTRUCTOR for MagicStick().
	/*====================================*/
	public MagicStick() {
		initGame();
		
		Thread t = new Thread() {
			public void run() {
				try {
					while (running) {
						updateGame();
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
				int key = kpe.getKeyCode();
				InputManager.setKey(key, true);
				
				switch(key) {
				case KeyEvent.VK_ESCAPE:
					running = false;
					System.exit(0);
					break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent kre) {
				int key = kre.getKeyCode();
				InputManager.setKey(key, false);
			}
		});
		
		setFocusable(true);
		requestFocusInWindow();
	}
	
	/*=========================================*/
	/* initialize, update, and render game.
	/*=========================================*/
	public void initGame() {
		sceneMgr = SceneManager.get();
		sceneMgr.initGame();
		
		running = true;
	}
	
	public void updateGame() {
		sceneMgr.updateGame();
		InputManager.updateGame();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (dbi == null) {
			dbi = createImage(WIDTH, HEIGHT);
			if (dbi == null) {
				System.err.println("ERROR: Buffer image is null.");
				System.exit(1); // There's no image to see here...
			} else {
				dbg = dbi.getGraphics();
			}
		}
		
		renderGame(dbg);
		g.drawImage(dbi, 0, 0, this);
	}
	
	public void renderGame(Graphics g) {
		sceneMgr.renderGame(g);
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//System.out.println("MagicStick - v0.5.0 loading frame...");
				
				MagicStick m = new MagicStick(); // make the game object.
				JFrame frm = new JFrame();
				
				frm.setTitle("MagicStick"); // Set the title.
				frm.add(m);
				
				frm.setSize(WIDTH, HEIGHT); // Set size 480 x 320.
				
				frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close program when exit clicked.
				frm.setLocationRelativeTo(null); // Center frame.
				frm.setResizable(false);
				frm.setVisible(true);
				
				//System.out.println("MagicStick - v0.5.0 running...");
			}
		});
	}
}
