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
		public int i,j;
		public int nb;
		public boolean T;
		public boolean placed=false;
	
	public Tresor(int i, int j,Labyrinthe map,int nb) {
		if(map.plateau[i][j]==0) {
			this.placed=true;
			}
		this.nb=nb;
		this.y=i*Game.tileSize;
		this.x=j*Game.tileSize;
		this.T=true;

		
	}
	
	public int getX() {
		
		return x;
	}
public int getI() {
		
		return i;
	}
	
	public void setX(int x) {
		
		this.x=x;
	}
	
	public int getY() {
		
		return y;
	}
public int getJ() {
		
		return j;
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
			map.plateau [x][y]=0;
			
	public void render(Graphics g) throws IOException {
		if(nb==1) {
		if(this.placed) {
		if(annuler()) {
		Image img = ImageIO.read(new File("images/wall.png"));
		g.drawImage(img, x+10,y+10 ,Game.tileSize-20,Game.tileSize-20,null,this);

		}
		else {
			Image img = ImageIO.read(new File("images/wall1.png"));
			g.drawImage(img, x+10,y+10 ,Game.tileSize-20,Game.tileSize-20,null,this);
		}
	}
		else {
			System.out.println("votre tresor n'est pas bien plac�");
		}
		}
		else if(nb==2) {
			if(this.placed) {
				if(annuler()) {
				Image img = ImageIO.read(new File("images/coeur.png"));
				g.drawImage(img, x+10,y+10 ,Game.tileSize-20,Game.tileSize-20,null,this);
				}
			}
				else {
					System.out.println("votre tresor n'est pas bien plac�");
				}
		}
		else if(nb==3) {
			if(this.placed) {
				if(annuler()) {
				Image img = ImageIO.read(new File("images/key.png"));
				g.drawImage(img, x+10,y+10 ,Game.tileSize-20,Game.tileSize-20,null,this);
				}
			}
				else {
					System.out.println("votre tresor n'est pas bien plac�");
				}
		}
		else {
			System.out.println("nombre non valide");
		}
	}
	public boolean annuler() {
		if(this.placed) {
		if ((Math.abs(Game.player.getX()-this.getX())<20 && Math.abs(Game.player.getY()-this.getY())<20))  {
			this.T=false;
		}
		return T;
		}
	return false;
	}
}
