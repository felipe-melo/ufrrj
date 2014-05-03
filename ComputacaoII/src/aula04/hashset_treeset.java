package aula04;

import java.util.HashSet;
import java.util.TreeSet;


public class hashset_treeset {
	
	public static void main(String[] args){
		
		TreeSet<String> nomes = new TreeSet<String>();
		
		nomes.add("Rafael");
		nomes.add("Larissa");
		nomes.add("André");
		
		for (String s: nomes){
			System.out.println(s);
		}
		
		HashSet<String> nomes2 = new HashSet<String>();
		
		nomes2.add("Rafael");
		nomes2.add("Larissa");
		nomes2.add("André");
		nomes2.add("André");
		
		System.out.println();
		System.out.println();
		
		for (String s: nomes2){
			System.out.println(s);
		}
		
	}
}
