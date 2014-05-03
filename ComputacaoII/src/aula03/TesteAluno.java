package aula03;

public class TesteAluno {
	
	public static void main(String[] args){
		Aluno bruna = new Aluno();
		
		bruna.nome = "Bruna";
		bruna.CRA = 9.5;
		bruna.periodo = 3;
		bruna.sexo = true;
		bruna.inscricao();				
		
		//System.out.println(bruna);
		
		Aluno felipe = new Aluno("Felipe", false, 9.6, 2);
		System.out.println (felipe.nome + " " + felipe.periodo);
		felipe.inscricao();
		System.out.println (felipe.nome + " " + felipe.periodo);		
	}

}
