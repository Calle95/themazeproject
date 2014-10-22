package org.hourglass.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class TextBlock
{
	FontMetrics fm;
	private String text;
	private int width;
	private int x, y;

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
		this.x = x;
		this.y = y;
	}
	
	public TextBlock(int width, int x, int y)
	{
		this.text = "";
		this.width = width;
		this.x = x;
		this.y = y;
	}

	public void update(Input i)
	{

	}

	public void render(Graphics g)
	{
		g.setFont(new Font("Verdana", Font.BOLD, 14));
		fm = g.getFontMetrics();
		
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, 20);
		
		g.setColor(Color.BLACK);
		g.drawString(text, x + (width / 2) - fm.stringWidth(text) / 2, y + fm.getHeight());
	}
	
	public void addText(String add)
	{
		text += add;
	}
}
