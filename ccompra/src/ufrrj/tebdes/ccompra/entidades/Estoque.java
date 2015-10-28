package ufrrj.tebdes.ccompra.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Estoque {
	
	@Id @GeneratedValue
	@Column(name="est_id")
	private Long id;
	
	@Column(name="est_quantidade", nullable=false, length=50)
	private Integer quantidade;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Produto produto;

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
