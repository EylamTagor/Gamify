package games.tetris;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PShape;

public class Tetris {

	public static void main(String args[]) {
//		TetrisDrawingSurface drawing = new TetrisDrawingSurface(args[0]);
		TetrisDrawingSurface drawing = new TetrisDrawingSurface("studysettest.txt");

		PApplet.runSketch(new String[] { "" }, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();

		window.setBounds(300, 50, 400, 600);
		window.setMinimumSize(new Dimension(200, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		canvas.requestFocus();

	}
}
