package org.hourglass.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import org.hourglass.base.Input;

public class TextBlock
{
	FontMetrics fm;
	private String text;
	private int width;
	private int height;
	private int x, y;

	private boolean hover = false;
	private Rectangle box;

	public TextBlock(String text, int width, int x, int y)
	{
		fm = new FontMetrics(null)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -8098706258636406700L;
		};

		this.text = text;
		this.width = width;
		this.height = 20;
		this.x = x;
		this.y = y;

		this.box = new Rectangle(x, y, width, height);
	}

	public TextBlock(int width, int x, int y)
	{
		this("", width, x, y);
	}

	public void update(Input i)
	{
		Point p = i.getPos();
		Rectangle mRec = new Rectangle(p.x, p.y, 1, 1);

		if (box.intersects(mRec))
		{
			hover = true;
		} else
		{
			hover = false;
		}

		if (hover)
		{
			if (i.getKeyDown(KeyEvent.VK_0))
				addText("0");
			if (i.getKeyDown(KeyEvent.VK_1))
				addText("1");
			if (i.getKeyDown(KeyEvent.VK_2))
				addText("2");
			if (i.getKeyDown(KeyEvent.VK_3))
				addText("3");
			if (i.getKeyDown(KeyEvent.VK_4))
				addText("4");
			if (i.getKeyDown(KeyEvent.VK_5))
				addText("5");
			if (i.getKeyDown(KeyEvent.VK_6))
				addText("6");
			if (i.getKeyDown(KeyEvent.VK_7))
				addText("7");
			if (i.getKeyDown(KeyEvent.VK_8))
				addText("8");
			if (i.getKeyDown(KeyEvent.VK_9))
				addText("9");
			if (i.getKeyDown(KeyEvent.VK_BACK_SPACE))
				remove();

		}

	}

	public void render(Graphics g)
	{
		g.setFont(new Font("Verdana", 0, 14));

		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);

		if (hover)
		{
			g.setColor(Color.RED);
			g.drawRect(x - 1, y - 1, width + 1, height + 1);
			g.setFont(new Font("Verdana", Font.BOLD, 14));
		} else
		{
			g.setFont(new Font("Verdana", 0, 14));
		}

		g.setColor(Color.BLACK);
		fm = g.getFontMetrics();
		g.drawString(text, x + (width / 2) - fm.stringWidth(text) / 2,
				y + fm.getHeight());
	}

	public void addText(String add)
	{
		if (!(text.length() > 8))
			text += add;
	}

	public void remove()
	{
		if (text.length() > 0)
		{
			text = text.substring(0, text.length() - 1);
		}
	}
	
	public int value()
	{
		return Integer.valueOf(text);
	}
}
