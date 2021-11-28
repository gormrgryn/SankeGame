package game;

import java.util.Random;

public final class Game {
	public Cell[][] matrix;
	public Snake snake;
	public char HeadDirection;
	public boolean Running;
	
	private SnakeCell[] snakeCells;
	
	public Game(int size, Snake snake) {
		matrix = new Cell[size][size];
		
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				matrix[i][j] = new Cell(j, i);
			}
		}
		this.snake = snake;
		snakeCells = snake.SnakeCells;
		
		Init();
		Running = true;
	}
	
	public void Init() {
		Cell snakeHead = matrix[snake.SnakeCells[0].y][snake.SnakeCells[0].x];
		if (matrix[snakeHead.y][snakeHead.x].getClass() == FoodCell.class) {
			SnakeCell[] newSnake = new SnakeCell[snake.SnakeCells.length + 1]; // max access s.s.length
			
			int j = 0;
			while(j < snake.SnakeCells.length) {
				newSnake[j] = new SnakeCell(snake.SnakeCells[j].x, snake.SnakeCells[j].y, j);
				j++;
			}
			
			switch(HeadDirection) {
				case 'L':
					newSnake[j] = new SnakeCell(
						newSnake[j-1].x + 1,
						newSnake[j-1].y,
						j
					);
					break;
				case 'R':
					newSnake[j] = new SnakeCell(
						newSnake[j-1].x - 1,
						newSnake[j-1].y,
						j
					);
					break;
				case 'U':
					newSnake[j] = new SnakeCell(
						newSnake[j-1].x,
						newSnake[j-1].y + 1,
						j
					);
					break;
				case 'D':
					newSnake[j] = new SnakeCell(
						newSnake[j-1].x,
						newSnake[j-1].y - 1,
						j
					);
					break;
			}
			for(int i = 0; i < matrix.length; i++) {
				for(int k = 0; k < matrix[0].length; k++) {
					matrix[i][k] = new Cell(k, i);
				}
			}
			snake.SnakeCells = newSnake;
			snake.length++;
		}
		snakeCells = snake.SnakeCells;
		
		for(int i = 0; i < snakeCells.length; i++) {
			if (snakeCells[i].y >= matrix.length || snakeCells[i].x >= matrix[0].length
					|| snakeCells[i].y < 0 || snakeCells[i].x < 0) {
				for(int j = 0; j < matrix.length; j++) {
					for(int k = 0; k < matrix[0].length; k++) {
						matrix[j][k] = new Cell(k, j);
					}
				}
				Running = false;
			} else matrix[snakeCells[i].y][snakeCells[i].x] = snakeCells[i];
//				matrix
//				[snake.SnakeCells[snake.SnakeCells.length - 1].y]
//				[snake.SnakeCells[snake.SnakeCells.length - 1].x]
//					= snake.SnakeCells[snake.SnakeCells.length - 1];
		}
		
		
		int foodNum = 0;
		
		for(int j = 0; j < matrix.length; j++) {
			for(int k = 0; k < matrix[0].length; k++) {
				if (matrix[j][k].getClass() == FoodCell.class) foodNum++;
			}
		}
		
		if (foodNum == 0) {
			final Random random = new Random();
			int y = random.nextInt(matrix.length - 1);
			int x = random.nextInt(matrix[0].length - 1);
			
			if (matrix[y][x].getClass() != SnakeCell.class) matrix[y][x] = new FoodCell(x, y);
		}
	}
	
	public void UpdateMatrix() {
		snake.Move(HeadDirection);
		Init();
	}
}
