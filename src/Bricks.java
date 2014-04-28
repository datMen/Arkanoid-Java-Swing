import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bricks {
	public static ArrayList<Brick> bricks = new ArrayList<Brick>();
	
	private Pantalla game;
	public Bricks(Pantalla game) {
		this.game = game;
		createBricks();
	}
	
	public class Brick {
		int x = 22;
		int y = 50;
		int width = 15;
		int height = 10;
		
		public int getTopY() {
			return y - height;
		}
		
		public Rectangle getBounds() {
			return new Rectangle(x, y, width, height);
		}
	}
	
	void createBricks() {
		for (int i = 0; i <= 12; i++) {
			Brick brick = new Brick();
			brick.x += i*20;
			bricks.add(brick);
		}
	}
	
	public void paint(Graphics2D g) {
		for (int i = 0; i < bricks.size(); i++) {
			g.setColor(Color.BLACK);
			g.fillRect(bricks.get(i).x, bricks.get(i).y, bricks.get(i).width, bricks.get(i).height);
		}
//		for (int i = 0; i < bricks.size(); i++) {
//			g.setColor(Color.RED);
//			g.fillRect(bricks.get(i).x, bricks.get(i).y+15, bricks.get(i).width, bricks.get(i).height);
//		}
	}
	

}
