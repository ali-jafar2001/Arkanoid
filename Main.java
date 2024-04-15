import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

public class Main {
	public static MainFrame frame;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				frame = new MainFrame();
			}
		});

	}

}
