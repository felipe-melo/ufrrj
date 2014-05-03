package aula02;

public class Test {
	
	public static void teste(double x){
		if (x > 5){
			System.out.println(x + " é maior que 5");
		}
	}
	
	public static void main (String[] args){
		teste(6);
		teste(5);
		teste(4);
	}

}
