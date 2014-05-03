package aula02;

public class ProdutoInterno {
	
	public static void main(String args[]){
		int[] x = {1, 2}, y = {0, 1};
		int n = 2, c = 0;
		
		for (int i = 0; i < n; i++){
			c += x[i] * y[i];
		}
		
		System.out.println(c);
		
	}

}
