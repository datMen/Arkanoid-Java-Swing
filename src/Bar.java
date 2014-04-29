import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bar {
	public static final int Y = 40;
	private static final int WIDTH = 27;
	private static final int HEIGHT = 10;
	public int x = 125;
	int move_speed = 10;
	private Pantalla game;

	public Bar(Pantalla game) {
		this.game = game;
	}


	void move(int direction) {
		int width_margin = 5;
		if (direction == ListenersHandler.LEFT) {
			if (x > width_margin) {
				x -= move_speed;
			}
		}
		else if (direction == ListenersHandler.RIGHT) {
			if (x < game.getWidth()-(WIDTH+width_margin)) {
				x += move_speed;
			}
		}

	}

	public void paint(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRoundRect(x-15, game.getHeight()-Y, WIDTH-10, HEIGHT, 10, 10);
		g.setColor(Color.RED);
		g.fillRoundRect(x+25, game.getHeight()-Y, WIDTH-10, HEIGHT, 10, 10);
		g.setColor(Color.BLACK);
		g.fillRect(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}
	
	public int getTopY() {
		return game.getHeight()-Y - HEIGHT;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}
	
	public Rectangle getLeftBounds() {
		return new Rectangle(x-20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
	
	public Rectangle getRightBounds() {
		return new Rectangle(x+20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
}
