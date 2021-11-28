package game;

public class SnakeCell extends Cell {
	public int Index;
	
	public SnakeCell(int x, int y, int index) {
		super(x, y);
		this.Index = index;
	}
}
