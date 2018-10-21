package games.tetris;

import java.awt.Color;

import processing.core.PApplet;

public class TetrisGamePiece {

	private int type;
	private int blockX[] = new int[4];
	private int blockY[] = new int[4];
	private int r, g, b;
	private int xShift, yShift;

	public TetrisGamePiece(int number) {
		type = number;
		r = g = b = 0;
		xShift = 5;
		yShift = 0;
		if (number == 1) {
			r = 255;

			blockX[0] = 1;
			blockX[1] = 0;
			blockX[2] = 1;
			blockX[3] = 2;

			blockY[0] = 1;
			blockY[1] = 1;
			blockY[2] = 0;
			blockY[3] = 0;

		} else if (number == 2) {
			g = 255;

			blockX[0] = 1;
			blockX[1] = 0;
			blockX[2] = 1;
			blockX[3] = 2;

			blockY[0] = 1;
			blockY[1] = 0;
			blockY[2] = 0;
			blockY[3] = 1;
		} else if (number == 3) {
			b = 255;

			blockX[0] = 1;
			blockX[1] = 0;
			blockX[2] = 0;
			blockX[3] = 2;

			blockY[0] = 1;
			blockY[1] = 0;
			blockY[2] = 1;
			blockY[3] = 1;
		} else if (number == 4) {
			r = 255;
			g = 165;

			blockX[0] = 1;
			blockX[1] = 0;
			blockX[2] = 2;
			blockX[3] = 2;

			blockY[0] = 1;
			blockY[1] = 1;
			blockY[2] = 1;
			blockY[3] = 0;
		} else if (number == 5) {
			r = g = 255;

			blockX[0] = 1;
			blockX[1] = 0;
			blockX[2] = 0;
			blockX[3] = 1;

			blockY[0] = 1;
			blockY[1] = 0;
			blockY[2] = 1;
			blockY[3] = 0;
		} else if (number == 6) {
			r = 0;
			g = 255;
			b = 255;

			blockX[0] = 1;
			blockX[1] = -1;
			blockX[2] = 0;
			blockX[3] = 2;

			blockY[0] = 0;
			blockY[1] = 0;
			blockY[2] = 0;
			blockY[3] = 0;
		} else if (number == 7) {
			r = 200;
			b = 255;

			blockX[0] = 1;
			blockX[1] = 0;
			blockX[2] = 1;
			blockX[3] = 2;

			blockY[0] = 1;
			blockY[1] = 1;
			blockY[2] = 0;
			blockY[3] = 1;
		}
	}

	public void rotate(int x) {
		if (x == 1) {
			if (type == 5) {

			} else if (type == 6) {
				for (int i = 0; i < 4; i++) {
					int temp = blockX[i];
					blockX[i] = 1 - blockY[i];
					blockY[i] = temp;
				}
			} else {
				for (int i = 0; i < 4; i++) {
					int temp = blockX[i];
					blockX[i] = 2 - blockY[i];
					blockY[i] = temp;
				}
			}
		} else {
			if (type == 5) {

			} else if (type == 6) {
				for (int i = 0; i < 4; i++) {
					int temp = blockX[i];
					blockX[i] = blockY[i];
					blockY[i] = 3 - temp;
				}
			} else {
				for (int i = 0; i < 4; i++) {
					int temp = blockX[i];
					blockX[i] = blockY[i];
					blockY[i] = 2 - temp;
				}
			}
		}
	}

	public void translateX(int x) {
		xShift += x;
	}

	public void draw(PApplet d) {
		for (int i = 0; i < 4; i++) {
			d.fill(r, g, b);
			d.rect((blockX[i] + xShift) * 25, 100 + (blockY[i] + yShift) * 25, 25, 25);
		}
	}

	public void drop(int x) {
		yShift += x;
	}

	public int getX(int i) {
		return blockX[i];
	}

	public int getY(int i) {
		return blockY[i];
	}

	public int getXShift() {
		return xShift;
	}

	public int getYShift() {
		return yShift;
	}

	public int getType() {
		return type;
	}

}
