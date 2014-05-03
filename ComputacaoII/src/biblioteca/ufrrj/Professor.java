package biblioteca.ufrrj;

public class Professor extends Usuario{
	
	
	String nome;
	int matriculo;
	String lotacao;
	String curso;
	
	public Professor(int id, String tipo, String nome, int matricula, String lotaco, String curso) {
		super(id, tipo);
		
		this.nome = nome;
		this.matriculo = matricula;
		this.lotacao = lotacao;
		this.curso = curso;
	}
}
