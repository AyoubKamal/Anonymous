import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster  {

	public boolean right, left, up, down;
	private int speed = 2;
	public int speed2 = 1;
	public static Labyrinthe map;
	public static int[][] t;
	int i,j;
	private int x;
	private int y;
	public String direction;
	BufferedImage [] images_monstres=new BufferedImage[8];
	public int nb=0;
	public int cmp=0;
	private boolean wellPlaced = false;

	public Monster(int x, int y) throws IOException {
		this.x = x;
		this.y = y;
		j=this.x/Game.tileSize;
		i=this.y/Game.tileSize;
		if (Labyrinthe.plateau[i][j]==0 ) {
			wellPlaced=true;
			System.out.println("Yes");
		}
		uploadImagesMonsters();
	}
	
	public void uploadImagesMonsters() {
		try {
			images_monstres[0]=ImageIO.read(new File("images/monster/bat_down_1.png"));
			images_monstres[1]=ImageIO.read(new File("images/monster/bat_down_2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		}
	public void update(Labyrinthe map)  {
		if (wellPlaced==true) {
		//int n = (int)(Math.random() * 4);
		//int [] res =Mouvement_aleat(map);
		//if (true && res[n]==1) {
			x += getSpeed();
			j += 1;
		//}
		/*else if (n==1 && res[n]==1) {
			y+=15;
			i+=1;
		}
		else if (n==2 && res[n]==1) {
			x-=15;
			j-=1;
		}
		else if (n==3 && res[n]==1) {
			 y-=15;
			i-=1;
		}*/
			cmp+=1;
			if (cmp>6) {
				cmp=0;
				if(nb==1) {nb=0;}
				else if(nb==0) {nb=1;}
			}
		}
		else {System.out.println("Vous n'avez pas bien placé le monstre");}
	}
	
	
	public int [] Mouvement_aleat(Labyrinthe map) {
		t=map.plateau;
		int [] res=new int [4];
		if (t[i][j + 1] == 0) {
			res[0]=1;
		}
		 if (t[i+1][j] == 0) {
			res[1]=1;
		}
		 if(j-1>=0) {
	 if (t[i][j - 1] == 0) {
			res[2]=1;
		}
		 }
		 if(i-1>=0) {
		 if (t[i-1][j] == 0) {
				res[3]=1;
			}
		 }
		return res;
	}
	
	
	
	public void updateIntelligente(Ayanman player,Labyrinthe map) {
		if (wellPlaced==true) {
		int a=player.x;
		int b=player.y;
		while(distance(a,b,this.x,this.y)<350 && distance(a,b,this.x,this.y)>50) {
			if (this.x>a) {
				this.x-=speed;
			}
			else  {this.x+=speed;}
		if (this.y>b) {
			this.y-=speed;
		}
		else {this.y+=speed;}
		cmp+=1;
		if (cmp>6) {
			cmp=0;
			if(nb==1) {nb=0;}
			else if(nb==0) {nb=1;}
		}
		break;
	}
		}
		else {System.out.println("Vous n'avez pas bien placé le monster");}
}
	public int distance(int a, int b , int c , int d) {
		return (int)Math.sqrt((c-a)*(c-a)+(d-b)*(d-b));
	}

	public void render(Graphics2D g) { // FANHUI
		if (nb==0) {
		g.drawImage(images_monstres[0], x, y,Game.tileSize,Game.tileSize, null);}
		else if (nb==1) {
			g.drawImage(images_monstres[1], x, y,Game.tileSize,Game.tileSize, null);}
		}
		
	

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
