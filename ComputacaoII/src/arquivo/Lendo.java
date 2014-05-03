package arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Lendo {
	
	public static void main(String[] args){
		File f = new File("Texto.txt");
		
		try {
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			
			String str = "";
			
			while (br.ready()){
				str += br.readLine() + "\n";
			}
			
			/*String str = br.readLine();
			while (str != null){
				System.out.println(str);
				str = br.readLine();
			}*/
			
			
			System.out.println(str);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
