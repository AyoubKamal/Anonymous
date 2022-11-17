import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Labyrinthe extends Rectangle {


	public Labyrinthe(int x, int y) {
		setBounds(x, y, 40, 40);
	}

	public void update() {

	}

	public void render(Graphics g) {  //fanhui
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}
}
