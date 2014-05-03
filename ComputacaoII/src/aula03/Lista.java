package aula03;

public class Lista {
	
	private int[] inteiros;
	private int tamanho;
	private int preenchidos;
	
	public Lista(int param){
		tamanho = param;
		inteiros = new int[param];
		preenchidos = 0;
	}
	
	public void adicionarValor(int valor){
		inteiros[preenchidos] = valor;
		preenchidos++;
	}
	
	public void ordenarLista(){
		int aux;
		for (int i = 0; i < preenchidos; i++){
			for (int j = i + 1; j < preenchidos; j++){
				if (inteiros[i] > inteiros[j]){
					aux = inteiros[i];
					inteiros[i] = inteiros[j];
					inteiros[j] = aux;
				}
			}
		}
	}
	
	public void imprimirLista(){
		for (int i = 0; i < preenchidos; i++){
			System.out.print (inteiros[i] + " ");
		}
	}

}
