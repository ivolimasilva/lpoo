package lpoo_1.test;

import static org.junit.Assert.*;
import lpoo_1.logic.*;
import org.junit.Before;
import org.junit.Test;

public class LabirintoTest
{
	
	Game newGame;
	
	char[][] lab;
	
	@Before
	public void setup()
	{
		newGame = new Game(10,false);
		lab = newGame.getMatrix();
	}
	
	@Test
	public void testMoveHero()
	{
		newGame.moveHero('w');
		assertEquals(1, newGame.getHero().getPosX());
		assertEquals(1, newGame.getHero().getPosY());
		
		newGame.moveHero('a');
		assertEquals(1, newGame.getHero().getPosX());
		assertEquals(1, newGame.getHero().getPosY());
		
		newGame.moveHero('d');
		newGame.moveHero('d');
		assertEquals(true, newGame.getHero().isShielded());

		newGame.moveHero('a');
		newGame.moveHero('a');
		newGame.moveHero('s');
		assertEquals(2, newGame.getHero().getPosX());
		assertEquals(1, newGame.getHero().getPosY());
		
		newGame.moveHero('d');
		assertEquals(2, newGame.getHero().getPosX());
		assertEquals(1, newGame.getHero().getPosY());
		
		newGame.moveHero('s');
		assertEquals(1, newGame.getHero().hasDarts());
		
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		assertEquals(Hero.HeroStates.ARMED, newGame.getHero().getState());

		newGame.moveHero('w');
		newGame.moveHero('w');
		newGame.moveHero('w');
		newGame.moveHero('d');
		assertEquals(Hero.HeroStates.ARMED, newGame.getHero().getState());
		assertEquals(Dragon.DragonStates.DEAD, newGame.getDragons().get(0).getState());
		
		
		newGame.moveHero('a');
		newGame.moveHero('w');
		newGame.moveHero('w');
		newGame.moveHero('w');
		newGame.moveHero('w');
		newGame.moveHero('d');
		newGame.moveHero('d');
		newGame.moveHero('d');
		newGame.moveHero('d');
		assertEquals(2, newGame.getHero().hasDarts());
		
		newGame.moveHero('d');
		newGame.moveHero('d');
		newGame.moveHero('d');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('d');
		newGame.printMatrix();
		assertEquals(Hero.HeroStates.WINNER, newGame.getHero().getState());
	}	
	
	@Test
	public void testDragon()
	{
		assertEquals(2, newGame.getDragons().size());
		
		for (Dragon dragon: newGame.getDragons())
		{
			if (dragon.getPosX() == 7 && dragon.getPosY() == 6)
			{
				assertEquals(Dragon.DragonStates.SLEEP, dragon.getState());
			}
			else if (dragon.getPosX() == 5 && dragon.getPosY() == 3)
			{
				assertEquals(Dragon.DragonStates.NORMAL, dragon.getState());
			}
		}
		
		assertEquals (5, newGame.getDragons().get(0).getPosX());
		assertEquals (3, newGame.getDragons().get(0).getPosY());
		
		assertEquals (7, newGame.getDragons().get(1).getPosX());
		assertEquals (6, newGame.getDragons().get(1).getPosY());
		
	}
	
	@Test
	public void testSword()
	{
		assertEquals(false, newGame.getSword().wasGrabbed());
		
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		newGame.moveHero('s');
		assertEquals(true, newGame.getSword().wasGrabbed());

		assertEquals(8, newGame.getSword().getPosX());
		assertEquals(1, newGame.getSword().getPosY());
	}
	
	@Test
	public void testShield()
	{
		assertEquals(false, newGame.getShield().wasGrabbed());
		
		newGame.moveHero('d');
		newGame.moveHero('d');
		assertEquals(true, newGame.getShield().wasGrabbed());
		
		assertEquals(1, newGame.getShield().getPosX());
		assertEquals(3, newGame.getShield().getPosY());
	}

	@Test
	public void testDart()
	{
		assertEquals(2, newGame.getDarts().size());
		assertEquals(0, newGame.getHero().hasDarts());
		
		
		for(Dart darts : newGame.getDarts()){
			if (darts.getPosX() == 2 && darts.getPosY() == 1)
			{
				assertEquals(false, darts.wasGrabbed());	
			}
			else if (darts.getPosX() == 1 && darts.getPosY() == 5) 
				assertEquals(false, darts.wasGrabbed());
		}
		
		newGame.moveHero('s');
		newGame.moveHero('s');
		for(Dart darts : newGame.getDarts()){
			if (darts.getPosX() == 2 && darts.getPosY() == 1)
			{
				assertEquals(true, darts.wasGrabbed());	
			}
			else if (darts.getPosX() == 1 && darts.getPosY() == 5) 
				assertEquals(false, darts.wasGrabbed());
		}
		assertEquals(1, newGame.getHero().hasDarts());
		
		newGame.moveHero('w');
		newGame.moveHero('w');
		newGame.moveHero('d');
		newGame.moveHero('d');
		newGame.moveHero('d');
		newGame.moveHero('d');
		for(Dart darts: newGame.getDarts()){
			if(darts.getPosX() == 1 && darts.getPosY() == 5)
			{
				assertEquals(true, darts.wasGrabbed());
			}
		}
		assertEquals(2, newGame.getHero().hasDarts());
	}
}


