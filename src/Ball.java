import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Ball {
	public static int DIAMETER = 10;
	public static int default_x = 205;
	public static int default_y = 350;
	int x = default_x;
	int y = default_y;
	int xa = 0;
	int ya = 0;
	int brick;
	
	public static boolean ultraballmode = false;
	public static int ultraball_color = 0;
	
	private Pantalla game;

	public Ball(Pantalla game) {
		this.game = game;
	}

	void move() {
		// Change ball's direction when touches a window border
		if (x + xa <= 0)
			xa *= -1;
		else if (x + xa >= game.getWidth() - DIAMETER)
			xa = -xa;
		else if (y + ya <= Text.menu_bar_height)
			ya = 1;
		else if (y + ya >= game.getHeight() - DIAMETER) {
			if (game.bar.lives == 0)
				game.gameOver();
			else if (game.bar.lives > 0) {
				Bar.looseLive(game);
			}
		}
		// Collision with bar
		else if (collision()){
			ya = -1;
			y = game.bar.getTopY() - DIAMETER + 10;
		}
		// Collision with a brick
		else if (brickCollision()){
			if (ultraballmode) {
				game.brick.bricks.remove(brick);
			}
			else {
				int ball_top_position = (y+DIAMETER)-(game.brick.bricks.get(brick).getTopY()+DIAMETER);
				int ball_bot_position = (y+DIAMETER)-(game.brick.bricks.get(brick).getTopY()+Bricks.Brick.height);
				// ball_bot_position == 1 means that the bottom of the ball is touching the top of the brick
				// ball_top_position == 19 means that the top of the ball is touching the bottom of the brick
				//
				// @FIXME: These methods are not perfect and sometimes the ball destroy the brick
				// because ball_top_position is 1 even if the ball is at the side not below.
				if (ball_bot_position == 1 || ball_top_position == 19) {
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
				// Remove brick if needed, else, remove a hit 
				if (game.brick.bricks.get(brick).hits == 0) {
					game.brick.bricks.remove(brick);
				}
				else {
					game.brick.updateHits(brick);
				}
			}
			// If all bricks were removed start a new level
			if (game.brick.bricks.size() == 0) {
				Levels.startNewLevel(game);
			}
		}
		// Update ball's direction
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
		for (int i = 0; i < game.brick.bricks.size(); i++) {
			if (game.brick.bricks.get(i).getBounds().intersects(getBounds())) {
				brick = i;
				// If the brick has rewards inside, create the reward
				if (game.brick.bricks.get(i).hasReward()) {
					game.rewards.createReward(game.brick.bricks.get(i).reward_type, game.brick.bricks.get(i).x-3, game.brick.bricks.get(i).y);
				}
				return game.brick.bricks.get(i).getBounds().intersects(getBounds());
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
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	// Returns ball's bounds (as a rectangle)
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
