package exercicio.I;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Dispersao {
	
	private ArrayList<RandomAccessFile>[] arrayr;
	private int tam;
	
	public Dispersao(int tam){
		this.setTam(tam);
		arrayr = (ArrayList<RandomAccessFile>[]) new ArrayList[this.getTam()];
	}
	
	public void runDespersao(String path){
		
		FileReader fr;
		try {
			
			fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
		
			while (br.ready()){
				
				int bytes = 14;
				RandomAccessFile raf = new RandomAccessFile(path, "r");
				String[] line = br.readLine().split("\t");
				int index = (Integer.parseInt(line[0])%this.getTam());
				
				bytes += line[0].length();
				bytes += line[1].length();
				bytes += line[2].length();
				bytes += line[3].length();
				bytes += line[4].length();
				bytes += line[5].length();
				bytes += line[6].length();
				
				raf.seek(bytes);
				arrayr[index].add(raf);
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<RandomAccessFile>[] getArrayr() {
		return arrayr;
	}
	public void setArrayr(ArrayList<RandomAccessFile>[] arrayr) {
		this.arrayr = arrayr;
	}
	public int getTam() {
		return tam;
	}
	public void setTam(int tam) {
		this.tam = tam;
	}
}
