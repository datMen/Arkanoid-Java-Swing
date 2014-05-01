import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Ball {
	private static final int DIAMETER = 10;
	int x = 205;
	int y = 400;
	int xa = 0;
	int ya = 0;
	int brick;
	int sxa = 1;
	private Pantalla game;
	static boolean ultraballmode = false;
	static int ultraball_color = 0;

	public Ball(Pantalla game) {
		this.game = game;
	}

	void move() {
		if (x + xa < 0)
			xa *= -1;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -xa;
		else if (y + ya < Text.menu_bar_height)
			ya = 1;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			ya = -1;
			y = game.bar.getTopY() - DIAMETER + 10;
		}
		else if (brickCollision()){
			if (ultraballmode) {
				Bricks.bricks.remove(brick);
			}
			else {
				int ball_position = (y+DIAMETER)-(Bricks.bricks.get(brick).getTopY()+10);
				if (ball_position == 1 || ball_position == 19) {
					if (ya == 1)
						ya = -1;
					else if (ya == -1)
						ya = 1;
				}
				else {
					if (xa > 0)
						xa *= -1;
					else if (xa < 0)
						xa *= -1;
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
		}
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
		if (game.bar.getBounds().intersects(getBounds())) {
			return true;
		}
		else if (game.bar.getLeftBounds().intersects(getBounds())) {
			if (xa > 0) {
				xa *= -1;
				if (x*xa < 0 && xa+1 != 0)
					xa++;
				if (game.speed < 10)
					game.speed += 1;
			}
			else if (xa < 0) {
				if (x*xa < 0 && xa-1 != 0)
					xa--;
				if (game.speed > 2)
					game.speed -= 1;
			}
			return true;
		}
		else if (game.bar.getRightBounds().intersects(getBounds())) {
			if (xa < 0) {
				xa *= -1;
				if (x*xa > 0 && xa-1 != 0)
					xa--;
				if (game.speed < 10)
					game.speed += 1;
			}
			else if (xa > 0) {
				if (x*xa > 0 && xa+1 != 0)
					xa++;
				if (game.speed > 2)
					game.speed -= 1;
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
				if (Bricks.bricks.get(i).hasReward()) {
					game.rewards.createReward(Bricks.bricks.get(i).reward_type, Bricks.bricks.get(i).x-3, Bricks.bricks.get(i).y);
				}
				return Bricks.bricks.get(i).getBounds().intersects(getBounds());
			}
		}
		return false;
	}

	public void paint(Graphics2D g) {
		g.setColor(Color.WHITE);
		if (ultraballmode) {
			if (ultraball_color == 0) {
				g.setColor(Color.RED);
				ultraball_color++;
			}
			else if (ultraball_color == 1) {
				g.setColor(Color.YELLOW);
				ultraball_color++;
			}
			else if (ultraball_color == 2) {
				g.setColor(Color.GREEN);
				ultraball_color = 0;
			}
		}
		g.fillOval(x, y, 10, 10);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
