package biblioteca.ufrrj;

public class Funcionario extends Usuario{
	
	
	String nome;
	int matricula;
	String lotacao;
	String funcao;
	
	public Funcionario(int id, String tipo, String nome, int matricula, String lotacao, String funcao) {
		super(id, tipo);
		
		this.nome = nome;
		this.matricula = matricula;
		this.lotacao = lotacao;
		this.funcao = funcao;
	}
}
