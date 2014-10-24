package org.hourglass.base;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Input implements KeyListener, MouseListener, MouseMotionListener
{

	private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> upKeys = new ArrayList<Integer>();

	public static final int NUM_KEYCODES = 256;
	public static final int NUM_MOUSEBUTTONS = 5;

	Point currentPos;

	private boolean mousePressed = false;
	private boolean mouseReleased = false;

	private boolean click = false;

	boolean[] keys;

	public Input()
	{
		keys = new boolean[NUM_KEYCODES];
		currentPos = new Point(0, 0);
	}

	public void update()
	{
		mouseReleased = false;

		upKeys.clear();

		for (int i = 0; i < NUM_KEYCODES; i++)
			if (!getKey(i) && currentKeys.contains(i))
				upKeys.add(i);

		downKeys.clear();

		for (int i = 0; i < NUM_KEYCODES; i++)
			if (getKey(i) && !currentKeys.contains(i))
				downKeys.add(i);

		currentKeys.clear();

		for (int i = 0; i < NUM_KEYCODES; i++)
			if (getKey(i))
				currentKeys.add(i);

		click = false;
	}

	private boolean getKey(int i)
	{
		return keys[i];
	}

	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public boolean getKeyDown(int keyCode)
	{
		return downKeys.contains(keyCode);

	}

	public boolean getKeyUp(int keyCode)
	{
		return upKeys.contains(keyCode);
	}

	public void printKeys()
	{
		for (int i = 0; i < currentKeys.size(); i++)
		{
			System.out.println(currentKeys.get(i));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		currentPos = e.getPoint();
	}

	public Point getPos()
	{
		return currentPos;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!click)
		{
			click = true;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		mousePressed = true;

	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		mousePressed = false;
		mouseReleased = true;

	}

	public String printMousePos()
	{
		return "(" + currentPos.x + ", " + currentPos.y + ")";
	}

	public boolean mousePressed()
	{
		return mousePressed;
	}

	public boolean mouseReleased()
	{
		return mouseReleased;
	}

	public boolean mouseClick()
	{
		return click;
	}

}
