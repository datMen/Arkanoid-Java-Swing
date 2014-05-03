
public class Levels {
	public static int current_level = 4;
	private Pantalla game;

	public Levels(Pantalla game) {
		this.game = game;
	}
	
	public static void startNewLevel(Pantalla game) {
		Pantalla.start_game = true;
		game.rewards.stopReward("UltraBall");
		game.rewards.stopReward("BigBall");
		game.ball.xa = 0;
		game.ball.ya = 0;
		game.ball.x = Ball.default_x;
		game.ball.y = Ball.default_y;
		game.bar.x = Bar.default_x;
		game.brick.createLevel();
		game.text.start_label.setText("Level "+current_level+", Click to Start");
		game.speed = Pantalla.default_speed;
	}
}

