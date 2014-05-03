package turing;

public class Key implements Comparable<Key>{
	
	String name;
	String entry;
	
	public Key(String name, String entry){
		this.setName(name);
		this.setEntry(entry);
	}
	
	public Key(){
	}
	
	public String getName() {
		return name;
	}
	public String getEntry() {
		return entry;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	
	@Override
	public int compareTo(Key o) {
		if  (this.getName().equals(o.getName()) && this.getEntry().equals(o.getEntry()))
			return 0;
		else
			return -1;
	}
	

}
