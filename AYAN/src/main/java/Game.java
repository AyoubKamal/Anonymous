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
	public boolean isRunning = false;
	public static final String TITLE = "Ayan-man";

	public final static int originalTileSize = 16;
	public final static int echelle = 3;
	public final static int tileSize = originalTileSize * echelle;
	public final static int Nb_col =26,Nb_row=12;
	public static final int WIDTH = Nb_col*tileSize, HEIGHT = Nb_row*tileSize;
	public int FPS =60;
	
	
	Thread thread; //SUBPROCESS to do multiple things
	public static Ayanman player;
	public static Monster monster1;
	public static Monster monster2;
	public static Labyrinthe map;
	public static Tresor tresor1;
	public static Tresor tresor2;
	public static score score ;
	public static Tresor [] liste_t;


	public Game() throws IOException {
		
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);
		this.setMaximumSize(dimension);
		this.addKeyListener(this);
		this.setFocusable(true);
		//addKeyListener(this);
		map=new Labyrinthe("Laby.txt");
		
		player =new Ayanman(0,0);
		monster1=new Monster(20,30);
		monster2=new Monster(0,0);
		monster2.setSpeed(2);
		//KeyHandler keyH = new KeyHandler();
		
		tresor1=new Tresor(7,14,map);
		tresor2=new Tresor(9,13,map);
		liste_t =new Tresor [2];
		liste_t[0]=tresor1;
		liste_t[1]=tresor2;
		//i=new imageHero("/HeroA.png");
		//qw=new imageHero("/HeroAA.png");
		//qs=new imageHero("/HeroAAA.png");
		//qa=new imageHero("/HeroAAAA.png");
		//o=new imageMonster("/Ac.png");
		//score =new score(liste_t);
	}

	
	

	public  void start() { //synchronized sert a ne pas perdre le thread en executant
		if(isRunning) {
			return;
			}
			isRunning=true;
			thread = new Thread(this);
			thread.start();
	}

	/*public synchronized void stop() {
		  if (!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/

	    private void update() {
	
		player.update(map);
		monster1.updateIntelligente(player,map);
		monster2.update(map);
		//score.update_score(map);
	    

	}
	    
	    
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			map.draw(g2);
			player.render(g2);
			monster1.render(g2);
			monster2.render(g2);
			g2.dispose();
		
	}

/*	private void render() throws IOException {
		BufferStrategy bs= getBufferStrategy(); //temporary place of storage "next step"
		if(bs==null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		map.draw(g2);
		player.draw(g2);
		g2.dispose();
	
		g.setColor(Color.white);
		g.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
		map.draw(g);
		player.render(g);
		monster1.render(g);
		monster2.render(g);
		//tresor1.render("images/heart_2.png",g);
		tresor1.annuler(map, player);
		tresor2.annuler(map, player);
		//score.render(g);
	

		g.dispose();
		bs.show();
	}*/

		public void run() {
			requestFocus();
			double drawInterval=1000000000/FPS;
			double nextDrawTime=System.nanoTime()+drawInterval;
			
			// TODO Auto-generated method stub
			while (thread != null) {
				
				
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
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up=false;
		if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down=false;
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) player.right=false;
		if(e.getKeyCode()==KeyEvent.VK_LEFT) player.left=false;	
	}
}
