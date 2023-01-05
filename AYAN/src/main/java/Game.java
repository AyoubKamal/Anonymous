import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Game extends JPanel implements Runnable,KeyListener {
	public static boolean isRunning = false;
	public static final String TITLE = "Ayan-man";


	public final static int originalTileSize = 16;
	public final static int echelle = 3;
	public final static int tileSize = originalTileSize * echelle;
	public final static int Nb_col =26,Nb_row=13;
	public static final int WIDTH = Nb_col*tileSize, HEIGHT = Nb_row*tileSize;
	public int FPS =60;
	
	
	Thread thread; //SUBPROCESS to do multiple things 
	public static Ayanman player;
	public static Monster monster1;
	public static Monster monster2;
	public static Monster monster3;
	public static Labyrinthe map;
	public static Tresor tresor1;
	public static Tresor tresor2;
	public static Tresor coeur1;
	public static Tresor coeur2;
	public static Tresor key;
	public static score score ;
	public static Vie vie;
	public static Gagner gagner;
	public static level Level;
	public static Tresor [] liste_t;
	public static Tresor [] liste_t1;
	public static Monster[] liste_monsters;
	public static Porte porte1,porte2;
	public static Piege piege;
	public static Piege [] liste3;
	static JFrame frame = new JFrame();
	

	public Game() throws IOException {
		
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);
		this.setMaximumSize(dimension);
		this.addKeyListener(this);
		this.setFocusable(true);
		//addKeyListener(this);
		
	
		player =new Ayanman(0,60);
		Level=new level (1);
		//Level.generateLaby();
		map=Level.map;
		
		monster1=new Monster(80,80,1);
		monster2=new Monster(150,250,2);
		monster3=new Monster(150,250,3);
		liste_monsters=new Monster[3];
		liste_monsters[0]=monster1;
		liste_monsters[1]=monster2;
		liste_monsters[2]=monster3;
		porte1=new Porte(4,17,map,1);
		porte2=new Porte(6,5,map,2);
		piege =new Piege(2,17,map);
		liste3 =new Piege[1];
		liste3[0]=piege;
		

		//KeyHandler keyH = new KeyHandler(
		
		
		//score: coeur : gagner
		
		
		tresor1=new Tresor(10,2,map,1);
		tresor2=new Tresor(5,9,map,1);
		
		coeur1=new Tresor(2,10,map,2);
		coeur2=new Tresor(6,5,map,2);
		
		key=new Tresor(10,24,map,3);
		
		
		liste_t=new Tresor[2];
		liste_t[0]=tresor1;
		liste_t[1]=tresor2;
		
		liste_t1=new Tresor[2];
		liste_t1[0]=coeur1;
		liste_t1[1]=coeur2;
		
		score=new score(liste_t);
		vie=new Vie(liste_t1,liste3);
	
		gagner=new Gagner(key);
		player.MonsterNull(liste_monsters, map);

	}

	
	
	
	
	public  void start() { //synchronized sert a ne pas perdre le thread en executant
		if(isRunning) {
			return;
			}
			isRunning=true;
			thread = new Thread(this);
			thread.start();
	}
	public static void stop() {
		if(isRunning=false) {
			return;
			}
			isRunning=false;
	}
	



	    private void update() {
	
		player.update(map);
		//player.attaquer(map);
		
		for (int i=0;i<liste_monsters.length;i++) {
			if (liste_monsters[i]!=null) {
					liste_monsters[i].update(player,map);
			}
		}
		//monster1.update(player,map);
		//monster2.update(player,map);
		//monster3.update(player,map);
		score.update_score(map);
	    vie.update_vie(map);
	    gagner.update_gagner(map);
	    
	}
	    
	    
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			map.draw(g2);
			for (int i=0;i<liste_monsters.length;i++) {
				if (liste_monsters[i]!=null) {
			liste_monsters[i].render(g2);
			//monster1.render(g2);
			//monster2.render(g2);
			//monster3.render(g2);
				}
			}
			try {
				tresor1.render(g2);
				tresor2.render(g2);
				coeur1.render(g2);
				coeur2.render(g2);
				key.render(g2);
				porte1.render(g2);
				porte2.render(g2);
				piege.render(g2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			player.render(g2);
			g2.dispose();
		
	}



		public void run() {
			requestFocus();
			double drawInterval=1000000000/FPS;
			double nextDrawTime=System.nanoTime()+drawInterval;
			
			// TODO Auto-generated method stub
			while (isRunning) {
				
				
				update();
				repaint();
				
				
				try {
					double remainingTime=nextDrawTime -System.nanoTime();
					remainingTime=remainingTime/1000000;
					if(remainingTime<0) {
						remainingTime=0;
					}
					Thread.sleep((long) remainingTime);
					nextDrawTime+=drawInterval;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	  
	  public static void main(String[] args) throws IOException {  
		  
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle(Game.TITLE);
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();

	  }
	  
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		//System.out.println("hello");
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up=true;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down=true;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.right=true;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) player.left=true;
		if(e.getKeyCode()==KeyEvent.VK_SPACE) player.attaque=true;
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.right=false;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) player.left=false;	
		//if(e.getKeyCode()==KeyEvent.VK_SPACE) player.attaque=false;
	}
}
