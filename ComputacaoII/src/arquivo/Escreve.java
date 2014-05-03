package arquivo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Escreve {
	
	public static void main(String[] args){
		
		File wr = new File("Texto2.txt");
		
		try {
			FileWriter fw = new FileWriter(wr);
			BufferedWriter rs = new BufferedWriter(fw);
			rs.write("Escrevendo\nno\narquivo");
			rs.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
