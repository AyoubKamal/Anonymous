import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Labyrinthe extends JPanel {
	
	private static String nomFichier; 
	public static int [][] plateau;
	public static int [] dim;
	BufferedImage[] images =new BufferedImage [15];
	BufferedImage[] images2 =new BufferedImage [15];
	public boolean [] colision =new boolean [15];
	


	Labyrinthe(String nomFichier) throws IOException{
		this.nomFichier=nomFichier;
		dim=dimensionFichier();
		LireFichier();
		images[1]=ImageIO.read(new File("images/tiles/wall.png"));
		images[0]=ImageIO.read(new File("images/tiles/grass.png"));

		
		images2[1]=ImageIO.read(new File("images/tiles/wall.png"));
		images2[0]=ImageIO.read(new File("images/tiles/grass2.png"));
		colision[0]=true;
		colision[1]=false;
		colision[2]=true;
	}
	
	
public static int [] dimensionFichier() throws IOException  {
	int [] dim = new int [2];
	File f = new File(nomFichier);
	BufferedReader fR = null;
	fR = new BufferedReader(new FileReader(f));
	BufferedReader fR2 = null;
	fR2 = new BufferedReader(new FileReader(f));

	String chaine ="";
	String chaine2="";
	int nb_ligne=0;
	int nb_colonne=0;
	
		chaine2=fR2.readLine();
	nb_colonne=chaine2.length();
    do {
   
			chaine = fR.readLine();
		
			// TODO Auto-generated catch block
		
    	if (chaine != null) nb_ligne++;
    } while (chaine != null);
    
    
   
		fR.close();
		// TODO Auto-generated catch block
    dim[0]=nb_ligne;
    dim[1]=nb_colonne;
    return dim;
}


public static int[][] getPlateau() {
	return plateau;
}


public static void setPlateau(int[][] plateau) {
	Labyrinthe.plateau = plateau;
}


public static void LireFichier() throws IOException {
	plateau=new int [dim[0]][dim[1]];
	File f = new File(nomFichier);
	BufferedReader fR = new BufferedReader(new FileReader(f));
	String chaine ="";
	String [] element;
    for(int i=0;i<dim[0];i++) {
        	chaine = fR.readLine();
        	element=chaine.split("");
        		for (int j=0;j<dim[1];j++) {
        			plateau[i][j]=Integer.parseInt(element[j]);
        			}
        } 
        fR.close();
}


	public void dissiner2(String nomFichierImage,Graphics2D g,int x,int y) throws IOException {
		Image img = ImageIO.read(new File(nomFichierImage));
		g.drawImage(img, x, y,46,46,null);
		
	
}

	public int [] getPostitionLaby(int i , int j) throws IOException {
		int x = Game.WIDTH/dim[1];
		int y = Game.HEIGHT/dim[0];
		int [] resultat = new int [2];
		resultat[0]=i*x;
		resultat[1]=j*y;
		return resultat;
		}

	public void draw (Graphics2D g) {
		if(Magic.elementmagic==0) {
			for (int i=0;i<Game.Nb_row;i++) {
				for (int j=0;j<Game.Nb_col;j++) {
					g.drawImage(images[plateau[i][j]],j*Game.tileSize,i*Game.tileSize,Game.tileSize,Game.tileSize,null);				
				}
			}
			}
			else if(Magic.elementmagic==1) {
			for (int i=0;i<Game.Nb_row;i++) {
				for (int j=0;j<Game.Nb_col;j++) {
					g.drawImage(images2[plateau[i][j]],j*Game.tileSize,i*Game.tileSize,Game.tileSize,Game.tileSize,null);				
				}
			}
			}

	}
}