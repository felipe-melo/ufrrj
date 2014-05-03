package lista2.exer2;

public class Principal {
	
	public static void main (String[] args){
		
		IntervaloDeTempo inter = new IntervaloDeTempo(3500);
		IntervaloDeTempo inter2 = new IntervaloDeTempo(3500);
		System.out.println(inter);
		//inter.SomaSegundos(100);
		inter.SomaIntervalo(inter2);
		System.out.println(inter);
		
	}

}
