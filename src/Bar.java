import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bar {
	public static final int Y = 40;
	public static int WIDTH = 30;
	public static int HEIGHT = 10;
	public static int SIDE_WIDTH = 20;
	public static Color bar_main_color = Color.WHITE;
	public static Color bar_side_color = Color.GRAY;
	public int lives = 3;
	public static int default_x = 197;
	public int x = default_x;
	public int move_speed = 10;

	private Pantalla game;

	public Bar(Pantalla game) {
		this.game = game;
	}


	void move(int direction) {
		int width_margin = 5;
		if (direction == ListenersHandler.LEFT) {
			if (x > width_margin) {
				x -= move_speed;
			}
		}
		else if (direction == ListenersHandler.RIGHT) {
			if (x < game.getWidth()-(WIDTH+width_margin)) {
				x += move_speed;
			}
		}

	}
	
	public static void looseLive(Pantalla game) {
		game.bar.lives--;
		Pantalla.start_game = true;
		game.ball.xa = 0;
		game.ball.ya = 0;
		game.ball.x = Ball.default_x;
		game.ball.y = Ball.default_y;
		game.bar.x = Bar.default_x;
		game.text.lives_label.setText(""+game.bar.lives);
		game.text.start_label.setText("Lives: "+game.bar.lives);
		game.speed = Pantalla.default_speed;
		game.rewards.stopAllRewards();
	}

	public void paint(Graphics2D g) {
		g.setColor(bar_side_color);
		g.fillRoundRect(x-(SIDE_WIDTH-3), game.getHeight()-Y, SIDE_WIDTH, HEIGHT, 10, 10);
		g.setColor(bar_side_color);
		g.fillRoundRect(x+(WIDTH-3), game.getHeight()-Y, SIDE_WIDTH, HEIGHT, 10, 10);
		g.setColor(bar_main_color);
		g.fillRect(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}
	
	public int getTopY() {
		return game.getHeight() - Y - HEIGHT;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, game.getHeight()-Y, WIDTH, HEIGHT);
	}
	
	public Rectangle getLeftBounds() {
		return new Rectangle(x-20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
	
	public Rectangle getRightBounds() {
		return new Rectangle(x+20, game.getHeight()-Y, WIDTH-3, HEIGHT);
	}
}
