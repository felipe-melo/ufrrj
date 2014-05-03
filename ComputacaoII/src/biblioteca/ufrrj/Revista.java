package biblioteca.ufrrj;

public class Revista extends Exemplar{
	
	
	String titulo;
	int numero, ano;
	String edicao;
	int quant;
	
	public Revista(int id, String tipo, String titulo) {
		super(id, tipo);
		
		this.titulo = titulo;
	}
}
