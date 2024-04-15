import java.util.ArrayList;

public class GameState {
	private static GameState state;
	private Board board;
	private ArrayList<Ball> balls;
	private ArrayList<House> houses;
	private int score;
	private int highscore;

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public int getScore() {
		return score;
	}

	public static void setNull() {
		state = null;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<House> getHouses() {
		return houses;
	}

	public void setHouses(ArrayList<House> houses) {
		this.houses = houses;
	}

	public ArrayList<Ball> getBalls() {
		return balls;
	}

	public void setBalls(ArrayList<Ball> balls) {
		this.balls = balls;
	}

	public GameState() {
		balls = new ArrayList<Ball>();
		houses = new ArrayList<House>();
		board = new Board(Constants.board_x, Constants.board_y, Constants.board_width, Constants.board_height);
		Ball ball = new Ball(Constants.ball_x, Constants.ball_y, Constants.ball_vx, Constants.ball_vy);
		ball.setSpirit(3);
		balls.add(ball);
		initHouses();
	}

	private void initHouses() {
		int x = Constants.house_firstx;
		int y = Constants.house_firsty;
		int width = Constants.house_width;
		int height = Constants.house_height;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				houses.add(Factory.getInstance().ProduceRandomHouse(x, y, width, height));
				y += Constants.house_disty;
			}
			y = Constants.house_firsty;
			x += Constants.house_distx;
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public static GameState getInstance() {
		if (state == null)
			state = new GameState();
		return state;
	}
}
