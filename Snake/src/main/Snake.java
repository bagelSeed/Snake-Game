package main;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Snake implements ActionListener, KeyListener {

	public static Snake game;// snake

	public JFrame board;// jframe
	public Render render;// renderPanel

	public Timer tick = new Timer(20, this);// timer

	// Building the snake array:
	// snakeparts
	public ArrayList<Point> snakeLength = new ArrayList<Point>();
	public Point sHead;// head
	public Point food;// cherry

	// direction:
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public static final int PROPORTION = 10;
	public static final int x = 99;
	public static final int y = 46;
	
	
	public int tCounter = 0;// ticks
	public int direction = DOWN;
	public int score;// score
	public int tailLength = 10;//

	// Spawn:
	public Random randNum;
	public boolean death = false;
	public boolean pause = false;
	public Dimension dmn;// dim

	public Snake() {
		// TODO Auto-generated constructor stub
		dmn = Toolkit.getDefaultToolkit().getScreenSize();

		board = new JFrame("SnakeGame");
		board.setVisible(true);
		board.setSize(1005, 500);
		board.setResizable(false);
		board.setLocation(dmn.width / 2 - board.getWidth() / 2, dmn.height / 2
				- board.getHeight() / 2);
		board.add(render = new Render());
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.addKeyListener(this);

		// direction = randNum.nextInt(3);
		// direction = 1;
		// int x = randNum.nextInt(500);
		// int y = randNum.nextInt(500);
		
		startGame();
	}
	
	public void startGame(){
		death = false;
		pause = false;
		
		score = 0;
		tCounter = 0;
		
		tailLength = 1;
		direction = DOWN;
		snakeLength.clear();
		
		randNum = new Random();
		sHead = new Point(0,0);
		food = new Point(randNum.nextInt(x), randNum.nextInt(y));
		System.out.println(food.x +" and " +food.y);
		System.out.println(sHead.x +" and " +sHead.y);
		tick.start();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		render.repaint();
		tCounter++;

		if (tCounter % 1 == 0 && sHead != null && death != true && pause != true) {
			snakeLength.add(new Point(sHead.x, sHead.y));
			//System.out.println(food.x + " " + food.y);
			if (direction == UP)
				if (sHead.y - 1 >= 0 && friendly(sHead.x, sHead.y - 1) != true)
					sHead = new Point(sHead.x, sHead.y - 1);
				else
					death = true;

			if (direction == DOWN)
				if (sHead.y + 1 <= y && friendly(sHead.x, sHead.y + 1) != true)
					sHead = new Point(sHead.x, sHead.y + 1);
				else
					death = true;

			if (direction == LEFT)
				if (sHead.x - 1 >= 0 && friendly(sHead.x - 1, sHead.y) != true)
					sHead = new Point(sHead.x - 1, sHead.y);
				else
					death = true;

			if (direction == RIGHT)
				if (sHead.x + 1 <= x && friendly(sHead.x + 1, sHead.y) != true)
					//System.out.println(dmn.width);
					sHead = new Point(sHead.x + 1, sHead.y);
				else
					death = true;

			if(snakeLength.size() > tailLength){
				snakeLength.remove(0);
			}

			if (food != null) {
				if (sHead.x == food.x && sHead.y == food.y) {
					score += 10;
					tailLength++;

					food.setLocation(randNum.nextInt(x),
							randNum.nextInt(y));
				}
			}
		}
	}

	public boolean friendly(int x, int y){
		for(Point temp : snakeLength){
			if(temp.equals(new Point(x,y))){
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		game = new Snake();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		
//		if(keyCode == KeyEvent.VK_UP && direction != DOWN)
//			direction = UP;
//		if(keyCode == KeyEvent.VK_DOWN && direction != UP)
//			direction = DOWN;
//		if(keyCode == KeyEvent.VK_LEFT && direction != RIGHT)
//			direction = LEFT;
//		if(keyCode == KeyEvent.VK_RIGHT && direction != LEFT)
//			direction = RIGHT;
//		if(keyCode == KeyEvent.VK_SPACE){
//			if(death == true){
//				startGame();
//			}
//			else {
//				pause = !pause;
//			}
//		}
		
		
		switch (keyCode) {
		case KeyEvent.VK_UP:
			if (direction != DOWN)
				direction = UP;
			break;
		case KeyEvent.VK_W:
			if (direction != DOWN)
				direction = UP;
			break;
			
		case KeyEvent.VK_DOWN:
			if (direction != UP)
				direction = DOWN;
			break;
		case KeyEvent.VK_S:
			if (direction != UP)
				direction = DOWN;
			break;
			
		case KeyEvent.VK_LEFT:
			if (direction != RIGHT)
				direction = LEFT;
			break;
		case KeyEvent.VK_A:
			if (direction != RIGHT)
				direction = LEFT;
			break;
			
		case KeyEvent.VK_RIGHT:
			if (direction != LEFT)
				direction = RIGHT;
			break;
		case KeyEvent.VK_D:
			if (direction != LEFT)
				direction = RIGHT;
			break;
			
		case KeyEvent.VK_SPACE:
			if(death == true){
				startGame();
			}
			else {
				pause = !pause;
			}
			
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
