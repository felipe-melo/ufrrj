package aula06.exercicios;

public class Principal {
	
	public static void main(String[] args){
		
		MeuVetor teste = new MeuVetor();
		
		for (int i = 0; i < 10; i++){
			try {
				teste.insere(i, 3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < teste.vetor.length; i++){
			System.out.println(teste.vetor[i]);
		}
		
	}

}
