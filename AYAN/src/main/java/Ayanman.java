import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ayanman extends Rectangle {
	public boolean right, left, up, down;
	private int speed = 50;

	public Ayanman(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public void update() {

		if (right)
			x += speed;
		if (left)
			x -= speed;
		if (up)
			y -= speed;
		if (down)
			y += speed;
	
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval(x, y,40 ,40);
	}
}
