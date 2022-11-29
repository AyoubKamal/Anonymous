import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class Tresor extends JPanel{
	

	private int x;
	private int y;
	private static String nomFichier; 
	

	
	public Tresor(int x, int y,Labyrinthe map ) {
		this.x=x;
		this.y=y;
		map.plateau [x][y]=2;
		
	}
	
	public int getX() {
		
		return x;
	}
	
	public void setX(int x) {
		
		this.x=x;
	}
	
	public int getY() {
		
		return y;
	}
	
	public void setY(int y) {
		
		this.y=y;
	}
	
	/*public void placertresor(Labyrinthe map) {
		if (map.plateau[x][y]==0)
		map.plateau [x][y]= 2;
		
	}
	
	*/
	public void render(String nomFichierImage,Graphics g) throws IOException {
		Image img = ImageIO.read(new File(nomFichierImage));
		g.drawImage(img, x*57,y*57 ,this);

	}
	
	public void annuler(Labyrinthe map, Ayanman player) {
		
		if ( player.getX()-y*57<20 && player.getX()-y*57>-20 && player.getY()-x*57<20 && player.getY()-x*57>-20 )  {
			System.out.println("hello");
			map.plateau [x][y]= 0;
		}
	}

}
