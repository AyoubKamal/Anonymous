public class Vie {
	
	private static Tresor [] liste_tresories; 

	public static Piege [] liste;
	public int nombre_de_vie=3;
	Vie(Tresor [] liste_tresories, Piege [] liste){
		this.liste_tresories=liste_tresories;
		this.liste=liste;
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

		
		
		
		for (int i=0;i<liste.length;i++) {
			if (liste[i]!=null) {
			if ((Math.abs(Game.player.getX()-liste[i].getX())<20 && Math.abs(Game.player.getY()-liste[i].getY())<20)) {
				
				nombre_de_vie-=1;
				liste[i]=null;
			}
		}
			
	}

	
		for (int i=0;i<Game.liste_monsters.length;i++) {

			if (Game.liste_monsters[i]!=null) {
				if ((Math.abs(Game.player.getX()-Game.liste_monsters[i].getX())<10 && Math.abs(Game.player.getY()-Game.liste_monsters[i].getY())<10)) {
					if(nombre_de_vie>1) {
					nombre_de_vie-=1;
					changerPlace(laby);
				}
					
					else { System.out.println("VOUS AVEZ PERDU !!!! ");
						Game.stop();
						Game.frame.dispose();
					}
				
				}

			}
					}
				}
	
	public int distance(int a, int b , int c , int d) {
		
		return (int)Math.sqrt((c-a)*(c-a)+(d-b)*(d-b));
	
	}
	



	public void changerPlace(Labyrinthe laby) {
		int a,b;
		int pos=1;
		int fait =0;
			for (int i=0;i<Game.Nb_row;i++) {
				for (int k=0;k<Game.Nb_col;k++) {
					
					for (int m=0;m<Game.liste_monsters.length;m++) {
						if (Game.liste_monsters[m]!=null) {
							if (laby.plateau[i][k]!=0 || distance(k*Game.tileSize,i*Game.tileSize,Game.liste_monsters[m].getX(),Game.liste_monsters[m].getY())<150) {
								pos=0;
								System.out.println("Hello world");
								break;
						}
					}
				}
						if (pos==1) {
							Game.player.setX(k*Game.tileSize);
							Game.player.setY(i*Game.tileSize);
							System.out.println("k = "+k+" i = "+i);
							fait=1;
						}
						
						else {pos=1;}
						if (fait==1) {
							break;
						}
			}
				if (fait==1) {
					break;
				}
				
		}
	
}
}