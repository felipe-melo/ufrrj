package comum.classes;

import java.io.IOException;

public class Selector implements Join{
	
	private Join file;
	private String[] params;
	private boolean content;
	
	public Selector(Join file1, boolean content, String... params){
		this.file = file1;
		this.params = params;
		this.content = content;
	}

	@Override
	public void open() {
		file.open();
	}

	@Override
	public void close() {
		file.close();
	}

	@Override
	public Tuple next() {
		if (params.length % 2 != 0){
			file.close();
			return new Tuple();
		}
		while (file.hasNext()){
			Tuple tuple = file.next();
			while (tuple.hasNext()){
				Tuple aux = tuple.next();
				try {
					aux.getFiles().get(0).seek(aux.getPositions().get(0));
					String[] values = (aux.getFiles().get(0).readLine()).split("\t");
					int index = 0;
					boolean flag = true;
					while(index < params.length - 1){
						if (!this.content){
							if (!values[Constants.fields.get(params[index])].equals(params[index+1])){
								index += 2;
								flag = false;
								break;
							}else index += 2;
						}else{
							if (!values[Constants.fields.get(params[index])].contains(params[index+1])){
								index += 2;
								flag = false;
								break;
							}else index += 2;
						}
					}
					if (flag) return tuple;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return new Tuple();
	}

	@Override
	public boolean hasNext() {
		return file.hasNext();
	}
}