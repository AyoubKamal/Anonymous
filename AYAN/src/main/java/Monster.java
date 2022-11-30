import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

public class Monster  {

	public boolean right = true, left, up, down;
	private int speed1 = 57;
	private int speed2 = 57;
	public static Labyrinthe map;
	public static int[][] t;
	int i,j;

	public Monster(int i, int j) {
		this.i = i;
		this.j = j;
		
	}

	public void update(Labyrinthe map) throws IOException, InterruptedException {
		int n = (int)(Math.random() * 4);
		int [] res =Mouvement_aleat(map);
		Thread.sleep(30);
		if (n==0 && res[n]==1) {
			//x += 57;
			j += 1;
		}
		else if (n==1 && res[n]==1) {
			//y+=57;
			i+=1;
		}
		else if (n==2 && res[n]==1) {
			//x-=57;
			j-=1;
		}
		else if (n==3 && res[n]==1) {
			//y-=57;
			i-=1;
		}
		
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

	public void render(Graphics g) { // FANHUI

		imageMonster o = Game.o;
		g.drawImage(o.getimage(0, 0), j*57, i*57, null);
		
	}
}
