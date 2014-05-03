package arquivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LendoTeclado {
	
	public static void main(String[] args){
		
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		String nome;
		try {
			nome = br1.readLine();
			System.out.println("olá " + nome + " bom dia.");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
