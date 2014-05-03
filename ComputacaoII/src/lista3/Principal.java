package lista3;

public class Principal {
	
	public static void main(String[] args){
		
		Aluno[] aluno = new Aluno[8];
		
		aluno[0] = new Aluno("Felipe", 5.0f);
		aluno[1] = new Aluno("Rafael", 3.5f);
		aluno[2] = new Aluno("Thiago", 2.0f);
		aluno[3] = new Aluno("Wilson", 3.5f);
		aluno[4] = new Aluno("Anderson", 0.5f);
		aluno[5] = new Aluno("Camila", 4.0f);
		aluno[6] = new Aluno("Raphael", 1.5f);
		aluno[7] = new Aluno("Bruno", 0.5f);
		
		QuickSort.ordenar(aluno);
		
		for (int i = 0; i < aluno.length; i++){
			System.out.println(aluno[i]);
		}
		
	}

}
