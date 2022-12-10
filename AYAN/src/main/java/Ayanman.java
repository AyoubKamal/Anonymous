import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;

public class Ayanman  {
	public static boolean right, left, up, down;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public String direction;
	private int speed= 4;
	public static Labyrinthe map;
	public static int[][] t;
	int x,y;
	public int spriteCounter=0;
	public int spriteNum=1;
	
	public Ayanman(int x, int y) throws IOException {
		//map=new Labyrinthe("Laby.txt");
		this.x=x;
		this.y=y;
		direction="left";
		getPlayerImage();
	}
	


	/*public void update2() throws IOException{
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
		}*/
	


	/*public void render2(Graphics g) {
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
	}*/
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(new File("images/player/boy_up_1.png"));
			up2 = ImageIO.read(new File("images/player/boy_up_2.png"));
			down1 = ImageIO.read(new File("images/player/boy_down_1.png"));
			down2 = ImageIO.read(new File("images/player/boy_down_2.png"));
			left1 = ImageIO.read(new File("images/player/boy_left_1.png"));
			left2 = ImageIO.read(new File("images/player/boy_left_2.png"));
			right1 = ImageIO.read(new File("images/player/boy_right_1.png"));
			right2 = ImageIO.read(new File("images/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void update(Labyrinthe map) {
		//System.out.println(this.up + " "+ this.down +" "+this.left+" "+this.right);
		if(this.up==true || this.down==true || this.left==true || this.right==true) {
			//System.out.println("Hello");
			if (this.up) {
				direction = "up";
				if (Colision("up",map)) {
				y -= speed;
				}

			} else if (this.down) {
				direction = "down";
				if (Colision("down",map)) {
				y += speed;}

			} else if (this.left) {
				direction = "left";
				if(Colision("left",map)) {
				x -= speed;}

			} else if (this.right) {
				direction = "right";
				if(Colision("right",map)) {
				x += speed;
			}
				}
			spriteCounter++;
			if(spriteCounter>10) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if(spriteNum==2) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
	}
	
	public boolean Colision(String direction,Labyrinthe map) {
		
		if (this.up==true) {
			int posX =(this.y-speed)/Game.tileSize;
			int posY1=(this.x+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posY2=(this.x+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			
			if (this.y>speed && map.plateau[posX][posY1]==0 && map.plateau[posX][posY2]==0) {
				System.out.println(" i = "+posX+" j = "+posY1);
				System.out.println(" y = "+(this.y+Game.tileSize/2)+" x = "+(this.x+Game.tileSize/2));
				return true;
				}
			else {return false;}
		}else if(this.down==true){
			int posX =(this.y+Game.tileSize+speed)/Game.tileSize;
			int posY1=(this.x+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posY2=(this.x+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			
			if (this.y<Game.HEIGHT-Game.tileSize-speed &&map.plateau[posX][posY1]==0 && map.plateau[posX][posY2]==0) {
				System.out.println(" i = "+posX+" j = "+posY1);
				System.out.println(" y = "+(this.y+Game.tileSize/2)+" x = "+(this.x+Game.tileSize/2));
				return true;
				}
			else {return false;}
		}else if (this.left==true) {
			int posX1 =(this.y+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posX2=(this.y+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			int posY=(this.x-speed)/Game.tileSize;
			if (this.x>speed && map.plateau[posX1][posY]==0 && map.plateau[posX2][posY]==0 ) {
				System.out.println(" i = "+posX1+" j = "+posY);
				System.out.println(" y = "+(this.y+Game.tileSize/2)+" x = "+(this.x+Game.tileSize/2));
				return true;
				}
			else {return false;}
		}else if (this.right==true) {
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

	public void render(Graphics2D g2) {

		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;
		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, x, y,Game.tileSize, Game.tileSize, null);
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
