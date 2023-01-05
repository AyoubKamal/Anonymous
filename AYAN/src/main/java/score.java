import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class score  {

	private  Tresor [] liste_tresories; 
	private int score =0;
	score(Tresor [] liste_tresorie){
		this.liste_tresories=liste_tresorie;
	}
	public void update_score(Labyrinthe laby) {
		int a,b;
		for (int i=0;i<this.liste_tresories.length;i++) {
			if (this.liste_tresories[i]!=null) {
				a=this.liste_tresories[i].getI();
				b=this.liste_tresories[i].getJ();
			if (liste_tresories[i].ouvert==0 &&(Math.abs(Game.player.getX()-this.liste_tresories[i].getX())<20 && Math.abs(Game.player.getY()-this.liste_tresories[i].getY())<20)) {
				score+=50;
				liste_tresories[i].ouvert=1;
				//this.liste_tresories[i]=null;
			}
		}
	}
	
	System.out.println("score = "+score);
	}


	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	void drawScore(Graphics2D g ) {
		g.setFont(new Font("Arial", Font.BOLD, Game.tileSize));
		g.setPaint(Color.white);
		//g.setColor((new Color(150,170,3)));
		String scorePlayer = " Score = "+String.valueOf(score);
		g.drawString(scorePlayer, 100, -(int)Game.tileSize/4+Game.tileSize*(Game.getligne()));
		//
		
	}
}