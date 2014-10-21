package org.hourglass.base;

import java.util.ArrayList;
import java.util.Random;

import org.hourglass.base.Cell;
import org.hourglass.base.Utils;

public class MazeGenerator
{
	private static ArrayList<Cell> stack;
	private static ArrayList<Cell> remainingCells;
	private static Cell maze[][];
	private static Cell currentCell;
	private static int GRID_WIDTH, GRID_HEIGHT;

	static Random r;

	public static void initStepWise(int x, int y, long seed)
	{
		GRID_WIDTH = x;
		GRID_HEIGHT = y;

		stack = new ArrayList<Cell>();
		remainingCells = new ArrayList<Cell>();

		// Init array of cells
		maze = new Cell[x][y];

		// Reset maze and add cells to remainingcell list
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
			{
				maze[i][j] = new Cell(i, j);
				remainingCells.add((maze[i][j]));
			}

		// Starting the maze generating algorithm
		/*
		 * TODO: Explanation
		 */

		// STARTING CELL
		currentCell = maze[0][0];
		maze[currentCell.getX()][currentCell.getY()].visit();
		stack.add(maze[currentCell.getX()][currentCell.getY()]);
		remainingCells.remove(maze[currentCell.getX()][currentCell.getY()]);

		// Initiating random with given seed
		r = new Random(seed);
	}

	public static boolean perfomStep()
	{
		
		if (remainingCells.isEmpty())
		{
			stack.removeAll(stack);
			return false;
		}
		
		if (checkForNeighbors(currentCell.getX(), currentCell.getY()).isEmpty())
		{
			stack.remove(stack.size() - 1);
			currentCell = stack.get(stack.size() - 1);
		} else
		{

			currentCell = checkForNeighbors(currentCell.getX(), currentCell.getY()).get(Math.abs(r.nextInt()) % checkForNeighbors(currentCell.getX(), currentCell.getY()).size());
			maze[currentCell.getX()][currentCell.getY()].visit();
			remainingCells.remove(currentCell);

			maze[currentCell.getX()][currentCell.getY()].removeWall(wallToRemove(currentCell.getX(), currentCell.getY(), stack.get(stack.size() - 1).getX(), stack.get(stack.size() - 1).getY()));
			maze[stack.get(stack.size() - 1).getX()][stack.get(stack.size() - 1).getY()].removeWall(wallToRemove(stack.get(stack.size() - 1).getX(), stack.get(stack.size() - 1).getY(),
					currentCell.getX(), currentCell.getY()));

			stack.add(maze[currentCell.getX()][currentCell.getY()]);
		}

		
		
		return true;
	}

	public static void generateMaze(int x, int y, long seed)
	{
		GRID_WIDTH = x;
		GRID_HEIGHT = y;

		stack = new ArrayList<Cell>();
		remainingCells = new ArrayList<Cell>();

		// Init array of cells
		maze = new Cell[x][y];

		// Reset maze and add cells to remainingcell list
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
			{
				maze[i][j] = new Cell(i, j);
				remainingCells.add((maze[i][j]));
			}

		// Starting the maze generating algorithm
		/*
		 * TODO: Explanation
		 */

		// STARTING CELL
		currentCell = maze[0][0];
		maze[currentCell.getX()][currentCell.getY()].visit();
		stack.add(maze[currentCell.getX()][currentCell.getY()]);
		remainingCells.remove(maze[currentCell.getX()][currentCell.getY()]);

		// Initiating random with given seed
		r = new Random(seed);

		while (!remainingCells.isEmpty())
		{
			if (checkForNeighbors(currentCell.getX(), currentCell.getY()).isEmpty())
			{
				stack.remove(stack.size() - 1);
				currentCell = stack.get(stack.size() - 1);
			} else
			{

				currentCell = checkForNeighbors(currentCell.getX(), currentCell.getY()).get(Math.abs(r.nextInt()) % checkForNeighbors(currentCell.getX(), currentCell.getY()).size());
				maze[currentCell.getX()][currentCell.getY()].visit();
				remainingCells.remove(currentCell);

				maze[currentCell.getX()][currentCell.getY()].removeWall(wallToRemove(currentCell.getX(), currentCell.getY(), stack.get(stack.size() - 1).getX(), stack.get(stack.size() - 1).getY()));
				maze[stack.get(stack.size() - 1).getX()][stack.get(stack.size() - 1).getY()].removeWall(wallToRemove(stack.get(stack.size() - 1).getX(), stack.get(stack.size() - 1).getY(),
						currentCell.getX(), currentCell.getY()));

				stack.add(maze[currentCell.getX()][currentCell.getY()]);
			}
		}

		if (remainingCells.isEmpty())
		{
			stack.removeAll(stack);
		}
	}

	private static int wallToRemove(int sq1x, int sq1y, int sq2x, int sq2y)
	{
		if (sq1x < sq2x)
			return Utils.RIGHT;
		else if (sq1x > sq2x)
			return Utils.LEFT;
		else if (sq1y < sq2y)
			return Utils.DOWN;
		else if (sq1y > sq2y)
			return Utils.UP;
		else
			return 0;
	}

	private static ArrayList<Cell> checkForNeighbors(int x, int y)
	{
		ArrayList<Cell> res = new ArrayList<Cell>();

		// UP DOWN LEFT RIGHT

		// UP
		if (!((y - 1) <= -1))
		{
			if (!maze[x][y - 1].isVisited())
			{
				res.add(maze[x][y - 1]);
			}
		}

		// DOWN
		if (!((y + 1) >= GRID_HEIGHT))
		{
			if (!maze[x][y + 1].isVisited())
			{
				res.add(maze[x][y + 1]);
			}
		}

		// LEFT
		if (!((x - 1) <= -1))
		{
			if (!maze[x - 1][y].isVisited())
			{
				res.add(maze[x - 1][y]);
			}
		}

		// RIGHT
		if (!((x + 1) >= GRID_WIDTH))
		{
			if (!maze[x + 1][y].isVisited())
			{
				res.add(maze[x + 1][y]);
			}
		}

		return res;
	}

	public static Cell[][] getMaze()
	{
		return maze;
	}

	public static ArrayList<Cell> getStack()
	{
		return stack;
	}

	public static void setStack(ArrayList<Cell> stack)
	{
		MazeGenerator.stack = stack;
	}

	public static ArrayList<Cell> getRemainingCells()
	{
		return remainingCells;
	}

	public static void setRemainingCells(ArrayList<Cell> remainingCells)
	{
		MazeGenerator.remainingCells = remainingCells;
	}

	public static Cell getCurrentCell()
	{
		return currentCell;
	}

	public static void setCurrentCell(Cell currentCell)
	{
		MazeGenerator.currentCell = currentCell;
	}
}
