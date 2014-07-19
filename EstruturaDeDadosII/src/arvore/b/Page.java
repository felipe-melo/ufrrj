package arvore.b;

import java.util.ArrayList;


public class Page<K, V> {
	
	private ArrayList<Node<K, V>> elements;
	private Page<K, V> father;
	private int d;
	private Page<K, V> page;
	private int height;
	private boolean overFlow;
	private boolean underFlow;
	
	public Page(int d){
		this.setD(d);
		this.setElements(new ArrayList<Node<K, V>>());
		this.setOverFlow(false);
		this.setUnderFlow(false);
	}
	
	public void checkLenght(){		
		if (this.getQuant() == 2*this.getD() + 1)
			this.setOverFlow(true);
		else{
			this.setOverFlow(false);
		}
		if (this.getQuant() >= this.getD()){
			this.setUnderFlow(false);
		}else{
			this.setUnderFlow(true);
		}
	}
	
	public boolean isFull(){
		return this.getQuant() == 2*d;
	}
	
	public int getQuant(){
		return this.getElements().size();
	}

	public ArrayList<Node<K, V>> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Node<K, V>> elements) {
		this.elements = elements;
	}

	public Page<K, V> getFather() {
		return father;
	}

	public void setFather(Page<K, V> father) {
		this.father = father;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getHeight() {
		return height;
	}
	
	public int setHeight(int height){
		this.height = height;
		return this.getHeight();
	}

	public boolean hasOverFlow() {
		return overFlow;
	}

	public void setOverFlow(boolean overFlow) {
		this.overFlow = overFlow;
	}

	public Page<K, V> getPage() {
		return page;
	}

	public void setPage(Page<K, V> page) {
		this.page = page;
	}

	public boolean hasUnderFlow() {
		return underFlow;
	}

	public void setUnderFlow(boolean underFlow) {
		this.underFlow = underFlow;
	}
	
}
