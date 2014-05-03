package biblioteca.ufrrj;

public class Exemplar {
	
	int id;
	String tipo;
	boolean disponivel;
	
	public Exemplar(int id, String tipo){
		this.id = id;
		this.tipo = tipo;
		disponivel = true;
	}

}
