
public class Block {
	private int x,y;
	private int direction;
	
	public Block(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int x, int y) {
		this.x +=x;
		this.y +=y;
	}
	
	public String toString() {
		return "(" + x + " , " + y + ")";
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void move() {
		switch(direction) {
			case 0:
				this.y += -1;
				break;
			case 1:
				x += 1;
				break;
			case 2:
				y += 1;
				break;
			case 3:
				x += -1;
				break;
				
		}
	}
	
	public boolean isEqual(Block other) {
		return x==other.x && y == other.y;
	}

	public boolean isBorder(int size) {
		if((x <0 && direction == 3) || (x >size && direction == 1) || (y<0 && direction  == 0) || (y > size && direction == 2)) {
			return true;
			
		}else {
			return false;
		}
	}
}
