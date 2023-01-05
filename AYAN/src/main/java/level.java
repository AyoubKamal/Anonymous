import java.io.IOException;

public class level {
	public int niveau_selectione;
	public int col;
	public int ligne;
	public int OriginalTileSize;
	public int echelle;
	Labyrinthe map;
	Monster monster1;
	Monster monster2;
	Monster monster3;
	Monster monster4;
	Monster monster5;
	Tresor tresor1,tresor2,tresor3,tresor4,tresor5,tresor6,tresor7,tresor8;
	
	
	public level (int niveau) {
		
		this.niveau_selectione=niveau;
		try {
			this.generateLaby();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Labyrinthe generateLaby() throws IOException {
		if (this.niveau_selectione==1) {
			 map = new Labyrinthe("Laby.txt");
			 col=26;
			 ligne=12;
			 OriginalTileSize=16;
			 echelle=3;

			 
		}
		else if (this.niveau_selectione==2) {
			 map = new Labyrinthe("Laby2.txt");
			 col=35;
			 ligne=12;
			 OriginalTileSize=14;
			 echelle=3;
			 
		}
		else if (this.niveau_selectione==3) {
			map = new Labyrinthe("Laby3.txt");
			col=49;
			 ligne=18;
			 OriginalTileSize=10;
			 echelle=3;
			 Ayanman.speed=3;
		}
		return map;
	}
	
	
	public Monster [] generateMonsters(Labyrinthe map) throws IOException {
		//Monster [] liste_m = new Monster [4];
		if (this.niveau_selectione==1) {
			monster1=new Monster(map,1);
			monster2=new Monster(map,1);
			monster3=new Monster(map,2);
		}
		else if (this.niveau_selectione==2) {
			monster1=new Monster(map,1);
			monster2=new Monster(map,1);
			monster3=new Monster(map,2);
			monster4=new Monster (map,2);
		}
		else if (this.niveau_selectione==3) {
			monster1=new Monster(map,1);
			monster2=new Monster(map,3);
			monster3=new Monster(map,3);
			monster4=new Monster(map,2);
			monster5=new Monster(map,2);
		}
		Monster [] liste_mons = {monster1,monster2,monster3,monster4,monster5};
		return liste_mons;
	}
	
	public Tresor [] generateTresors1(Labyrinthe map) throws IOException{
			tresor1=new Tresor(map,1);
			tresor2=new Tresor(map,1);
			tresor3=new Tresor(map,1);
			tresor4=new Tresor(map,1);
		Tresor [] liste_mons = {tresor1,tresor2,tresor3,tresor4};
		return liste_mons;
	}
	
	
	public Tresor [] generateTresors2(Labyrinthe map) throws IOException{
		if (this.niveau_selectione==1) {

			tresor5=new Tresor(map,2);
		}
		else if (this.niveau_selectione==2) {

			tresor5=new Tresor(map,2);
			tresor6=new Tresor (map,2);
		}
		else if (this.niveau_selectione==3) {

			tresor5=new Tresor (map,2);
			tresor6=new Tresor (map,2);
			tresor7=new Tresor(map,2);
		}
		Tresor [] liste_mons = {tresor5,tresor6,tresor7};
		return liste_mons;
	}
	
	
	
	
	
	
	
	public Tresor generateTresors3(Labyrinthe map) throws IOException{
		tresor8=new Tresor (map,3);
		return tresor8;
	}
	
	
	
	
	
	
}
