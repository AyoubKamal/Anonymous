import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

class Test {

	@org.junit.jupiter.api.Test
	void testX() throws IOException {
		Game game = new Game();
		assertEquals(Game.player.getX(),Game.tileSize);
	}
	@org.junit.jupiter.api.Test
	void testXbordure() throws IOException {
		Game game = new Game();
		Game.player.setX(Game.tileSize-30);
		assertEquals(Game.player.getX(),Game.tileSize);
	}
	
	@org.junit.jupiter.api.Test
	void testY() throws IOException {
		Game game = new Game();
		assertEquals(Game.player.getY(),Game.tileSize);
	}
	
	@org.junit.jupiter.api.Test
	void testYbordure() throws IOException {
		Game game = new Game();
		Game.player.setY(Game.tileSize-30);
		assertEquals(Game.player.getY(),Game.tileSize);
	}
	
	@org.junit.jupiter.api.Test
	void testVie() throws IOException {
		Game game = new Game();
		assertEquals(Game.vie.nombre_de_vie,3);
	
	}
	
	@org.junit.jupiter.api.Test
	void testViemax() throws IOException {
		Game game = new Game();
		assertTrue(Game.vie.nombre_de_vie<4);
	}
	

	@org.junit.jupiter.api.Test
	void testGagner() throws IOException {
		Game game = new Game();
		Game.player.setX(Game.key.getX());
		Game.player.setY(Game.key.getY());
		Game.gagner.update_gagner(Game.map);
		assertTrue(Game.gagner.ga==1);
	}
	
	@org.junit.jupiter.api.Test
	void Score() throws IOException {
		Game game = new Game();
		assertTrue(Game.score.getScore()==0);
	}
	
	@org.junit.jupiter.api.Test
	void UpdateScore() throws IOException {
		Game game = new Game();
		Game.player.setX(Game.liste_tresor_1[0].getX());
		Game.player.setY(Game.liste_tresor_1[0].getY());
		Game.score.update_score(Game.map);
		assertTrue(Game.score.getScore()==50);
	}
	
	
	
	@org.junit.jupiter.api.Test
	void Damage_monster1() throws IOException {
		Game game = new Game();
		Game.player.setX(Game.liste_monsters[0].getX());
		Game.player.setY(Game.liste_monsters[0].getY());
		Game.vie.update_vie(Game.map);
		assertEquals(Game.vie.nombre_de_vie,2);
	}
	
	@org.junit.jupiter.api.Test
	void Damagepiege() throws IOException {
		Game game = new Game();
		Game.player.setX(Game.liste3[0].getX());
		Game.player.setY(Game.liste3[0].getY());
		Game.vie.update_vie(Game.map);
		assertEquals(Game.vie.nombre_de_vie,2);
	}
	
	@org.junit.jupiter.api.Test
	void Damage_monster2() throws IOException {
		Game game = new Game();
		Game.player.setX(Game.liste_monsters[1].getX());
		Game.player.setY(Game.liste_monsters[1].getY());
		Game.vie.update_vie(Game.map);
		assertEquals(Game.vie.nombre_de_vie,2);
	}
	
	

}
