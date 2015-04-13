package lpoo_1.logic;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

import lpoo_1.logic.Dragon.DragonStates;

/**
 * RandomMaze.java - generates a random maze with a given dimension
 * @author Mariana and Ivo
 *
 */
public class RandomMaze implements MazeBuilder, Serializable
{
	private static final long serialVersionUID = 1L;
	private char matrix[][];
	private char helpingBoard[][];
	private int size;
	private Deque<Position> path;
	private Position pos;
	private ArrayList<Dart> dardos = new ArrayList<Dart>();
	private Shield escudo;
	private Hero homem = new Hero(-1,-1);
	private Sword espada = new Sword(-1,-1);
	
	private ArrayList<Dragon> dragons = new ArrayList<Dragon>();
	
	/**
	 * Constructor of the random maze
	 * @param size - dimension of the maze
	 */
	public RandomMaze (int size)
	{
		this.size = size;
		
		generateMatrix();
	}
	
	/**
	 * Returns the matrix
	 * @return A char[][] data type
	 */
	public char[][] getMatrix()
	{
		return matrix;
	}

	/**
	 * Returns homem
	 * @return Hero data type
	 */
	public Hero getHero()
	{
		return homem;
	}

	/**
	 * Returns espada
	 * @return A Sword data type 
	 */
	public Sword getSword()
	{
		return espada;
	}
	
	/**
	 * Returns dragons
	 * @return An ArrayList<Dragon> data type
	 */
	public ArrayList<Dragon> getDragons()
	{
		return dragons;
	}

	/**
	 * Returns the shield
	 * @return A Shield data type
	 */
	public Shield getShield()
	{
		return escudo;
	}

	/**
	 * Returns dardos
	 * @return An ArrayList<Dart> data type
	 */
	public ArrayList<Dart> getDarts()
	{
		return dardos;
	}
	
	/**
	 * Creates a random matrix with all its elements
	 */
	public void generateMatrix ()
	{
		Random MyRandom = new Random();
		int numdragons = MyRandom.nextInt(4) + 1;
		escudo = new Shield(-1,-1);
		for (int i = 0; i < numdragons; i++)
		{
			dragons.add(new Dragon(-1, -1, DragonStates.SLEEP));
			dardos.add(new Dart(-1, -1));
		}

		//this.size = size;
		matrix = new char[size][size];
		helpingBoard = new char[(size - 1) / 2][(size - 1) / 2];
		path = new ArrayDeque<Position>();
		
		//Filling helpingBoard
		for(int xi = 0; xi < (size - 1) / 2; xi++)
			for(int yi = 0; yi < (size - 1) / 2; yi++)
				helpingBoard[xi][yi] = '.';

		//Filling board
		for(int xi = 0; xi < size; xi++)
			for(int yi = 0; yi < size; yi++)
				if(isOnHelpingBoard(xi,yi))
					matrix[xi][yi] = ' ';
				else
					matrix[xi][yi] = 'X';

		//set exit
		int startX = 0, startY = 0;
		switch((int)(Math.random()*4)){
		case 0: // Left wall
			Random random = new Random();
			startX = 0;
			startY = random.nextInt(((size-1)/2) - 1) + 1;
			matrix[0][startY*2+1] = 'S';
			break;
		case 1: // Right wall
			Random random2 = new Random();
			startX = (size-1)/2-1;
			startY = random2.nextInt(((size-1)/2) - 1) + 1;
			matrix[size-1][startY*2+1] = 'S';
			break;
		case 2: // Up wall
			Random random3 = new Random();
			startX = random3.nextInt(((size-1)/2) - 1) + 1;
			startY = 0;
			matrix[startX*2+1][0] = 'S';
			break;
		case 3: // Down wall
			Random random4 = new Random();
			startX = random4.nextInt(((size-1)/2) - 1) + 1;
			startY = (size-1)/2-1;
			matrix[startX*2+1][size-1] = 'S';
			break;
		}
		pos = new Position(startX*2+1,startY*2+1);
		path.push(new Position(startX,startY));
		helpingBoard[startX][startY] = '+';
		int first_time = 0;
		while(!path.isEmpty()){
			pos.setX(path.peek().getX() * 2 + 1);
			pos.setY(path.peek().getY() * 2 + 1);
			if(!hasNeighbors(pos.getX(),pos.getY())){
				if(first_time == 0){ 
					homem.changePos(pos.getX(), pos.getY());
				}

				path.pop();
				first_time++;
			}

			switch((int)(Math.random()*4)){
			//GOING UP
			case 0:
				if(!isBorder(pos.getX(),pos.getY() - 1) && !wasVisited(pos.getX(),pos.getY() - 2)){
					matrix[pos.getX()][pos.getY() - 1] = ' ';
					matrix[pos.getX()][pos.getY() - 2] = ' ';
					pos.setY(pos.getY() - 2);
					path.push(new Position((pos.getX() - 1) / 2,(pos.getY() - 1)/2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1)/2] = '+';
				}
				break;
				//GOING DOWN
			case 1:
				if(!isBorder(pos.getX(),pos.getY() + 1) && !wasVisited(pos.getX(),pos.getY() + 2)){
					matrix[pos.getX()][pos.getY() + 1] = ' ';
					matrix[pos.getX()][pos.getY() + 2] = ' ';
					pos.setY(pos.getY() + 2);
					path.push(new Position((pos.getX() - 1) / 2,(pos.getY() - 1)/2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1)/2] = '+';
				}
				break;
				//GOING LEFT
			case 2:
				if(!isBorder(pos.getX() - 1,pos.getY()) && !wasVisited(pos.getX() - 2,pos.getY())){
					matrix[pos.getX() - 1][pos.getY()] = ' ';
					matrix[pos.getX() - 2][pos.getY()] = ' ';
					pos.setX(pos.getX() - 2);
					path.push(new Position((pos.getX() - 1) / 2,(pos.getY() - 1)/2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1)/2] = '+';
				}
				break;
				//GOING RIGHT
			case 3:
				if(!isBorder(pos.getX() + 1,pos.getY()) && !wasVisited(pos.getX() + 2,pos.getY())){
					matrix[pos.getX() + 1][pos.getY()] = ' ';
					matrix[pos.getX() + 2][pos.getY()] = ' ';
					pos.setX(pos.getX() + 2);
					path.push(new Position((pos.getX() - 1) / 2,(pos.getY() - 1)/2));
					helpingBoard[(pos.getX() - 1) / 2][(pos.getY() - 1)/2] = '+';
				}
				break;
			}
			//Gera posicao aleatória do dragao e da espada

			while (samePos(numdragons))
			{
				int x = (int) (Math.random()*((size-1)/2));
				int y = (int) (Math.random()*((size-1)/2));
				espada.setPosX(x*2+1);
				espada.setPosY(y*2+1);

				x = (int) (Math.random()*((size-1)/2));
				y = (int) (Math.random()*((size-1)/2));
				escudo.setPosX(x*2+1);
				escudo.setPosY(y*2+1);

				for (int i = 0; i < numdragons; i++)
				{
					x = (int) (Math.random()*((size-1)/2));
					y = (int) (Math.random()*((size-1)/2));
					dragons.get(i).changePos(x*2 +1, y*2 +1);
				}

				for (int i = 0; i < numdragons; i++)
				{
					x = (int) (Math.random()*((size-1)/2));
					y = (int) (Math.random()*((size-1)/2));
					dardos.get(i).setPosX(x*2 +1);
					dardos.get(i).setPosY(y*2 +1);
				}
			}

		}
		System.out.println("Número de dragões: " + dragons.size() +"\n");
	}
	
	/**
	 * Verifies if a certain number is in the matrix
	 * Returns True if it is or False otherwise
	 * @param x
	 * @param y
	 * @return A boolean data type
	 */
	private boolean isOnHelpingBoard(int x, int y)
	{
		for(int xi = 1; xi < size; xi = xi + 2)
		{
			if(xi == x)
			{
				for(int yi = 1; yi < size; yi = yi + 2)
				{
					if(yi == y)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Verifies if the coordinates given represent a border
	 * Returns True if it is or False otherwise
	 * @param Horizontal
	 * @param Vertical
	 * @return A boolean data type
	 */
	public boolean isBorder(int Horizontal, int Vertical)
	{
		if (Horizontal == 0)
		{
			return true;
		}
		else if (Horizontal == size - 1)
		{
			return true;
		}
		else if (Vertical == 0)
		{
			return true;
		}
		else if (Vertical == size - 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Verifies the existence of adjacent cells
	 * Returns True if there is or False if there isn't 
	 * @param x
	 * @param y
	 * @return A boolean data type
	 */
	public boolean hasNeighbors(int x, int y)
	{
		x = (x - 1) / 2;
		y = (y - 1) / 2;
		if(x != 0)
			if(helpingBoard[x - 1][y] == '.')
				return true;

		if(y != 0)
			if(helpingBoard[x][y - 1] == '.')
				return true;

		if(x != ((size - 1) / 2) - 1)
			if(helpingBoard[x + 1][y] == '.')
				return true;

		if(y != ((size - 1) / 2) - 1)
			if(helpingBoard[x][y + 1] == '.')
				return true;

		return false;
	}

	/**
	 * Verifies if an element has the same position as another element
	 * Returns True if there is or False otherwise
	 * @param nrdragons
	 * @return
	 */
	boolean samePos(int nrdragons)
	{
		for (int i = 0; i < nrdragons;i++)
		{
			for (int j = 0; j < nrdragons; j++)
			{
				if (i != j)
				{
					if (dardos.get(i).getPosX() == dardos.get(j).getPosX() && dardos.get(i).getPosY() == dardos.get(j).getPosY())
					{
						return true;
					}
				}

			}
		}
		for (int i = 0; i < nrdragons; i++)
		{
			if (dardos.get(i).getPosX() == espada.getPosX() && dardos.get(i).getPosY() == espada.getPosY())
			{
				return true;
			}
		}
		for (int i = 0; i < nrdragons; i++)
		{
			if (dardos.get(i).getPosX() == homem.getPosX() && dardos.get(i).getPosY() == homem.getPosY())
			{
				return true;
			}
		}
		for (int i = 0; i < nrdragons;i++)
		{
			for (int j = 0; j < nrdragons; j++)
			{
				if (dardos.get(i).getPosX() == dragons.get(j).getPosX() && dardos.get(i).getPosY() == dragons.get(j).getPosY())
				{
					return true;
				}
			}
		}	

		for (int i = 0; i < nrdragons;i++)
		{
			for (int j = 0; j < nrdragons; j++)
			{
				if (i != j)
				{
					if (dragons.get(i).getPosX() == dragons.get(j).getPosX() && dragons.get(j).getPosY() == dragons.get(j).getPosY())
					{
						return true;
					}
				}

			}
		}
		for (int i = 0; i < nrdragons; i++)
		{
			if (dragons.get(i).getPosX() == espada.getPosX() && dragons.get(i).getPosY() == espada.getPosY())
			{
				return true;
			}
		}
		for (int i = 0; i < nrdragons; i++)
		{
			if (dragons.get(i).getPosX() == homem.getPosX() && dragons.get(i).getPosY() == homem.getPosY())
			{
				return true;
			}
		}

		if (espada.getPosX() == homem.getPosX() && espada.getPosY() == homem.getPosY())
			return true;


		if (escudo.getPosX() == homem.getPosX() && escudo.getPosY() == homem.getPosY())
			return true;

		if (espada.getPosX() == escudo.getPosX() && espada.getPosY() == escudo.getPosY())
			return true;

		for (int i = 0; i < nrdragons; i++)
		{
			if (dragons.get(i).getPosX() == escudo.getPosX() && dragons.get(i).getPosY() == escudo.getPosY())
			{
				return true;
			}
		}

		for (int i = 0; i < nrdragons; i++)
		{
			if (dardos.get(i).getPosX() == escudo.getPosX() && dardos.get(i).getPosY() == escudo.getPosY())
			{
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Verifies if the parameters were already visited by the random generator
	 * Returns True if it was or False otherwise 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean wasVisited(int x,int y)
	{
		if(x <= 0 || y <= 0 || y > size || x > size)
		{
			return true;
		}
		x = (x - 1) / 2;
		y = (y - 1) / 2;

		if( helpingBoard[x][y] == '+')
			return true;
		else
			return false;
	}
}
