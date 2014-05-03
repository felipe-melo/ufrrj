package turing;

public class Transition {
	
	Key key;
	State state;
	
	public Transition(Key key, State state){
		this.setKey(key);;
		this.setState(state);
	}
		
	public Key getKey() {
		return key;
	}
	public State getState() {
		return state;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	

}
