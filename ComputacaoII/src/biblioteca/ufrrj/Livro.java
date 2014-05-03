package biblioteca.ufrrj;

public class Livro extends Exemplar{
	
	
	String titulo;
	String autores;
	String editora;
	int ano;
	
	public Livro(int id, String tipo, String titulo) {
		super(id, tipo);
		this.titulo = titulo;
	}
}
