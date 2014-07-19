package join.nestloop;

import comum.classes.Join;
import comum.classes.Tuple;

public class NestedLoopJoin implements Join{
	
	private Join file1;
	private Join file2;
	private String key1;
	private String key2;
	
	public NestedLoopJoin(Join file1, String key1, Join file2, String key2){
		this.file1 = file1;
		this.file2 = file2;
		this.key1 = key1;
		this.key2 = key2;
	}

	@Override
	public Tuple next() {
		while(file1.hasNext()){
			Tuple aux1 = file1.next();
			while(file2.hasNext()){
				Tuple aux2 = this.file2.next();
				String field1 = aux1.getKeyField();
				String field2 = aux2.getKeyField();
				if (field1.equals(field2)){
					Tuple tuple = new Tuple();
					tuple.addReferences(aux1.getFiles(), aux1.getKeys(), aux1.getPositions());
					tuple.addReferences(aux2.getFiles(), aux2.getKeys(), aux2.getPositions());
					return tuple;
				}
			}
			this.file2.open();
		}
		return new Tuple();
	}

	@Override
	public void open() {
		this.file1.open();
		this.file2.open();
	}

	@Override
	public void close() {
		this.file1.close();
		this.file2.close();
	}

	@Override
	public boolean hasNext() {
		return this.file1.hasNext() && this.file2.hasNext();
	}
}