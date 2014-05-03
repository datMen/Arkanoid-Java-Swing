import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Rewards {
	private Pantalla game;
	boolean reward_brick_on = false;
	public static boolean reward_on = false;
	public static int ultraball_time = 10;
	
	public class Reward {
		int x;
		int y;
		String type;
	}
	
	Reward current_reward = new Reward();

	public Rewards(Pantalla game) {
		this.game = game;
	}
	
	public void createReward(String type, int x, int y) {
		Reward reward = new Reward();
		reward.type = type;
		reward.x = x;
		reward.y = y;
		current_reward = reward;
		reward_brick_on = true;
	}
	
	public void startReward() {
		Pantalla.time_counter = 0;
		reward_brick_on = false;
		if (current_reward.type == "UltraBall") {
			Ball.ultraballmode = true;
			reward_on = true;
			ultraball_time = 10;
		}
		else if (current_reward.type == "BigBall") {
			Ball.DIAMETER += 10;
		}
	}
	
	public void stopReward(String type) {
		if (type == "UltraBall") {
			Ball.ultraballmode = false;
			reward_on = false;
			game.text.rewards_label.setText("");
		}
		else if (type == "BigBall") {
			Ball.DIAMETER = 10;
		}
		current_reward.type = "";
	}
	
	public void paintBrickReward(Graphics2D g) {
		if (current_reward.type == "UltraBall") {
			if (Ball.ultraball_color == 0) {
				g.setColor(Color.RED);
				Ball.ultraball_color++;
			}
			else if (Ball.ultraball_color == 1) {
				g.setColor(Color.YELLOW);
				Ball.ultraball_color++;
			}
			else if (Ball.ultraball_color == 2) {
				g.setColor(Color.GREEN);
				Ball.ultraball_color = 0;
			}
			g.fillRoundRect(current_reward.x, current_reward.y, Bricks.Brick.width+6, Bricks.Brick.height, 10, 10);
		}
		else if (current_reward.type == "BigBall") {
			g.setColor(Color.WHITE);
			g.fillRoundRect(current_reward.x, current_reward.y, Bricks.Brick.width+5, Bricks.Brick.width+5, 100, 100);
		}
	}
	
	public void paintReward() {
		if (reward_on) {
			if (current_reward.type == "UltraBall") {
				if (ultraball_time == 0) {
					stopReward("UltraBall");
				}
				if (ultraball_time > 0) {
					ultraball_time--;
					game.text.rewards_label.setText(""+ultraball_time);
				}
			}
		}
	}
	
	public void paint(Graphics2D g) {
		if (reward_brick_on) {
			if (!barCollision())
				current_reward.y++;
			else if (barCollision())
				startReward();
			if (current_reward.y == Pantalla.HEIGHT) {
				reward_brick_on = false;
			}
			paintBrickReward(g);
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(current_reward.x, current_reward.y, Bricks.Brick.width+6, Bricks.Brick.height);
	}
	
	private boolean barCollision() {
		if (game.bar.getBounds().intersects(getBounds())) {
			return true;
		}
		else if (game.bar.getLeftBounds().intersects(getBounds())) {
			return true;
		}
		else if (game.bar.getRightBounds().intersects(getBounds())) {
			return true;
		}
		else {
			return false;
		}
	}
}
