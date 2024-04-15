import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Action implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (GameLogic.getInstance().isGame_start()) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP: {
				if (!GameLogic.getInstance().isPrize_effect4())
					GameLogic.getInstance().moveBoardUp(GameState.getInstance().getBoard());
				else
					GameLogic.getInstance().moveBoardDown(GameState.getInstance().getBoard());
				break;
			}
			case KeyEvent.VK_LEFT: {
				if (!GameLogic.getInstance().isPrize_effect4())
					GameLogic.getInstance().moveBoardLeft(GameState.getInstance().getBoard());
				else
					GameLogic.getInstance().moveBoardRight(GameState.getInstance().getBoard());
				break;
			}
			case KeyEvent.VK_RIGHT: {
				if (!GameLogic.getInstance().isPrize_effect4())
					GameLogic.getInstance().moveBoardRight(GameState.getInstance().getBoard());
				else
					GameLogic.getInstance().moveBoardLeft(GameState.getInstance().getBoard());
				break;
			}
			case KeyEvent.VK_DOWN: {
				if (!GameLogic.getInstance().isPrize_effect4())
					GameLogic.getInstance().moveBoardDown(GameState.getInstance().getBoard());
				else
					GameLogic.getInstance().moveBoardUp(GameState.getInstance().getBoard());
				break;
			}
			default: {
				break;
			}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public static void Exit(MainFrame frame) {
		frame.getExit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.exit(0);
			}
		});
	}

	public static void NewGame(MainFrame frame) {
		frame.getNew_game().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameLogic.getInstance().setGame_pause(false);
				GameLogic.getInstance().setGame_start(true);
				GameState.setNull();
				GameState.getInstance().setScore(0);
			}
		});
	}

	public static void Pause(MainFrame frame) {
		frame.getPause().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (GameLogic.getInstance().isGame_pause()) {
					frame.getGame_panel().resume();
					GameLogic.getInstance().setGame_pause(false);
				} else {
					frame.getGame_panel().pause();
					GameLogic.getInstance().setGame_pause(true);
				}

			}
		});
	}

	public static void Save(MainFrame frame) {
		frame.getSave().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveConditions();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			private void saveConditions() throws IOException {
				frame.getGame_panel().pause();
				GameLogic.getInstance().setGame_pause(true);
				String name = JOptionPane.showInputDialog(Main.frame, "enter file name :");
				File file = new File("resource\\files\\" + name + ".txt");
				if (file.exists()) {
					int ans = JOptionPane.showConfirmDialog(Main.frame,
							"there is a file with this name. are you sure??");
					if (ans == JOptionPane.YES_OPTION) {
						Saver.saveGame(name);
					}
				} else {
					Saver.saveGame(name);
				}
				frame.getGame_panel().resume();
				GameLogic.getInstance().setGame_pause(false);

			}
		});
	}

	public static void Load(MainFrame frame) {
		frame.getLoad().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loadConditions();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void loadConditions() throws FileNotFoundException {
				frame.getGame_panel().pause();
				GameLogic.getInstance().setGame_pause(true);
				String name = JOptionPane.showInputDialog(Main.frame, "input file name :");
				File file = new File("resource\\files\\" + name + ".txt");
				if (!file.exists()) {
					JOptionPane.showMessageDialog(Main.frame, "there is no file with this name", "Alert",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Loader.loadGame(name);
					if (!GameLogic.getInstance().isGame_start())
						GameLogic.getInstance().setGame_start(true);
				}
				frame.getGame_panel().resume();
				GameLogic.getInstance().setGame_pause(false);
			}
		});
	}

	public static void Scores(MainFrame frame) {
		frame.getScores().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new ScoreFrame();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
