package game.strike.characters;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ScoreBoard extends JButton{

	public ScoreBoard() {
		super("Your Score : 0");
		setEnabled(false);
		setSize(180, 35);
	}
}
