import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Gagner {
private Tresor tresor;
public static int ga=0;
Gagner(Tresor tresor){
	this.tresor=tresor;
}

public void update_gagner(Labyrinthe laby) {
	int a,b;
		if (tresor!=null) {
			a=tresor.getI();
			b=tresor.getJ();
		if ((Math.abs(Game.player.getX()-tresor.getX())<20 && Math.abs(Game.player.getY()-tresor.getY())<20)) {
			ga=1;
				Game.stop();
				
				System.out.println("BRAVO !!!!!!!!!!");
				tresor=null;
			}
	}
		
}
public static void drawgameover(Graphics2D g) {
	g.setFont(new Font("Serif", Font.BOLD, Game.tileSize*2));
	String gameOver = " GAME OVER ";
	g.drawString(gameOver, -12*Game.tileSize +(int)Game.WIDTH/2,(int)Game.HEIGHT/2);
	g.setPaint(Color.white);
}

public static void drawg(Graphics2D g) {
	
	g.setFont(new Font("Serif", Font.BOLD, Game.tileSize*2));
	g.setPaint(Color.white);
	String Felicitation = " CONGRATULATIONS ";
	g.drawString(Felicitation, -12*Game.tileSize +(int)Game.WIDTH/2,(int)Game.HEIGHT/2);

	}
}
