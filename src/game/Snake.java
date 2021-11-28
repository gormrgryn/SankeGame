package game;

public class Snake {
	public 	SnakeCell[] SnakeCells;
	public int x1;
	public int x2;
	public int y;
	public int length;
	
	public Snake(int y, int x1, int x2) {
		SnakeCells = new SnakeCell[x2 - x1];
		this.x1 = x1;
		this.x2 = x2;
		this.y = y;
		length = SnakeCells.length;
		
		for(int i = 0; i < x2 - x1; i++) {
			SnakeCells[i] = new SnakeCell(x1 + i, y, i);
		}
	}
	
	public void Move(char dir) {
		for(int i = length - 1; i > 0 ; i--) {
			SnakeCells[i].x = SnakeCells[i - 1].x;
			SnakeCells[i].y = SnakeCells[i - 1].y;
		}
		
		switch(dir) {
			case 'L':
				SnakeCells[0].x--;
				break;
			case 'R':
				SnakeCells[0].x++;
				break;
			case 'U':
				SnakeCells[0].y--;
				break;
			case 'D':
				SnakeCells[0].y++;
				break;
		}
	}
}
