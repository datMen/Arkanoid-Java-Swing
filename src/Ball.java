import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Ball {
	private static final int DIAMETER = 10;
	int x = 100;
	int y = 100;
	int xa = 1;
	int ya = 1;
	int brick;
	private Pantalla game;

	public Ball(Pantalla game) {
		this.game = game;
	}

	void move() {
		if (x + xa < 0)
			xa = 1;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -1;
		else if (y + ya < 0)
			ya = 1;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			System.out.println(game.bar.getBounds().intersection(getBounds()));
			ya = -1;
			y = game.bar.getTopY() - DIAMETER + 10;
		}
		else if (brickCollision()){
			if (ya == 1) {
				ya = -1;
			}
			else if (ya == -1) {
				ya = 1;
			}
			Bricks.bricks.remove(brick);
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		return game.bar.getBounds().intersects(getBounds());
	}
	
	private boolean brickCollision() {
		for (int i = 0; i < Bricks.bricks.size(); i++) {
			if (Bricks.bricks.get(i).getBounds().intersects(getBounds())) {
				brick = i;
				return Bricks.bricks.get(i).getBounds().intersects(getBounds());
			}
		}
		return false;
	}

	public void paint(Graphics2D g) {
		g.fillOval(x, y, 10, 10);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
