package ufrrj.aa;

public class Alimento {
	
	protected int id;
	protected String descricao;
	protected float[] atributos_taco;
	
	public Alimento() {		
	}

	public Alimento(int id, String descricao, float[] atributos_taco) {
		
		this.setId(id);;
		this.setDescricao(descricao);
		this.setAtributos_taco(atributos_taco);
		
		for (int i = 0; i < this.atributos_taco.length; i++) {
			this.atributos_taco[i] = atributos_taco[i];
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setAtributos_taco(float[] atributos) {
		this.atributos_taco = new float[atributos.length];
	}

}
