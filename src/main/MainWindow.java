package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import game.*;

public final class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel;
	private Game game;
	private Timer timer;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPanel);
		addKeyListener(new MyKeyAdapter());
		
		timer = new Timer(200, this);
        timer.start();
		
		game = new Game(20, new Snake(3, 10, 11));
		game.HeadDirection = 'L';
	}
	
	@Override
    public void paint(Graphics g) {
		Graphics2D graphic2d = (Graphics2D) g;
		if (game.Running) game.UpdateMatrix();
		else timer.stop();
		
		graphic2d.drawString(String.valueOf(game.snake.SnakeCells.length), 450, 450);
		
		RedrawMatrix(graphic2d);
	}
	
	private void RedrawMatrix(Graphics2D graphic2d) {
		graphic2d.setColor(Color.BLACK);
		graphic2d.fillRect(0, 0, 420, 420);
		
		for(Cell[] row : game.matrix) {
			for(Cell cell : row) {
				if (cell.getClass() == SnakeCell.class) {
					graphic2d.setColor(Color.GREEN);
					graphic2d.fillRect(cell.x*20, cell.y*20 + 50, 20, 20);
					graphic2d.setColor(Color.WHITE);
					graphic2d.drawString(String.valueOf(
							((SnakeCell) cell).Index
							), cell.x*20, cell.y*20 + 50);
				}
				else if (cell.getClass() == FoodCell.class) {
					graphic2d.setColor(Color.RED);
					graphic2d.fillRect(cell.x*20, cell.y*20 + 50, 20, 20);
					graphic2d.setColor(Color.WHITE);
					graphic2d.drawString(String.valueOf(game.snake.SnakeCells.length), cell.x*21, cell.y*21 + 50);
				}
				else {
					graphic2d.setColor(Color.BLACK);
					graphic2d.fillRect(cell.x*20, cell.y*20 + 50, 20, 20);
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                	if (game.HeadDirection != 'R') game.HeadDirection = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                	if (game.HeadDirection != 'L') game.HeadDirection = 'R';
                    break;
                case KeyEvent.VK_UP:
                	if (game.HeadDirection != 'D') game.HeadDirection = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                	if (game.HeadDirection != 'U') game.HeadDirection = 'D';
                    break;
            }
        }
    }
}
