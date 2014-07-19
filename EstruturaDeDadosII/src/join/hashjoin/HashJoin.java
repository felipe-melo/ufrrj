package join.hashjoin;

import join.hashtable.HashTable;
import join.hashtable.Node;
import comum.classes.Join;
import comum.classes.Tuple;

public class HashJoin implements Join{
	
	private int tam;
	private Join file1;
	private Join file2;
	private String key1;
	private String key2;
	private HashTable table;
	
	public HashJoin(int tam, Join file1, String key1, Join file2, String key2){
		this.file1 = file1;
		this.file2 = file2;
		this.key1 = key1;
		this.key2 = key2;
		this.tam = tam;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void open() {
		this.file1.open();
		this.file2.open();
		
		table = new HashTable(this.tam);
		
		//Criar a dispersão para o primeiro arquivo
		while (this.file1.hasNext()){
			Tuple tuple = file1.next();
			while (tuple.hasNext()){
				Tuple aux = tuple.next();
				table.insert(aux.getKeyField(), aux);
			}	
		}
	}

	@Override
	public void close() {
		this.file1.close();
		this.file2.close();
	}

	@Override
	public Tuple next() {
		if (file2.hasNext()){
			Tuple tuple = file2.next();
			while (tuple.hasNext()){
				Tuple aux = tuple.next();
				Node<Integer, Tuple> no = this.table.find(aux.getKeyField());
				if (no != null){
					Tuple t = new Tuple(); 
					t.addReferences(((Tuple)no.getValue()).getFiles(), ((Tuple)no.getValue()).getKeys(), ((Tuple)no.getValue()).getPositions());
					t.addReferences(aux.getFiles(), aux.getKeys(), aux.getPositions());
					return t;
				}else
					return new Tuple();
			}
		}
		return new Tuple();
	}

	@Override
	public boolean hasNext() {
		return file2.hasNext();
	}
	
	public Node getList(){
		if (file2.hasNext()){
			Tuple tuple = file2.next();
			while (tuple.hasNext()){
				Tuple aux = tuple.next();
				Node no = this.table.find(aux.getKeyField());
				if (no != null){
					return no;
				}
			}
		}
		return new Node();
	}
	
	public String getKey2(){
		return this.key2;
	}

}