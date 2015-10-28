package ufrrj.tebdes.ccompra.entidades;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ufrrj.tebdes.ccompra.embutidos.Tamanho;

@Entity
public class Produto {
	
	@Transient
	Map<String, String> quants;
	
	public Produto(){
		this.setTamanho(new Tamanho());
		this.setEstoque(new Estoque());
	}
	
	@Id @GeneratedValue
	@Column(name="pro_id")
	private Long id;
	
	@Column(name="pro_nome", nullable=false, length=50)
	private String nome;
	
	@Column(name="pro_preco", nullable=false, length=50)
	private Double preco;
	
	@Embedded
	private Tamanho tamanho;
	
	@Column(name="pro_peso", nullable=false, length=50)
	private Double peso;
	@Column(name="pro_cor", nullable=false, length=30)
	private String cor;
	@Column(name="pro_marca", nullable=false, length=30)
	private String marca;
	@Column(name="pro_descricao", nullable=false, length=200)
	private String descricao;
	@Column(name="pro_imagem")
	private String imagem;
	@Column(name="pro_alt")
	private String alt;
	
	@OneToOne(mappedBy="produto", cascade=CascadeType.ALL)
	private Estoque estoque;
	
	@Column(name="pro_desconto", length=5)
	private Double desconto;
	
	public Map<String, String> getQuantEstoque(){
		quants = new LinkedHashMap<String, String>();
		for (Integer i = 1; i <= estoque.getQuantidade(); i++){
			quants.put(i.toString(), i.toString());
		}
		return quants;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

}
