import java.awt.Graphics;
import javax.swing.*;
public class test extends JPanel
{
  public void paint(Graphics g){
	  int[] l= {0,1,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,1,0,1};
	  for(int i=0;i<20;i++) {
		  if(l[i]==0) {
		  g.drawRect(i*30, 35, 30, 30);
		  }}
		
  }
  
  public static void main(String[] args){
    JFrame f = new JFrame("Dessiner un rectangle");
    f.getContentPane().add(new test());
    f.setSize(3000, 3000);
    f.setVisible(true);
    f.setResizable(false);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}

