import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bricks {
	public static ArrayList<ArrayList<Brick>> brickRows = new ArrayList<ArrayList<Brick>>();
	public static ArrayList<Brick> bricks = new ArrayList<Brick>();
	private int row_num = 4;
	private int col_counter = 1;
	
	private Pantalla game;
	public Bricks(Pantalla game) {
		this.game = game;
	}
	
	public class Brick {
		int x = 15;
		int y = 50;
		int width = 15;
		int height = 10;
		Color color = Color.BLACK;
		int hits = 1;
		
		public int getTopY() {
			return y - height;
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, width, height);
		}
	}
	
	void bricksRowConfig(Brick brick, int i) {
		if (i == 0) {
			brick.color = Color.BLACK;
			brick.hits = 4;
		}
		else if (i == 1) {
			brick.color = Color.RED;
			brick.hits = 3;
		}
		else if (i == 2) {
			brick.color = Color.ORANGE;
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
	
	void createBricks() {
		for (int i = 0; i <= row_num; i++) {
			for (int j = 0; j <= 12; j++) {
				Brick brick = new Brick();
				brick.x += j*20;
				brick.y = i*15+50;
				bricksRowConfig(brick, i);
				if (j % 3 != 0) {
					bricks.add(brick);
				}
			}
			brickRows.add(bricks);
		}
	}
	
	void addBricks() {
		if (brickRows.size() > 0) {
			int x = bricks.get(bricks.size()-1).x+5;
			int border_margin = ((game.getWidth()-5)-bricks.get(bricks.size()-1).width-bricks.get(bricks.size()-1).x);
			if (border_margin > 20) {
				for (int i = 0; i < brickRows.size(); i++) {
					Brick brick = new Brick();
					brick.x += x;
					brick.y += i*15;
					bricksRowConfig(brick, i);
					System.out.println(col_counter%3);
					if (col_counter%3 != 0) {
						if (col_counter%3 == 1) {
							brick.x += 15;
						}
						brickRows.get(i).add(brick);
					}
				}
				col_counter++;
			}
		}
		else if (brickRows.size() == 0) {
			createBricks();
		}
	}
	
	void remBricks() {
		if (brickRows.size() > 0) {
			int border_margin = ((game.getWidth()-5)-bricks.get(bricks.size()-1).width-bricks.get(bricks.size()-1).x);
			if (border_margin < 20) {
				bricks.remove(bricks.size()-1);
				for (int i = 0; i < brickRows.size(); i++) {
					brickRows.get(i).remove(brickRows.get(i).size()-1);
				}
			}
		}
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
