package org.hourglass.base;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MazeWindow extends Canvas implements Runnable
{
	
	private final String TITLE = "Maze Generator";
	private boolean running;
	private boolean debug;
	private Dimension dim;
	private int gridWidth;
	private int gridHeight;
	private int blockSize;

	private Cell[][] maze;
	private Input input;
	
	
	public MazeWindow(int width, int height, int blockS, long seed, boolean debug)
	{
		if (debug)
		{
			MazeGenerator.initStepWise(width, height, seed);
			maze = MazeGenerator.getMaze();
		} else
		{
			MazeGenerator.generateMaze(width, height, seed);
			maze = MazeGenerator.getMaze();
		}

		gridWidth = width;
		gridHeight = height;
		blockSize = blockS;
		
		input = new Input();
		
		dim = new Dimension(width * blockSize, height * blockSize);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);

		JFrame frame = new JFrame(TITLE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.toFront();
		frame.setVisible(true);
		
		requestFocus();

		this.debug = debug;
		running = true;
	}

	@Override
	public void run()
	{
		while(true)
		{
			System.out.println("KILL ME!");
		}
	}
}
