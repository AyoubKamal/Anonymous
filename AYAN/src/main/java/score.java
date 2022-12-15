import java.awt.Color;
import java.awt.Graphics;

public class score  {
	int width =1500;
	int heigth =57;
	private static Tresor [] liste_tresories; 
	private int score =0;
	score(Tresor [] liste_tresories){
		this.liste_tresories=liste_tresories;
	}
	public void update_score(Labyrinthe laby) {
		int a,b;
		for (int i=0;i<liste_tresories.length;i++) {
			if (liste_tresories[i]!=null) {
				a=liste_tresories[i].getX();
				b=liste_tresories[i].getY();
			if (laby.plateau[a][b]==0) {
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