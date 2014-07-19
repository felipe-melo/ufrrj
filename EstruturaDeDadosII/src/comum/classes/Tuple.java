package comum.classes;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Tuple implements Join{
	
	private ArrayList<RandomAccessFile> files;
	private ArrayList<String> keys;
	private ArrayList<Integer> positions;
	private int position;
	
	public Tuple(){
		files = new ArrayList<RandomAccessFile>();
		keys = new ArrayList<String>();
		positions = new ArrayList<Integer>();
		position = 0;
	}
	
	public void addReferences(RandomAccessFile file, String key, Integer position){
		files.add(file);
		keys.add(key);
		positions.add(position);
	}
	
	public void addReferences(ArrayList<RandomAccessFile> files, ArrayList<String> keys, ArrayList<Integer> positions){
		this.files.addAll(files);
		this.keys.addAll(keys);
		this.positions.addAll(positions);
	}

	@Override
	public void open() {
		this.position = 0;
	}

	@Override
	public void close() {
		
	}

	@Override
	public Tuple next() {
		Tuple tuple = new Tuple();
		tuple.addReferences(this.files.get(this.position), this.keys.get(this.position), this.positions.get(this.position));
		this.position++;
		return tuple;
	}
	
	@Override
	public boolean hasNext() {
		return this.position < this.files.size();
	}
	
	public String getLine(){
		String retorno = "";
		try {
			int i = 0;
			while (i < this.files.size()){
				this.getFiles().get(i).seek(this.getPositions().get(i));
				retorno += this.getFiles().get(i).readLine() + "\t";
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (retorno.length() > 0) return retorno.substring(0, retorno.length() - 1);
		return retorno;
	}
	
	public String getLine(int file, String... params){
		String retorno = "";
		try {
			if (this.getFiles().size() == 0) return retorno;
			this.getFiles().get(file).seek(this.getPositions().get(file));
			String vet[] = this.getFiles().get(file).readLine().split("\t");
			for (int j = 0; j < params.length; j++){
				if (Constants.fields.get(params[j]) < vet.length)
					retorno += vet[Constants.fields.get(params[j])] + "\t";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (retorno.length() > 0) return retorno.substring(0, retorno.length() - 1);
		return retorno;
	}
	
	public String getComponent(int file, String... params){
		String retorno = "";
		try {
			if (this.getFiles().size() == 0) return retorno;
			this.getFiles().get(file).seek(this.getPositions().get(file));
			String vet[] = this.getFiles().get(file).readLine().split("\t");
			for (int j = 0; j < params.length; j++){
				if (Constants.fields.get(params[j]) < vet.length)
					retorno += vet[Constants.fields.get(params[j])] + "\t";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (retorno.length() > 0) return retorno.substring(0, retorno.length() - 1);
		return retorno;
	}
	
	public String getKeyField(){
		String aux = this.getLine(); 
		if (aux != null){
			return aux.split("\t")[Constants.fields.get(this.getKeys().get(position))];
		}
		return "";
	}

	public ArrayList<RandomAccessFile> getFiles() {
		return files;
	}

	public ArrayList<String> getKeys() {
		return keys;
	}
	
	public ArrayList<Integer> getPositions() {
		return positions;
	}
}
