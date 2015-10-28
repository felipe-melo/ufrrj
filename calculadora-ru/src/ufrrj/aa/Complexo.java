package ufrrj.aa;

public class Complexo extends Alimento {
	
	protected Gordura gordura;
	protected Aminoacido aminoacido;
	
	Complexo(int id, String descricao, float[] atributos_taco, Gordura gordura, Aminoacido aminoacido) {
		super(id, descricao, atributos_taco);
		
		this.gordura = gordura;
		this.aminoacido = aminoacido;
	}
}
