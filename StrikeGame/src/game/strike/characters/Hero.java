package game.strike.characters;

import game.strike.ImagesUtil;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Hero extends JButton {

	private int dy;
	private int x;
	private int y;
	private int craftSize;
	private List<Bomb> bombs;
	private Board board;

	public Hero() {

		super("", ImagesUtil.getHeroImage());

		x = 30;
		y = 180;
		craftSize = 20;
		bombs = new ArrayList<>();

		setSelected(false);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(ImagesUtil.getHeroImage().getImage(), this.getX(), this.getY(), 50, 50, this);
		Toolkit.getDefaultToolkit().sync();
	}

	// Savaşçının hareket etmesini sağlayan fonksiyon.
	public void move() {

		if (y > 1 && y < 340) {
			y += dy;
		} else {
			if (y < 1) {
				y += 20;
			} else if (y >= 340) {
				y -= 20;
			}
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public List<Bomb> getBombs() {
		return bombs;
	}

	public void setBombs(List<Bomb> bombs) {
		this.bombs = bombs;
	}

	// Bomba üretilir listeye ve ekrana yerleştirilir.
	public void fireBomb() {
		Bomb m = new Bomb(x + craftSize, y + craftSize / 2);
		this.bombs.add(m);
		this.board.add(m);
	}

	// Klavye tuşlarına basıldığında çağrılan fonksiyon
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		// Space tuşuna basılırsa yeni bir bomba üretilir.
		if (key == KeyEvent.VK_SPACE) {
			fireBomb();
		}

		// Yukarı tuşa basılırsa savaşçı yukarı çıkar.
		if (key == KeyEvent.VK_UP) {
			dy = -2;
		}

		// Savaşçı aşağı iner.
		if (key == KeyEvent.VK_DOWN) {
			dy = 2;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setBombsNewLocations() {

		List<Bomb> activeBombs = this.getBombs();
		for (int i = 0; i < activeBombs.size(); i++) {
			Bomb bomb = (Bomb) activeBombs.get(i);
			bomb.setLocation(bomb.getX(), bomb.getY());
		}
	}

	public void moveBombs() {

		List<Bomb> activeBombs = this.getBombs();
		for (int i = 0; i < activeBombs.size(); i++) {
			Bomb m = (Bomb) activeBombs.get(i);
			if (m.isVisible() == true) {
				m.move();
			} else {
				activeBombs.remove(m);
				remove(m);
			}
		}
	}

}
