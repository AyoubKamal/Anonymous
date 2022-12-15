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
			System.out.println("votre tresor n'est pas bien placé");
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
					System.out.println("votre tresor n'est pas bien placé");
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
					System.out.println("votre tresor n'est pas bien placé");
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
