package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Render extends JPanel {
	
	public static Color blue = Color.BLUE;
	public static Color red = Color.RED;
	public static Color black = Color.BLACK;
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(blue);
		g.fillRect(0,0,1000,500);
		//System.out.println("Stuff");
		//super.paintComponent(g);
		
		//Fill Snake
		Snake snake = Snake.game;
		
		g.setColor(red);
		for(Point point : snake.snakeLength){
			g.fillRect(point.x * Snake.PROPORTION,
						point.y * Snake.PROPORTION,
						Snake.PROPORTION,
						Snake.PROPORTION);
		
		}
		
		g.setColor(black);
		g.fillRect(snake.food.x * Snake.PROPORTION,
				snake.food.y * Snake.PROPORTION,
				Snake.PROPORTION,
				Snake.PROPORTION);
		String message = "Length: " + snake.score/10;
		g.drawString(message, 0, 10);
		message = "Space Bar to pause the game, or restart if the snake dies";
		g.drawString(message, 0, 20);
	}
}
