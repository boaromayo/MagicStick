package base;

import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

public class ImageManager {
	// SINGLETON OBJECT.
	private static ImageManager singleton = null;
	
	private ImageManager() {}
	
	public static ImageManager get() {
		if (singleton == null) {
			synchronized (ImageManager.class) {
				if (singleton == null) {
					singleton = new ImageManager();
				}
			}
		}
		
		return singleton;
	}
	
	public BufferedImage loadImage(String path) {
		// Load image from classpath assets
		System.out.println("Loading image " + path + "...");
		String src = "/assets/img/";
		try {
			BufferedImage img;
			img = ImageIO.read(getClass().getResourceAsStream(src + path));
			return img;
		} catch(FileNotFoundException e) {
			System.err.println("ERROR: Unable to find file " + path + 
					".\nREASON: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERROR: Unable to load image " + path + 
					".\nREASON: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public BufferedImage loadImageFromFile(String path) {
		// Load external image
		System.out.println("Loading image from file " + path + "...");
		File file = null;
		FileInputStream fis = null;
		BufferedImage img;
		try {
			file = new File(path);
			fis = new FileInputStream(file);
			img = ImageIO.read(fis);
			return img;
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: Unable to find file " + path + 
					".\nREASON: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("ERROR: Unable to load image " + path + 
					".\nREASON: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				// Close input stream whether img loading successful or not.
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				System.err.println("ERROR: Unable to close file input stream.\n" + 
						"REASON: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return null;
	}
}
