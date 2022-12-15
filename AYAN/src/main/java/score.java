import java.awt.Color;
import java.awt.Graphics;

public class score  {

	private static Tresor [] liste_tresories; 
	private int score =0;
	score(Tresor [] liste_tresories){
		this.liste_tresories=liste_tresories;
	}
	public void update_score(Labyrinthe laby) {
		int a,b;
		for (int i=0;i<liste_tresories.length;i++) {
			if (liste_tresories[i]!=null) {
				a=liste_tresories[i].getI();
				b=liste_tresories[i].getJ();
			if ((Math.abs(Game.player.getX()-liste_tresories[i].getX())<20 && Math.abs(Game.player.getY()-liste_tresories[i].getY())<20)) {
				score+=50;
				liste_tresories[i]=null;
			}
		}
	}
	
	System.out.println("score = "+score);
	}
	public void render (Graphics g) {
		String score_str =String.valueOf(score);
		g.drawString(score_str, 750,28);
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}