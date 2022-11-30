import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Game extends Canvas implements Runnable,KeyListener {
	public boolean isRunning = false;

	public static final int WIDTH = 1500, HEIGHT = 690;
	public static final String TITLE = "ayan-man";
	private Thread thread; //SUBPROCESS to do multiple things
	public static Ayanman player;
	public static Monster monster1;
	public static Monster monster2;
	public static imageHero i;
	public static imageHero qw;
	public static imageHero qs;
	public static imageHero qa;
	public static imageMonster o;
	public static Labyrinthe map;
	public static Tresor tresor1;
	public static Tresor tresor2;
	public static score score ;
	public static Tresor [] liste_t;

	public Game() throws IOException {
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);
		map=new Labyrinthe("Laby.txt");
		addKeyListener(this);
		player =new Ayanman(0,0);
		monster1=new Monster(3,4);
		monster2=new Monster(4,8);

		tresor1=new Tresor(7,14,map);
		tresor2=new Tresor(9,13,map);
		liste_t =new Tresor [2];
		liste_t[0]=tresor1;
		liste_t[1]=tresor2;
		i=new imageHero("/HeroA.png");
		qw=new imageHero("/HeroAA.png");
		qs=new imageHero("/HeroAAA.png");
		qa=new imageHero("/HeroAAAA.png");
		o=new imageMonster("/Ac.png");
		score =new score(liste_t);
	}

	
	

	public synchronized void start() { //synchronized sert a ne pas perdre le thread en executant
		if (isRunning)
			return;
		    isRunning = true;
		   thread = new Thread(this);
		  thread.start();
	}

	public synchronized void stop() {
		  if (!isRunning)
			return;
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	    private void update() throws IOException, InterruptedException{
	
		player.update();
		monster1.update(map);
		monster2.update(map);
		score.update_score(map);
	    

	}

	private void render() throws IOException {
		BufferStrategy bs= getBufferStrategy(); //temporary place of storage "next step"
		if(bs==null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g=bs.getDrawGraphics();
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
	}

	  public void run() {
		requestFocus();
		int fps=0;
		double timer=System.currentTimeMillis();
		long lastTime=System.nanoTime();
		double targetTick=60.0;
		double delta=0;
		double ns=1000000000/targetTick;
		while (isRunning) {
			long now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			
			while(delta>=1) {
				
				try {
					update();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					render();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				fps++;
				delta--;
			}
			
			if(System.currentTimeMillis()-timer>=1000) {
				System.out.println(fps);
				fps=0;
				timer+=1000;
				
			}
		}
		stop();
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
