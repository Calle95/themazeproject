package org.hourglass.gui;

public class LblValue
{
	String label;
	int value;
	
	public LblValue(String label, int value)
	{
		this.label = label;
		this.value = value;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
