package arvore.b;

import java.util.ArrayList;


public class Page<T> {
	
	private ArrayList<Node<T>> elements;
	private Page<T> father;
	private int d;
	private Page<T> page;
	private int height;
	private boolean overFlow;
	
	public Page(int d){
		this.setD(d);
		this.setElements(new ArrayList<Node<T>>());
		this.setOverFlow(false);
	}
	
	public void checkLenght(){		
		if (this.getQuant() == 2*d + 1)
			this.setOverFlow(true);
		else{
			this.setOverFlow(false);
		}
	}
	
	public boolean isFull(){
		return this.getQuant() == 2*d;
	}
	
	public int getQuant(){
		return this.getElements().size();
	}

	public ArrayList<Node<T>> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Node<T>> elements) {
		this.elements = elements;
	}

	public Page<T> getFather() {
		return father;
	}

	public void setFather(Page<T> father) {
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

	private int setHeightRec(Page<T> page) {
		if (page == null){
			return 1;
		}else{
			return this.height += this.setHeightRec(page.getPage());
		}
	}
	
	public void setHeight(){
		this.height = 1;
	}

	public boolean hasOverFlow() {
		return overFlow;
	}

	public void setOverFlow(boolean overFlow) {
		this.overFlow = overFlow;
	}

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
	
}
