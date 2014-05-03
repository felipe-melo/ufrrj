package lista1;

public class Principal_Www {
	
	public static void main(String[] args){
		
		Www teste = new Www("www.google.com.br/batata/frita/123");
		Www teste2 = new Www("www.google.com.br/frita/123");
		
		if (teste.equals(teste2)){
			System.out.println("Os domínios são iguais");
		}
	}

}
