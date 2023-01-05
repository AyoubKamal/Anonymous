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



public class Porte extends JPanel{
	
	private int x;
	private int y;
	private static String nomFichier;
	
		
		public int i,j;
		public int nb;
		public boolean T;
		public boolean placed=false;
	
	public Porte(Labyrinthe map,int nb) {
		if (nb==1) {
		int j = (int)(Math.random() * (Game.getCol()-1));
		int i = (int)(Math.random() * (Game.getligne()-1));
		while(map.plateau[i][j]!=0) {
			j = (int)(Math.random() * (Game.getCol()-1));
			i = (int)(Math.random() * (Game.getligne()-1));
		}
		this.x = j*Game.tileSize;
		this.y = i*Game.tileSize;
		this.nb=nb;
		this.T=true;
		this.placed=true;}
		else {
			int j = (int)(Math.random() * (Game.getCol()-1));
			int i = (int)(Math.random() * (Game.getligne()-1));
			this.x = j*Game.tileSize;
			this.y = i*Game.tileSize;
			while(map.plateau[i][j]!=0 || distance(x,y,(int)Game.porte1.getX(),(int)Game.porte1.getY())<200) {
				j = (int)(Math.random() * (Game.getCol()-1));
				i = (int)(Math.random() * (Game.getligne()-1));
				this.x = j*Game.tileSize;
				this.y = i*Game.tileSize;
			}

			this.nb=nb;
			this.T=true;
			this.placed=true;
		}

		
	}
	public int distance(int a, int b , int c , int d) {
		
		return (int)Math.sqrt((c-a)*(c-a)+(d-b)*(d-b));
	
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


				
	public void render(Graphics g) throws IOException {
		if(nb==1) {
		if(this.placed) {
		if(annuler()) {
		Image img = ImageIO.read(new File("images/door.png"));
		g.drawImage(img, x+10,y+10 ,Game.tileSize-20,Game.tileSize-20,null,this);

		}
		else {
			Image img = ImageIO.read(new File("images/door.png"));
			g.drawImage(img, x+10,y+10 ,Game.tileSize-20,Game.tileSize-20,null,this);
			Game.player.setX(Game.porte2.getX()+10);
			Game.player.setY(Game.porte2.getY()+10);
			T=true;
		}
	}
		else {
			System.out.println("votre Porte n'est pas bien placï¿½");
		}
		}
		else if(nb==2) {
			if(this.placed) {
			if(annuler()) {
			Image img = ImageIO.read(new File("images/doorr.png"));
			g.drawImage(img, x+10,y+10 ,Game.tileSize-15,Game.tileSize-15,null,this);

			}
			else {
				Image img = ImageIO.read(new File("images/doorr.png"));
				g.drawImage(img, x+10,y+10 ,Game.tileSize-15,Game.tileSize-15,null,this);
				Game.player.setX(Game.porte1.getX()+15);
				Game.player.setY(Game.porte1.getY()+15);
				T=true;
			}
		}
			else {
				System.out.println("votre Porte n'est pas bien placï¿½");
				}
			}
		else {
			System.out.println("nombre non valide");
		}
	}
	public boolean annuler() {
		if(this.placed) {
		if ((Math.abs(Game.player.getX()-this.getX())<10 && Math.abs(Game.player.getY()-this.getY())<10))  {
			this.T=false;
		}
		return T;
		}
	return false;
	}
}