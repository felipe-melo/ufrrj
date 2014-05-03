package aula02;

public class SAXPY {
	
	public static void main(String[] args){
		
		int[] x = {1, 2}, y = {0, 1};
		int a = 2, n = 2;
		
		for (int i = 0; i < n; i++){
			y[i] += a*x[i];
			System.out.print (y[i] + " ");
		}
		
	}

}
