package aula2.exercicios.parte1;

public class OrdenacaoVetor {
	
	public static int[] GeraVetor(int dim){
		int[] vet = new int[dim];
		for (int i = 0; i < dim; i++){
			vet[i] = (int) (Math.random() * 10);
			System.out.print ("'" + vet[i] + "' ");
		}
		return vet;
	}
	
	public static void OrdenaVetor(int[] vet){
		
		int aux;
		
		for (int i = 0; i < vet.length -1; i++){
			for (int j = i + 1; j < vet.length; j++){
				if (vet[j] < vet[i]){
					aux = vet[j];
					vet[j] = vet[i];
					vet[i] = aux;
				}
			}
		}
		
		System.out.println();
		System.out.println();
		
		for (int i = 0; i < vet.length; i++){
			System.out.print("'" + vet[i] + "' ");
		}
	}
	
	public static void main(String[] args){
		OrdenaVetor(GeraVetor(5));
	}

}
