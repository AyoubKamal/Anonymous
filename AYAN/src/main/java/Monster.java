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
	private int speed = 1;
	public int speed2 = 1;
	public static Labyrinthe map;
	int i,j;
	private int x;
	private int y;
	public String direction;
	BufferedImage [] images_monstres=new BufferedImage[8];
	public int nb=0;
	public int cmp=0;
	public int typeM;
	public String direct="up";
	private int b;

	public Monster(int a,int b, int typeM) throws IOException {
		
		this.x = a;
		this.y = b;
		j=this.x/Game.tileSize;
		i=this.y/Game.tileSize;
		this.typeM=typeM;
		if (Labyrinthe.plateau[i][j]==0) {
			System.out.println("YES");
		}
		uploadImagesMonsters();	
	}
	
	public Monster(int typeM) throws IOException {
		int a = (int)(Math.random() * (Game.WIDTH-Game.tileSize))+1;
		int b = (int)(Math.random() * (Game.HEIGHT-Game.tileSize))+1;
		this.x = a;
		this.y = b;
		j=this.x/Game.tileSize;
		i=this.y/Game.tileSize;
		System.out.println("i = "+i+" j = "+j);
		while(Labyrinthe.plateau[i][j]!=0) {
			a = (int)(Math.random() * (Game.WIDTH-50))+1;
			b = (int)(Math.random() * (Game.HEIGHT-50))+1;
			this.x = a;
			this.y = b;
			j=a/Game.tileSize;
			i=b/Game.tileSize;
			System.out.println("i = "+i+" j = "+j);
		}
		
		this.typeM=typeM;
		
		uploadImagesMonsters();
	}
	
	
	
	
	
	
	public void uploadImagesMonsters() {
		try {
			images_monstres[0]=ImageIO.read(new File("images/monster/monster2Up.png"));
			images_monstres[1]=ImageIO.read(new File("images/monster/monster2Down.png"));
			images_monstres[2]=ImageIO.read(new File("images/monster/redslime_down_1.png"));
			images_monstres[3]=ImageIO.read(new File("images/monster/redslime_down_2.png"));
			images_monstres[4]=ImageIO.read(new File("images/monster/bat_down_1.png"));
			images_monstres[5]=ImageIO.read(new File("images/monster/bat_down_2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	/*public void update(Labyrinthe map)  {
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
		}
			cmp+=1;
			if (cmp>6) {
				cmp=0;
				if(nb==1) {nb=0;}
				else if(nb==0) {nb=1;}
			}
		}
		else {System.out.println("Vous n'avez pas bien placé le monstre");}
	}
	*/
	
	public void Mouvement_aleat(Labyrinthe map) {
			
			if (direct=="up") {
				
				if(Colision("up",map)) {
					this.y-=speed;	
					direct ="up";
					
				}
				
				else if (Colision("right",map)) {
					this.x+=speed;	
					direct ="right";
					}
				
				else if (Colision("left",map)) {
					this.x+=speed;	
					direct ="left";
					}
				else if (Colision("down",map)) {
					this.y+=speed;	
				direct ="down";
					}
				}
			
			
			else if (direct=="right") {
				
				 if (Colision("right",map)) {
					this.x+=speed;	
					direct ="right";
					}
				
				 else if(Colision("up",map)) {
					this.y-=speed;	
					direct ="up";
					}
				 
					else if (Colision("down",map)) {
						this.y+=speed;	
					direct ="down";
						}
				
				else if (Colision("left",map)) {
					this.x+=speed;	
					direct ="left";
					}
				}
			
			else if (direct=="down") {
				
				if (Colision("down",map)) {
					this.y+=speed;	
				direct ="down";
					}
				
				else if (Colision("right",map)) {
					this.x+=speed;	
					direct ="right";
					}
	
				else if (Colision("left",map)) {
					this.x+=speed;	
					direct ="left";
					}
				
				 else if(Colision("up",map)) {
					this.y-=speed;	
					direct ="up";
					}
				}
			
			
			else if (direct=="left") {
				
				if (Colision("left",map)) {
					this.x-=speed;	
				direct ="left";
					}
				

	
				else if (Colision("down",map)) {
					this.y+=speed;	
					direct ="down";
					}
				
				 else if(Colision("up",map)) {
					this.y-=speed;	
					direct ="up";
					}
				
					else if (Colision("right",map)) {
						this.x+=speed;	
						direct ="right";
						}
				}
				cmp+=1;
				if (cmp>6) {
					cmp=0;
					if(nb==1) {nb=0;}
					else if(nb==0) {nb=1;
				}
			}
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


	public void updateIntelligenteColl(Ayanman player,Labyrinthe map) {

			cmp+=1;
			if (cmp>6) {
				cmp=0;
				if(nb==1) {nb=0;}
				else if(nb==0) {nb=1;}
			}
		int a=player.x;
		int b=player.y;
		while(distance(a,b,this.x,this.y)<350 && distance(a,b,this.x,this.y)>5) {
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

		break;
	}
}	
	public void updateIntellignete (Ayanman Player , Labyrinthe map ) {
			cmp+=1;
			if (cmp>6) {
				cmp=0;
				if(nb==1) {nb=0;}
				else if(nb==0) {nb=1;}
			}
		int a=Player.x;
		int b=Player.y;
		while(distance(a,b,this.x,this.y)<350 && distance(a,b,this.x,this.y)>5 ) {
			if (this.x>a && this.x > speed) {
				this.x-=speed;
			}
			else if(this.x<a && this.x +speed <Game.WIDTH) {
				this.x+=speed;
				}
			if (this.y>b && this.y>speed) {
				this.y-=speed;
			}
		else if (this.y<b && this.y +speed <Game.HEIGHT){
			this.y+=speed;}
		break;
	}
		}

	
	public int distance(int a, int b , int c , int d) {
		
		return (int)Math.sqrt((c-a)*(c-a)+(d-b)*(d-b));
	
	}
	
	public void update (Ayanman Player,Labyrinthe map) {

		if (this.typeM==1) {
			this.Mouvement_aleat(map);
		}
		else if (this.typeM==2) {
			this.updateIntelligenteColl(Player, map);
		}
		else if (this.typeM==3) {
			this.updateIntellignete(Player,map);
		}
	}

	public void render(Graphics2D g) {
		if(this!=null) {
		System.out.println(this.typeM);
			if(this.typeM==1) {
				if (nb==0) {
					g.drawImage(images_monstres[0], x, y,Game.tileSize,Game.tileSize, null);}
				else if (nb==1) {
					g.drawImage(images_monstres[1], x, y,Game.tileSize,Game.tileSize, null);}
				}
			
			else if (this.typeM==2) {
			if (nb==0) {
				g.drawImage(images_monstres[2], x, y,Game.tileSize,Game.tileSize, null);}
				else if (nb==1) {
					g.drawImage(images_monstres[3], x, y,Game.tileSize,Game.tileSize, null);}			
		}
			
		
			else if (this.typeM==3) {
				if (nb==0) {
					g.drawImage(images_monstres[4], x, y,Game.tileSize,Game.tileSize, null);}
					else if (nb==1) {
						g.drawImage(images_monstres[5], x, y,Game.tileSize,Game.tileSize, null);}			
			}
				}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
