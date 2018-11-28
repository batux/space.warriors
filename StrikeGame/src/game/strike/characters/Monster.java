package game.strike.characters;

import game.strike.ImagesUtil;
import game.strike.random.RandomUtil;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Monster extends JButton{

	private int x;
	private int y;
	private int monsterSpeed;
	private int boardWidth;
	private boolean visibl;
	
	public Monster() {
		
		super("",ImagesUtil.getMonsterImage());
		this.monsterSpeed = 1 + RandomUtil.getRandom().nextInt(2);
		this.boardWidth = 750;
		this.x = 500 + RandomUtil.getRandom().nextInt(251);
		this.y = 100 + RandomUtil.getRandom().nextInt(190);
		this.setVisibl(true);
		
		setSelected(false);
		setSize(50, 50);
		setLocation(this.x, this.y);
	}
	
	 @Override
	 protected void paintComponent(Graphics g) {
		
		 super.paintComponent(g);
		 Graphics2D g2d = (Graphics2D)g;
	     g2d.drawImage(ImagesUtil.getMonsterImage().getImage(), this.getX(), this.getY(), 50, 50, this);
	     Toolkit.getDefaultToolkit().sync();
	 }
	
	public void move() {
		this.x -= monsterSpeed;
		if(this.boardWidth < this.x) {
			this.setVisible(false);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMonsterSpeed() {
		return monsterSpeed;
	}

	public void setMonsterSpeed(int monsterSpeed) {
		this.monsterSpeed = monsterSpeed;
	}

	public int getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}

	public boolean isVisibl() {
		return visibl;
	}

	public void setVisibl(boolean visibl) {
		this.visibl = visibl;
	}
	
}
