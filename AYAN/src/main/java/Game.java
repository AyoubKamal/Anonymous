import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Game extends JPanel implements Runnable,KeyListener {
	public static boolean isRunning = false;
	public static final String TITLE = "Ayan-man";
	public static final int Lev=1;
	public final static int originalTileSize = getOriginalTileSize();
	public final static int echelle = getEchelle();
	public final static int tileSize = originalTileSize * echelle;
	public static int Nb_col=getCol();
	public static int Nb_row=getligne();
	public static int WIDTH = Nb_col*tileSize, HEIGHT = Nb_row*tileSize;
	public int FPS =60;
	
	
	Thread thread; //SUBPROCESS to do multiple things 
	public static Ayanman player;
	public static Labyrinthe map;
	public static Tresor tresor1;
	public static Tresor tresor2;
	public static Tresor coeur1;
	public static Tresor coeur2;
	public static Tresor key;
	public static score score ;
	public static Vie vie;
	public static Magic magic;
	public static Gagner gagner;
	public static level Level;
	public static Tresor [] liste_t;
	public static Tresor [] liste_t1;
	public static Monster[] liste_monsters;
	public static Porte porte1,porte2;
	public static Piege piege;
	public static Piege [] liste3;
	static JFrame frame = new JFrame();
	

	
	public static int  getCol() {
		Level=new level (Lev);
		return Level.col;
	}
	public static int getligne() {
		Level=new level (Lev);
		return Level.ligne;
	}
	
	public static int getOriginalTileSize() {
		Level=new level (Lev);
		return Level.OriginalTileSize;
	}
	public static int getEchelle() {
		Level=new level (Lev);
		return Level.echelle;
		}
	public Game() throws IOException {
		
		Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);
		this.setMaximumSize(dimension);
		this.addKeyListener(this);
		this.setFocusable(true);
		
		/*monster1=new Monster(80,80,1);
		monster2=new Monster(150,250,2);
		monster3=new Monster(150,250,3);*/
		Level=new level(Lev);
		map=Level.map;
		player =new Ayanman(tileSize,tileSize,map);
		liste_monsters=Level.generateMonsters(map);
		/*liste_monsters[0]=monster1;
		liste_monsters[1]=monster2;
		liste_monsters[2]=monster3;*/
		porte1=new Porte(4,17,map,1);
		porte2=new Porte(6,5,map,2);
		piege =new Piege(2,17,map);
		liste3 =new Piege[1];
		liste3[0]=piege;
		
		magic = new Magic(5,5,map);
		
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
		
		
		
		
		
		
		//Level.generateLaby();
		
		

		

		//KeyHandler keyH = new KeyHandler(
		
		
		//score: coeur : gagner
		
		
		
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
				score.drawScore(g2);
				magic.render(g2);
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
			ImageIcon img=new ImageIcon("back.png");
			JLabel back;
			JFrame frame = new JFrame();
			frame.setTitle(Game.TITLE);
			frame.add(game);
			frame.setResizable(false);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			JMenuBar m=new JMenuBar();
			JPanel pan=new JPanel();
			frame.setContentPane(pan);
			pan.setLayout(null);
			pan.add(m);
			m.setBounds(0, 0, Game.WIDTH, 70);
			JMenu menu=new JMenu("Game");
			menu.setFont(new Font("Arial",Font.PLAIN,50));
			m.add(menu);
			JMenuItem start=new JMenuItem("START");
			start.setFont(new Font("Arial",Font.PLAIN,25));
			JMenuItem quitter=new JMenuItem("QUITTER");
			quitter.setFont(new Font("Arial",Font.PLAIN,25));
			menu.setPreferredSize(new Dimension(200,200));
			menu.add(start);
			menu.add(quitter);
			back=new JLabel("",img,JLabel.CENTER);
			back.setBounds(0, 0,Game.WIDTH , Game.HEIGHT);
			frame.add(back);
			frame.setVisible(true);
			start.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					frame.add(game);
					game.start();
				}
			});
			quitter.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			
			frame.setVisible(true);
			

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
