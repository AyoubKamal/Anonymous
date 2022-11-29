import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;

public class Monster extends Rectangle {

	public boolean right = true, left, up, down;
	private int speed1 = 57;
	private int speed2 = 57;
	public static Labyrinthe map;
	public static int[][] t;
	int i = 2, j = 2;

	public Monster(int x, int y) {
		this.x = 57 * 2;
		this.y = 57 * 2;
	}

	public void update() throws IOException {
		int n = (int)(Math.random() * 4);
		int [] res =Mouvement_aleat();
		if (n==0 && res[n]==1) {
			x += 57;
			j += 1;
		}
		else if (n==1 && res[n]==1) {
			y+=57;
			i+=1;
		}
		else if (n==2 && res[n]==1) {
			x-=57;
			j-=1;
		}
		else if (n==3 && res[n]==1) {
			y-=57;
			i-=1;
		}
		
	}
	
	
	public int [] Mouvement_aleat() {
		int [] res=new int [4];
		t = map.getPlateau();
		if (t[i][j + 1] == 0) {

			res[0]=1;
		}
		 if (t[i+1][j] == 0) {
			res[1]=1;
		}
	 if (t[i][j - 1] == 0) {
			res[2]=1;
		}
		 if (t[i-1][j] == 0) {
				res[3]=1;
			}
		return res;
	}

	public void render(Graphics g) { // FANHUI
		imageMonster o = Game.o;
		g.drawImage(o.getimage(0, 0), x, y, null);
	}
}
