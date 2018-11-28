package game.strike.characters;

import game.strike.ImagesUtil;
import game.strike.StrikeGame;
import game.strike.runnables.MonsterRunnable;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Hero hero;
	private ScoreBoard scoreBoard;
	private List<Monster> monsters;
	private MonsterRunnable monsterRunabble;
	private Thread monsterCreatorThread;
	private int collisionCounter;
	private long totalScore;
	private double totalTime;
	private StrikeGame game;

	public Board(StrikeGame game) {

		// Layout null setlenir. Böylece JPanel üzerinde herhangi bir noktaya eleman
		// yerleştrilebilir.
		setLayout(null);
		// Klavye olaylarını (event) dinleyen sınıfa ait nesne oluşturulur.
		addKeyListener(new KeyBoardAdapter());
		setFocusable(true);
		setDoubleBuffered(true);

		this.game = game;
		this.scoreBoard = new ScoreBoard();
		this.scoreBoard.setLocation(300, 2);
		add(this.scoreBoard);

		// Oyun alanı içinde aktif olan canavarların listesi tutulur.
		this.monsters = new ArrayList<Monster>();
		// Vurulan canavar sayısını tutar.
		this.setCollisionCounter(0);
		// Toplam puan degeri.
		this.totalScore = 0;
		this.totalTime = 0;

		// Oyun alanı içinde rastgele canavar üretmeyi sağlayan Thread oluşturulur.
		this.monsterRunabble = new MonsterRunnable();
		this.monsterRunabble.setMonsters(this.monsters);
		this.monsterRunabble.setBoard(this);
		this.monsterRunabble.startupMonsters();
		this.monsterCreatorThread = new Thread(monsterRunabble);
		this.monsterCreatorThread.start();

		this.hero = new Hero();
		this.hero.setBoard(this);
		add(this.hero);

		this.timer = new Timer(5, this);
		this.timer.start();

		validate();
		repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		this.scoreBoard.setLocation(300, 2);

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				String txt = "Your Score : " + totalScore;
				scoreBoard.setText(txt);
			}
		});

		this.hero.setLocation(this.hero.getX(), this.hero.getY());
		g.drawImage(ImagesUtil.getBackGroundImage().getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		// bombalar tahta üzerinde çizdirilir.
		this.hero.setBombsNewLocations();
		// Canavarların yeni konumları çizilir.
		this.setMonstersNewLocations();

		Toolkit.getDefaultToolkit().sync();
	}

	private void setMonstersNewLocations() {

		for (int i = 0; i < monsters.size(); i++) {
			Monster m = (Monster) monsters.get(i);
			m.setLocation(m.getX(), m.getY());
		}
	}

	// Vurulan canavarlar tespit edilir.
	public void checkCollisions() {

		Rectangle heroRectangle = this.hero.getBounds();
		for (int i = 0; i < monsters.size(); i++) {
			Monster mo = monsters.get(i);
			Rectangle moRect = mo.getBounds();

			if (heroRectangle.intersects(moRect) == true) {
				gameOver();
			}

			for (int j = 0; j < this.hero.getBombs().size(); j++) {
				Bomb mis = this.hero.getBombs().get(j);
				Rectangle misRect = mis.getBounds();
				// Canavar ve Bomba dikdörtgenlerinin kesişip kesişmediği kontrol edilir.
				if (moRect.intersects(misRect) == true) {
					this.monsters.remove(mo);
					this.hero.getBombs().remove(mis);
					remove(mis);
					remove(mo);
					this.setCollisionCounter(this.getCollisionCounter() + 1);
					// Canavarın hızına göre 10 puanla çarpılarak toplam puana eklenir.
					this.totalScore = this.totalScore + mo.getMonsterSpeed() * 10;
					System.out.println("TOTAL SCORE : " + this.totalScore);
					break;
				}
			}
		}

	}

	public void actionPerformed(ActionEvent e) {

		this.totalTime += 0.0005;
		System.out.println("TOTAL TIME : " + this.totalTime);
		if (this.totalTime > 1000 * 60 * 5) {
			win();
		}

		this.hero.moveBombs();
		this.moveMonsters();
		this.hero.move();
		checkCollisions();
		repaint();
	}

	// Canavarlar yeni yerleri belirlenir.
	private void moveMonsters() {

		for (int i = 0; i < this.monsters.size(); i++) {
			Monster monster = this.monsters.get(i);
			if (monster.isVisibl() == true) {
				monster.move();
			} else {
				this.monsters.remove(monster);
				remove(monster);
			}
		}
	}

	public void gameOver() {
		this.stopGame();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				int output = JOptionPane.showConfirmDialog(game, "GAME OVER !", "Strike Game", JOptionPane.OK_OPTION);
				if (output == JOptionPane.OK_OPTION) {
					game.setVisible(false);
					game.dispose();
				}
			}
		});

	}

	public void win() {
		this.stopGame();
		System.out.println("YOU WON !");
	}

	public void stopGame() {
		this.timer.stop();
		this.monsterRunabble.stop();
	}

	public int getCollisionCounter() {
		return collisionCounter;
	}

	public void setCollisionCounter(int collisionCounter) {
		this.collisionCounter = collisionCounter;
	}

	public long getTotalScore() {
		return this.totalScore;
	}

	private class KeyBoardAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			hero.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			hero.keyPressed(e);
		}
	}
}
