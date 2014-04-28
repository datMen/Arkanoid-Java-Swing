import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard {
	private Pantalla game;
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
			if (KeyEvent.getKeyText(e.getKeyCode()) == "Izquierda") {
				game.bar.move("Izquierda");
			}
			else if (KeyEvent.getKeyText(e.getKeyCode()) == "Derecha") {
				game.bar.move("Derecha");
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}