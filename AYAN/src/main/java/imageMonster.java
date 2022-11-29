import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class imageMonster{
	private BufferedImage imag;
 
	public imageMonster(String path) {
	 try {
		 imag=ImageIO.read(getClass().getResource(path));
	 }catch(IOException e) {
		 System.out.println("failed");
	 }
 }
 public BufferedImage getimage(int xx,int yy) {
	 return imag.getSubimage(xx, yy, xx+57, yy+57);
 }
}