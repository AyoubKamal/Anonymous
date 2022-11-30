import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

public class Ayanman extends Rectangle {
	public boolean right, left, up, down;
	private int speed1 = 57;
	private int speed2 = 57;
	public static Labyrinthe map;
	public static int[][] t;
	int i=1,j=1;
	int x,y;
	public static int jk=0;
	
	public Ayanman(int x, int y) throws IOException {
		map=new Labyrinthe("Laby.txt");
		this.x=57;
		this.y=57;
	}
	
	public int[] getposition() {
		int[] u = null;
		u[0]=x;
		u[1]=y;
		return u;
	}


	public void update() throws IOException{
		t=map.getPlateau();

		if ((right && t[i][j+1]!=1)) {
			if(x<Game.WIDTH-40) {
			x+=speed1;
			right=false;
			j+=1;
			jk=0;
			}
		}
		if(j-1>=0) {
		if ((left && t[i][j-1]!=1)) {
			if(x>0) {
			x-=speed1;
			left=false;
			j-=1;
			
			jk=1;
			}
		}
		}
		if(i-1>=0) {
		if ((up && t[i-1][j]!=1 )) {
			if(y>0) {
			y-=speed2;
			up=false;
			i-=1;
			jk=2;
			}
		}
		}
		if ((down && t[i+1][j]!=1)) {
			if(y<Game.HEIGHT-66) {
			y+=speed2;
			down=false;
			i+=1;
			jk=3;
			}
		}
		}
	


	public void render(Graphics g) {
		if(jk==0) {
		imageHero i=Game.i;
		g.drawImage(i.getimage(0, 0),x,y,null);
		}
		else if(jk==1) {
			imageHero qw=Game.qw;
			g.drawImage(qw.getimage(0, 0),x,y,null);
		}
		else if(jk==2) {
			imageHero qs=Game.qs;
			g.drawImage(qs.getimage(0, 0),x,y,null);
		}
		else if(jk==3) {
			imageHero qa=Game.qa;
			g.drawImage(qa.getimage(0, 0),x,y,null);
		}
	}
	
	public double getX() {
		
		return x;
	}
	
	public void setX(int x) {
		
		this.x=x;
	}
	
	public double getY() {
		
		return y;
	}
	
	public void setY(int y) {
		
		this.y=y;
	}
}
