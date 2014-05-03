package aula04;

import java.util.HashMap;


public class Map {
	
	public static void main(String[] args){
		
		HashMap<String, String> emails = new HashMap<String, String> ();
		
		emails.put("Rafael", "rafa@yahoo.com.br");
		emails.put("Larissa", "larissa@ufrrj.br");
		emails.put("Jose Siva", "zes@att.com");
		
		for (String s: emails.values()){
			System.out.println(s);
		}
		
	}

}
