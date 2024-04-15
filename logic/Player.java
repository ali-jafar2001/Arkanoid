
public class Player {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return name + "                             "  + highscore;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	private int highscore;

	public Player(String name, int highscore) {
		this.name = name;
		this.highscore = highscore;
	}

}
