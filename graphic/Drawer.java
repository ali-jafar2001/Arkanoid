import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class Drawer {
	public void draw_theme(Image image, Graphics2D g2D, JPanel panel) {
		g2D.drawImage(image, 0, 0, panel);
	}

	public void draw_score(MainFrame frame) {
		String score = Integer.toString(GameState.getInstance().getScore());
		frame.getScore().setText(score);
	}

	public void draw_highscore(MainFrame frame) {
		String score = Integer.toString(GameState.getInstance().getHighscore());
		frame.getHighscore().setText(score);
	}

	public void draw_walls(Graphics2D g2D) {

		int y = 20;
		for (int i = 0; i < 28; i++) {
			g2D.setColor(Color.BLUE);
			g2D.fillRect(20, y, 5, 20);
			y = y + 21;
		}
		int Y = y;
		int x = 20;
		for (int i = 0; i < 18; i++) {
			g2D.setColor(Color.BLUE);
			g2D.fillRect(x, 20, 20, 5);
			x = x + 21;
		}
		int X = x;

		x = 20;
		for (int i = 0; i < 18; i++) {
			g2D.setColor(Color.BLUE);
			g2D.fillRect(x, Y - 2, 20, 5);
			x = x + 21;
		}
		y = 20;
		for (int i = 0; i < 28; i++) {
			g2D.setColor(Color.BLUE);
			g2D.fillRect(X - 6, y, 5, 20);
			y = y + 21;
		}
	}

	public void drawBoard(Graphics2D g2D, Board board) {
		g2D.setColor(Constants.board_color);
		g2D.fillRect(board.getX(), board.getY(), board.getWidth(), board.getHeight());
	}

	public void drawBall(Graphics2D g2D, Ball ball, JPanel panel) {
		g2D.drawImage(ball.image, ball.getX(), ball.getY(), panel);
	}

	public void drawHouse(Graphics2D g2D, House house) {
		if (house.getType() != Type.glass && house.getType() != Type.blink) {
			g2D.setColor(house.getColor());
			g2D.fillRect(house.getX(), house.getY(), house.getWidth(), house.getHeight());
		}
		if (house.getType() == Type.blink && house.getColor() != null) {
			g2D.setColor(house.getColor());
			g2D.fillRect(house.getX(), house.getY(), house.getWidth(), house.getHeight());
		}
	}
}
