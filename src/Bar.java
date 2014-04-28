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


	void move(String direction) {
		if (direction == "Izquierda") {
			if (x > 5) {
				x -= 10;
			}
		}
		else if (direction == "Derecha") {
			if (x < game.getWidth()-55) {
				x += 10;
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
