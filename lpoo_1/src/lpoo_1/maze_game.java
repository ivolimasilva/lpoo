package lpoo_1;

public class maze_game
{
	static char maze_map[][] =
	{
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
		{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
		{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
		{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' }
	};
	
	public static void main(String[] args)
	{
		printMaze();
	}
	
	public static void printMaze()
	{
		for (int i = 0; i < 10; i ++)
		{
			for (int j = 0; j < 10; j++)
				System.out.print(maze_map[i][j]);
			
			System.out.println();
		}	
	}
}