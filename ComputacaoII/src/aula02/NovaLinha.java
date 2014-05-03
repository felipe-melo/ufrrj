package aula02;

public class NovaLinha {
	
	public static void novaLinha(){
		System.out.println("");
	}
	
	public static void tresLinhas(){
		novaLinha(); novaLinha(); novaLinha();
	}
	
	public static void main(String[] args){
		System.out.println("Linha 1");
		tresLinhas();
		System.out.println("Linha 2");		
	}

}
