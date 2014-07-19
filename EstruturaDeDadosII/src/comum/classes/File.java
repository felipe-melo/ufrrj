package comum.classes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class File implements Join{
	
	private String keyField;
	private RandomAccessFile file;
	private String path;
	private int position;
	
	public int getPosition(){
		return position;
	}
	
	public String getKeyField(){
		return keyField;
	}
	
	public File(String path, String key){
		this.path = path;
		this.keyField = key;
	}
	
	@Override
	public void open() {
		try {
			this.file = new RandomAccessFile(this.path, "r");
			this.position = 0;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			this.file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Tuple next() {
		try {
			this.file.seek(this.position);
			String values;
			if ((values = this.file.readLine()) != null){
				Tuple tuple = new Tuple();
				this.file.seek(this.position);
				tuple.addReferences(this.file, keyField, this.position);
				this.position += values.length() + 1;				
				return tuple;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Tuple();
	}

	@Override
	public boolean hasNext() {
		try {
			this.file.seek(this.position);
			return this.file.readLine() != null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
