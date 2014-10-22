package org.hourglass.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

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
		g.drawString(text, x + (width / 2) - fm.stringWidth(text) / 2, y + fm.getHeight());
	}

	public void addText(String add)
	{
		text += add;
	}
}
