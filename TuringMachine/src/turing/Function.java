package turing;

import java.util.ArrayList;
import java.util.TreeSet;

public class Function {
	
	private TreeSet<State> states;
	private State now;
	private TreeSet<State> finalStates;
	private State inicial;
	private String chain;
	private TreeSet<String> alpha;
	private TreeSet<String> alphaAux;
	private String entry;
	private ArrayList<Transition> transitions;
	
	public Function(){
		this.states = new TreeSet<State>();
		this.transitions = new ArrayList<Transition>();
		this.finalStates = new TreeSet<State>();
		this.alpha = new TreeSet<String>();
		this.alphaAux = new TreeSet<String>();
	}
	
	public TreeSet<State> getStates() {
		return states;
	}
	public State getNow() {
		return now;
	}
	public State getInicial() {
		return inicial;
	}
	public String getChain() {
		return chain;
	}
	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}
	public void setNow(State now) {
		this.now = now;
	}
	public void setInicial(State inicial) {
		this.inicial = inicial;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}
	public TreeSet<State> getFinalStates() {
		return finalStates;
	}
	public TreeSet<String> getAlpha() {
		return alpha;
	}
	public TreeSet<String> getAlphaAux() {
		return alphaAux;
	}
	public void setFinalStates(TreeSet<State> finalStates) {
		this.finalStates = finalStates;
	}
	public void setAlpha(TreeSet<String> alpha) {
		this.alpha = alpha;
	}
	public void setAlphaAux(TreeSet<String> alphaAux) {
		this.alphaAux = alphaAux;
	}	
	public ArrayList<Transition> getTransitions() {
		return transitions;
	}
	public void setStates(TreeSet<State> states) {
		this.states = states;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}

	boolean processChain(){
		
		this.setNow(inicial);
		char[] chainVet = this.getChain().toCharArray();
		int index = 0;
		boolean flag = false;
		State std = new State();
		
		while (index < chainVet.length){
			
			flag = false;
			
			this.setEntry(String.valueOf(chainVet[index]));
			
			Key key = new Key(this.now.getName(), this.getEntry());
			
			for (Transition aux: this.getTransitions()){
				if (key.compareTo(aux.getKey()) == 0){
					flag = true;
					std = aux.getState();
					break;
				}
			}
			
			if (flag){
				
				this.setNow(std.getDestiny());
				chainVet[index] = std.getPrint().charAt(0);
				
				if (std.getMove().equalsIgnoreCase("d"))
					index += 1;
				else
					index -= 1;
				
			}else{
				return false; 
			}
		}
		if (this.getFinalStates().contains(this.getNow()))
			return true;
		else
			return false;
	}

}
