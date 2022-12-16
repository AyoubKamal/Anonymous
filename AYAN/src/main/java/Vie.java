
public class Vie {
	
	private static Tresor [] liste_tresories; 
	public int nombre_de_vie=3;
	Vie(Tresor [] liste_tresories){
		this.liste_tresories=liste_tresories;
	}
	
	public void update_vie(Labyrinthe laby) {
		
		System.out.println("nombre de vie restant = "+nombre_de_vie);
		for (int i=0;i<liste_tresories.length;i++) {
			if (liste_tresories[i]!=null) {
			if ((Math.abs(Game.player.getX()-liste_tresories[i].getX())<20 && Math.abs(Game.player.getY()-liste_tresories[i].getY())<20)) {
				if(nombre_de_vie<4) {
				nombre_de_vie+=1;
				liste_tresories[i]=null;
				}
			}
		}
			
	}
	
	
	
		for (int i=0;i<Game.liste_monsters.length;i++) {
				if ((Math.abs(Game.player.getX()-Game.liste_monsters[i].getX())<10 && Math.abs(Game.player.getY()-Game.liste_monsters[i].getY())<10)) {
					if(nombre_de_vie>1) {
					nombre_de_vie-=1;
					Game.player.setX(0);
					Game.player.setY(Game.HEIGHT-49);
				}
					else { System.out.println("VOUS AVEZ PERDU !!!! Looooser ");
						Game.stop();
						Game.frame.dispose();
					}
		}
	}
}
}