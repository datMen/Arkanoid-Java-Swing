import javax.swing.JLabel;

public class Text {
	private Pantalla game;
	JLabel start_label = new JLabel("Click To Start");

	public Text(Pantalla game) {
		this.game = game;
		start_label.setLocation(10, 10);
		game.add(start_label);
	}
}
