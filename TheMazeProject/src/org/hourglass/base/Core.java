package org.hourglass.base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Core extends Canvas implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3787548247542872040L;
	
	private enum State
	{

		MAIN_MENU;

	};
	
	private State state;

	private static final String TITLE = "Maze Generator";
	public static final int WIDTH = 360;
	public static final int HEIGHT = 300;
	
	private static boolean running;
	private static boolean debug;
	private static Dimension dim;
	private static int gridWidth;
	private static int gridHeight;
	private static int blockSize;
	private static long seed;

	private static Cell[][] maze;
	private static Input input;
	private MainMenu menu;
	private JFrame frame;

//	private JFrame frame;

	public Core()
	{
		this.gridWidth = 60;
		this.gridHeight = 50;
		this.blockSize = 8;
		this.seed = System.currentTimeMillis();
		this.debug = false;
		
		input = new Input();
		menu = new MainMenu();
		
		state = State.MAIN_MENU;
		
		dim = new Dimension(WIDTH, HEIGHT);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		addKeyListener(input);
		addMouseListener(input);
		addMouseMotionListener(input);

		frame = new JFrame(TITLE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.toFront();
		frame.setVisible(true);
		requestFocus();

		running = true;
		
		Thread t = new Thread(this);
		t.run();
	}

	public void run()
	{
		while (running)
		{
			update();
			render();
			input.update();
		}
		
		System.exit(0);
	}
	
	public void update()
	{
		
		
		switch(state)
		{
		case MAIN_MENU:
			
			temp.setLocation(input.getPos().getX(), input.getPos().getY());
			menu.update(input);
			
			break;
		}
	}
	
	Point temp = new Point(0,0);

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

		// TODO: rendering algorithm
		
		
		switch(state)
		{
		case MAIN_MENU:
			
			menu.render(g);
			
			break;
		}

// NON-debug code
//		
//		if (!debug)
//		{
//			for (int y = 0; y < gridHeight; y++)
//			{
//				for (int x = 0; x < gridWidth; x++)
//				{
//					g.setColor(Color.WHITE);
//					g.drawRect(x * blockSize, y * blockSize, x * blockSize
//							+ blockSize, y * blockSize + blockSize);
//				}
//			}
//
//			for (int y = 0; y < gridHeight; y++)
//				for (int x = 0; x < gridWidth; x++)
//				{
//					g.setColor(Color.BLACK);
//					if ((maze[x][y].getWalls() & 0x0100) >> 2 == 0)
//					{
//						g.setColor(Color.BLACK);
//						g.drawLine(x * blockSize + 1,
//								y * blockSize + blockSize, x * blockSize
//										+ (blockSize - 1), y * blockSize
//										+ blockSize);
//					}
//
//					g.setColor(Color.BLACK);
//					if ((maze[x][y].getWalls() & 0x0010) >> 1 == 0)
//					{
//						g.setColor(Color.BLACK);
//						g.drawLine(x * blockSize, y * blockSize + 1, x
//								* blockSize, y * blockSize + (blockSize - 1));
//					}
//				}
//		}

		g.dispose();
		bs.show();
	}

	public static void main(String[] args)
	{
		new Core();
	}
}
