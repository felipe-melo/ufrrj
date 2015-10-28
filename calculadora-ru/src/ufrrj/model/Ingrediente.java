package ufrrj.model;

public class Ingrediente
{
	public Ingrediente(Integer key, String value)
	{
		this.setKey(key);
		this.setValue(value);
	}

	public Integer getKey()
	{
		return key;
	}
	public void setKey(Integer key)
	{
		this.key = key;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}

	private Integer key;
	private String value;
}
