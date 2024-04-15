import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private Image theme;
	private Drawer drawer;
	private Timer timer;
	private Ticker ticker;

	public GamePanel() {
		setLayout(null);
		setBackground(Color.BLACK);
		theme = Toolkit.getDefaultToolkit().getImage("resource//theme.jpg");
		drawer = new Drawer();
		timer = new Timer();
		ticker = new Ticker();
		timer.scheduleAtFixedRate(ticker, 1000, 100);
	}

	public void pause() {
		timer.cancel();
	}

	public void resume() {
		timer = new Timer();
		ticker = new Ticker();
		timer.scheduleAtFixedRate(ticker, 1000, 100);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		drawer.draw_theme(theme, g2D, this);
		drawer.draw_walls(g2D);
		if (GameLogic.getInstance().isGame_start()) {
			drawer.draw_score(Main.frame);
			drawer.draw_highscore(Main.frame);
			for (House house : GameState.getInstance().getHouses())
				drawer.drawHouse(g2D, house);
			for (Ball ball : GameState.getInstance().getBalls())
				drawer.drawBall(g2D, ball, this);
			drawer.drawBoard(g2D, GameState.getInstance().getBoard());
		}

	}

	class Ticker extends TimerTask {

		@Override
		public void run() {
			if (GameLogic.getInstance().isGame_start()) {
//				GameLogic.getInstance().checkRemoveBalls();
				try {
					GameLogic.getInstance().checkEndGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
				GameLogic.getInstance().removeExtraBalls();
				GameLogic.getInstance().removeExtraHouses();
				GameLogic.getInstance().updateHousesMove();
				GameLogic.getInstance().updateBlinkHouses();
				GameLogic.getInstance().addNewBalls();
				for (Ball ball : GameState.getInstance().getBalls()) {
					GameLogic.getInstance().checkBallStrikesToWalls(ball);
					GameLogic.getInstance().checkBallStrikesToBoard(ball, GameState.getInstance().getBoard());
					GameLogic.getInstance().checkBallStrikesToHouses(ball);
					GameLogic.getInstance().updatePrizes(ball);
				}
				GameLogic.getInstance().moveBalls(GameState.getInstance().getBalls());

			}
			repaint();
		}

	}
}
