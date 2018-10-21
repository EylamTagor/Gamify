package games.match;

import java.awt.Dimension;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Match {

	public static void main(String args[]) {
		MatchDrawingSurface drawing = new MatchDrawingSurface(args[0]);
//		MatchDrawingSurface drawing = new MatchDrawingSurface("studysettest.txt");
		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		window.setLocation(100, 100);
		window.setSize(800, 600);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setLocation(100, 100);
		window.setSize(800, 600);

		window.setVisible(true);
		canvas.requestFocus();
	}

}
