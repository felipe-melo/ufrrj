package lista2.exer1;

public class Secretaria extends Empregado{
	
	String[] recados;
	
	public void anotaRecado(String nome){
		verificaAlocacao();
		for (int i = 0; i < this.recados.length; i++){
			if (this.recados[i] == null){
				this.recados[i] = nome;
				break;
			}
		}
	}
	
	public void imprimeContato(){
		int ultimo = 0;
		for (int i = 0; i < this.recados.length; i++){
			if (this.recados[i] == null){
				ultimo = i - 1;
				break;			
			}
			if (i == this.recados.length - 1){
				ultimo = i;
				break;			
			}
			
		}
		
		for (int i = ultimo; i >= 0 && i + 5 > ultimo; i--){
			System.out.println(this.recados[i]);
		}
	}
	
	private void verificaAlocacao(){
		if (!(this.recados[this.recados.length - 1] == null)){
			String[] aux = new String[this.recados.length];
			for (int i = 0; i < this.recados.length; i++){
				aux[i] = this.recados[i];
			}
			
			this.recados = new String[this.recados.length * 2];
			
			for (int i = 0; i < aux.length; i++){
				this.recados[i] = aux[i]; 
			}
			
		}
	}
	
	public Secretaria(String nome, double salario, Data data) {
		super(nome, salario, data);
		this.recados = new String[3];
	}

}
