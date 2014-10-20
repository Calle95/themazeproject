package org.hourglass.base;

import java.util.ArrayList;

import com.hourglass.base.Core.Cell;

public class MazeGenerator
{
	private static Cell maze[][];
	
	public static void generateMaze(int x, int y, long seed)
	{
		ArrayList<Cell> stack = new ArrayList<Cell>();
		ArrayList<Cell> remainingCells = new ArrayList<Cell>();
		
		//Init array of cells
		maze = new Cell[x][y];
		
		//Reset maze
		for(int i = 0; i < x; i++)
			for(int j = 0; j < y; j++)
				maze[i][j] = new Cell(i, j);
		
		
	}
}
