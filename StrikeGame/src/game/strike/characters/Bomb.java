package game.strike.characters;

import game.strike.ImagesUtil;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Bomb extends JButton{

	private int x;
	private int y;
	private boolean visible;
	private int missileSpeed;
	private int boardWidth;
	private Image image;
	
	public Bomb(int x, int y) {
		
		super("",ImagesUtil.getBombImage());
		this.missileSpeed = 2;
		this.boardWidth = 750;
		this.x = x;
		this.y = y;
		this.setVisible(true);
		
		setSelected(false);
		setSize(20, 5);
		setLocation(this.x, this.y);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	
    	 Graphics2D g2d = (Graphics2D)g;
         g2d.drawImage(this.getImage(), this.getX(), this.getY(), this);
         Toolkit.getDefaultToolkit().sync();
    }
	
	public void move() {
		this.x += missileSpeed;
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
	public int getMissileSpeed() {
		return missileSpeed;
	}
	public void setMissileSpeed(int missileSpeed) {
		this.missileSpeed = missileSpeed;
	}
	public int getBoardWidth() {
		return boardWidth;
	}
	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
