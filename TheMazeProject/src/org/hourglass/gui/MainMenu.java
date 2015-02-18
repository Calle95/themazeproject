package org.hourglass.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.hourglass.base.Core;
import org.hourglass.base.Input;

public class MainMenu
{
	FontMetrics fm;
	TextBlock tbWidth;
	TextBlock tbHeight;
	TextBlock tbBlockSize;
	TextBlock tbSeed;
	Button btnGenerate;

	public MainMenu()
	{
		fm = new FontMetrics(null)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = -8098706258636406700L;
		};

		tbWidth = new TextBlock("60", 150, 200, 84, 60);
		tbHeight = new TextBlock("50", 150, 200, 114, 50);
		tbBlockSize = new TextBlock("8", 150, 200, 144, 8);
		tbSeed = new TextBlock(150, 200, 174, System.currentTimeMillis());
		btnGenerate = new Button("Generate", Core.WIDTH - 6, 90, 3, 205);
	}

	public void update(Input i)
	{
		tbWidth.update(i);
		tbHeight.update(i);
		tbBlockSize.update(i);
		tbSeed.update(i);
		btnGenerate.update(i);
	}

	public void render(Graphics g)
	{
		g.setColor(new Color(0x444499));
		g.fillRect(0, 0, Core.WIDTH, 60);

		g.setColor(new Color(0xFF9999));
		g.setFont(new Font("Verdana", 0, 20));

		fm = g.getFontMetrics(g.getFont());

		g.drawString("Maze Generator", Core.WIDTH / 2 - fm.stringWidth("Maze Generator") / 2, 40);

		g.setColor(new Color(0xFFFFFF));

		g.setFont(new Font("Verdana", 0, 16));
		g.drawString("Grid width:", 10, 100);
		g.drawString("Grid height:", 10, 130);
		g.drawString("Block size:", 10, 160);
		g.drawString("Seed:", 10, 190);

		tbWidth.render(g);
		tbHeight.render(g);
		tbBlockSize.render(g);
		tbSeed.render(g);
		btnGenerate.render(g);
	}
	
	public ArrayList<LblValue> getValues()
	{
		ArrayList<LblValue> res = new ArrayList<>();
		
		res.add(new LblValue("width", tbWidth.value()));
		res.add(new LblValue("height", tbHeight.value()));
		res.add(new LblValue("blocksize", tbBlockSize.value()));
		res.add(new LblValue("seed", tbSeed.value()));
		
		return res;
	}
	
	public boolean isReadyToClose()
	{
		return btnGenerate.isPressed();
	}
}
