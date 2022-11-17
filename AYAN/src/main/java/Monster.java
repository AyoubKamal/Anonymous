import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster extends Rectangle {


	public Monster(int x, int y) {
		setBounds(x, y, 40, 40);

	}

	public void update() {
		
	}

	public void render(Graphics g) {  //FANHUI
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
	}
}
