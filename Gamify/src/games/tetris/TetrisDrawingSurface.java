package games.tetris;

import javax.swing.JOptionPane;

import base.StudySet;
import processing.core.PApplet;

public class TetrisDrawingSurface extends PApplet {

	private int board[][] = new int[19][12];
	private TetrisGamePiece activePiece;
	private TetrisGamePiece ghostPiece;
	private TetrisGamePiece nextPiece;
	private int numRepetition;
	private int score, level;
	private boolean activeIntersecting, landed, end;
	private boolean studying;
	private boolean paused;
	private int yDown, linesCleared, correctAnswers;
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
			board[18][i] = 8;
		}
		activePiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		// activePiece = new TetrisGamePiece(6);

		ghostPiece = new TetrisGamePiece(activePiece.getType());
		ghostPiece.setFill(200);
		activeIntersecting = false;

		nextPiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		nextPiece.translateX(8);
		nextPiece.drop(2);
		if (nextPiece.getType() == 6) {
			nextPiece.translateX(1);
		}

	}

	public void setup() {
		// frameRate(60);
	}

	public void draw() {
		background(230);
		stroke(0);
		strokeWeight(3);
		line(300, 100, 300, 550);
		line(0, 100, 440, 100);
		line(0, 550, 440, 550);
		line(440, 100, 440, 550);

		textSize(10);
		fill(0);
		text("left arrow key - left, right - right, up - rotate, down - soft drop, space - hard drop", 10, 10);

		textSize(50);
		fill(0);
		text("TETRIS", 110, 75);

		textSize(20);
		text("NEXT", 330, 130);

		text("SCORE", 325, 240);
		text(score, 330, 285);

		text("LINES", 330, 320);
		text("CLEARED", 315, 345);
		text(linesCleared, 330, 390);

		text("ACCURACY", 315, 450);
		if (linesCleared != 0) {
			text((int) ((double) (10000 * correctAnswers / linesCleared)) / 100.0 + "%", 330, 495);
		} else {
			text("NaN", 330, 495);
		}
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

		findGhost();
		ghostPiece.drop(yDown);
		ghostPiece.draw(this);
		ghostPiece.drop(-yDown);
		activePiece.draw(this);

		nextPiece.draw(this);

		if (!paused && !end) {
			checkIntersecting();
			if (landed) {
				score += 12;
				for (int i = 0; i < 4; i++) {
					board[activePiece.getY(i) + activePiece.getYShift()][activePiece.getX(i)
							+ activePiece.getXShift()] = activePiece.getType();
				}
				checkFullLine();
				reset();
			} else if (numRepetition == 60) {
				if (activeIntersecting && !landed) {
					landed = true;
					numRepetition -= 30;
				} else {
					activePiece.drop(1);
					ghostPiece.drop(1);
					numRepetition -= 60;
				}

			}
			numRepetition++;

		}

	}

	public void mousePressed() {
	}

	public void keyPressed() {
		if (key == 'p') {
			paused = !paused;
		}
		if (!paused && !end) {
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
						ghostPiece.drop(1);
						score += 10;
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
						ghostPiece.translateX(-1);
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
						ghostPiece.translateX(1);
					}
				} else if (keyCode == UP) {
					int xShift, yShift;
					xShift = yShift = 0;
					if (activePiece.getType() == 6) {
						for (int i = 0; i < 4; i++) {
							if (1 - activePiece.getY(i) + activePiece.getXShift() < 0) {
								xShift = 0 - (1 - activePiece.getY(i) + activePiece.getXShift());
							} else if (1 - activePiece.getY(i) + activePiece.getXShift() > 11) {
								xShift = (11 - (1 - activePiece.getY(i) + activePiece.getXShift()));
							}

							if (activePiece.getX(i) + activePiece.getYShift() < 0) {
								yShift = (0 - (activePiece.getX(i) + activePiece.getYShift()));
							} else if (activePiece.getX(i) + activePiece.getYShift() > 17) {
								yShift = (17 - (activePiece.getX(i) + activePiece.getYShift()));

							}
						}

						// for (int i = 0; i < 4; i++) {
						// while (board[activePiece.getX(i) + activePiece.getYShift()][1 -
						// activePiece.getY(i) + activePiece.getXShift()] > 0) {
						// if (activePiece.getX(i)+activePiece.getXShift() > 1 - activePiece.getY(i) +
						// activePiece.getXShift()) {
						// activePiece.translateX(1);
						// }
						// else if (activePiece.getX(i)+activePiece.getXShift() < 1 -
						// activePiece.getY(i) + activePiece.getXShift()) {
						// activePiece.translateX(-1);
						// }
						// activePiece.translateX(1);
						// }
						// }
						for (int i = 0; i < 4; i++) {
							if (board[activePiece.getX(i) + activePiece.getYShift() + yShift][1 - activePiece.getY(i)
									+ activePiece.getXShift() + xShift] > 0) {
								translatable = false;
							}
						}

						if (translatable) {
							activePiece.translateX(xShift);
							activePiece.drop(yShift);

							ghostPiece.translateX(xShift);
							ghostPiece.drop(yShift);
							activePiece.rotate(1);
							ghostPiece.rotate(1);
						}

					} else {
						for (int i = 0; i < 4; i++) {
							if (2 - activePiece.getY(i) + activePiece.getXShift() < 0) {
								xShift = (0 - (2 - activePiece.getY(i) + activePiece.getXShift()));

							} else if (2 - activePiece.getY(i) + activePiece.getXShift() > 11) {
								xShift = (11 - (2 - activePiece.getY(i) + activePiece.getXShift()));

							}

							if (activePiece.getX(i) + activePiece.getYShift() < 0) {
								yShift = (0 - (activePiece.getX(i) + activePiece.getYShift()));
							} else if (activePiece.getX(i) + activePiece.getYShift() > 17) {

								yShift = (17 - (activePiece.getX(i) + activePiece.getYShift()));

							}
						}
						// for (int i = 0; i < 4; i++) {
						// while (board[activePiece.getX(i) + activePiece.getYShift()][2 -
						// activePiece.getY(i) + activePiece.getXShift()] > 0) {
						// if (activePiece.getX(i)+activePiece.getXShift() > 2 - activePiece.getY(i) +
						// activePiece.getXShift()) {
						// activePiece.translateX(1);
						// }
						// else if (activePiece.getX(i)+activePiece.getXShift() < 2 -
						// activePiece.getY(i) + activePiece.getXShift()) {
						// activePiece.translateX(-1);
						// }
						// activePiece.translateX(1);
						// }
						// }
						for (int i = 0; i < 4; i++) {
							if (board[activePiece.getX(i) + activePiece.getYShift() + yShift][2 - activePiece.getY(i)
									+ activePiece.getXShift() + xShift] > 0) {
								translatable = false;
							}
						}

						if (translatable) {
							activePiece.translateX(xShift);
							activePiece.drop(yShift);

							ghostPiece.translateX(xShift);
							ghostPiece.drop(yShift);

							activePiece.rotate(1);
							ghostPiece.rotate(1);
						}
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
						ghostPiece.drop(1);
						score += 5;

					}
				}

			}
		}
	}

	private void checkIntersecting() {
		activeIntersecting = false;

		for (int i = 0; i < 4; i++) {
			if (board[activePiece.getY(i) + activePiece.getYShift() + 1][activePiece.getX(i)
					+ activePiece.getXShift()] > 0) {
				activeIntersecting = true;
			}
		}

	}

	private void clearLine(int number) {

		for (int i = number; i >= 1; i--) {
			for (int j = 0; j < 12; j++) {
				board[i][j] = board[i - 1][j];
			}
		}
	}

	private void reset() {
		// activePiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		activePiece = new TetrisGamePiece(nextPiece.getType());

		activeIntersecting = false;
		landed = false;
		for (int i = 0; i < 4; i++) {
			if (board[activePiece.getY(i) + activePiece.getYShift()][activePiece.getX(i)
					+ activePiece.getXShift()] > 0) {
				end = true;

			}
		}
		if (end) {
			Object[] options = { "Play Again", "Exit Tetris" };

			int choice;

			if (linesCleared > 0) {
				choice = JOptionPane.showOptionDialog(frame,
						"You have achieved a score of " + score + " and accuracy of "
								+ (int) ((double) (10000 * correctAnswers / linesCleared)) / 100.0 + "%",
						"GAME OVER", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
						options[0]);
			} else {
				choice = JOptionPane.showOptionDialog(frame,
						"You have achieved a score of " + score + ", but have not cleared any lines", "GAME OVER",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			}

			if (choice == JOptionPane.YES_OPTION) {
				restart();
			} else {
				System.exit(0);
			}
		}

		ghostPiece = new TetrisGamePiece(activePiece.getType());
		ghostPiece.setFill(200);

		nextPiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		nextPiece.translateX(8);
		nextPiece.drop(2);
		if (nextPiece.getType() == 6) {
			nextPiece.translateX(1);
		}

	}

	private void checkFullLine() {
		for (int i = 0; i < 18; i++) {
			boolean fullLine = true;
			for (int j = 0; j < 12; j++) {
				if (board[i][j] < 1) {
					fullLine = false;
				}
			}
			if (fullLine) {
				score += 50;
				linesCleared++;
				clearLine(i);

				int index = (int) (Math.random() * set.size());
				String answer = (String) JOptionPane.showInputDialog(frame, "Question: " + set.getTermAt(index),
						"Problem", JOptionPane.PLAIN_MESSAGE, null, null, " ");
				answer = answer.trim();
				if (answer.equalsIgnoreCase(set.getDefinitionAt(index).trim())) {
					correctAnswers++;
					score += 50;

				} else {
					JOptionPane.showMessageDialog(frame,
							"Incorrect! Term/Phrase: " + set.getTermAt(index) + "  Your Answer: " + answer
									+ "  The Correct Answer: " + set.getDefinitionAt(index),
							"Incorrect", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	public void findGhost() {
		boolean movable = true;
		yDown = 0;
		while (movable) {
			for (int i = 0; i < 4; i++) {
				if (board[ghostPiece.getY(i) + ghostPiece.getYShift() + 1 + yDown][ghostPiece.getX(i)
						+ ghostPiece.getXShift()] > 0) {
					movable = false;
				}
			}
			if (movable) {
				yDown++;
			}
		}
	}

	public void restart() {
		paused = end = studying = false;
		score = 0;
		level = 1;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 18; j++) {
				board[j][i] = 0;
			}
			board[18][i] = 8;
		}
		activePiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		// activePiece = new TetrisGamePiece(6);

		ghostPiece = new TetrisGamePiece(activePiece.getType());
		ghostPiece.setFill(200);
		activeIntersecting = false;

		nextPiece = new TetrisGamePiece((int) (Math.random() * 7 + 1));
		nextPiece.translateX(8);
		nextPiece.drop(2);
		if (nextPiece.getType() == 6) {
			nextPiece.translateX(1);
		}
	}
}
