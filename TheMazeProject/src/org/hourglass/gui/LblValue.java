package org.hourglass.gui;

public class LblValue
{
	String label;
	long value;
	
	public LblValue(String label, int value)
	{
		this.label = label;
		this.value = value;
	}
	
	public LblValue(String label, long value)
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

	public long getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
