package ufrrj.model;

import java.util.ArrayList;
import java.util.Collection;

public class Ingredientes
{
	public Ingredientes()
	{
		total = 0;
		ingredientes = new ArrayList<Ingrediente>();
	}
	
	public void addIngrediente(String nome)
	{
		this.ingredientes.add(new Ingrediente(this.nextKey(), nome));
	}
	
	private int nextKey()
	{
		this.total = this.total + 1;
		return total;
	}
	
	public Collection<Ingrediente> getIngredientes()
	{
		return ingredientes;
	}
	
	private int total;
	Collection<Ingrediente> ingredientes;
}
