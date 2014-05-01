import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.SwingConstants;

public class Text {
	private Pantalla game;
	JLabel start_label = new JLabel("Click To Start", SwingConstants.CENTER);
	JLabel rewards_label = new JLabel("", SwingConstants.CENTER);
	Font lfont = new Font("courier", Font.PLAIN, 13);
	private JMenuBar mb;
    public static int menu_bar_height = 20;

	public Text(Pantalla game) {
		this.game = game;
		makeStartLabel();
		makeRewardsLabel();
        mb = new JMenuBar();
        mb.setBackground(Color.BLACK);
        mb.setBounds(0, 0, Pantalla.WIDTH, menu_bar_height);
        game.add(mb);
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
}
