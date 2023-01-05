import java.io.IOException;

public class level {
	public int niveau_selectione;
	public int col;
	public int ligne;
	Labyrinthe map;
	
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
			 
		}
		else if (this.niveau_selectione==2) {
			 map = new Labyrinthe("Laby2.txt");
			 col=35;
			 ligne=12;
		}
		else if (this.niveau_selectione==3) {
			map = new Labyrinthe("Laby3.txt");
			col=52;
			 ligne=18;
		}
		return map;
	}
	
	
	
}
