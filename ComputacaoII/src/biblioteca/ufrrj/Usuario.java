package biblioteca.ufrrj;

import java.util.ArrayList;

public class Usuario {
	
	int id;
	String tipo;
	ArrayList<Exemplar> exemplar = new ArrayList<Exemplar>();
	boolean cheio;
	
	public Usuario(int id, String tipo){
		this.id = id;
		this.tipo = tipo;
		this.cheio = false;
	}
}
