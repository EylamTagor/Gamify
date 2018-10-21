package games.tetris;

import base.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;
import processing.core.PShape;

public class TetrisDrawingSurface extends PApplet {

	private int board[][] = new int[19][12];
	private TetrisGamePiece activePiece;
	private int numRepetition;
	private int score, level;
	private boolean activeIntersecting, landed, end;
	private boolean studying;
	private boolean paused;
	private StudySet set;

	public TetrisDrawingSurface(String fileName) {
		set = new StudySet(fileName);
		paused = end = studying = false;
		score = 0;
		level = 1;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 18; j++) {
				board[j][i] = 0;
			}
			board[18][i] = 9;
		}
		activePiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		activeIntersecting = false;

	}

	public void setup() {
		// frameRate(60);
	}

	public void draw() {
		background(230);
		stroke(0);
		strokeWeight(3);
		line(300, 100, 300, 550);
		line(0, 100, 400, 100);
		line(0, 550, 400, 550);

		textSize(50);
		fill(0);
		text("TETRIS", 100, 75);

		textSize(20);
		text("NEXT", 320, 98);

		strokeWeight(1);
		stroke(25);
		for (int i = 0; i < 12; i++) {
			for (int j = 1; j < 18; j++) {
				line(25 * i, 100, 25 * i, 550);
				line(0, 100 + 25 * j, 300, 100 + 25 * j);
			}
		}

		for (int i = 0; i < 18; i++) {
			for (int j = 0; j < 12; j++) {
				switch (board[i][j]) {
				case 0:
					if ((i + j) % 2 == 0) {
						fill(50);
					} else {
						fill(75);
					}
					break;
				case 1:
					fill(255, 0, 0);
					break;
				case 2:
					fill(0, 255, 0);
					break;
				case 3:
					fill(0, 0, 255);
					break;
				case 4:
					fill(255, 165, 0);
					break;
				case 5:
					fill(255, 255, 0);
					break;
				case 6:
					fill(0, 255, 255);
					break;
				case 7:
					fill(200, 0, 255);
				}
				rect(j * 25, 100 + i * 25, 25, 25);
			}
		}

		if (!paused && !end) {
			checkIntersecting();

			if (numRepetition == 60) {
				if (activeIntersecting && !landed) {
					landed = true;
					numRepetition -= 30;
				} else if (activeIntersecting && landed) {
					for (int i = 0; i < 4; i++) {
						board[activePiece.getY(i) + activePiece.getYShift()][activePiece.getX(i)
								+ activePiece.getXShift()] = activePiece.getType();
					}
					checkFullLine();
					reset();
					numRepetition -= 1;
				} else {
					activePiece.drop(1);
					numRepetition -= 60;

				}
			}
			numRepetition++;

		}
		activePiece.draw(this);

	}

	public void mousePressed() {
	}

	public void keyPressed() {
		if (key == 'p') {
			paused = !paused;
		}
		if (!paused) {
			if (key == ' ') {
				boolean translatable = true;
				landed = true;
				while (translatable) {
					for (int i = 0; i < 4; i++) {
						if (board[activePiece.getY(i) + activePiece.getYShift() + 1][activePiece.getX(i)
								+ activePiece.getXShift()] > 0) {
							translatable = false;
						}
					}

					if (translatable) {
						activePiece.drop(1);
					}
				}
			}
			if (key == CODED) {
				boolean translatable = true;
				if (keyCode == LEFT) {
					for (int i = 0; i < 4; i++) {
						if (activePiece.getX(i) + activePiece.getXShift() - 1 < 0
								|| board[activePiece.getY(i) + activePiece.getYShift()][activePiece.getX(i)
										+ activePiece.getXShift() - 1] > 0) {
							translatable = false;
						}
					}
					if (translatable) {
						activePiece.translateX(-1);
					}
				}

				else if (keyCode == RIGHT) {
					for (int i = 0; i < 4; i++) {
						if (activePiece.getX(i) + activePiece.getXShift() + 1 > 11
								|| board[activePiece.getY(i) + activePiece.getYShift()][activePiece.getX(i)
										+ activePiece.getXShift() + 1] > 0) {
							translatable = false;
						}
					}
					if (translatable) {
						activePiece.translateX(1);
					}
				} else if (keyCode == UP) {
					if (activePiece.getType() == 6) {
						for (int i = 0; i < 4; i++) {
							if (1 - activePiece.getY(i) + activePiece.getXShift() < 0) {
								activePiece.translateX(0 - (1 - activePiece.getY(i) + activePiece.getXShift()));
							} else if (1 - activePiece.getY(i) + activePiece.getXShift() > 11) {
								activePiece.translateX(11 - (1 - activePiece.getY(i) + activePiece.getXShift()));
							}

							if (activePiece.getX(i) + activePiece.getYShift() < 0) {
								activePiece.drop(0 - (activePiece.getX(i) + activePiece.getYShift()));
							}

						}
						activePiece.rotate(1);
					} else {
						for (int i = 0; i < 4; i++) {
							if (2 - activePiece.getY(i) + activePiece.getXShift() < 0) {
								activePiece.translateX(0 - (2 - activePiece.getY(i) + activePiece.getXShift()));
							} else if (2 - activePiece.getY(i) + activePiece.getXShift() > 11) {
								activePiece.translateX(11 - (2 - activePiece.getY(i) + activePiece.getXShift()));
							}

							if (activePiece.getX(i) + activePiece.getYShift() < 0) {
								activePiece.drop(0 - (activePiece.getX(i) + activePiece.getYShift()));
							}

						}
						activePiece.rotate(1);
					}
				} else if (keyCode == DOWN) {
					for (int i = 0; i < 4; i++) {
						if (board[activePiece.getY(i) + activePiece.getYShift() + 1][activePiece.getX(i)
								+ activePiece.getXShift()] > 0) {
							translatable = false;
						}
					}

					if (translatable) {
						activePiece.drop(1);
					}
				}
			}
		}
	}

	public void checkIntersecting() {
		activeIntersecting = false;

		for (int i = 0; i < 4; i++) {
			// System.out.println(activePiece.getY(i)+activePiece.getYShift()+1);
			if (board[activePiece.getY(i) + activePiece.getYShift() + 1][activePiece.getX(i)
					+ activePiece.getXShift()] > 0) {
				activeIntersecting = true;
			}
		}

	}

	public void clearLine(int number) {
		for (int i = number; i >= 1; i--) {
			for (int j = 0; j < 12; j++) {
				board[i][j] = board[i - 1][j];
			}
		}

	}

	public void reset() {
		activePiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		// activePiece = new TetrisGamePiece(6);

		activeIntersecting = false;
		landed = false;
		checkIntersecting();
		if (activeIntersecting) {
			end = true;
		}
	}

	public void checkFullLine() {
		for (int i = 0; i < 18; i++) {
			boolean fullLine = true;
			for (int j = 0; j < 12; j++) {
				if (board[i][j] < 1) {
					fullLine = false;
				}
			}
			if (fullLine) {
				clearLine(i);
			}
		}
	}
}
