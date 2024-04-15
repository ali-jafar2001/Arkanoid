import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MainFrame extends JFrame {
	private JMenuBar menu_bar;

	public JMenuBar getMenu_bar() {
		return menu_bar;
	}

	public void setMenu_bar(JMenuBar menu_bar) {
		this.menu_bar = menu_bar;
	}

	private JMenu game_menu;
	private JMenuItem new_game;
	private JMenuItem pause;
	private JMenuItem save;
	private JMenuItem load;
	private JMenuItem exit;
	private JMenuItem scores;

	public JMenuItem getScores() {
		return scores;
	}

	public void setScores(JMenuItem scores) {
		this.scores = scores;
	}

	private JLabel score_text;
	private JLabel score;
	private JLabel highscore_text;

	public JLabel getHighscore_text() {
		return highscore_text;
	}

	public void setHighscore_text(JLabel highscore_text) {
		this.highscore_text = highscore_text;
	}

	public JLabel getHighscore() {
		return highscore;
	}

	public void setHighscore(JLabel highscore) {
		this.highscore = highscore;
	}

	private JLabel highscore;

	private GamePanel game_panel;

	public GamePanel getGame_panel() {
		return game_panel;
	}

	public void setGame_panel(GamePanel game_panel) {
		this.game_panel = game_panel;
	}

	public JMenu getGame_menu() {
		return game_menu;
	}

	public void setGame_menu(JMenu game_menu) {
		this.game_menu = game_menu;
	}

	public JMenuItem getNew_game() {
		return new_game;
	}

	public void setNew_game(JMenuItem new_game) {
		this.new_game = new_game;
	}

	public JMenuItem getPause() {
		return pause;
	}

	public void setPause(JMenuItem pause) {
		this.pause = pause;
	}

	public JMenuItem getSave() {
		return save;
	}

	public void setSave(JMenuItem save) {
		this.save = save;
	}

	public JMenuItem getLoad() {
		return load;
	}

	public void setLoad(JMenuItem load) {
		this.load = load;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}

	public JLabel getScore_text() {
		return score_text;
	}

	public void setScore_text(JLabel score_text) {
		this.score_text = score_text;
	}

	public JLabel getScore() {
		return score;
	}

	public void setScore(JLabel score) {
		this.score = score;
	}

	public MainFrame() {
		Initialize();
		Action.Exit(this);
		Action.NewGame(this);
		Action.Pause(this);
		Action.Save(this);
		Action.Load(this);
		Action.Scores(this);
	}

	private void Initialize() {
		setTitle("Arkanoid");
		setSize(new Dimension(Constants.frame_width, Constants.frame_height));
		setResizable(false);
		setLocationRelativeTo(null);
		Create_Menu_Bar();
		setJMenuBar(menu_bar);
		game_panel = new GamePanel();
		setContentPane(game_panel);
		SetLabels();
		addKeyListener(new Action());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void SetLabels() {
		score = new JLabel("0");
		score.setFont(new Font("serif", 10, 20));
		score.setBounds(410, 200, 160, 140);
		score.setForeground(Color.yellow);
		this.add(score);
		score_text = new JLabel("Score");
		score_text.setForeground(Color.yellow);
		score_text.setFont(new Font("serif", 10, 20));
		score_text.setBounds(410, 170, 160, 140);
		this.add(score_text);
		highscore = new JLabel("0");
		highscore.setForeground(Color.yellow);
		highscore.setFont(new Font("serif", 10, 20));
		highscore.setBounds(410, 80, 160, 140);
		this.add(highscore);
		highscore_text = new JLabel("High Score");
		highscore_text.setForeground(Color.yellow);
		highscore_text.setFont(new Font("serif", 10, 20));
		highscore_text.setBounds(410, 50, 160, 140);
		this.add(highscore_text);
	}

	private void Create_Menu_Bar() {
		menu_bar = new JMenuBar();
		game_menu = new JMenu("Game");
		game_menu.setMnemonic(KeyEvent.VK_G);
		this.new_game = new JMenuItem("New");
		this.scores = new JMenuItem("Scores");
		this.save = new JMenuItem("Save");
		this.load = new JMenuItem("Load");
		this.pause = new JMenuItem("Pause");
		this.exit = new JMenuItem("Exit");
		new_game.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		this.scores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		game_menu.add(new_game);
		game_menu.add(load);
		game_menu.add(save);
		game_menu.add(pause);
		game_menu.add(scores);
		game_menu.addSeparator();
		game_menu.add(exit);
		menu_bar.add(game_menu);
	}
}
