import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class GameLogic {
	private static GameLogic logic;
	private boolean game_start = false;
	private boolean game_pause = false;
	private int down_time = 0;
	private int blink_time = 0;
	private boolean prize_effect0 = false;
	private boolean prize_effect1 = false;
	private boolean prize_effect2 = false;
	private boolean prize_effect3 = false;
	private boolean prize_effect4 = false;
	public ArrayList<Ball> extra;

	public boolean isPrize_effect4() {
		return prize_effect4;
	}

	public void setPrize_effect4(boolean prize_effect4) {
		this.prize_effect4 = prize_effect4;
	}

	private boolean prize_effect5 = false;

	private int prize_time0 = 0;
	private int prize_time1 = 0;
	private int prize_time2 = 0;
	private int prize_time3 = 0;
	private int prize_time4 = 0;
	private int prize_time5 = 0;

	public void moveBoardLeft(Board board) {
		if (board.getX() >= 34) {
			board.setX(board.getX() - Constants.board_Vx);
		}
	}

	public void removeExtraHouses() {
		ArrayList<House> temp = new ArrayList<House>();
		for (House house : GameState.getInstance().getHouses()) {
			if (house.getSpirit() <= 0)
				temp.add(house);
		}
		for (House house : temp) {
			GameState.getInstance().getHouses().remove(house);
		}
	}

	public void removeExtraBalls() {
		ArrayList<Ball> temp = new ArrayList<Ball>();
		for (Ball ball : GameState.getInstance().getBalls()) {
			if (ball.getSpirit() <= 0)
				temp.add(ball);
		}
		for (Ball ball : temp) {
			GameState.getInstance().getBalls().remove(ball);
		}
	}

	public void checkEndGame() throws IOException {
		boolean pr = false;
		if (GameState.getInstance().getBalls().size() == 0)
			pr = true;
		for (House house : GameState.getInstance().getHouses()) {
			if (house.getY() >= 500)
				pr = true;
		}
		if (pr) {
			this.game_start = false;
			String name = JOptionPane.showInputDialog(Main.frame, "Enter your name");
			Saver.saveScore(new Player(name, GameState.getInstance().getScore()));
			if (GameState.getInstance().getHighscore() < GameState.getInstance().getScore())
				GameState.getInstance().setHighscore(GameState.getInstance().getScore());
		}
	}

	public void moveBalls(ArrayList<Ball> balls) {
		for (Ball ball : balls) {
			ball.setX(ball.getX() + ball.getVx());
			ball.setY(ball.getY() + ball.getVy());
		}
	}

	public void moveBoardRight(Board board) {
		if (board.getX() <= 339)
			board.setX(board.getX() + Constants.board_Vx);
	}

	public void moveBoardDown(Board board) {
		if (board.getY() <= 585) {
			board.setY(board.getY() + Constants.board_Vy);
		}
	}

	public void moveBoardUp(Board board) {
		if (board.getY() >= 35) {
			board.setY(board.getY() - Constants.board_Vy);
		}
	}

	public void checkBallStrikesToWalls(Ball ball) {
		if (ball.getX() <= 30) {
			ball.setVx(ball.getVx() * -1);
		}
		if (ball.getX() >= 365)
			ball.setVx(ball.getVx() * -1);
		if (ball.getY() >= 585) {
			ball.setSpirit(ball.getSpirit() - 1);
//			ball.setY(ball.getY() - 10);
			ball.setVy(ball.getVy() * -1);
//			ball.setX(ball.getX() + ball.getVy());
		}

		if (ball.getY() <= 30)
			ball.setVy(ball.getVy() * -1);
	}

//	private void removeBall(Ball ball) {
//		removableBalls.add(ball);
//	}

//	public void checkRemoveBalls() {
//		for (Ball ball : removableBalls)
//			GameState.getInstance().getBalls().remove(ball);
//	}

	public void checkBallStrikesToBoard(Ball ball, Board board) {
		if (ball.getX() >= board.getX() && ball.getX() <= board.getX() + board.getWidth() - 5) {
			if (ball.getY() >= board.getY() - 16 && ball.getY() <= board.getY() - 10) {
				if (ball.getVy() > 0) {
					strikesToBoard(ball, board);
					GameState.getInstance().setScore(GameState.getInstance().getScore() + 1);
				}

			}
		}
	}

	private void strikesToBoard(Ball ball, Board board) {
		if ((ball.getVx() > 0 && ball.getX() <= board.getX() + board.getWidth() / 3)
				|| (ball.getVx() < 0 && ball.getX() >= board.getX() + 2 * board.getWidth() / 3)) {
			if (ball.getVx() > 0)
				ball.setVx((int) ((int) 8 * ball.getRatio()));
			else
				ball.setVx((int) (-8 * ball.getRatio()));
			if (ball.getVy() > 0)
				ball.setVy((int) (-12 * ball.getRatio()));
			else
				ball.setVy((int) (12 * ball.getRatio()));
		} else if ((ball.getVx() < 0 && ball.getX() <= board.getX() + board.getWidth() / 3)
				|| (ball.getVx() > 0 && ball.getX() >= board.getX() + 2 * board.getWidth() / 3)) {
			if (ball.getVx() > 0)
				ball.setVx((int) (12 * ball.getRatio()));
			else
				ball.setVx((int) (-12 * ball.getRatio()));
			if (ball.getVy() > 0)
				ball.setVy((int) (-8 * ball.getRatio()));
			else
				ball.setVy((int) (8 * ball.getRatio()));
		} else if ((ball.getX() > board.getX() + board.getWidth() / 3)
				&& (ball.getX() < board.getX() + 2 * board.getWidth() / 3)) {

			if (ball.getVx() > 0)
				ball.setVx((int) (10 * ball.getRatio()));
			else
				ball.setVx((int) (-10 * ball.getRatio()));
			if (ball.getVy() > 0)
				ball.setVy((int) (-10 * ball.getRatio()));
			else
				ball.setVy((int) (10 * ball.getRatio()));
		}
	}

	public void updatePrizes(Ball ball) {
		if (prize_effect0) {
			prize_time0++;
			if (prize_time0 == 50) {
				prize_time0 = 0;
				prize_effect0 = false;
				ball.setRatio(1);
			}
		}
		if (prize_effect1) {
			prize_time1++;
			if (prize_time1 == 50) {
				prize_time1 = 0;
				prize_effect1 = false;
				ball.setRatio(1);
			}
		}
		if (prize_effect2) {
			prize_time2++;
			if (prize_time2 == 50) {
				prize_time2 = 0;
				prize_effect2 = false;
				GameState.getInstance().getBoard().setWidth(75);
			}
		}
		if (prize_effect3) {
			prize_time3++;
			if (prize_time3 == 50) {
				prize_time3 = 0;
				prize_effect3 = false;
				GameState.getInstance().getBoard().setWidth(75);
			}
		}
		if (prize_effect4) {
			prize_time4++;
			if (prize_time4 == 50) {
				prize_time4 = 0;
				prize_effect4 = false;
			}
		}
		if (prize_effect5) {
			prize_time5++;
			if (prize_time5 == 50) {
				prize_time5 = 0;
				prize_effect5 = false;
				ball.setFired(false);
			}
		}
	}

	public void updateBlinkHouses() {
		blink_time++;
		if (blink_time == 50) {
			blink_time = 0;
			for (House house : GameState.getInstance().getHouses()) {
				if (house.getType() == Type.blink) {
					if (house.getColor() == null) {
						house.setColor(Constants.blink_house_first);
					} else {
						house.setColor(null);
					}
				}
			}
		}
	}

	public void updateHousesMove() {
		down_time++;
		if (down_time == 300) {
			down_time = 0;
			for (House house : GameState.getInstance().getHouses()) {
				house.setY(house.getY() + Constants.house_disty);
			}
			int x = Constants.house_firstx;
			int y = Constants.house_firsty;
			int width = Constants.house_width;
			int height = Constants.house_height;
			for (int i = 0; i < 7; i++) {
				GameState.getInstance().getHouses().add(Factory.getInstance().ProduceRandomHouse(x, y, width, height));
				x += Constants.house_distx;
			}
		}
	}

	public int getBlink_time() {
		return blink_time;
	}

	public void setBlink_time(int blink_time) {
		this.blink_time = blink_time;
	}

	public int getDown_time() {
		return down_time;
	}

	public void setDown_time(int down_time) {
		this.down_time = down_time;
	}

	public boolean isGame_pause() {
		return game_pause;
	}

	public void setGame_pause(boolean game_pause) {
		this.game_pause = game_pause;
	}

	public boolean isGame_start() {
		return game_start;
	}

	public void setGame_start(boolean game_start) {
		this.game_start = game_start;
	}

	public GameLogic() {
//		removableBalls = new ArrayList<Ball>();
		extra = new ArrayList<Ball>();
	}

	public void addNewBalls() {
		for (Ball ball : extra) {
			GameState.getInstance().getBalls().add(ball);
		}
		extra = new ArrayList<Ball>();
	}

	public static GameLogic getInstance() {
		if (logic == null)
			logic = new GameLogic();
		return logic;
	}

	public void checkBallStrikesToHouses(Ball ball) {
		for (House house : GameState.getInstance().getHouses()) {
			if (ball.getX() >= house.getX() && ball.getX() <= house.getX() + house.getWidth() - 5) {
				if (ball.getY() >= house.getY() && ball.getY() <= house.getY() + house.getHeight()) {
					if (ball.getVy() < 0) {
						strikes(house, ball);
						ball.setVy(ball.getVy() * -1);
						if (ball.isFired())
							ball.setVy(ball.getVy() * -1);
					}

				}
			}
		}
	}

	private void strikes(House house, Ball ball) {
		switch (house.getType()) {
		case invisible: {
			strikesToInvisible(house, ball);
			GameState.getInstance().setScore(GameState.getInstance().getScore() + 2);
			break;
		}
		case blink: {
			strikesToBlink(house, ball);
			GameState.getInstance().setScore(GameState.getInstance().getScore() + 4);
			break;
		}
		case glass: {
			strikesToGlass(house, ball);
			GameState.getInstance().setScore(GameState.getInstance().getScore() + 3);
			break;
		}
		case wood: {
			strikesToWood(house, ball);
			GameState.getInstance().setScore(GameState.getInstance().getScore() + 1);
			if (ball.isFired())
				house.setSpirit(0);
			break;
		}
		case prize: {
			strikesToPrize(house, ball);
			GameState.getInstance().setScore(GameState.getInstance().getScore() + 5);
			break;
		}
		}
	}

	private void strikesToPrize(House house, Ball ball) {
		house.setSpirit(house.getSpirit() - 1);
		Random random = new Random();
		int r = random.nextInt(7);
		switch (r) {
		case 0: {
			prize_effect0 = true;
			ball.setRatio(2);
			break;
		}
		case 1: {
			prize_effect1 = true;
			ball.setRatio(0.5);
			break;
		}
		case 2: {
			prize_effect2 = true;
			GameState.getInstance().getBoard().setWidth(50);
			break;
		}
		case 3: {
			prize_effect3 = true;
			GameState.getInstance().getBoard().setWidth(100);
			break;
		}
		case 4: {
			prize_effect4 = true;
			break;
		}
		case 5: {
			ball.setFired(true);
			prize_effect5 = true;
			break;
		}
		case 6: {
			Ball b1 = new Ball(ball.getX(), ball.getY(), 10, 10);
			Ball b2 = new Ball(ball.getX(), ball.getY(), -10, -10);
//			GameState.getInstance().getBalls().add(b1);
//			GameState.getInstance().getBalls().add(b2);
			extra.add(b1);
			extra.add(b2);
			break;
		}
		}
	}

	private void strikesToWood(House house, Ball ball) {
		house.setSpirit(house.getSpirit() - 1);
	}

	private void strikesToGlass(House house, Ball ball) {
		house.setSpirit(house.getSpirit() - 1);
	}

	private void strikesToBlink(House house, Ball ball) {
		if (house.getColor() != null)
			house.setSpirit(house.getSpirit() - 1);
	}

	private void strikesToInvisible(House house, Ball ball) {
		house.setSpirit(house.getSpirit() - 1);
	}
}
