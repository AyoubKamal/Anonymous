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


public class Piege extends JPanel{
	
	private int x;
	private int y;
	private static String nomFichier;
	
	
		public int i,j;
		public int nb;
		public boolean T;
		public boolean placed=false;
	
	public Piege(Labyrinthe map) {
		int j = (int)(Math.random() * (Game.getCol()-1));
		int i = (int)(Math.random() * (Game.getligne()-1));
		while(map.plateau[i][j]!=0) {
			j = (int)(Math.random() * (Game.getCol()-1));
			i = (int)(Math.random() * (Game.getligne()-1));
		}
		this.x = j*Game.tileSize;
		this.y = i*Game.tileSize;
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
		
		
		if(annuler()) {
		Image img = ImageIO.read(new File("images/piege.png"));
		g.drawImage(img, x+10,y+10 ,Game.tileSize-20,Game.tileSize-20,null,this);
		}

	}
	public boolean annuler() {
		
		if ((Math.abs(Game.player.getX()-this.getX())<20 && Math.abs(Game.player.getY()-this.getY())<20))  {
			this.T=false;
		}
		return T;
		
	}
}