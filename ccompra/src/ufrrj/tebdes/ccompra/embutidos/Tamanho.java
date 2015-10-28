package ufrrj.tebdes.ccompra.embutidos;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Tamanho {
	
	@Column(name="pro_altura", nullable=false, length=50)
	private String altura;
	@Column(name="pro_largura", nullable=false, length=50)
	private String largura;
	@Column(name="pro_comprimento", nullable=false, length=50)
	private String comprimento;
	
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getLargura() {
		return largura;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}
	public String getComprimento() {
		return comprimento;
	}
	public void setComprimento(String comprimento) {
		this.comprimento = comprimento;
	}

}
