import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bricks {
	public static ArrayList<ArrayList<Brick>> brickRows = new ArrayList<ArrayList<Brick>>();
	public static ArrayList<Brick> bricks = new ArrayList<Brick>();
	private int row_num = 4;
	public int reward_rand_ultraball  = 0;
	public int reward_rand_bigball  = 0;
	public int level_counter = 0;
	
	private Pantalla game;
	public Bricks(Pantalla game) {
		this.game = game;
		createLevel();
	}
	
	public class Brick {
		int x = 15;
		int y = 50;
		static final int width = 15;
		static final int height = 10;
		Color color = Color.BLACK;
		int hits = 1;
		String reward_type = "";
		int reward_num = (int) Math.floor(Math.random()*hits+1);
		
		public boolean hasReward() {
			if (reward_type != "" && reward_num == hits) {
				return true;
			}
			return false;
		}
		
		public int getTopY() {
			return y - height;
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, width, height);
		}
	}
	
	void bricksRowConfig(Brick brick, int i) {
		if (i == 0) {
			brick.color = Color.DARK_GRAY;
			brick.hits = 4;
		}
		else if (i == 1) {
			brick.color = Color.GRAY;
			brick.hits = 3;
		}
		else if (i == 2) {
			brick.color = Color.LIGHT_GRAY;
			brick.hits = 2;
		}
		else if (i == 3) {
			brick.color = Color.YELLOW;
			brick.hits = 1;
		}
		else if (i == 4) {
			brick.color = Color.GREEN;
			brick.hits = 0;
		}
	}
	
	void createLevel() {
		Levels.current_level++;
		for (int i = 0; i <= row_num; i++) {
			drawLevel(i);
			brickRows.add(bricks);
			reward_rand_ultraball = (int) Math.floor(Math.random()*(bricks.size()-1)+1);
			reward_rand_bigball = (int) Math.floor(Math.random()*(bricks.size()-1)+1);
			bricks.get(reward_rand_ultraball).reward_type = "UltraBall";
			bricks.get(reward_rand_bigball).reward_type = "BigBall";
		}
	}
	
	void drawLevel(int i) {
		int level = Levels.current_level;
		if (level == 1) {
			for (int j = 0; j <= 17; j++) {
				Brick brick = new Brick();
				brick.x += j*20;
				brick.y = i*20+80;
				bricksRowConfig(brick, i);
				if (j % 2 != 0 && i%2 == 0) {
					bricks.add(brick);
				}
			}
		}
		else if (level == 2) {
			for (int j = 0; j <= 14; j++) {
				Brick brick = new Brick();
				brick.x += (j*25)+10;
				brick.y = i*30+50;
				bricksRowConfig(brick, i);
				if ((j)%3 != i%3) {
					bricks.add(brick);
				}
			}
		}
		else if (level == 3) {
			for (int j = 0; j <= 17; j++) {
				Brick brick = new Brick();
				if (level_counter == 0) {
					brick.x += 2*21;
					level_counter = 1;
				}
				else {
					brick.x += j*20;
				}
				brick.y = i*25+50;
				bricksRowConfig(brick, i);
				if ((j/1)%2 != i%2) {
					bricks.add(brick);
				}
			}
			level_counter = 0;
		}
		else if (level == 4) {
			for (int j = 0; j <= 17; j++) {
				Brick brick = new Brick();
				brick.x += (j*20)+10;
				brick.y = i*30+50;
				bricksRowConfig(brick, i);
				bricks.add(brick);
			}
		}
		else {
			for (int j = 0; j <= 10; j++) {
				Brick brick = new Brick();
				brick.x += (j*28)+((int) Math.floor(Math.random()*40+1)+10);
				brick.y = (i*15+50)+((int) Math.floor(Math.random()*10+1));
				bricksRowConfig(brick, i);
				bricks.add(brick);
			}
		}
	}
	
	void updateHits(int brick) {
		int hits = bricks.get(brick).hits;
		if (hits == 1) {
			bricks.get(brick).color = Color.GREEN;
		}
		else if (hits == 2) {
			bricks.get(brick).color = Color.YELLOW;
		}
		else if (hits == 3) {
			bricks.get(brick).color = Color.LIGHT_GRAY;
		}
		else if (hits == 4) {
			bricks.get(brick).color = Color.GRAY;
		}
		else if (hits == 5) {
			bricks.get(brick).color = Color.DARK_GRAY;
		}
		bricks.get(brick).hits -= 1;
	}
	
	public void paint(Graphics2D g) {
		for (int i = 0; i < brickRows.size(); i++) {
			for (int j = 0; j < brickRows.get(i).size(); j++) {
				g.setColor(brickRows.get(i).get(j).color);
				g.fillRect(brickRows.get(i).get(j).x, brickRows.get(i).get(j).y, brickRows.get(i).get(j).width, brickRows.get(i).get(j).height);
			}
		}
	}
}
