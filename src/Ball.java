import java.awt.Graphics2D;

public class Ball {
	int x = 0;
	int y = 0;
	int xa = 1;
	int ya = 1;
	private Pantalla game;

	public Ball(Pantalla game) {
		this.game = game;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		if (x + xa > game.getWidth() - 10)
			xa = -1;
		if (y + ya < 0)
			ya = 1;
		if (y + ya > game.getHeight() - 10)
			ya = -1;

		x = x + xa;
		y = y + ya;
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, 10, 10);
	}
}
