package ufrrj.aa;

public class Gordura extends Alimento {
	
	protected float[] atributos_gor;
	
	public Gordura(int id, String descricao, float[] atributos_taco, float[] atributos_gor) {
		super(id, descricao, atributos_taco);
		
		this.SetAtributo_gor(atributos_gor);
		
		for (int i = 0; i < this.atributos_gor.length; i++) {
			this.atributos_gor[i] = atributos_gor[i];
		}
	}
		
	public void SetAtributo_gor(float[] atributo_gor) {
		this.atributos_gor = new float[atributo_gor.length];
	}
	
}
