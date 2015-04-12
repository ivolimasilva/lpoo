package lpoo_1.logic;

import java.util.ArrayList;

public interface MazeBuilder
{
	public void generateMatrix();
	public char[][] getMatrix();
	
	public Hero getHero();
	public ArrayList<Dragon> getDragons();
	public Sword getSword();
	public Shield getShield();
	public ArrayList<Dart> getDarts();
}
