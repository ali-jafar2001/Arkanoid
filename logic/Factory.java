import java.awt.Color;
import java.util.Random;

public class Factory {
	private static Factory factory;

	public static Factory getInstance() {
		if (factory == null)
			factory = new Factory();
		return factory;
	}

	public Ball ProduceBall(int x, int y, int Vx, int Vy) {
		Ball ball = new Ball(x, y, Vx, Vy);
		return ball;
	}

	public Player ProducePlayer(String name, int highscore) {
		return new Player(name, highscore);
	}

//	private House ProduceHouse(int x, int y, int width, int height, Color color, Type type, int spirit) {
//		return new House(x, y, width, height, color, type, spirit);
//	}

	public House ProduceRandomHouse(int x, int y, int width, int height) {
		Random random = new Random(System.nanoTime());
		int i = random.nextInt(5);
		Type type = getType(i);
		Color color = getColor(type);
		int spirit = getSpirit(type);
		return new House(x, y, width, height, color, type, spirit);
	}

	private Type getType(int i) {
		if (i == 0)
			return Type.glass;
		else if (i == 1)
			return Type.wood;
		else if (i == 2)
			return Type.invisible;
		else if (i == 3)
			return Type.blink;
		return Type.prize;

	}

	private Color getColor(Type type) {
		if (type == Type.glass)
			return null;
		if (type == Type.wood)
			return Constants.wood_house;
		if (type == Type.invisible)
			return Constants.invisible_house;
		if (type == Type.blink)
			return Constants.blink_house_first;
		return Constants.prize_house;
	}

	private Color randomColor() {
		Random random = new Random();
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return new Color(r,g,b);
	}

	private int getSpirit(Type type) {
		if (type == Type.wood)
			return 2;
		return 1;
	}
}
