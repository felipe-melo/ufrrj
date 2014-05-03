package biblioteca.ufrrj;

public class Filme extends Exemplar{
	
	
	String titulo;
	String produtor;
	String diretor;
	int ano;

	public Filme(int id, String tipo, String titulo) {
		super(id, tipo);
		
		this.titulo = titulo;
	}
}
