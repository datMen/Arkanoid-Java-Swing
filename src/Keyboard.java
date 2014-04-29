import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard {
	private Pantalla game;
	public static final int LEFT = 37;
	public static final int RIGHT = 39;
	public Keyboard(Pantalla game) {
		KeyListener listener = new MyKeyListener();
		game.addKeyListener(listener);
		game.setFocusable(true);
		this.game = game;
	}

	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == LEFT) {
				game.bar.move(LEFT);
			}
			else if (e.getKeyCode() == RIGHT) {
				game.bar.move(RIGHT);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}