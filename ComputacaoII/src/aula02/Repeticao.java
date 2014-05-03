package aula02;

public class Repeticao {
	
	public static void main (String[] args){
		int i1 = 0;
		
		/*while (i < 200){
			System.out.println("Regra " + (i+1));
			i++;
		}*/
		
		for (int i = 0; i < 200; i++){
			if (i == 50)
				break;
				//continue;
			
			System.out.println("Regra " + (i+1));
		}
	}

}
