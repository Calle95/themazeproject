package org.hourglass.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import org.hourglass.base.Input;

public class Button
{
	FontMetrics fm;
	private String text;
	private int width;
	private int height;
	private int x, y;
	
	private boolean pressed = false;
	private boolean hover = false;
	private Rectangle box;
	
	public Button(String text, int width, int height, int x, int y)
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
		this.height = height;
		this.x = x;
		this.y = y;

		this.box = new Rectangle(x, y, width, height);
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
		
		//Bullshit error in input class or something
		//TODO: FIX!
		if(hover && i.mousePressed())
			press();
	}
	
	public void render(Graphics g)
	{
		if(hover)
			g.setColor(Color.CYAN);
		else
			g.setColor(Color.WHITE);
		
		g.drawRect(x, y, width, height);
		
		
		if(hover)
			g.setColor(new Color(0xAAAAAA));
		else
			g.setColor(new Color(0x888888));
		
		g.fillRect(x + 1, y + 1, width - 1, height - 1);
		
		
		if(hover)
			g.setFont(new Font("Verdana", 0, 30));
		else
			g.setFont(new Font("Verdana", 0, 33));
		
		g.setColor(Color.BLACK);
		fm = g.getFontMetrics();
		g.drawString(text, x + (width / 2) - fm.stringWidth(text) / 2, y + height / 2 + (fm.getHeight() / 2) - 8);
	}
	
	public void press()
	{
		pressed = true;
	}
	
	public boolean isPressed()
	{
		return pressed;
	}
}
