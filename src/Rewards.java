import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Rewards {
	private Pantalla game;
	public static int ultraball_time = 10;
	
	public class Reward {
		int x;
		int y;
		int width;
		int height;
		String type;
		boolean reward_brick_on = false;
		public boolean reward_on = false;
	}
	
	Reward current_reward = new Reward();
	public static ArrayList<Reward> current_rewards = new ArrayList<Reward>();

	public Rewards(Pantalla game) {
		this.game = game;
	}
	
	public void createReward(String type, int x, int y) {
		Reward reward = new Reward();
		reward.type = type;
		reward.x = x;
		reward.y = y;
		current_reward = reward;
		current_reward.reward_brick_on = true;
		if (type == "UltraBall") {
			reward.width = Bricks.Brick.width+6;
			reward.height = Bricks.Brick.height;
		}
		else if (type == "BigBall") {
			reward.width = Bricks.Brick.width+5;
			reward.height = Bricks.Brick.width+5;
		}
		current_rewards.add(current_reward);
	}
	
	public void startReward() {
		Pantalla.time_counter = 0;
		for (int i = 0; i < current_rewards.size(); i++) {
			if (current_rewards.get(i).type == "UltraBall") {
				current_rewards.get(i).reward_brick_on = false;
				current_rewards.get(i).reward_on = true;
				Ball.ultraballmode = true;
				ultraball_time = 10;
				break;
			}
			else if (current_rewards.get(i).type == "BigBall") {
				current_rewards.remove(current_rewards.get(i));
				Ball.DIAMETER += 10;
				break;
			}
		}
	}
	
	public void stopReward(String type) {
		if (type == "UltraBall") {
			for (int i = 0; i < current_rewards.size(); i++) {
				if (type == current_rewards.get(i).type) {
					Ball.ultraballmode = false;
					current_rewards.get(i).reward_on = false;
					game.text.rewards_label.setText("");
					current_rewards.remove(current_rewards.get(i));
				}
			}
		}
		else if (type == "BigBall") {
			Ball.DIAMETER = 10;
		}
	}
	
	public void paintBrickReward(Graphics2D g) {
		for (int i = 0; i < current_rewards.size(); i++) {
			if (current_rewards.get(i).type == "UltraBall" && current_rewards.get(i).reward_brick_on) {
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
				g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 10, 10);
			}
			else if (current_rewards.get(i).type == "BigBall" && current_rewards.get(i).reward_brick_on) {
				g.setColor(Color.WHITE);
				g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 100, 100);
			}
		}
	}
	
	public void paintReward() {
		for (int i = 0; i < current_rewards.size(); i++) {
			if (current_rewards.get(i).reward_on) {
				if (current_rewards.get(i).type == "UltraBall") {
					if (ultraball_time == 0) {
						stopReward("UltraBall");
					}
					if (ultraball_time > 0) {
						game.text.rewards_label.setText(""+ultraball_time);
						ultraball_time--;
					}
				}
			}
		}
	}
	
	public void paint(Graphics2D g) {
//		System.out.println(current_rewards);
		for (int i = 0; i < current_rewards.size(); i++) {
			if (current_rewards.get(i).reward_brick_on) {
				if (!barCollision(i)) {
					if (current_rewards.get(i).y == Pantalla.HEIGHT) {
						current_rewards.remove(current_rewards.get(i));
					}
					else {
						current_rewards.get(i).y++;
					}
				}
				else if (barCollision(i))
					startReward();
				paintBrickReward(g);
			}
		}
	}
	
	public Rectangle getBounds(int i) {
		return new Rectangle(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height);
	}
	
	private boolean barCollision(int i) {
		if (game.bar.getBounds().intersects(getBounds(i))) {
			return true;
		}
		else if (game.bar.getLeftBounds().intersects(getBounds(i))) {
			return true;
		}
		else if (game.bar.getRightBounds().intersects(getBounds(i))) {
			return true;
		}
		else {
			return false;
		}
	}
}
