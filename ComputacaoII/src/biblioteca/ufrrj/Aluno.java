package biblioteca.ufrrj;

public class Aluno extends Usuario{	
	
	String nome;
	int matricula;
	String curso;
	
	public Aluno(int id, String tipo, String nome, int matricula, String curso) {
		super(id, tipo);
		
		this.nome = nome;
		this.matricula = matricula;
		this.curso = curso;
	}
}
