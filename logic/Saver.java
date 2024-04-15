import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Saver {
	public static void saveScore(Player player) throws IOException {
		File file = new File("resource\\files\\players.txt");
		Scanner scanner = new Scanner(file);
		ArrayList<String> list = new ArrayList<String>();
		while (scanner.hasNextLine())
			list.add(scanner.nextLine());
		String name = "name=" + player.getName();
		String score = "score=" + player.getHighscore();
		list.add(name);
		list.add(score);
		FileWriter filewriter = new FileWriter(file);
		PrintWriter printwriter = new PrintWriter(filewriter);
		for (String st : list) {
			printwriter.println(st);
		}
		filewriter.close();
	}

	public static void saveGame(String name) throws IOException {
		File file = new File("resource\\files\\" + name + ".txt");
		if (!file.exists())
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println("board-x=" + GameState.getInstance().getBoard().getX());
		pw.println("board-y=" + GameState.getInstance().getBoard().getY());
		pw.println("board-width=" + GameState.getInstance().getBoard().getWidth());
		pw.println("board-height=" + GameState.getInstance().getBoard().getHeight());
		for (Ball ball : GameState.getInstance().getBalls()) {
			pw.println("ball-x=" + ball.getX());
			pw.println("ball-y=" + ball.getY());
			pw.println("ball-Vx=" + ball.getVx());
			pw.println("ball-Vy=" + ball.getVy());
			pw.println("ball-spirit=" + ball.getSpirit());
			pw.println("ball-isFired=" + ball.isFired());
			pw.println("ball-ratio=" + ball.getRatio());
		}
		for (House house : GameState.getInstance().getHouses()) {
			pw.println("house-x=" + house.getX());
			pw.println("house-y=" + house.getY());
			pw.println("house-width=" + house.getWidth());
			pw.println("house-height=" + house.getHeight());
			pw.println("house-type=" + house.getType());
			pw.println("house-spirit=" + house.getSpirit());
		}
		pw.println("score=" + GameState.getInstance().getScore());
		fw.close();
	}
}
