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

	private final String TITLE = "Maze Generator";
	private boolean running;
	private boolean debug;
	private Dimension dim;

	private Cell[][] maze;

	private JFrame frame;

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

		dim = new Dimension(width * blockSize, height * blockSize);
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

		this.debug = debug;
		running = true;
		this.run();
	}

	public Core(int width, int height, int blockSize)
	{
		new Core(width, height, blockSize, 0, false);
	}

	@Override
	public void run()
	{
		while (running)
		{
			if(debug)
			{
				while(MazeGenerator.perfomStep())
				{
					maze = MazeGenerator.getMaze();
					try
					{
						Thread.sleep(50);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			render();
		}
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
		g.fillRect(0, 0, (int)dim.getWidth(), (int)dim.getHeight());
		
		//TODO: rendering mechanic
		
		
		
		
		g.dispose();
		bs.show();
	}

	public static void main(String[] args)
	{
		Window.createWindow();
	}
}
