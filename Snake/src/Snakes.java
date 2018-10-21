import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.*;
public class Snakes {
	
	
	
	public static void main(String[] args) {
		
		String fileName = args[0];
		DrawingSurface drawing = new DrawingSurface(fileName);
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas)surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();
		drawing.setJFrame(window);
		window.setSize(500, 500);
		window.setLocation(500,0);
		window.setMinimumSize(new Dimension(400,300));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setSize(500, 500);
		window.setLocation(500,0);

		window.setVisible(true);
		canvas.requestFocus();

	}
	
	

}
