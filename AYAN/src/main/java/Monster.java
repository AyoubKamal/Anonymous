import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster  {
	// monstres 2 types :
			// Classique 2 types : déplace aleat et intellig
			// fantome 2 types ; depla aleat et intellig

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

	public Monster(int a,int b) throws IOException {
		this.x = a;
		this.y = b;
		j=this.x/Game.tileSize;
		i=this.y/Game.tileSize;
		if (Labyrinthe.plateau[i][j]==0) {
			wellPlaced = true;
			System.out.println("YES");
		}
		uploadImagesMonsters();
	}
	public Monster() throws IOException {
		int a = (int)(Math.random() * (Game.WIDTH-1))+1;
		int b = (int)(Math.random() * (Game.HEIGHT-1))+1;
		j=this.x/Game.tileSize;
		i=this.y/Game.tileSize;
		this.x = a;
		this.y = b;
		while(Labyrinthe.plateau[i][j]!=0) {
			a = (int)(Math.random() * (Game.WIDTH-1))+1;
			b = (int)(Math.random() * (Game.HEIGHT-1))+1;
		this.x = a;
		this.y = b;
		j=this.x/Game.tileSize;
		i=this.y/Game.tileSize;
		}
		wellPlaced = true;
		
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
			this.x += getSpeed();
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
public boolean Colision(String direction,Labyrinthe map) {
		
		if (direction=="up") {
			int posX =(this.y-speed)/Game.tileSize;
			int posY1=(this.x+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posY2=(this.x+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			
			if (this.y>speed && map.plateau[posX][posY1]==0 && map.plateau[posX][posY2]==0) {
				System.out.println(" i = "+posX+" j = "+posY1);
				System.out.println(" y = "+(this.y+Game.tileSize/2)+" x = "+(this.x+Game.tileSize/2));
				return true;
				}
			else {return false;}
		}else if(direction=="down"){
			int posX =(this.y+Game.tileSize+speed)/Game.tileSize;
			int posY1=(this.x+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posY2=(this.x+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			
			if (this.y<Game.HEIGHT-Game.tileSize-speed &&map.plateau[posX][posY1]==0 && map.plateau[posX][posY2]==0) {
				System.out.println(" i = "+posX+" j = "+posY1);
				System.out.println(" y = "+(this.y+Game.tileSize/2)+" x = "+(this.x+Game.tileSize/2));
				return true;
				}
			else {return false;}
		}else if (direction=="left") {
			int posX1 =(this.y+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posX2=(this.y+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			int posY=(this.x-speed)/Game.tileSize;
			if (this.x>speed && map.plateau[posX1][posY]==0 && map.plateau[posX2][posY]==0 ) {
				System.out.println(" i = "+posX1+" j = "+posY);
				System.out.println(" y = "+(this.y+Game.tileSize/2)+" x = "+(this.x+Game.tileSize/2));
				return true;
				}
			else {return false;}
		}else if (direction=="right") {
			int posX1 =(this.y+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posX2=(this.y+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			int posY=(this.x+Game.tileSize+speed)/Game.tileSize;
			if (this.x<Game.WIDTH-Game.tileSize -speed&& map.plateau[posX1][posY]==0 && map.plateau[posX2][posY]==0 ) {
				System.out.println(" i = "+posX1+" j = "+posY);
				System.out.println(" y = "+(this.y+Game.tileSize/2)+" x = "+(this.x+Game.tileSize/2));
				return true;
				}
			else {return false;}
		}
		return true;
			
	}


	public void updateIntelligente(Ayanman player,Labyrinthe map) {
		if (wellPlaced==true) {
		int a=player.x;
		int b=player.y;
		while(distance(a,b,this.x,this.y)<350 && distance(a,b,this.x,this.y)>50) {
			if (this.x>a && Colision("left",map)) {
				this.x-=speed;
			}
			else if(this.x<a && Colision("right",map)) {
				this.x+=speed;
				}
			if (this.y>b  && Colision("up",map)) {
				this.y-=speed;
			}
		else if (this.y<b  && Colision("down",map)){this.y+=speed;}
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

	public void render(Graphics2D g) {
		if (wellPlaced ==true){// FANHUI
		if (nb==0) {
		g.drawImage(images_monstres[0], x, y,Game.tileSize,Game.tileSize, null);}
		else if (nb==1) {
			g.drawImage(images_monstres[1], x, y,Game.tileSize,Game.tileSize, null);}
		}
	}
	

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
