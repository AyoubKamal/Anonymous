
public class Vie {
	
	private static Tresor [] liste_tresories; 
	public int nombre_de_vie=2;
	Vie(Tresor [] liste_tresories){
		this.liste_tresories=liste_tresories;
	}
	
	public void update_vie(Labyrinthe laby) {
		int a,b;
		for (int i=0;i<liste_tresories.length;i++) {
			if (liste_tresories[i]!=null) {
				a=liste_tresories[i].getI();
				b=liste_tresories[i].getJ();
			if ((Math.abs(Game.player.getX()-liste_tresories[i].getX())<20 && Math.abs(Game.player.getY()-liste_tresories[i].getY())<20)) {
				if(nombre_de_vie<3) {
				nombre_de_vie+=1;
				liste_tresories[i]=null;
				}
			}
		}
		}
	System.out.println("nombre de vie restant = "+nombre_de_vie);
	}
}
