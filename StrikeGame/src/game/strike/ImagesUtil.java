package game.strike;

import javax.swing.ImageIcon;

public class ImagesUtil {

	private static ImageIcon heroImage;
	private static ImageIcon bombImage;
	private static ImageIcon monsterImage;
	private static ImageIcon backGroundImage;
	
	public static ImageIcon getHeroImage() {
		return heroImage;
	}
	public static void setHeroImage(ImageIcon heroImage) {
		ImagesUtil.heroImage = heroImage;
	}
	public static ImageIcon getBombImage() {
		return bombImage;
	}
	public static void setBombImage(ImageIcon bombImage) {
		ImagesUtil.bombImage = bombImage;
	}
	public static ImageIcon getMonsterImage() {
		return monsterImage;
	}
	public static void setMonsterImage(ImageIcon monsterImage) {
		ImagesUtil.monsterImage = monsterImage;
	}
	public static ImageIcon getBackGroundImage() {
		return backGroundImage;
	}
	public static void setBackGroundImage(ImageIcon backGroundImage) {
		ImagesUtil.backGroundImage = backGroundImage;
	}
	
}
