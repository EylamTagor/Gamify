package games.match;

import base.StudySet;
import processing.core.PApplet;
import tagor.shapes.Rectangle;

public class MatchDrawingSurface extends PApplet {

	private Rectangle[] rects;
	private String[] terms, defs;
	private StudySet set;
	private boolean[] visible;
	private double time;

	public MatchDrawingSurface() {
		time = 0;
		set = new StudySet("C:\\Users\\eylam\\Documents\\studysettest.txt");
		rects = new Rectangle[set.size() * 2];
		terms = new String[set.size()];
		defs = new String[set.size()];
		visible = new boolean[set.size() * 2];

		for (int i = 0; i < set.size(); i++) {
			rects[i] = new Rectangle(100 + Math.random() * 600, 100 + Math.random() * 400, 100, 25);
			rects[set.size() + i] = new Rectangle(100 + Math.random() * 600, 100 + Math.random() * 400, 100, 25);
			terms[i] = set.getTermAt(i);
			defs[i] = set.getDefinitionAt(i);
			visible[i] = true;
			visible[i + 3] = true;
		}
	}

	public void draw() {
		background(255, 255, 255);

		for (int x = 0; x < set.size(); x++) {
			if (visible[x]) {
				rects[x].draw(this);

				fill(0);
				textSize(12);

				text(terms[x], (float) (rects[x].getX() + 7.5), (float) (rects[x].getY() + 15));
			}
			if (visible[x + set.size()]) {
				rects[x + set.size()].draw(this);
				text(defs[x], (float) (rects[x + terms.length].getX() + 7.5),
						(float) (rects[x + terms.length].getY() + 15));
			}
		}
		
		int numVisible = 0;
		for (int z = 0; z < visible.length; z++) {
			if (visible[z])
				numVisible++;
		}
		if (numVisible > 0)
			time++;
		text("Time: " + ((int) (100 * time / 60)) / 100.0 + "", 225f, 75f);
		
		textSize(50);
		text("MATCH", 25f, 75f);
	}

	public void mouseDragged() {
		for (int i = 0; i < rects.length; i++) {
			if (visible[i] && rects[i].isPointInside(mouseX, mouseY)) {
				rects[i].moveTo(mouseX - 50.0, mouseY - 12.5);
				for (int k = 0; k < rects.length; k++) {
					for (int j = 0; j < rects.length; j++) {
						if (k != j && rects[k].isPointInside(mouseX, mouseY)) {
							if (rects[j].isPointInside(mouseX, mouseY)) {
								if (Math.abs(k - j) == set.size()) {
									visible[k] = false;
									visible[j] = false;
								} else {
									if (rects[k].getX() > rects[j].getX()) {
										rects[k].moveTo(rects[k].getX() + 100, rects[k].getY());
										rects[j].moveTo(rects[j].getX() - 100, rects[j].getY());
									} else {
										rects[k].moveTo(rects[k].getX() - 100, rects[k].getY());
										rects[j].moveTo(rects[j].getX() + 100, rects[j].getY());
									}
								}
							}

						}
					}
				}
			}
		}

	}

}
