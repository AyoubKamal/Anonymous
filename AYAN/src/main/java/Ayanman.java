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
	public static boolean right, left, up, down,attaque;
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
	public BufferedImage upAtt1,upAtt2,downAtt1,downAtt2,leftAtt1,leftAtt2,rightAtt1,rightAtt2;
	public String direction;
	private int speed= 4;
	public static Labyrinthe map;
	public static int[][] t;
	int x,y;
	int i,j;
	public int spriteCounter=0;
	public int spriteNum=1;
	public boolean wellPlaced=false ;

	
	public Ayanman(int x, int y) throws IOException {
		
		map=new Labyrinthe("Laby.txt");
		this.x=x;
		this.y=y;
		i =this.y/Game.tileSize;
		j=this.x/Game.tileSize;
		direction="left";
		getPlayerImage();
		getPlayerAttaqueImage();

		if(map.plateau[i][j]==0) {
			this.wellPlaced=true;
			}
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
	
	public void getPlayerAttaqueImage() {
		try {

			upAtt1 = ImageIO.read(new File("images/player/boy_pick_up_1.png"));
			upAtt2 = ImageIO.read(new File("images/player/boy_pick_up_2.png"));
			downAtt1 = ImageIO.read(new File("images/player/boy_pick_down_1.png"));
			downAtt2 = ImageIO.read(new File("images/player/boy_pick_down_2.png"));
			leftAtt1 = ImageIO.read(new File("images/player/boy_pick_left_1.png"));
			leftAtt2 = ImageIO.read(new File("images/player/boy_pick_left_2.png"));
			rightAtt1 = ImageIO.read(new File("images/player/boy_pick_right_1.png"));
			rightAtt2 = ImageIO.read(new File("images/player/boy_pick_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
	
	
	
	

	public void update(Labyrinthe map) {
		if (this.wellPlaced) {
			if(this.attaque) {
				attaquer();
			}
			
		//System.out.println(this.up + " "+ this.down +" "+this.left+" "+this.right);
			else if(this.up==true || this.down==true || this.left==true || this.right==true) {
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
}
	public boolean Colision(String direction,Labyrinthe map) {
		
		if (this.up==true) {
			int posX =(this.y-speed)/Game.tileSize;
			int posY1=(this.x+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posY2=(this.x+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			
			if (this.y>speed && map.plateau[posX][posY1]==0 && map.plateau[posX][posY2]==0) {
				
				return true;
				}
			else {return false;}
		}else if(this.down==true){
			int posX =(this.y+Game.tileSize+speed)/Game.tileSize;
			int posY1=(this.x+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posY2=(this.x+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			
			if (this.y<Game.HEIGHT-Game.tileSize-speed &&map.plateau[posX][posY1]==0 && map.plateau[posX][posY2]==0) {
				return true;
				}
			else {return false;}
		}else if (this.left==true) {
			int posX1 =(this.y+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posX2=(this.y+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			int posY=(this.x-speed)/Game.tileSize;
			
			if (this.x>speed && map.plateau[posX1][posY]==0 && map.plateau[posX2][posY]==0 ) {
				return true;
				}
			else {return false;}
		}else if (this.right==true) {
			int posX1 =(this.y+Game.tileSize/2+Game.tileSize/3)/Game.tileSize;
			int posX2=(this.y+Game.tileSize/2-Game.tileSize/3)/Game.tileSize;
			int posY=(this.x+Game.tileSize+speed)/Game.tileSize;
			if (this.x<Game.WIDTH-Game.tileSize -speed&& map.plateau[posX1][posY]==0 && map.plateau[posX2][posY]==0 ) {
				return true;
				}
			else {return false;}
		}
		return true;
			
	}

	public void render(Graphics2D g2) {
		if (this.wellPlaced) {

		// g2.setColor(Color.white);
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;
		switch (direction) {
		case "up":
			if (!this.attaque) {
				if (spriteNum == 1) {image = up1;}
				if (spriteNum == 2) {image = up2;}
			}
			if(this.attaque) {
				if (spriteNum == 1) {image = upAtt1;}
				if (spriteNum == 2) {image = upAtt2;}
			}
			break;
		case "down":
			if(!this.attaque) {
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
					}
			}
			if(this.attaque) {
				if (spriteNum == 1) {
					image = downAtt1;
				}
				if (spriteNum == 2) {
					image = downAtt2;
				}
			}
			break;
		case "left":
			if(!this.attaque) {
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
					}
			}
			if (this.attaque) {
				if (spriteNum == 1) {
					image = leftAtt1;
				}
				if (spriteNum == 2) {
					image = leftAtt2;
				}
			}
			break;
		case "right":
			if(!this.attaque) {
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
					}
				}
			if(this.attaque) {
				if (spriteNum == 1) {
					image = rightAtt1;
				}
				if (spriteNum == 2) {
					image = rightAtt2;
					}
				}
			
			break;
		}
		//g2.drawImage(image, x, y,Game.tileSize, Game.tileSize, null);
		draw(g2,image,x,y);
	}
	
}
	
	public void draw(Graphics2D g2, BufferedImage image, int x , int y) {
		if (image==downAtt1 || image==downAtt2 ) {
			g2.drawImage(image, x, y, Game.tileSize,2*Game.tileSize,null);
		}
		else if (image==upAtt1 || image==upAtt2  ) {
			g2.drawImage(image, x, y-Game.tileSize, Game.tileSize,2*Game.tileSize,null);
		}
		else if (image==leftAtt1 || image==leftAtt2  ) {
			g2.drawImage(image, x-Game.tileSize, y, 2*Game.tileSize,Game.tileSize,null);
		}
		else if (image==rightAtt1 ||image==rightAtt2 ) {
			g2.drawImage(image, x, y, 2*Game.tileSize,Game.tileSize,null);
		}
		else {
			g2.drawImage(image, x, y,Game.tileSize,Game.tileSize,null);
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
	
	public void attaquer () {
			spriteCounter++;
			if(spriteCounter<5) {
				spriteNum=1;
			}
			else if(spriteCounter>5 && spriteCounter<25) {
				spriteNum=2;
				checkDammage();
			}
			else if (spriteCounter>25) {
				spriteNum=1;
				spriteCounter=0;
				this.attaque=false;
			}
			
	}
	
	public void checkDammage() {
		int a=this.x;
		int b=this.y;
		/*switch (direction) {
		case "up":  b=b-Game.tileSize;
					a+=Game.tileSize/2;
		case"down" :b+=Game.tileSize;
					a+=Game.tileSize/2;
		case "left"	: a-=Game.tileSize;
					b+=Game.tileSize/2;
		case "right" :a+=Game.tileSize;
					b+=Game.tileSize/2;
		}	*/
		for (int i=0;i<Game.liste_monsters.length;i++) {
			if(Game.liste_monsters[i]!=null) {
				if ((Math.abs(a-Game.liste_monsters[i].getX())<Game.tileSize && Math.abs(b-Game.liste_monsters[i].getY())<10)||(Math.abs(b-Game.liste_monsters[i].getY())<Game.tileSize && Math.abs(a-Game.liste_monsters[i].getX())<10)) {
					Game.liste_monsters[i]=null;
				}
			}
		}	
	}
}
