package ufrrj.tebdes.ccompra.embutidos;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	@Column(name="usu_logradouro", nullable=false, length=70)
	private String logradouro;
	
	@Column(name="usu_numero", nullable=false, length=7)
	private Integer numero;
	
	@Column(name="usu_complemento", nullable=true, length=20)
	private String complemento;
	
	@Column(name="usu_cep", nullable=false, length=9)
	private String cep;
	
	@Column(name="usu_cidade", nullable=false, length=30)
	private String cidade;
	
	@Column(name="usu_uf", nullable=false, length=2)
	private String uf;
	
	@Column(name="usu_bairro", nullable=false, length=50)
	private String bairro;
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
