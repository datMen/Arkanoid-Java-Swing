import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bricks {
	public static ArrayList<ArrayList<Brick>> brickRows = new ArrayList<ArrayList<Brick>>();
	public static ArrayList<Brick> bricks = new ArrayList<Brick>();
	public int row_num = 4;
	public int reward_rand_ultraball  = 0;
	public int reward_rand_bigball  = 0;
	public int reward_rand_smallball  = 0;
	public int reward_rand_bigbar  = 0;
	public int reward_rand_smallbar  = 0;
	public int level_counter = 0;
	
	private Pantalla game;

	public Bricks(Pantalla game) {
		this.game = game;
		createLevel();
	}
	
	public class Brick {
		static final int width = 15;
		static final int height = 10;
		int x = 15;
		int y = 50;
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
	
	void createLevel() {
		Levels.current_level++;
		for (int i = 0; i <= row_num; i++) {
			drawLevel(i);
			brickRows.add(bricks);
			reward_rand_ultraball = (int) Math.floor(Math.random()*(bricks.size()-1)+1);
			reward_rand_bigball = (int) Math.floor(Math.random()*(bricks.size()-1)+1);
			reward_rand_smallball = (int) Math.floor(Math.random()*(bricks.size()-1)+1);
			reward_rand_bigbar = (int) Math.floor(Math.random()*(bricks.size()-1)+1);
			reward_rand_smallbar = (int) Math.floor(Math.random()*(bricks.size()-1)+1);
			bricks.get(reward_rand_ultraball).reward_type = "UltraBall";
			bricks.get(reward_rand_bigball).reward_type = "BigBall";
			bricks.get(reward_rand_smallball).reward_type = "SmallBall";
			bricks.get(reward_rand_bigbar).reward_type = "BigBar";
			bricks.get(reward_rand_smallbar).reward_type = "SmallBar";
		}
	}
	
	void updateHits(int brick) {
		int hits = bricks.get(brick).hits;
		switch (hits) {
			case 1: bricks.get(brick).color = Color.GREEN;
				break;
			case 2: bricks.get(brick).color = Color.YELLOW;
				break;
			case 3: bricks.get(brick).color = Color.LIGHT_GRAY;
				break;
			case 4: bricks.get(brick).color = Color.GRAY;
				break;
			case 5: bricks.get(brick).color = Color.DARK_GRAY;
				break;
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
	
	void drawLevel(int i) {
		int level = Levels.current_level;
		if (level == 1) {
			for (int j = 0; j <= 17; j++) {
				Brick brick = new Brick();
				brick.x += j*20;
				brick.y = i*20+80;
				switch (i) {
					case 0:
						brick.color = Color.LIGHT_GRAY;
						brick.hits = 2;
						break;
					case 2:
						brick.color = Color.YELLOW;
						brick.hits = 1;
						break;
					case 4:
						brick.color = Color.GREEN;
						brick.hits = 0;
						break;
				}
				if (j % 2 != 0 && i%2 == 0) {
					bricks.add(brick);
				}
			}
		}
		else if (level == 2) {
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
				switch (i) {
					case 0:
						brick.color = Color.GRAY;
						brick.hits = 3;
						break;
					case 1:
						brick.color = Color.LIGHT_GRAY;
						brick.hits = 2;
						break;
					case 2:
						brick.color = Color.YELLOW;
						brick.hits = 1;
						break;
					case 3:
						brick.color = Color.GREEN;
						brick.hits = 0;
						break;
					case 4:
						brick.color = Color.GREEN;
						brick.hits = 0;
						break;
				}
				if ((j/1)%2 != i%2) {
					bricks.add(brick);
				}
			}
			level_counter = 0;
		}
		else if (level == 3) {
			for (int j = 0; j <= 14; j++) {
				Brick brick = new Brick();
				brick.x += (j*25)+10;
				brick.y = i*30+50;
				switch (i) {
					case 0:
						brick.color = Color.DARK_GRAY;
						brick.hits = 4;
						break;
					case 1:
						brick.color = Color.GRAY;
						brick.hits = 3;
						break;
					case 2:
						brick.color = Color.LIGHT_GRAY;
						brick.hits = 2;
						break;
					case 3:
						brick.color = Color.YELLOW;
						brick.hits = 1;
						break;
					case 4:
						brick.color = Color.GREEN;
						brick.hits = 0;
						break;
				}
				if ((j)%3 != i%3) {
					bricks.add(brick);
				}
			}
		}
		else if (level == 4) {
			for (int j = 0; j <= 17; j++) {
				Brick brick = new Brick();
				brick.x += (j*20)+10;
				brick.y = i*30+50;
				switch (i) {
					case 0:
						brick.color = Color.DARK_GRAY;
						brick.hits = 4;
						break;
					case 1:
						brick.color = Color.GRAY;
						brick.hits = 3;
						break;
					case 2:
						brick.color = Color.LIGHT_GRAY;
						brick.hits = 2;
						break;
					case 3:
						brick.color = Color.YELLOW;
						brick.hits = 1;
						break;
					case 4:
						brick.color = Color.GREEN;
						brick.hits = 0;
						break;
				}
				bricks.add(brick);
			}
		}
		else if (level == 5) {
			for (int j = 0; j <= 10; j++) {
				Brick brick = new Brick();
				brick.x += (j*28)+((int) Math.floor(Math.random()*40+1)+10);
				brick.y = (i*15+50)+((int) Math.floor(Math.random()*10+1));
				switch (i) {
					case 0:
						brick.color = Color.DARK_GRAY;
						brick.hits = 4;
						break;
					case 1:
						brick.color = Color.GRAY;
						brick.hits = 3;
						break;
					case 2:
						brick.color = Color.LIGHT_GRAY;
						brick.hits = 2;
						break;
					case 3:
						brick.color = Color.YELLOW;
						brick.hits = 1;
						break;
					case 4:
						brick.color = Color.GREEN;
						brick.hits = 0;
						break;
				}
				bricks.add(brick);
			}
		}
		else {
			for (int j = 0; j <= 7; j++) {
				Brick brick = new Brick();
				brick.x += (j*40)+((int) Math.floor(Math.random()*40+1)+10);
				brick.y = (i*40+50)+((int) Math.floor(Math.random()*70+1));
				switch (i) {
					case 0:
						brick.color = Color.DARK_GRAY;
						brick.hits = 4;
						break;
					case 1:
						brick.color = Color.GRAY;
						brick.hits = 3;
						break;
					case 2:
						brick.color = Color.LIGHT_GRAY;
						brick.hits = 2;
						break;
					case 3:
						brick.color = Color.YELLOW;
						brick.hits = 1;
						break;
					case 4:
						brick.color = Color.GREEN;
						brick.hits = 0;
						break;
				}
				bricks.add(brick);
			}
		}
	}
}
