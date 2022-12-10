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
	public boolean [] colision =new boolean [15];
	


	Labyrinthe(String nomFichier) throws IOException{
		this.nomFichier=nomFichier;
		dim=dimensionFichier();
		LireFichier();
		images[1]=ImageIO.read(new File("images/tiles/wall.png"));
		images[0]=ImageIO.read(new File("images/tiles/grass.png"));
		images[2]=ImageIO.read(new File("images/tiles/water.png"));
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
/*	public static void afficher () throws IOException {
		LireFichier();
		int [] dim =dimensionFichier();
		for(int i =0;i<dim[0];i++) {
			for(int j=0;j<dim[1];j++) {
				System.out.print(plateau[i][j]);
			}
			System.out.println("");
		}
	}*/
	


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
		//dissiner2("images/grass2.png",g,0,0);
		//System.out.println(Game.HEIGHT/dim[0] +" "+ Game.WIDTH/dim[1]);
		for (int i=0;i<Game.Nb_row;i++) {
			for (int j=0;j<Game.Nb_col;j++) {
				g.drawImage(images[plateau[i][j]],j*Game.tileSize,i*Game.tileSize,Game.tileSize,Game.tileSize,null);
				
				/*dissiner2("images/tiles/wall.png",g,positionLaby[0],positionLaby[1]);
				if(plateau[i][j]==1) {
					 		dissiner2("images/tiles/water.png",g,positionLaby[0],positionLaby[1]);
					  		}
				else if (plateau[i][j]==2) {
							dissiner2("images/tiles/grass.png",g,positionLaby[0],positionLaby[1]);
				}*/
				
			}
		}
			
	}
	
	
	
	public void draw3(Graphics2D g2) {
	}
	public void draw2(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while (col <dim[1]  && row <dim[0] ) {
			int tileNum=plateau[col][row];
			g2.drawImage(images[tileNum], x, y, Game.tileSize, Game.tileSize, null);
			col+=1;
			x += Game.tileSize;
			if (col == 10) {
				col = 0;
				x = 0;
				row++;
				y += Game.tileSize;
			
			}
		}
	}
}
