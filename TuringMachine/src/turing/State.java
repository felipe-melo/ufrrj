package turing;

public class State implements Comparable{
	
	private String name;
	private String entry;
	private String print;
	private State destiny;
	private String move;
	
	public State(String name){
		this.setName(name);
	}
	
	public State(){
		
	}
	
	public String getName() {
		return name;
	}
	public String getEntry() {
		return entry;
	}
	public String getPrint() {
		return print;
	}
	public State getDestiny() {
		return destiny;
	}
	public String getMove() {
		return move;
	}
	public void setName(String Name) {
		this.name = Name;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public void setPrint(String print) {
		this.print = print;
	}
	public void setDestiny(State destiny) {
		this.destiny = destiny;
	}
	public void setMove(String move) {
		this.move = move;
	}
	@Override
	public int compareTo(Object o) {
		if (this.getName().equals(((State) o).getName())){
			return 0;
		}
		return 1;
	}
	
	

}
