package exercicio.I;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Constants {
	
	public final static String pessoas = "arquivos\\pessoas.txt";
	public final static String enderecos = "arquivos\\enderecos.txt";
	
	public static BufferedReader setFileToRead(String path) throws FileNotFoundException{
		FileReader fr = new FileReader(path);
		return new BufferedReader(fr);
	}

}
