
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import processing.core.PApplet;

public class DrawingSurface extends PApplet{
	
	protected static StudySet ss;
	protected static int[][] grid;
	private Snake snake;
	protected static JFrame w = new JFrame();
	protected static int foodx, foody;
	private static boolean bool;
	private static boolean paused;
	
	public DrawingSurface(String filename) {
		ss = new StudySet(filename);
		snake = new Snake();
		foodx = (int)(Math.random()*17);
		foody = (int)(Math.random()*17);
		bool = true;
	}
	
	
	public void draw() {
		background(255);
		pushStyle();
		fill(0,0,0);
		textSize(20);
		text("SNAKE", 200,25);
		popStyle();

		this.translate(50,50);
		int rows = 20;
		int columns = 20;
		grid = new int[rows][columns];
		for(int i = 0; i< rows; i++) {
			for(int x = 0; x<columns; x++) {
				grid[i][x] = 0;
			}
		}
		
		grid[foodx][foody] = 2;
		
		for(int i = 0; i < snake.snake.size(); i++){
			
			if(i == 0) {
				grid[snake.snake.get(i).getX()][snake.snake.get(i).getY()] = 3;

			}else {
				grid[snake.snake.get(i).getX()][snake.snake.get(i).getY()] = 1;

			}
		}
		
		
		
		
		int size = 20;
		
		for(int x = 0; x<rows * size; x+=size) {
			for(int y = 0; y<columns * size; y+=size) {
				if(grid[x/size][y/size] == 0) {
					
					pushStyle();
					noFill();
					rect(x,y,size,size);
					popStyle();

				}else if(grid[x/size][y/size] == 1){
					pushStyle();
					fill(255, 0 ,0);
					rect(x,y,size,size);
					popStyle();
				}else if((grid[x/size][y/size] == 2)){
					pushStyle();
					noFill();
					rect(x,y,size,size);
					fill(255,255,0);
					ellipseMode(PApplet.CORNER);
					ellipse(x + size/4,y + size/4,size/2,size/2);
					popStyle();
				}else {
					pushStyle();
					fill(0, 0 ,255);
					rect(x,y,size,size);
					popStyle();
				}
			}
		}
		
		if(paused) {
			
		}else {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			snake.move();
		}
		
	
	}
	
	public static void lose(int score) {
		
		for(int i = 0; i<3000; i++) {
			if(bool) {
				JOptionPane.showMessageDialog(w, "Score: " + score, "You Lose!", JOptionPane.PLAIN_MESSAGE);

			}
			bool = false;

		}
		
		w.dispose();
		

	}
	
	public static void lose(int score, String message) {
		
		for(int i = 0; i<3000; i++) {
			if(bool) {
				JOptionPane.showMessageDialog(w, "Score: " + score + " The correct answer was " + message, "You Lose!", JOptionPane.PLAIN_MESSAGE);

			}
			bool = false;

		}
		
		w.dispose();
		

	}
	
	public void keyPressed() {
		
		if(!paused) {
			if(key == 'w') {
				snake.moveUp();
			}else if(key == 'a') {
				snake.moveLeft();
			}else if(key == 's') {
				snake.moveDown();
			}else if(key == 'd') {
				snake.moveRight();
			}
		}
		if(key == 'p') {
			paused = !paused;
		}
	}
	public void setJFrame(JFrame f) {
		this.w = f;
	}
	
	
	public static void pausee(boolean bool) {
		paused = bool;
	}
	
	
	
}