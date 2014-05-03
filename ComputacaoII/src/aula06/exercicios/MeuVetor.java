package aula06.exercicios;

public class MeuVetor{
	
	int[] vetor = new int[5];
	
	public void insere(int p, int n) throws Exception{
				
		try{
			this.vetor[p] = n;
		}catch (ArrayIndexOutOfBoundsException e){
			int[] temp = new int[vetor.length];
			
			for(int i = 0; i < temp.length; i++){
				temp[i] = vetor[i];
			}
			
			vetor = new int[temp.length + 1];
			
			for(int i = 0; i < temp.length; i++){
				vetor[i] = temp[i];
			}
		}finally{
			this.vetor[p] = n;
		}
	}

}
