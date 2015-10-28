package ufrrj.tebdes.ccompra.entidades;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ufrrj.tebdes.ccompra.utils.SessionOld;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	
	@Id @GeneratedValue
	@Column(name="ite_ped_id")
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Produto produto;
	
	@Column(name="ite_ped_quantidade", nullable=false, length=50)
	private Integer quantidade;
	
	@Override
	public boolean equals(Object o) {
		long id1 = this.getProduto().getId();
		long id2 = ((ItemPedido)o).getProduto().getId();
		return id1 == id2;
	}

	@Override
	public int hashCode() {
		return this.getProduto().getId().hashCode();
	}
	
	public void iterateQuantidade(int i){
		this.quantidade += i;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoTotal() {
		Double valor = new Double(0);
		if (this.getProduto().getDesconto() == null)
			this.getProduto().setDesconto(new Double(0));
		if (this.getQuantidade() != null && this.getProduto() != null && this.getProduto().getPreco() != null && this.getProduto().getDesconto() != null){
			valor = this.getQuantidade() * this.getProduto().getPreco() - this.getProduto().getDesconto();
		}
		return new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
