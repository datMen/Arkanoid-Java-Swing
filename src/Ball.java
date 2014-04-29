import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Ball {
	private static final int DIAMETER = 10;
	int x = 133;
	int y = 325;
	int xa = 0;
	int ya = 0;
	int brick;
	int sxa = 1;
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
			ya = -1;
			xa = sxa;
			y = game.bar.getTopY() - DIAMETER + 10;
		}
		else if (brickCollision()){
			int ball_position = (y+DIAMETER)-(Bricks.bricks.get(brick).getTopY()+10);
			if (ball_position == 1 || ball_position == 19) {
				if (ya == 1) {
					ya = -1;
				}
				else if (ya == -1) {
					ya = 1;
				}
			}
			else {
				if (xa == 1) {
					xa = -1;
				}
				else if (xa == -1) {
					xa = 1;
				}
			}
			if (Bricks.bricks.get(brick).hits == 0) {
				Bricks.bricks.remove(brick);
				if (Bricks.bricks.size() == 0) {
					game.text.start_label.setText("Congrats, You Won!");
				}
			}
			else {
				game.brick.updateHits(brick);
			}
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		if (game.bar.getBounds().intersects(getBounds())) {
			sxa = xa;
			return true;
		}
		else if (game.bar.getLeftBounds().intersects(getBounds())) {
			if (xa == 1) {
				sxa = -1;
				if (game.speed < 10) {
					game.speed += 1;
				}
			}
			else if (xa == -1) {
				sxa = -1;
				if (game.speed > 1) {
					game.speed -= 1;
				}
			}
			return true;
		}
		else if (game.bar.getRightBounds().intersects(getBounds())) {
			if (xa == -1) {
				sxa = 1;
				if (game.speed < 10) {
					game.speed += 1;
				}
			}
			else if (xa == 1) {
				sxa = 1;
				if (game.speed > 1) {
					game.speed -= 1;
				}
			}
			return true;
		}
		else {
			return false;
		}
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
