package aula03;

public class Aluno {
	
	String nome;
	boolean sexo;
	double CRA;
	int periodo;
	
	void inscricao(){
		periodo++;
	}
	
	public Aluno(){ //Construtor padrão
		
	}
	
	public Aluno (String nomeAluno, boolean sexoAluno, double CRAAluno, int periodoAluno){
		nome = nomeAluno;
		sexo = sexoAluno;
		CRA = CRAAluno;
		periodo = periodoAluno;
	}

}
