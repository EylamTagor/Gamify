
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Snake {

	
	protected ArrayList<Block> snake;
	
	//0 = up, 1 = right, 2 = down, 3= left
	
	public Snake() {
		snake = new ArrayList<>();
		snake.add(new Block(5,10,0));
	}
	
	
	public Snake(Block head) {
		snake = new ArrayList<>();
		snake.add(head);

	}
	
	public void moveUp() {
		//head
		Block tempChecker = new Block(snake.get(0).getX(), snake.get(0).getY(), snake.get(0).getDirection());

		try{
			boolean isCandy = DrawingSurface.grid[tempChecker.getX()][tempChecker.getY() - 1] == 2;
			tempChecker.setDirection(0);
			tempChecker.move();
			
			
			
			for(int i = 0; i<snake.size(); i++) {
				if(tempChecker.isEqual(snake.get(i)) || tempChecker.isBorder(20)) {
					System.out.println(tempChecker.isBorder(20));
					System.out.println(tempChecker);
					System.out.println(snake.get(i));
					
					DrawingSurface.lose(snake.size());
					
				}
			}
		
			if(!isCandy) {
				snake.remove(snake.size()-1);

			}else {
				DrawingSurface.foodx = (int)(Math.random() * 15 + 1);
				DrawingSurface.foody = (int)(Math.random() * 15 + 1);
				
				DrawingSurface.pausee(true);
				System.out.println("asdf");
				int indexnum = ((int)(Math.random()* DrawingSurface.ss.getStudySet().size()));
				String fileName = (String) JOptionPane.showInputDialog(DrawingSurface.w, "What is " +
						DrawingSurface.ss.getTermAt(indexnum) + 
						" in " + DrawingSurface.ss.getName() + "?", "Question", JOptionPane.PLAIN_MESSAGE, null, null, "");
				if(fileName.equalsIgnoreCase(DrawingSurface.ss.getDefinitionAt(indexnum))) {
					DrawingSurface.pausee(false);
				}else {
					DrawingSurface.lose(snake.size(), DrawingSurface.ss.getDefinitionAt(indexnum));
				}
					
				
			}
			snake.add(0, tempChecker);
		}catch(Exception e) {
			snake.remove(snake.size()-1);
			snake.add(0, tempChecker);
			DrawingSurface.lose(snake.size());
		}
		
	
		
		
		
	}
	
	public void moveRight() {
		
		
		try{
			Block tempChecker = new Block(snake.get(0).getX(), snake.get(0).getY(), snake.get(0).getDirection());
			boolean isCandy = DrawingSurface.grid[tempChecker.getX() + 1][tempChecker.getY()] == 2;
			tempChecker.setDirection(1);
			tempChecker.move();
			
			
			
			for(int i = 0; i<snake.size(); i++) {
				if(tempChecker.isEqual(snake.get(i)) || tempChecker.isBorder(20)) {
					System.out.println(tempChecker.isBorder(20));
					System.out.println(tempChecker);
					System.out.println(snake.get(i));
					DrawingSurface.lose(snake.size());
				}
			}
		
			if(!isCandy) {
				snake.remove(snake.size()-1);

			}else {
				DrawingSurface.foodx = (int)(Math.random() * 15 + 1);
				DrawingSurface.foody = (int)(Math.random() * 15 + 1);
				
				DrawingSurface.pausee(true);
				System.out.println("asdf");
				int indexnum = ((int)(Math.random()* DrawingSurface.ss.getStudySet().size()));
				String fileName = (String) JOptionPane.showInputDialog(DrawingSurface.w, "What is " +
						DrawingSurface.ss.getTermAt(indexnum) + 
						" in " + DrawingSurface.ss.getName() + "?", "Question", JOptionPane.PLAIN_MESSAGE, null, null, "");
				
				if(fileName.equalsIgnoreCase(DrawingSurface.ss.getDefinitionAt(indexnum))) {
					DrawingSurface.pausee(false);
				}else {
					DrawingSurface.lose(snake.size(), DrawingSurface.ss.getDefinitionAt(indexnum));
				}
			}
			snake.add(0, tempChecker);
			
		}catch(Exception e) {
			DrawingSurface.lose(snake.size());
		}
		
		
	}
	
	public void moveDown() {
		//head
		
		try {
			Block tempChecker = new Block(snake.get(0).getX(), snake.get(0).getY(), snake.get(0).getDirection());
			boolean isCandy = DrawingSurface.grid[tempChecker.getX()][tempChecker.getY() + 1] == 2;
			tempChecker.setDirection(2);
			tempChecker.move();
			
			
			
			for(int i = 0; i<snake.size(); i++) {
				if(tempChecker.isEqual(snake.get(i)) || tempChecker.isBorder(20)) {
					System.out.println(tempChecker.isBorder(20));
					System.out.println(tempChecker);
					System.out.println(snake.get(i));
					DrawingSurface.lose(snake.size());
				}
			}
		
			if(!isCandy) {
				snake.remove(snake.size()-1);

			}else {
				DrawingSurface.foodx = (int)(Math.random() * 15 + 1);
				DrawingSurface.foody = (int)(Math.random() * 15 + 1);
				
				DrawingSurface.pausee(true);
				System.out.println("asdf");
				int indexnum = ((int)(Math.random()* DrawingSurface.ss.getStudySet().size()));
				String fileName = (String) JOptionPane.showInputDialog(DrawingSurface.w, "What is " +
						DrawingSurface.ss.getTermAt(indexnum) + 
						" in " + DrawingSurface.ss.getName() + "?", "Question", JOptionPane.PLAIN_MESSAGE, null, null, "");
				
				if(fileName.equalsIgnoreCase(DrawingSurface.ss.getDefinitionAt(indexnum))) {
					DrawingSurface.pausee(false);
				}else {
					DrawingSurface.lose(snake.size(), DrawingSurface.ss.getDefinitionAt(indexnum));
				}
			}
			snake.add(0, tempChecker);
		}catch(Exception e) {
			DrawingSurface.lose(snake.size());
		}
		
		
		
	}
	
	public void moveLeft() {
		//head
		
		
		
		try {
			Block tempChecker = new Block(snake.get(0).getX(), snake.get(0).getY(), snake.get(0).getDirection());
			boolean isCandy = DrawingSurface.grid[tempChecker.getX() - 1][tempChecker.getY()] == 2;
			tempChecker.setDirection(3);
			tempChecker.move();
			
			
			
			for(int i = 0; i<snake.size(); i++) {
				if(tempChecker.isEqual(snake.get(i)) || tempChecker.isBorder(20)) {
					System.out.println(tempChecker.isBorder(20));
					System.out.println(tempChecker);
					System.out.println(snake.get(i));
					DrawingSurface.lose(snake.size());
				}
			}
		
			if(!isCandy) {
				snake.remove(snake.size()-1);

			}else {
				DrawingSurface.foodx = (int)(Math.random() * 15 + 1);
				DrawingSurface.foody = (int)(Math.random() * 15 + 1);
				
				DrawingSurface.pausee(true);
				System.out.println("asdf");
				int indexnum = ((int)(Math.random()* DrawingSurface.ss.getStudySet().size()));
				String fileName = (String) JOptionPane.showInputDialog(DrawingSurface.w, "What is " +
				DrawingSurface.ss.getTermAt(indexnum) + 
				" in " + DrawingSurface.ss.getName() + "?", "Question", JOptionPane.PLAIN_MESSAGE, null, null, "");
				
				if(fileName.equalsIgnoreCase(DrawingSurface.ss.getDefinitionAt(indexnum))) {
					DrawingSurface.pausee(false);
				}else {
					DrawingSurface.lose(snake.size(), DrawingSurface.ss.getDefinitionAt(indexnum));
				}
			}
			snake.add(0, tempChecker);
		}catch(Exception e) {
			DrawingSurface.lose(snake.size());
		}
		
		
	}
	
	public void move() {
		switch(snake.get(0).getDirection()) {
			case 0:
				moveUp();
				break;
			case 1:
				moveRight();
				break;
			case 2:
				moveDown();
				break;
			case 3:
				moveLeft();
				break;
		
		
		}
	}
	
	public String toString() {
		String ret = "";
		
		for(int i = 0; i<snake.size(); i++) {
			ret += snake.get(i).toString();
		}
		
		return ret;
	}
}