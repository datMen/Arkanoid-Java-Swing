import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class Rewards {
	public static int ultraball_time = 10;
	
	private Pantalla game;
	
	public class Reward {
		int x;
		int y;
		int width;
		int height;
		String type;
		boolean reward_brick_on = false;
		boolean reward_on = false;
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
		else if (type == "SmallBall") {
			reward.width = Bricks.Brick.width-8;
			reward.height = Bricks.Brick.width-8;
		}
		else if (type == "BigBar" || type == "SmallBar") {
			reward.width = Bricks.Brick.width+5;
			reward.height = Bricks.Brick.height-2;
		}
		current_rewards.add(current_reward);
	}
	
	public void startReward(String type) {
		Pantalla.time_counter = 0;
		for (int i = 0; i < current_rewards.size(); i++) {
			if (current_rewards.get(i).type == "UltraBall" && type == current_rewards.get(i).type) {
				current_rewards.get(i).reward_brick_on = false;
				current_rewards.get(i).reward_on = true;
				Ball.ultraballmode = true;
				ultraball_time = 10;
			}
			else if (current_rewards.get(i).type == "BigBall" && type == current_rewards.get(i).type) {
				current_rewards.remove(current_rewards.get(i));
				if (Ball.DIAMETER == 5) {
					Ball.DIAMETER += 5;
				}
				else {
					Ball.DIAMETER += 10;
				}
			}
			else if (current_rewards.get(i).type == "SmallBall" && type == current_rewards.get(i).type) {
				current_rewards.remove(current_rewards.get(i));
				if (Ball.DIAMETER <= 10 && Ball.DIAMETER > 5) {
					Ball.DIAMETER -= 5;
				}
				else if (Ball.DIAMETER > 10) {
					Ball.DIAMETER -= 10;
				}
			}
			else if (current_rewards.get(i).type == "BigBar" && type == current_rewards.get(i).type) {
				current_rewards.remove(current_rewards.get(i));
				if (Bar.WIDTH == 15) {
					Bar.WIDTH += 15;
					Bar.SIDE_WIDTH += 5;
					Bar.bar_main_color = Color.WHITE;
					Bar.bar_side_color = Color.GRAY;
				}
				else {
					Bar.WIDTH += 15;
					Bar.bar_main_color = Color.CYAN;
					Bar.bar_side_color = Color.BLUE;
				}
			}
			else if (current_rewards.get(i).type == "SmallBar" && type == current_rewards.get(i).type) {
				current_rewards.remove(current_rewards.get(i));
				if (Bar.WIDTH == 30) {
					Bar.WIDTH -= 15;
					Bar.SIDE_WIDTH -= 5;
					Bar.bar_main_color = Color.CYAN;
					Bar.bar_side_color = Color.BLUE;
				}
				else if (Bar.WIDTH == 45) {
					Bar.WIDTH -= 15;
					Bar.bar_main_color = Color.WHITE;
					Bar.bar_side_color = Color.GRAY;
				}
				else if (Bar.WIDTH > 15) {
					Bar.WIDTH -= 15;
					Bar.bar_main_color = Color.CYAN;
					Bar.bar_side_color = Color.BLUE;
				}
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
		else if (type == "BigBall" || type == "SmallBall") {
			Ball.DIAMETER = 10;
		}
		else if (type == "BigBar" || type == "SmallBar") {
			Bar.WIDTH = 30;
			Bar.SIDE_WIDTH = 20;
			Bar.bar_main_color = Color.WHITE;
			Bar.bar_side_color = Color.GRAY;
		}
	}
	
	public void stopAllRewards() {
		stopReward("UltraBall");
		stopReward("BigBall");
		stopReward("SmallBall");
		stopReward("BigBar");
		stopReward("SmallBar");
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
			else if (current_rewards.get(i).type == "SmallBall" && current_rewards.get(i).reward_brick_on) {
				g.setColor(Color.WHITE);
				g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 100, 100);
			}
			else if (current_rewards.get(i).type == "BigBar" && current_rewards.get(i).reward_brick_on) {
				g.setColor(Color.CYAN);
				g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 10, 10);
			    g.setFont(new Font( "SansSerif", Font.BOLD, 10 ));
			    g.setColor(Color.WHITE);
			    g.drawString("<        >", current_rewards.get(i).x+-7, current_rewards.get(i).y+7);
			}
			else if (current_rewards.get(i).type == "SmallBar" && current_rewards.get(i).reward_brick_on) {
				g.setColor(Color.CYAN);
				g.fillRoundRect(current_rewards.get(i).x, current_rewards.get(i).y, current_rewards.get(i).width, current_rewards.get(i).height, 10, 10);
			    g.setFont(new Font( "SansSerif", Font.BOLD, 10 ));
			    g.setColor(Color.WHITE);
			    g.drawString(">        <", current_rewards.get(i).x+-7, current_rewards.get(i).y+7);
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
					paintBrickReward(g);
					if (current_rewards.get(i).y == Pantalla.HEIGHT) {
						current_rewards.remove(current_rewards.get(i));
					}
					else {
						current_rewards.get(i).y++;
					}
				}
				else if (barCollision(i))
					startReward(current_rewards.get(i).type);
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
