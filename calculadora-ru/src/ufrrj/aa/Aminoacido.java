package ufrrj.aa;

public class Aminoacido extends Alimento {
	
	protected float[] atributos_ami;
	
	Aminoacido(int id, String descricao, float[] atributos_taco, float[] atributos_ami) {
		super(id, descricao, atributos_taco);
		
		this.SetAtributos_ami(atributos_ami);
		
		for (int i = 0; i < this.atributos_ami.length; i++) {
			this.atributos_ami[i] = atributos_ami[i];
		}
	}
	
	public void SetAtributos_ami(float[] atributos_ami) {
		this.atributos_ami = new float[atributos_ami.length];
	}
	
}
