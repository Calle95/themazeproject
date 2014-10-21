package org.hourglass.base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Core extends Canvas implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3787548247542872040L;

	private static final String TITLE = "Maze Generator";
	private static boolean running;
	private static boolean debug;
	private static Dimension dim;
	private static int gridWidth;
	private static int gridHeight;
	private static int blockSize;

	private static Cell[][] maze;
	private static Input input;

//	private JFrame frame;

	public Core(int width, int height, int blockSize, long seed, boolean debug)
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

		this.gridWidth = width;
		this.gridHeight = height;
		this.blockSize = blockSize;
		
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
		
		Thread t = new Thread(this);
		t.run();
	}

	public Core(int width, int height, int blockSize)
	{
		new Core(width, height, blockSize, 0, false);
	}

	public void run()
	{
		while (running)
		{
			if (debug)
			{
				while (MazeGenerator.perfomStep())
				{
					maze = MazeGenerator.getMaze();
//					try
//					{
//						Thread.sleep(50);
//					} catch (InterruptedException e)
//					{
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					render();
				}

			}

			render();
			update();
		}
		
		System.exit(0);
	}
	
	public void update()
	{
		input.update();
		input.printMousePos();
//		if(input.keyPressed(KeyBoa);)
	}

	public void render()
	{
		BufferStrategy bs = getBufferStrategy();

		if (bs == null)
		{
			createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

		// TODO: rendering mechanic

		// NON-debug code
//		if (!debug)
		{
			for (int y = 0; y < gridHeight; y++)
			{
				for (int x = 0; x < gridWidth; x++)
				{
					g.setColor(Color.WHITE);
					g.drawRect(x * blockSize, y * blockSize, x * blockSize
							+ blockSize, y * blockSize + blockSize);
				}
			}

			for (int y = 0; y < gridHeight; y++)
				for (int x = 0; x < gridWidth; x++)
				{
					g.setColor(Color.BLACK);
					if ((maze[x][y].getWalls() & 0x0100) >> 2 == 0)
					{
						g.setColor(Color.BLACK);
						g.drawLine(x * blockSize + 1,
								y * blockSize + blockSize, x * blockSize
										+ (blockSize - 1), y * blockSize
										+ blockSize);
					}

					g.setColor(Color.BLACK);
					if ((maze[x][y].getWalls() & 0x0010) >> 1 == 0)
					{
						g.setColor(Color.BLACK);
						g.drawLine(x * blockSize, y * blockSize + 1, x
								* blockSize, y * blockSize + (blockSize - 1));
					}
				}
		}

		g.dispose();
		bs.show();
	}

	public static void main(String[] args)
	{
//		Window.createWindow();
		new Core(60, 50, 8, 1289312983, false);
	}
}
