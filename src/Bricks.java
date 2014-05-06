import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bricks {
	public ArrayList<ArrayList<Brick>> brickRows = new ArrayList<ArrayList<Brick>>();
	public ArrayList<Brick> bricks = new ArrayList<Brick>();
	
	private Pantalla game;

	public Bricks(Pantalla game) {
		this.game = game;
	}
	
	public static class Brick {
		public static int width = 15;
		public static int height = 10;
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
	
	@SuppressWarnings("static-access")
	public void paint(Graphics2D g) {
		for (int i = 0; i < game.brick.brickRows.size(); i++) {
			for (int j = 0; j < game.brick.brickRows.get(i).size(); j++) {
				g.setColor(game.brick.brickRows.get(i).get(j).color);
				g.fillRect(game.brick.brickRows.get(i).get(j).x, game.brick.brickRows.get(i).get(j).y, game.brick.brickRows.get(i).get(j).width, game.brick.brickRows.get(i).get(j).height);
			}
		}
	}
}
