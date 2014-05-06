import java.awt.Color;



public class Levels {
	public static int current_level = 1;
	public int row_num = 4;
	public int level_counter = 0;
	
	private Pantalla game;

	public Levels(Pantalla game) {
		this.game = game;
		createLevel();
	}
	
	public static void startNewLevel(Pantalla game) {
		Levels.current_level++;
		Pantalla.start_game = true;
		game.rewards.stopAllRewards();
		game.ball.xa = 0;
		game.ball.ya = 0;
		game.ball.x = Ball.default_x;
		game.ball.y = Ball.default_y;
		game.bar.x = Bar.default_x;
		game.levels.createLevel();
		game.text.start_label.setText("Level "+current_level+", Click to Start");
		game.speed = Pantalla.default_speed;
	}
	
	void createLevel() {
		for (int i = 0; i <= row_num; i++) {
			drawLevel(i);
			game.brick.brickRows.add(game.brick.bricks);
		}
		game.rewards.createAllRewards();
	}
	
	void drawLevel(int i) {
		int level = Levels.current_level;
		if (level == 1) {
			for (int j = 0; j <= 17; j++) {
				Bricks.Brick brick = new Bricks.Brick();
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
					game.brick.bricks.add(brick);
				}
			}
		}
		else if (level == 2) {
			for (int j = 0; j <= 17; j++) {
				Bricks.Brick brick = new Bricks.Brick();
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
					game.brick.bricks.add(brick);
				}
			}
			level_counter = 0;
		}
		else if (level == 3) {
			for (int j = 0; j <= 14; j++) {
				Bricks.Brick brick = new Bricks.Brick();
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
					game.brick.bricks.add(brick);
				}
			}
		}
		else if (level == 4) {
			for (int j = 0; j <= 17; j++) {
				Bricks.Brick brick = new Bricks.Brick();
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
				game.brick.bricks.add(brick);
			}
		}
		else if (level == 5) {
			for (int j = 0; j <= 10; j++) {
				Bricks.Brick brick = new Bricks.Brick();
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
				game.brick.bricks.add(brick);
			}
		}
		else {
			for (int j = 0; j <= 7; j++) {
				Bricks.Brick brick = new Bricks.Brick();
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
				game.brick.bricks.add(brick);
			}
		}
	}
}

