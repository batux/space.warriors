package game.strike;

import game.strike.characters.Board;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class StrikeGame extends JFrame {
	
	private Board board;
	
	public StrikeGame() {

		createImages();
		// Oyun tahtası oluşturulur.
		this.board = new Board(this);
		getContentPane().add(this.board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 420);
        setLocationRelativeTo(null);
        setTitle(" *** Strike Game *** ");
        setResizable(false);
        setVisible(true);
    }
	
	// Resimler oluşturulur. Fotoğraflar sürekli kullanılacağı için Util sınıfı olarak tasarlandı.
	private void createImages() {
		
		ImagesUtil.setBombImage(new ImageIcon(this.getClass().getResource("/missile.png")));
		ImagesUtil.setHeroImage(new ImageIcon(this.getClass().getResource("/Firing2__000.png")));
		ImagesUtil.setMonsterImage(new ImageIcon(this.getClass().getResource("/icon_monster.png")));
		ImagesUtil.setBackGroundImage(new ImageIcon(this.getClass().getResource("/background.jpg")));
	}

    public static void main(String[] args) {
    	
    	// invokeLater ile Swing GUI'nin etkilenmeden güncellenmesi sağlanır.
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new StrikeGame();
			}
		});
    	
    }
}
