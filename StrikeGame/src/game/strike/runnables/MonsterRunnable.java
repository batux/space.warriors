package game.strike.runnables;

import game.strike.characters.Board;
import game.strike.characters.Monster;
import game.strike.random.RandomUtil;

import java.util.List;
import javax.swing.SwingUtilities;

public class MonsterRunnable implements Runnable {

	private List<Monster> monsters;
	private int counter;
	private Board board;
	private Monster monster;
	private boolean flag;
	
	public MonsterRunnable() { this.counter = 0; this.flag = true; }
	
	@Override
	public void run() {
		
		while(this.flag) {
			synchronized (monsters) {
				try {
					if(monsters.size() > 100) { continue; }
					monster = new Monster();
					monsters.add(monster);
					
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							board.add(monster);
						}
					});
					counter++;
					System.out.println(counter);
					
					if(board.getTotalScore() < 500) {
						Thread.sleep(750);
					}
					else if(board.getTotalScore() >= 500 && this.board.getTotalScore() <= 1000) {
						Thread.sleep(500 + RandomUtil.getRandom().nextInt(1500));
					}
					else if(board.getTotalScore() > 1000 && this.board.getTotalScore() <= 1500) {
						Thread.sleep(250 + RandomUtil.getRandom().nextInt(1000));
					}
					else if(board.getTotalScore() > 1500 && this.board.getTotalScore() <= 2000) {
						Thread.sleep(125 + RandomUtil.getRandom().nextInt(550));
					}
					else if(board.getTotalScore() > 2000 && this.board.getTotalScore() <= 2500) {
						Thread.sleep(100 + RandomUtil.getRandom().nextInt(500));
					}
					else if(board.getTotalScore() > 2500 && this.board.getTotalScore() <= 3000) {
						Thread.sleep(80 + RandomUtil.getRandom().nextInt(400));
					}
					else if(board.getTotalScore() > 3000 && this.board.getTotalScore() <= 4000) {
						Thread.sleep(70 + RandomUtil.getRandom().nextInt(350));
					}
					Thread.yield();
				} 
				catch (InterruptedException e) { e.printStackTrace(); }
			}
		}
	}
	
	public void stop() {
		flag = false;
	}
	
	public void startupMonsters() {
		
		synchronized (monsters) {
			monster = new Monster();
			monsters.add(monster);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					board.add(monster);
				}
			});
			counter++;
			monster = new Monster();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					board.add(monster);
				}
			});
			counter++;
			monster = new Monster();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					board.add(monster);
				}
			});
			counter++;
		}
	}

	public List<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(List<Monster> monsters) {
		this.monsters = monsters;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
