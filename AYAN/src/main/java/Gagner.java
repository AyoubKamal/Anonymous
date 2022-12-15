
public class Gagner {
private Tresor tresor;

Gagner(Tresor tresor){
	this.tresor=tresor;
}

public void update_gagner(Labyrinthe laby) {
	int a,b;
		if (tresor!=null) {
			a=tresor.getI();
			b=tresor.getJ();
		if ((Math.abs(Game.player.getX()-tresor.getX())<20 && Math.abs(Game.player.getY()-tresor.getY())<20)) {
			Game.stop();
			System.out.println("BRAVO !!!!!!!!!!!!!!!!!!!!!!!!!");
			tresor=null;
			}
	}
}
}
