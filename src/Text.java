import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Text {
	private Pantalla game;
	JLabel start_label = new JLabel("Click To Start", SwingConstants.CENTER);
	JLabel rewards_label = new JLabel("", SwingConstants.CENTER);
	JLabel lives_text_label = new JLabel("|| Lives: ");
	JLabel lives_label = new JLabel(""+Bar.lives);
	Font lfont = new Font("courier", Font.PLAIN, 13);
    public static int menu_bar_height = 25;

	public Text(Pantalla game) {
		this.game = game;
		makeStartLabel();
		makeRewardsLabel();
		makeLivesLabel();
	}
	
	void makeStartLabel() {
		start_label.setVisible(true);
		start_label.setBounds(0, 155, Pantalla.WIDTH, 100);
		start_label.setFont(lfont);
		start_label.setForeground(Color.GREEN);
		game.add(start_label);
	}
	
	void makeRewardsLabel() {
		rewards_label.setVisible(true);
		rewards_label.setBounds(0, -13, Pantalla.WIDTH, 100);
		rewards_label.setFont(lfont);
		rewards_label.setForeground(Color.CYAN);
		game.add(rewards_label);
	}
	
	void makeLivesLabel() {
		lives_text_label.setVisible(true); lives_label.setVisible(true); 
		lives_text_label.setBounds(Pantalla.WIDTH-103, -33, Pantalla.WIDTH, 100); lives_label.setBounds(Pantalla.WIDTH-22, -33, Pantalla.WIDTH, 100);
		lives_text_label.setFont(lfont); lives_label.setFont(lfont);
		lives_text_label.setForeground(Color.WHITE); lives_label.setForeground(Color.GREEN);
		game.add(lives_text_label);
		game.add(lives_label);
	}
	public void paint(Graphics2D g) {
	    g.setColor(Color.WHITE);
	    g.fillRect(0, 5, Pantalla.WIDTH, 2);
	    g.fillRect(0, menu_bar_height, Pantalla.WIDTH, 2);
//	    g.fillRect(0, 0, Pantalla.WIDTH, 2);
//	    g.fillRect(0, 0, Pantalla.WIDTH, 2);
	    makeLivesLabel();
	}
}
