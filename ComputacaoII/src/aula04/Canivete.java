package aula04;


public class Canivete {
	
	static void ordenavet (int[] vet){
		
		int temp = vet[0];
		
		for (int i = 0; i < vet.length; i++){
			for (int j = i + 1; j < vet.length; j++){
				if (vet[i] > vet[j]){
					temp = vet[i];
					vet[i] = vet[j];
					vet[j] = temp;
				}
			}
		}
	}

}
