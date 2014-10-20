package org.hourglass.base;

public class Cell
{
	private boolean visited;
	private int walls;

	private int x;
	private int y;

	public Cell(int x, int y)
	{
		visited = false;
		walls = 0x1111;

		this.x = x;
		this.y = y;
	}

	public void visit()
	{
		visited = true;
	}

	public boolean isVisited()
	{
		return visited;
	}

	public void removeWall(int wallToRemove)
	{
		this.walls = this.walls - wallToRemove;
	}

	public int getWalls()
	{
		return walls;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
}
