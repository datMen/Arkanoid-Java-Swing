import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bar {
	public static final int Y = 350;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 10;
	public int x = 125;
	private Pantalla game;

	public Bar(Pantalla game) {
		this.game = game;
	}


	void move(int direction) {
		int width_margin = 5;
		int move_speed = 10;
		if (direction == Keyboard.LEFT) {
			if (x > width_margin) {
				x -= move_speed;
			}
		}
		else if (direction == Keyboard.RIGHT) {
			if (x < game.getWidth()-(WIDTH+width_margin)) {
				x += move_speed;
			}
		}

	}

	public void paint(Graphics2D g) {
		g.fillRoundRect(x, Y, WIDTH, HEIGHT, 20, 20);
	}
	
	public int getTopY() {
		return Y - HEIGHT;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, Y, WIDTH, HEIGHT);
	}
}
