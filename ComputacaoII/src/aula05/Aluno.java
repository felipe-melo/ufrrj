package aula05;
public class Aluno implements Pessoa{
	
	String nome;
	int idade;

	@Override
	public int getIdade() {		
		return this.idade;
	}

	@Override
	public String getNome() {		
		return this.nome;
	}

}
