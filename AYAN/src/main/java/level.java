import java.io.IOException;

public class level {
	public int niveau_selectione;
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
		}
		else if (this.niveau_selectione==2) {
			 map = new Labyrinthe("Laby2.txt");
		}
		else if (this.niveau_selectione==3) {
			map = new Labyrinthe("Laby3.txt");
		}
		return map;
	}
	
	
	
}
