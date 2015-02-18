package org.hourglass.base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.hourglass.gui.LblValue;
import org.hourglass.gui.MainMenu;

public class Core extends Canvas implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3787548247542872040L;

	private enum State
	{

		MAIN_MENU, MAZE_SETUP, MAZE_LOOP;

	};

	private State state;

	private static final String TITLE = "Maze Generator";
	public static final int WIDTH = 360;
	public static final int HEIGHT = 300;

	private static boolean running;
	private static boolean debug;
	private static Dimension dim;
	private static Dimension dimWindow;
	private static int gridWidth;
	private static int gridHeight;
	private static int blockSize;
	private static long seed;

	private static int delay;
	private static long timer;

	private static Cell[][] maze;
	private static Input input;
	private MainMenu menu;
	private JFrame frame;

	// private JFrame frame;

	public Core()
	{
		gridWidth = 60;
		gridHeight = 50;
		blockSize = 8;
		seed = System.currentTimeMillis();
		debug = false;
		delay = 100;
		timer = System.currentTimeMillis();

		input = new Input();
		menu = new MainMenu();

		state = State.MAIN_MENU;

		dim = new Dimension(WIDTH, HEIGHT);
		dimWindow = new Dimension(WIDTH, HEIGHT);
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

		switch (state)
		{
		case MAIN_MENU:

			temp.setLocation(input.getPos().getX(), input.getPos().getY());
			menu.update(input);

			if (menu.isReadyToClose())
			{
				ArrayList<LblValue> values = menu.getValues();

				for (int i = 0; i < values.size() - 1; i++)
				{
					if (values.get(i).getLabel().equals("width"))
						gridWidth = (int) values.get(i).getValue();

					if (values.get(i).getLabel().equals("height"))
						gridHeight = (int) values.get(i).getValue();

					if (values.get(i).getLabel().equals("blocksize"))
						blockSize = (int) values.get(i).getValue();

					if (values.get(i).getLabel().equals("seed"))
						seed = values.get(i).getValue();
				}

				state = State.MAZE_SETUP;
			}

			break;

		case MAZE_SETUP:

			dim = new Dimension(gridWidth * blockSize, gridHeight * blockSize);
			dimWindow = new Dimension(gridWidth * blockSize, gridHeight * blockSize + 40);
			setPreferredSize(dimWindow);
			setMinimumSize(dimWindow);
			setMaximumSize(dimWindow);
			frame.pack();
			frame.setLocationRelativeTo(null);

			MazeGenerator.initStepWise(gridWidth, gridHeight, seed);
			maze = MazeGenerator.getMaze();

			state = State.MAZE_LOOP;

			break;

		case MAZE_LOOP:

			if (timer <= System.currentTimeMillis())
			{
				if (!MazeGenerator.getRemainingCells().isEmpty())
				{
					MazeGenerator.perfomStep();
					maze = MazeGenerator.getMaze();
				}
				
				timer = System.currentTimeMillis() + delay;
			}
			
			if (input.getKeyDown(107))
			{
				delay += 10;
			}

			if (input.getKeyDown(109))
			{
				delay -= 10;
				if(delay < 0)
					delay = 0;
			}
			
			break;
		}
	}

	Point temp = new Point(0, 0);

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
		g.fillRect(0, 0, (int) dimWindow.getWidth(), (int) dimWindow.getHeight());

		// TODO: rendering algorithm

		switch (state)
		{
		case MAIN_MENU:

			menu.render(g);

			break;

		case MAZE_SETUP:

			break;

		case MAZE_LOOP:

			g.setColor(Color.WHITE);

			if (gridWidth >= gridHeight)
				for (int i = 0; i < gridWidth; i++)
				{
					g.drawLine(0, i * blockSize, (int) dim.getWidth(), i * blockSize);
					g.drawLine(i * blockSize, 0, i * blockSize, (int) dim.getHeight());
				}
			else
				for (int i = 0; i < gridHeight; i++)
				{
					g.drawLine(0, i * blockSize, (int) dim.getWidth(), i * blockSize);
					g.drawLine(i * blockSize, 0, i * blockSize, (int) dim.getHeight());
				}

			g.setColor(Color.BLACK);

			for (int i = 0; i < MazeGenerator.getVisitedCells().size(); i++)
			{
				if ((MazeGenerator.getVisitedCells().get(i).getWalls() & 0x0100) >> 2 == 0)
				{
					g.drawLine(MazeGenerator.getVisitedCells().get(i).getX() * blockSize + 1, MazeGenerator.getVisitedCells().get(i).getY() * blockSize + blockSize, MazeGenerator.getVisitedCells()
							.get(i).getX()
							* blockSize + (blockSize - 1), MazeGenerator.getVisitedCells().get(i).getY() * blockSize + blockSize);
				}

				if ((MazeGenerator.getVisitedCells().get(i).getWalls() & 0x0010) >> 1 == 0)
				{
					g.drawLine(MazeGenerator.getVisitedCells().get(i).getX() * blockSize, MazeGenerator.getVisitedCells().get(i).getY() * blockSize + 1, MazeGenerator.getVisitedCells().get(i).getX()
							* blockSize, MazeGenerator.getVisitedCells().get(i).getY() * blockSize + (blockSize - 1));
				}
			}

			break;
		}

		g.dispose();
		bs.show();
	}

	public static void main(String[] args)
	{
		new Core();
	}
}
