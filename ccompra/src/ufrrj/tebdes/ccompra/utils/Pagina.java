package ufrrj.tebdes.ccompra.utils;

public class Pagina {
	
	public Pagina(){
		primeiraPag = 0;
		numero = 1;
	}
	
	private Integer numero;
	private Integer primeiraPag;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getPrimeiraPag() {
		return primeiraPag;
	}

	public void setPrimeiraPag(Integer primeiraPag) {
		this.primeiraPag = primeiraPag;
	}

}
