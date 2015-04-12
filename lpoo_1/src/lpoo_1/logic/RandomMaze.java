package lpoo_1.logic;

import java.util.ArrayList;
import lpoo_1.logic.Dragon.DragonStates;

public class RandomMaze implements MazeBuilder
{
	private char[][] matrix;
	private int size;
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	public void generateMatrix()
	{
		for (int i = 0; i < this.size; i++)
			for (int j = 0; j < this.size; j++)
				matrix[i][j] = ' ';
		
		Dragon dragon_aux = new Dragon(8, 3, DragonStates.NORMAL);
		dragons.add(dragon_aux);
		
		dragon_aux = new Dragon(3, 6, DragonStates.SLEEPONSHIELD);
		dragons.add(dragon_aux);
	}
	
	public char[][] getMatrix()
	{
		return matrix;
	}

	@Override
	public Hero getHero() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sword getSword() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Dragon> getDragons()
	{
		return dragons;
	}

	@Override
	public Shield getShield() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Dart> getDarts() {
		// TODO Auto-generated method stub
		return null;
	}
}
