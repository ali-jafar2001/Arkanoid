import java.awt.Image;
import java.awt.Toolkit;

public class Ball {
	private int x;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVx() {
		return Vx;
	}

	public void setVx(int vx) {
		Vx = vx;
	}

	public int getVy() {
		return Vy;
	}

	public void setVy(int vy) {
		Vy = vy;
	}

	private int y;
	private int Vx;
	private double ratio;
	private boolean isFired = false;

	public double getRatio() {
		return ratio;
	}

	public void setRatio(double ratio) {
		this.ratio = ratio;
	}

	public boolean isFired() {
		return isFired;
	}

	public void setFired(boolean isFired) {
		this.isFired = isFired;
	}

	private int Vy;
	private int spirit;

	public int getSpirit() {
		return spirit;
	}

	public void setSpirit(int spirit) {
		this.spirit = spirit;
	}

	Image image;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Ball(int x, int y, int Vx, int Vy) {
		this.x = x;
		this.y = y;
		this.Vx = Vx;
		this.Vy = Vy;
		this.spirit = 3;
		image = Toolkit.getDefaultToolkit().getImage("resource//ball.png");
		ratio = 1;
	}

	public void move() {
		setX(x + Vx);
		setY(y + Vy);
	}
}
