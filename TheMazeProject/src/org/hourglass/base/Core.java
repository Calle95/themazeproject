package org.hourglass.base;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Core extends Canvas
{
	private final String TITLE = "Maze Generator";

	private Cell[][] maze;

	private JFrame frame;

	public Core(int width, int height, int blockSize, long seed)
	{
		MazeGenerator.generateMaze(width, height, seed);
		maze = MazeGenerator.getMaze();

		Dimension dim = new Dimension(width * blockSize, height * blockSize);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public Core(int width, int height, int blockSize)
	{
		new Core(width, height, blockSize, 0);
	}

	public static void main(String[] args)
	{
		Window.createWindow();
	}

}
