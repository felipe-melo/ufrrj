package aula06;

public class Principal {
	
	public static void main(String[] args){
		Calc calculo = new Calc();
		
		int result;
		
		try {
			result = calculo.div(10, 0);
		} catch (DivPorZero e) {			
			e.printStackTrace();
			System.out.println(e.origemCodigo);
		}
	}

}
