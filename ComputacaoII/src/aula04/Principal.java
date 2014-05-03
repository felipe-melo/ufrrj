package aula04;


public class Principal {
	
	public static void main(String[] args){
		
		int[] vetor = new int[5];
		
		vetor[0] = 1568;
		vetor[1] = -479;
		vetor[2] = 204;
		vetor[3] = 0;
		vetor[4] = 676;
		
		Canivete.ordenavet(vetor);
		
		for (int i = 0; i < vetor.length; i++){
			System.out.println(vetor[i]);
		}
		
	}

}

