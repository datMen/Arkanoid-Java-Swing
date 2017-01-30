import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


// Base from: http://www.edu4java.com/en/game/game1.html
@SuppressWarnings("serial")
public class Pantalla extends JPanel {
	public static final int WIDTH = 410;
	public static final int HEIGHT = 450;
	public static int default_speed = 6;
	public int speed = default_speed;
	public static boolean paused = false;
	public static boolean start_game = true;
	public static long time_counter = 0;
	public static int oldballxa = 0;
	public static int oldballya = 0;
	
	public Pantalla() {
		setLayout(null);
		setVisible(true);
		setBackground(Color.BLACK);
	}
	
	Ball ball = new Ball(this);
	Bar bar = new Bar(this);
	Bricks brick = new Bricks(this);
	Rewards rewards = new Rewards(this);
	ListenersHandler listeners = new ListenersHandler(this);
	Text text = new Text(this);
	Levels levels = new Levels(this);

	private void move() {
		ball.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		bar.paint(g2d);
		brick.paint(g2d);
		rewards.paint(g2d);
		text.paint(g2d);
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public static void startGame(Pantalla game) {
		if (start_game) {
			// Select a random direction for the ball at game start
			int xdireccion = (int) Math.floor(Math.random()*2+1);
			game.ball.ya = -1;
			if (xdireccion == 1) {
				game.ball.xa = 1;
			}
			else if (xdireccion == 2) {
				game.ball.xa = -1;
			}
			start_game = false;
			game.text.start_label.setText("");
			game.text.level_label.setText("LEVEL "+Levels.current_level);
		} 
		else {
			if (!paused) {
				// Pause the game
				oldballxa = game.ball.xa;
				oldballya = game.ball.ya;
				game.ball.ya = 0;
				game.ball.xa = 0;
				game.text.start_label.setText("Game Paused");
				game.text.start_label.setForeground(Color.RED);
				paused = true;
			}
			else {
				// Resume the game
				game.ball.xa = oldballxa;
				game.ball.ya = oldballya;
				game.text.start_label.setText("");
				game.text.start_label.setForeground(Color.GREEN);
				paused = false;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Arkanoid");
		Pantalla game = new Pantalla();
		frame.getContentPane().add(game);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable( false );
		
		while (true) {
			System.out.println(paused);
			if (!paused && !start_game) {
				game.move();
				game.repaint();
				time_counter++;
				if (time_counter%100 == 0) {
					// Move all the bricks down every 15 seconds
					if ((time_counter/100)%15 == 0) {
						for (int i = 0; i < game.brick.bricks.size(); i++) {
							game.brick.bricks.get(i).y += 10;
						}
					}
					game.rewards.paintReward();
				}
				// Manage game speed
				Thread.sleep(game.speed);
			}
		}
	}
}
