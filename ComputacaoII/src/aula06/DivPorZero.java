package aula06;

public class DivPorZero extends Exception{
	
	public int origemCodigo = 0; 
	
	@Override
	public String toString() {
		return "Divisão por zero.";
	}
}
