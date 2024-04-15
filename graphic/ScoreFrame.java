import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScoreFrame extends JPanel {
	private Image background;
	private List<Player> players;

	public ScoreFrame() throws FileNotFoundException {
		players = Loader.loadScoreTable();
		sortPlayers();
		JFrame frame = new JFrame();
		JButton ok = new JButton("OK");
		frame.setSize(new Dimension(600, 400));
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(this);
		background = Toolkit.getDefaultToolkit().getImage("resource//theme.jpg");
		frame.setVisible(true);
	}

	private void sortPlayers() {
		Player[] temp = new Player[players.size()];
		for (int i = 0; i < players.size(); i++) {
			temp[i] = players.get(i);
		}
		for (int i = 0; i < players.size(); i++) {
			for (int j = i + 1; j < players.size(); j++) {
				if (temp[i].getHighscore() < temp[j].getHighscore()) {
					Player p = temp[i];
					temp[i] = temp[j];
					temp[j] = p;
				}
			}
		}
		players = new ArrayList<Player>();
		for (int i = 0; i < temp.length; i++) {
			players.add(temp[i]);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background, 0, 0, 600, 400, this);
		g2D.setFont(new Font("serif", 100, 20));
		g2D.setColor(Color.yellow);
		g2D.drawString("name:" + "                     " + "score:", 100, 50);
		int y = 80;
		for (int i = 0; i < players.size(); i++) {
			g2D.drawString(Integer.toString(i + 1) + "- " + players.get(i).getName(), 80, y);
			g2D.drawString(Integer.toString(players.get(i).getHighscore()), 250, y);
			y += 30;
		}
	}
}
