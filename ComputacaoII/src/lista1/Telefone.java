package lista1;

public class Telefone {
	
	int ci;
	int ca;
	int numero;
	
	public Telefone(int ci, int ca, int numero){
		this.ci = ci;
		this.ca = ca;
		this.numero = numero;
	}
	
	public Telefone(int ca, int numero){
		this.ci = 55;
		this.ca = ca;
		this.numero = numero;
	}
	
	public Telefone(int numero){
		this.ci = 55;
		this.ca = 21;
		this.numero = numero;
	}

	@Override
	public String toString() {
		String str = "+" + this.ci + " " + this.ca + " " + this.numero; 
		
		return str;
	}
		
}
