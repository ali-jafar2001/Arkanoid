import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
	public static ArrayList<Player> loadScoreTable() throws FileNotFoundException {
		File file = new File("resource\\files\\players.txt");
		Scanner scanner = new Scanner(file);
		ArrayList<String> list = new ArrayList<String>();
		while (scanner.hasNextLine())
			list.add(scanner.nextLine());
		ArrayList<Integer> scores = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < list.size(); i += 2) {
			names.add(getPlayerName(list.get(i)));
			scores.add(getPlayerScore(list.get(i + 1)));
		}
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < names.size(); i++) {
			Player player = new Player(names.get(i), scores.get(i));
			players.add(player);
		}
		return players;
	}

	private static String getPlayerName(String st) {
		return st.substring(5);
	}

	private static int getPlayerScore(String st) {
		String str = st.substring(6);
		return Integer.valueOf(str);
	}

	public static void loadGame(String name) throws FileNotFoundException {
		File file = new File("resource\\files\\" + name + ".txt");
		ArrayList<String> list = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine())
			list.add(scanner.nextLine());
		int board_x = 0, board_y = 0, board_width = 0, board_height = 0, score = 0;
		for (String st : list) {
			if (st.contains("board-x=")) {
				board_x = Integer.valueOf(st.substring(8));
			} else if (st.contains("board-y=")) {
				board_y = Integer.valueOf(st.substring(8));
			} else if (st.contains("board-width=")) {
				board_width = Integer.valueOf(st.substring(12));
			} else if (st.contains("board-height=")) {
				board_height = Integer.valueOf(st.substring(13));
			} else if (st.contains("score=")) {
				score = Integer.valueOf(st.substring(6));
			}
		}
		Board board = new Board(board_x, board_y, board_width, board_height);
		ArrayList<Integer> ball_x = new ArrayList<Integer>();
		ArrayList<Integer> ball_y = new ArrayList<Integer>();
		ArrayList<Integer> ball_Vx = new ArrayList<Integer>();
		ArrayList<Integer> ball_Vy = new ArrayList<Integer>();
		ArrayList<Integer> ball_spirit = new ArrayList<Integer>();
		ArrayList<Double> ball_ratio = new ArrayList<Double>();
		ArrayList<Boolean> ball_isFired = new ArrayList<Boolean>();
		for (String st : list) {
			if (st.contains("ball-x=")) {
				ball_x.add(Integer.valueOf(st.substring(7)));
			} else if (st.contains("ball-y=")) {
				ball_y.add(Integer.valueOf(st.substring(7)));
			} else if (st.contains("ball-Vx=")) {
				ball_Vx.add(Integer.valueOf(st.substring(8)));
			} else if (st.contains("ball-Vy=")) {
				ball_Vy.add(Integer.valueOf(st.substring(8)));
			} else if (st.contains("ball-spirit=")) {
				ball_spirit.add(Integer.valueOf(st.substring(12)));
			} else if (st.contains("ball-ratio=")) {
				ball_ratio.add(Double.valueOf(st.substring(11)));
			} else if (st.contains("ball-isFired=")) {
				ball_isFired.add(Boolean.valueOf(st.substring(13)));
			}
		}
		ArrayList<Ball> balls = new ArrayList<Ball>();
		for (int i = 0; i < ball_x.size(); i++) {
			Ball ball = new Ball(ball_x.get(i), ball_y.get(i), ball_Vx.get(i), ball_Vy.get(i));
			ball.setSpirit(ball_spirit.get(i));
			ball.setImage(Toolkit.getDefaultToolkit().getImage("resource//ball.png"));
			ball.setFired(ball_isFired.get(i));
			ball.setRatio(ball_ratio.get(i));
			balls.add(ball);
			
		}
		ArrayList<Integer> house_x = new ArrayList<Integer>();
		ArrayList<Integer> house_y = new ArrayList<Integer>();
		ArrayList<Integer> house_width = new ArrayList<Integer>();
		ArrayList<Integer> house_height = new ArrayList<Integer>();
		ArrayList<Integer> house_spirit = new ArrayList<Integer>();
		ArrayList<Type> house_type = new ArrayList<Type>();
		for (String st : list) {
			if (st.contains("house-x=")) {
				house_x.add(Integer.valueOf(st.substring(8)));
			} else if (st.contains("house-y=")) {
				house_y.add(Integer.valueOf(st.substring(8)));
			} else if (st.contains("house-width=")) {
				house_width.add(Integer.valueOf(st.substring(12)));
			} else if (st.contains("house-height=")) {
				house_height.add(Integer.valueOf(st.substring(13)));
			} else if (st.contains("house-spirit=")) {
				house_spirit.add(Integer.valueOf(st.substring(13)));
			} else if (st.contains("house-type=")) {
				String type = st.substring(11);
				switch (type) {
				case "invisible": {
					house_type.add(Type.invisible);
					break;
				}
				case "wood": {
					house_type.add(Type.wood);
					break;
				}
				case "glass": {
					house_type.add(Type.glass);
					break;
				}
				case "blink": {
					house_type.add(Type.blink);
					break;
				}
				case "prize": {
					house_type.add(Type.prize);
					break;
				}
				}
			}
		}
		ArrayList<House> houses = new ArrayList<House>();
		for (int i = 0; i < house_x.size(); i++) {
			Color color = Constants.prize_house;
			if (house_type.get(i) == Type.glass)
				color = null;
			if (house_type.get(i) == Type.wood)
				color = Constants.wood_house;
			if (house_type.get(i) == Type.invisible)
				color = Constants.invisible_house;
			if (house_type.get(i) == Type.blink)
				color = Constants.blink_house_first;
			House house = new House(house_x.get(i), house_y.get(i), house_width.get(i), house_height.get(i), color,
					house_type.get(i), house_spirit.get(i));
			houses.add(house);
		}
		GameState.getInstance().setScore(score);
		GameState.getInstance().setBalls(balls);
		GameState.getInstance().setBoard(board);
		GameState.getInstance().setHouses(houses);
	}
}
