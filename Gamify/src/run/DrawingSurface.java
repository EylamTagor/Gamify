package run;

import processing.core.PApplet;
import tagor.shapes.Circle;

public class DrawingSurface extends PApplet {

	private Circle edit, load, save;

	public DrawingSurface() {
		edit = new Circle(600, 50, 25);
		load = new Circle(675, 50, 25);
		save = new Circle(750, 50, 25);
	}

	public void draw() {
		background(255, 255, 255);

		fill(100);

		edit.draw(this);
		load.draw(this);
		save.draw(this);

		if (load.isPointInside(mouseX, mouseY)) {
			load.setStroke(200, 200, 200);
			edit.setStroke(100, 100, 100);
			save.setStroke(100, 100, 100);
		} else if (save.isPointInside(mouseX, mouseY)) {
			save.setStroke(200, 200, 200);
			edit.setStroke(100, 100, 100);
			load.setStroke(100, 100, 100);
		} else if (edit.isPointInside(mouseX, mouseY)) {
			edit.setStroke(200, 200, 200);
			load.setStroke(100, 100, 100);
			save.setStroke(100, 100, 100);
		} else {
			edit.setStroke(100, 100, 100);
			load.setStroke(100, 100, 100);
			save.setStroke(100, 100, 100);
		}

		text("EDIT", 587, 55);
		text("LOAD", 660, 55);
		text("SAVE", 735, 55);
	}

	public void mousePressed() {
		if (load.isPointInside(mouseX, mouseY)) {
			load.setStroke(20, 20, 20);
			edit.setStroke(10, 10, 10);
			save.setStroke(10, 10, 10);
		} else if (save.isPointInside(mouseX, mouseY)) {
			save.setStroke(20, 20, 20);
			edit.setStroke(10, 10, 10);
			load.setStroke(10, 10, 10);
		} else if (edit.isPointInside(mouseX, mouseY)) {
			edit.setStroke(20, 20, 20);
			load.setStroke(10, 10, 10);
			save.setStroke(10, 10, 10);
		} else {
			edit.setStroke(10, 10, 10);
			load.setStroke(10, 10, 10);
			save.setStroke(10, 10, 10);
		}
	}

}
